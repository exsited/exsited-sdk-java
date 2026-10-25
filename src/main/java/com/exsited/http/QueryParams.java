package com.exsited.http;

import com.exsited.errors.SdkUsageError;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/** Query parameter normalisation, and the v4 filter and sort naming helpers. */
public final class QueryParams {

    private QueryParams() {}

    /** Sort direction: a leading {@code -} on {@code sort}, or the {@code direction} value. */
    public enum SortDirection {
        /** Lowest value first, sent as {@code asc}. */
        ASC("asc"),
        /** Highest value first, sent as {@code desc}. */
        DESC("desc");

        private final String value;

        SortDirection(String value) {
            this.value = value;
        }

        /** The spelling the server accepts: {@code "asc"} or {@code "desc"}. */
        public String value() {
            return value;
        }

        /** The wire spelling, so a direction anywhere renders {@code desc}, not {@code DESC}. */
        @Override
        public String toString() {
            return value;
        }
    }

    /** The filter comparisons v4 accepts; {@code eq} is the bare name. */
    public static final List<String> FILTER_OPERATORS =
            List.of("eq", "neq", "in", "nin", "contains", "not_contains", "starts_with",
                    "gt", "gte", "lt", "lte", "has_value", "does_not_have_value");

    /** Presence checks that read no value: pair them with {@code true}, never {@code null}. */
    public static final List<String> PRESENCE_OPERATORS =
            List.of("has_value", "does_not_have_value");

    /** The operators that take a comma separated list in one parameter; see {@link #filterValues}. */
    public static final List<String> LIST_OPERATORS = List.of("in", "nin");

    /** The flat filter name, {@code filter_<field>} or {@code filter_<field>_<operator>}. */
    public static String filterParam(String field, String operator) {
        if (field == null || field.isBlank()) {
            throw new SdkUsageError("filter field: expected a name like 'status', got \"" + field + "\"");
        }
        String resolved = operator == null ? "eq" : operator.toLowerCase(Locale.ROOT);
        if (!FILTER_OPERATORS.contains(resolved)) {
            throw new SdkUsageError("filter operator \"" + operator
                    + "\": expected one of " + String.join(", ", FILTER_OPERATORS));
        }
        String stem = "filter_" + field.strip();
        return resolved.equals("eq") ? stem : stem + "_" + resolved;
    }

    /** Equality, the common case. */
    public static String filterParam(String field) {
        return filterParam(field, "eq");
    }

    /** One comma separated value for {@code in}/{@code nin}; a list value would repeat the key. */
    public static String filterValues(List<?> values) {
        if (values == null) {
            throw new SdkUsageError(
                    "filter values: expected a list of values like [\"ACTIVE\", \"INACTIVE\"], got null");
        }
        List<String> rendered = new ArrayList<>(values.size());
        for (Object value : values) {
            if (value == null) {
                throw new SdkUsageError(
                        "filter values: a member is null, which has no value to match; drop it, "
                        + "or use the 'does_not_have_value' operator if what was meant is "
                        + "'this field is unset'");
            }
            Object normalised = normalise(value, "filter values", depthZeroSeen(), 1);
            if (normalised instanceof List<?> || normalised instanceof byte[]) {
                throw new SdkUsageError("filter values: a member is itself a list, which "
                        + "has no place in one comma separated value");
            }
            String text = String.valueOf(normalised);
            if (text.indexOf(',') >= 0) {
                throw new SdkUsageError("filter values: the member \"" + text
                        + "\" contains a comma, which the API reads as the separator between "
                        + "two values, so it would match something other than what was asked for");
            }
            rendered.add(text);
        }
        if (rendered.isEmpty()) {
            throw new SdkUsageError(
                    "filter values: expected at least one value; an empty 'in' filter is sent "
                    + "as 'filter_<field>_in=' and answered 200 with no rows, which reads as "
                    + "'nothing matched' rather than as the mistake it is");
        }
        return String.join(",", rendered);
    }

    /** A {@code sort} value: the field, prefixed {@code -} to descend. */
    public static String sortParam(String field, SortDirection direction) {
        if (field == null || field.isBlank()) {
            throw new SdkUsageError("sort field: expected a name like 'created_on', got \"" + field + "\"");
        }
        return direction == SortDirection.DESC ? "-" + field.strip() : field.strip();
    }

    /** Ascending, the default the API applies when the {@code -} is absent. */
    public static String sortParam(String field) {
        return sortParam(field, SortDirection.ASC);
    }

    /** The {@code order_by} plus {@code direction} pair the other sorting families take. */
    public static Map<String, Object> sortParams(String field, boolean descending) {
        if (field == null || field.isBlank()) {
            throw new SdkUsageError("sort field: expected a name like 'created_on', got \"" + field + "\"");
        }
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("order_by", field.strip());
        params.put("direction", (descending ? SortDirection.DESC : SortDirection.ASC).value());
        return params;
    }

    /** Ascending, the default the API applies when {@code direction} is absent. */
    public static Map<String, Object> sortParams(String field) {
        return sortParams(field, false);
    }

    /** Normalise query values for the wire; a {@code null} value drops that parameter. */
    public static Map<String, Object> buildQueryParams(Map<String, ?> params) {
        Map<String, Object> normalised = new LinkedHashMap<>();
        if (params == null) {
            return normalised;
        }
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;   // an unset filter is never sent
            }
            normalised.put(entry.getKey(), normalise(value, entry.getKey(), depthZeroSeen(), 0));
        }
        return normalised;
    }

    private static Set<Object> depthZeroSeen() {
        return java.util.Collections.newSetFromMap(new IdentityHashMap<>());
    }

    /** Date and time to whole seconds, with any sub-second part appended. */
    private static final DateTimeFormatter ISO_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .toFormatter(Locale.ROOT);

    /**
     * The offset always {@code +HH:MM}, UTC included, so the application reads every value with
     * one pattern.
     */
    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .appendOffset("+HH:MM", "+00:00")
            .toFormatter(Locale.ROOT);

    private static Object normalise(Object value, String key, Set<Object> seen, int depth) {
        if (depth >= com.exsited.Redaction.MAX_REDACTION_DEPTH) {
            throw new SdkUsageError("query param '" + key + "': nested more than "
                    + com.exsited.Redaction.MAX_REDACTION_DEPTH
                    + " levels deep, which is too deep to encode in a query string");
        }
        if (value instanceof Map<?, ?>) {
            throw new SdkUsageError("query param '" + key + "': nested objects cannot "
                    + "be encoded in a query string; flatten it or send it in the body");
        }
        if (value instanceof Boolean flag) {
            return flag ? "true" : "false";
        }
        if (value instanceof Enum<?> constant) {
            // Sent as it renders, never as a lowercased name(), which cannot say "requires-2fa".
            return constant.toString();
        }
        if (value instanceof LocalDate date) {
            return DateTimeFormatter.ISO_LOCAL_DATE.format(date);
        }
        if (value instanceof LocalDateTime dateTime) {
            return ISO_LOCAL_DATE_TIME.format(dateTime);
        }
        if (value instanceof ZonedDateTime zoned) {
            // The offset alone: no ISO 8601 parser reads the zone id toString() appends.
            return ISO_OFFSET_DATE_TIME.format(zoned.toOffsetDateTime());
        }
        if (value instanceof OffsetDateTime offsetDateTime) {
            return ISO_OFFSET_DATE_TIME.format(offsetDateTime);
        }
        if (value instanceof Instant instant) {
            return ISO_OFFSET_DATE_TIME.format(instant.atOffset(ZoneOffset.UTC));
        }
        if (value instanceof BigDecimal decimal) {
            return decimal.toPlainString();     // never scientific notation on the wire
        }
        if (value instanceof CharSequence || value instanceof byte[]) {
            return value;   // scalars, never exploded
        }
        if (value instanceof Iterable<?> iterable) {
            if (!seen.add(value)) {
                throw new SdkUsageError("query param '" + key + "': the value refers to "
                        + "itself, so it has no finite query-string encoding");
            }
            if (depth >= 1) {
                throw new SdkUsageError("query param '" + key + "': a sequence inside a "
                        + "sequence has no query-string encoding; flatten it to one list of scalars");
            }
            try {
                List<Object> entries = new ArrayList<>();
                for (Object entry : iterable) {
                    if (entry != null) {
                        entries.add(normalise(entry, key, seen, depth + 1));
                    }
                }
                return entries;
            } finally {
                seen.remove(value);
            }
        }
        return value;   // anything else is the transport's to encode as text
    }
}
