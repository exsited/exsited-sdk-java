package com.exsited;

import com.exsited.errors.SdkError;
import com.exsited.json.Json;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Secret handling: the single place that decides what counts as a secret. */
public final class Redaction {

    private Redaction() {}

    /** What a redacted value is replaced with. */
    public static final String REDACTED = "***";
    /** What replaces a structure nested deeper than {@link #MAX_REDACTION_DEPTH}. */
    public static final String TOO_DEEP = "<...>";
    /** What replaces a container that contains itself. */
    public static final String CIRCULAR = "<circular>";
    private static final String UNRENDERABLE = "<unrenderable>";
    /** How deep {@link #redactValue} walks before writing {@link #TOO_DEEP}. */
    public static final int MAX_REDACTION_DEPTH = 64;

    /** A key is secret when its name contains any of these, ignoring case and separators. */
    public static final List<String> SENSITIVE_KEY_MARKERS = List.of(
            "secret", "password", "passphrase", "passwd", "pwd", "token", "bearer",
            "jwt", "authorization", "authstring", "apikey", "assertion", "credential",
            // PKCE: code_verifier is a credential for the length of one exchange.
            "verifier",
            // Response-side names, which arrive from the server rather than a payload.
            "cookie", "session", "signature", "privatekey");

    /** Trailing words that mean the field <i>describes</i> a secret rather than being one. */
    private static final Set<String> NON_SECRET_TAIL_WORDS = Set.of(
            // "expired": password_expired is a boolean about a password, never one.
            "amount", "at", "consent", "count", "duration", "expired", "level",
            "path", "policy", "remaining", "status", "type");

    /** Leading words that make the field a boolean <i>about</i> a secret. */
    private static final Set<String> NON_SECRET_LEAD_WORDS = Set.of("has", "is", "was");

    /** Splits a name at punctuation and at camel-case humps. */
    private static final Pattern KEY_WORD_BOUNDARY =
            Pattern.compile("[^A-Za-z0-9]+|(?<=[a-z0-9])(?=[A-Z])");

    /** Judged run by run because an RFC 8288 {@code Link} header carries two URLs. */
    private static final Pattern URL_RUN =
            Pattern.compile("[A-Za-z][A-Za-z0-9+.\\-]{0,31}://[^\\s<>\"']*");

    /** A schemeless {@code /path?query} run, as HTTP clients embed in their exception messages. */
    private static final Pattern PATH_QUERY_RUN =
            Pattern.compile("/[^\\s<>\"'?#]*[?#][^\\s<>\"']+");

    /** A scheme-relative {@code //user:pass@host}, which neither pattern above can reach. */
    private static final Pattern AUTHORITY_RUN =
            Pattern.compile("(?<!:)//[^\\s<>\"'/?#@:]*:[^\\s<>\"'/?#@]*@[^\\s<>\"'/?#]*");

    /** Whether a key's name means its value must not be shown; {@code null} is not a secret. */
    public static boolean shouldRedact(String key) {
        List<String> words = keyWords(key);
        if (!words.isEmpty()
                && (NON_SECRET_LEAD_WORDS.contains(words.get(0))
                    || NON_SECRET_TAIL_WORDS.contains(words.get(words.size() - 1)))) {
            return false;
        }
        String normalised = String.join("", words);
        for (String marker : SENSITIVE_KEY_MARKERS) {
            if (normalised.contains(marker)) {
                return true;
            }
        }
        return false;
    }

    /** Render {@code name=value} pairs for a {@code toString}, redacting secret-named fields. */
    public static List<String> redactFields(Object instance, List<String> fieldNames) {
        List<String> shown = new ArrayList<>();
        if (fieldNames == null) {
            return shown;
        }
        for (String name : fieldNames) {
            Object value = readField(instance, name);
            Object rendered = isPresent(value) && shouldRedact(name)
                    ? REDACTED : redactValue(value);
            shown.add(name + "=" + renderValue(rendered));
        }
        return shown;
    }

    private static Object readField(Object instance, String name) {
        if (instance == null || name == null) {
            return null;
        }
        for (Class<?> type = instance.getClass(); type != null; type = type.getSuperclass()) {
            try {
                java.lang.reflect.Field field = type.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(instance);
            } catch (NoSuchFieldException absent) {
                continue;   // a field may be declared on a superclass
            } catch (ReflectiveOperationException | RuntimeException denied) {
                return null;    // an inaccessible field is not worth a failed toString
            }
        }
        return null;
    }

    /** Only a present value is redacted: the marker on an empty secret would imply one is set. */
    private static boolean isPresent(Object value) {
        if (value == null || Boolean.FALSE.equals(value)) {
            return false;
        }
        if (value instanceof CharSequence text) {
            return text.length() > 0;
        }
        if (value instanceof Map<?, ?> map) {
            return !map.isEmpty();
        }
        if (value instanceof List<?> list) {
            return !list.isEmpty();
        }
        if (value instanceof Number number) {
            return number.doubleValue() != 0;
        }
        return true;
    }

    private static String renderValue(Object value) {
        return value instanceof CharSequence text ? "'" + text + "'" : String.valueOf(value);
    }

    /** Deep-copy a value with secret-named entries replaced by {@link #REDACTED}. */
    public static Object redactValue(Object value) {
        return redact(value, 0, java.util.Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    private static Object redact(Object value, int depth, Set<Object> seen) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            if (depth >= MAX_REDACTION_DEPTH) {
                return TOO_DEEP;
            }
            if (!seen.add(value)) {
                return CIRCULAR;
            }
            try {
                if (value instanceof Map<?, ?> map) {
                    Map<Object, Object> copy = new LinkedHashMap<>();
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        // Reduced first: a key's toString is caller code that nothing downstream may reach.
                        Object key = summariseLeaf(entry.getKey());
                        copy.put(key, shouldRedact(String.valueOf(key))
                                ? REDACTED : redact(entry.getValue(), depth + 1, seen));
                    }
                    return copy;
                }
                List<?> list = (List<?>) value;
                List<Object> copy = new ArrayList<>(list.size());
                for (Object entry : list) {
                    copy.add(redactEntry(entry, depth + 1, seen));
                }
                return copy;
            } finally {
                seen.remove(value);
            }
        }
        if (value instanceof CharSequence text) {
            // JSON that arrived as text first, then any URL left in the result.
            return redactText(redactJsonText(text.toString(), depth, seen));
        }
        // Any other leaf becomes a type summary, for the reason given on the map key above.
        return summariseLeaf(value);
    }

    private static Object summariseLeaf(Object value) {
        if (value == null || SNAPSHOT_LEAF_TYPES.contains(value.getClass())) {
            return value;
        }
        if (value instanceof CharSequence text) {
            return text.toString();
        }
        if (value instanceof Enum<?> constant) {
            // name(), not toString(): the declared spelling, which no override can change.
            return constant.name();
        }
        if (value instanceof byte[] bytes) {
            return bytes.clone();
        }
        if (value.getClass().isArray()) {
            return "<" + typeName(value.getClass().getComponentType()) + "["
                    + java.lang.reflect.Array.getLength(value) + "]>";
        }
        return "<" + typeName(value.getClass()) + ">";
    }

    private static String redactJsonText(String text, int depth, Set<Object> seen) {
        String leading = text.stripLeading();
        if (leading.startsWith("\uFEFF")) {
            leading = leading.substring(1).stripLeading();
        }
        if (!leading.startsWith("{") && !leading.startsWith("[")) {
            return text;
        }
        Object parsed;
        try {
            parsed = Json.parse(text);
        } catch (Json.JsonException error) {
            // Not one document: withheld by default, since the name-based scrub cannot promise to
            // reach its pairs. The scrub runs only under SdkError.ERROR_DETAIL_PROPERTY.
            return SdkError.showsFullDetail() ? scrubJsonPairs(text) : UNRENDERABLE;
        } catch (Throwable failure) {
            // An Error means the parser ran out of stack or memory; the scrub would exhaust it too.
            return UNRENDERABLE;
        }
        Object redacted = redact(parsed, depth + 1, seen);
        if (java.util.Objects.equals(redacted, parsed)) {
            return text;
        }
        return renderOrWithhold(redacted, Json::writeSpaced);
    }

    private static final Pattern JSON_NAME = Pattern.compile(
            "\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"(\\s*:\\s*)");

    /** The same with escaped quotes: JSON nested in a string value, which parsing cannot reach. */
    private static final Pattern JSON_NAME_ESCAPED = Pattern.compile(
            "\\\\\"([^\"\\\\]*(?:\\\\[^\"][^\"\\\\]*)*)\\\\\"(\\s*:\\s*)");

    private static final Pattern SCALAR_END = Pattern.compile("[,}\\]\\s]");

    /** Redact secret-named values in text that would not parse, leaving the rest byte for byte. */
    private static String scrubJsonPairs(String text) {
        if (countBackslashes(text, MAX_SCRUB_ESCAPES) > MAX_SCRUB_ESCAPES) {
            return UNRENDERABLE;
        }
        try {
            return scrubPairs(JSON_NAME_ESCAPED, scrubPairs(JSON_NAME, text, "\""), "\\\"");
        } catch (Throwable failure) {
            // Fail closed: text holds the secret, so it is never returned. Throwable, because
            // the regex can overflow the stack on a heavily escaped body.
            return UNRENDERABLE;
        }
    }

    /** How many backslashes {@link #scrubJsonPairs} scans past before withholding the body. */
    private static final int MAX_SCRUB_ESCAPES = 256;

    private static int countBackslashes(String text, int limit) {
        int found = 0;
        for (int index = 0; index < text.length(); index++) {
            if (text.charAt(index) == '\\' && ++found > limit) {
                return found;
            }
        }
        return found;
    }

    private static String scrubPairs(Pattern pattern, String text, String quote) {
        Matcher matcher = pattern.matcher(text);
        StringBuilder out = new StringBuilder();
        int cursor = 0;
        int position = 0;
        while (position <= text.length() && matcher.find(position)) {
            if (!shouldRedact(matcher.group(1))) {
                // Resume inside the match: a non-secret name may end where the next name begins.
                position = matcher.start() + 1;
                continue;
            }
            int end = endOfJsonValue(text, matcher.end(), quote);
            out.append(text, cursor, matcher.end());
            out.append(quote).append(REDACTED).append(quote);
            cursor = end;
            position = end;
        }
        out.append(text, cursor, text.length());
        return out.toString();
    }

    /** Index just past the value at {@code start}; {@code quote} is the delimiter at this depth. */
    private static int endOfJsonValue(String text, int start, String quote) {
        if (start >= text.length()) {
            return start;
        }
        if (text.startsWith(quote, start)) {
            return endOfJsonString(text, start, quote);
        }
        char opening = text.charAt(start);
        if (opening == '{' || opening == '[') {
            int depth = 0;
            int index = start;
            while (index < text.length()) {
                if (text.startsWith(quote, index)) {
                    index = endOfJsonString(text, index, quote);
                    continue;
                }
                char character = text.charAt(index);
                if (character == '{' || character == '[') {
                    depth++;
                } else if (character == '}' || character == ']') {
                    depth--;
                    if (depth == 0) {
                        return index + 1;
                    }
                }
                index++;
            }
            return text.length();
        }
        Matcher matcher = SCALAR_END.matcher(text);
        return matcher.find(start) ? matcher.start() : text.length();
    }

    private static int endOfJsonString(String text, int start, String quote) {
        int index = start + quote.length();
        while (index < text.length()) {
            // Delimiter first, and the order is load-bearing: a \" quote begins with a backslash.
            if (text.startsWith(quote, index)) {
                return index + quote.length();
            }
            if (text.charAt(index) == '\\') {
                index += 2;
                continue;
            }
            index++;
        }
        return text.length();
    }

    /** How a redacted tree becomes text. */
    @FunctionalInterface
    interface RedactedRenderer {
        String render(Object redacted) throws Exception;
    }

    /** Render a redacted tree, or withhold the whole body when it cannot be rendered. */
    static String renderOrWithhold(Object redacted, RedactedRenderer renderer) {
        try {
            return renderer.render(redacted);
        } catch (Throwable failure) {
            return UNRENDERABLE;
        }
    }

    /** Honours a {@code [name, value]} pair, as header lists and form bodies carry secrets. */
    private static Object redactEntry(Object entry, int depth, Set<Object> seen) {
        if (entry instanceof List<?> pair
                && pair.size() == 2
                && pair.get(0) instanceof String name
                && shouldRedact(name)) {
            return List.of(name, REDACTED);
        }
        return redact(entry, depth, seen);
    }

    /** A detached, unmodifiable copy of a JSON-shaped value. */
    public static Object detachValue(Object value) {
        return detach(value, 0, java.util.Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    /** {@link #detachValue} for a string-keyed mapping; {@code null} becomes an empty map. */
    @SuppressWarnings("unchecked")
    public static <V> Map<String, V> detachMapping(Map<String, V> values) {
        if (values == null) {
            return Map.of();
        }
        return (Map<String, V>) detachValue(values);
    }

    /** The immutable, redacted snapshot a record advertised as safe to log keeps. */
    public static Object redactedSnapshot(Object value) {
        return snapshotValue(redactValue(value));
    }

    /** Leaf types a log-safe snapshot keeps: value types whose toString the platform defines. */
    private static final Set<Class<?>> SNAPSHOT_LEAF_TYPES = Set.of(
            String.class, Boolean.class, Character.class,
            Byte.class, Short.class, Integer.class, Long.class,
            Float.class, Double.class,
            java.math.BigInteger.class, java.math.BigDecimal.class);

    /** A capture that keeps nothing the caller still owns: the copy a safe-to-log value holds. */
    public static Object snapshotValue(Object value) {
        return snapshot(value, 0, java.util.Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    /** {@link #snapshotValue} for a string-keyed mapping; {@code null} becomes an empty map. */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> snapshotMapping(Map<String, ?> values) {
        if (values == null) {
            return Map.of();
        }
        return (Map<String, Object>) snapshotValue(values);
    }

    private static Object snapshot(Object value, int depth, Set<Object> seen) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            if (depth >= MAX_REDACTION_DEPTH) {
                throw new IllegalArgumentException("value nests deeper than "
                        + MAX_REDACTION_DEPTH + " levels, so it cannot be captured");
            }
            if (!seen.add(value)) {
                throw new IllegalArgumentException(
                        "value contains itself, so it cannot be captured");
            }
            try {
                if (value instanceof Map<?, ?> map) {
                    // LinkedHashMap, not Map.copyOf: keeps wire order and legal null values.
                    Map<Object, Object> copy = new LinkedHashMap<>();
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        copy.put(snapshot(entry.getKey(), depth + 1, seen),
                                snapshot(entry.getValue(), depth + 1, seen));
                    }
                    return java.util.Collections.unmodifiableMap(copy);
                }
                List<?> list = (List<?>) value;
                List<Object> copy = new ArrayList<>(list.size());
                for (Object entry : list) {
                    copy.add(snapshot(entry, depth + 1, seen));
                }
                return java.util.Collections.unmodifiableList(copy);
            } finally {
                seen.remove(value);
            }
        }
        if (value == null || SNAPSHOT_LEAF_TYPES.contains(value.getClass())) {
            return value;
        }
        if (value instanceof byte[] bytes) {
            return bytes.clone();
        }
        if (value.getClass().isArray()) {
            return "<" + typeName(value.getClass().getComponentType()) + "["
                    + java.lang.reflect.Array.getLength(value) + "]>";
        }
        return "<" + typeName(value.getClass()) + ">";
    }

    /** The simple name, or the binary name for an anonymous class, whose simple name is empty. */
    private static String typeName(Class<?> type) {
        String simple = type.getSimpleName();
        return simple.isEmpty() ? type.getName() : simple;
    }

    private static Object detach(Object value, int depth, Set<Object> seen) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            if (depth >= MAX_REDACTION_DEPTH) {
                throw new IllegalArgumentException("request value nests deeper than "
                        + MAX_REDACTION_DEPTH + " levels, so it cannot be captured");
            }
            if (!seen.add(value)) {
                throw new IllegalArgumentException(
                        "request value contains itself, so it cannot be captured");
            }
            try {
                if (value instanceof Map<?, ?> map) {
                    Map<Object, Object> copy = new LinkedHashMap<>();
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        copy.put(entry.getKey(), detach(entry.getValue(), depth + 1, seen));
                    }
                    return java.util.Collections.unmodifiableMap(copy);
                }
                List<?> list = (List<?>) value;
                List<Object> copy = new ArrayList<>(list.size());
                for (Object entry : list) {
                    copy.add(detach(entry, depth + 1, seen));
                }
                return java.util.Collections.unmodifiableList(copy);
            } finally {
                seen.remove(value);
            }
        }
        if (value instanceof byte[] bytes) {
            return bytes.clone();
        }
        return value;
    }

    /** Redact a flat mapping such as headers; {@code null} becomes an empty map. */
    public static Map<String, String> redactMapping(Map<String, String> headers) {
        Map<String, String> shown = new LinkedHashMap<>();
        if (headers == null) {
            return shown;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            shown.put(entry.getKey(),
                    shouldRedact(entry.getKey())
                            ? REDACTED
                            : redactText(String.valueOf(entry.getValue())));
        }
        return shown;
    }

    private static final String WITHHELD_PATH = "<path>";

    private static final Pattern VOCABULARY_SEGMENT =
            Pattern.compile("[A-Za-z][A-Za-z0-9_-]{0,31}");

    /** Three digits in a row is an identifier, not a word. */
    private static final Pattern DIGIT_RUN = Pattern.compile("[0-9]{3,}");

    /** A URL or path reduced to the endpoint it names: structure, with every value taken out. */
    public static String reduceToEndpoint(String pathOrUrl) {
        if (pathOrUrl == null) {
            return null;
        }
        String rest = pathOrUrl;
        int authority = rest.indexOf("://");
        if (authority >= 0) {
            int pathStart = indexOfAny(rest, authority + 3, '/', '?', '#');
            rest = pathStart >= rest.length() ? "/" : rest.substring(pathStart);
        }
        rest = rest.substring(0, indexOfAny(rest, 0, '?', '#'));
        if (rest.isEmpty()) {
            return "/";
        }
        StringBuilder shape = new StringBuilder();
        // -1 keeps a trailing empty segment, so "/a/" does not read as "/a".
        String[] segments = rest.split("/", -1);
        for (int i = 0; i < segments.length; i++) {
            if (i > 0) {
                shape.append('/');
            }
            shape.append(structuralSegment(segments[i]));
        }
        return shape.toString();
    }

    /** One path segment: itself when it is a word, {@code {id}} when it is a value. */
    private static String structuralSegment(String segment) {
        if (segment.isEmpty()
                || (segment.startsWith("{") && segment.endsWith("}"))) {
            return segment;
        }
        if (VOCABULARY_SEGMENT.matcher(segment).matches()
                && !DIGIT_RUN.matcher(segment).find()) {
            return segment;
        }
        return "{id}";
    }

    /** A filesystem path as safe output carries it: withheld unless raw detail is opted in. */
    public static String redactPath(Object path) {
        return SdkError.showsFullDetail() ? redactText(String.valueOf(path)) : WITHHELD_PATH;
    }

    /** A lower layer's failure: its type, plus its redacted text when detail is opted in. */
    public static String describeFailure(Throwable failure) {
        if (failure == null) {
            return "";
        }
        String type = failure.getClass().getSimpleName();
        if (!SdkError.showsFullDetail() || failure.getMessage() == null) {
            return type;
        }
        return type + ": " + redactText(failure.getMessage());
    }

    /** Redact every URL and schemeless {@code path?query} inside free text. */
    public static String redactText(String value) {
        if (value == null) {
            return null;
        }
        // Each pass is skipped when its delimiter is absent, which cannot change an answer and
        // spares a quadratic search over a long body holding no URL.
        String cleaned = value;
        if (cleaned.contains("://")) {
            cleaned = replaceRuns(URL_RUN, cleaned);
        }
        if (cleaned.indexOf('?') >= 0 || cleaned.indexOf('#') >= 0) {
            cleaned = replaceRuns(PATH_QUERY_RUN, cleaned);
        }
        // Gated on '@' by the same rule; idempotent, so a re-rendered exchange is byte-stable.
        if (cleaned.indexOf('@') >= 0) {
            cleaned = replaceRuns(AUTHORITY_RUN, cleaned);
        }
        return cleaned;
    }

    private static String replaceRuns(Pattern pattern, String value) {
        Matcher matcher = pattern.matcher(value);
        StringBuilder out = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(out, Matcher.quoteReplacement(redactOneUrl(matcher.group())));
        }
        matcher.appendTail(out);
        return out.toString();
    }

    private static String redactOneUrl(String url) {
        // Rewritten in place, since parse and re-encode is not lossless; a malformed URL comes back
        // unchanged, never as an exception.
        String rest = url;
        String prefix = "";
        String authority = null;
        String shownAuthority = null;
        // startsWith first: indexOf("://") could find a later embedded URL's scheme instead.
        int authorityStart = -1;
        if (rest.startsWith("//")) {
            authorityStart = 2;
        } else {
            int schemeEnd = rest.indexOf("://");
            if (schemeEnd >= 0) {
                authorityStart = schemeEnd + 3;
            }
        }
        if (authorityStart >= 0) {
            int authorityEnd = indexOfAny(rest, authorityStart, '/', '?', '#');
            authority = rest.substring(authorityStart, authorityEnd);
            shownAuthority = redactUserinfo(authority);
            prefix = rest.substring(0, authorityStart) + shownAuthority;
            rest = rest.substring(authorityEnd);
        }
        // Fragment cut first, then the query (RFC 3986 3.5): cut at the first delimiter, a
        // secret-named fragment parameter behind a query would go out in clear.
        String fragment = null;
        int hash = rest.indexOf('#');
        if (hash >= 0) {
            fragment = rest.substring(hash + 1);
            rest = rest.substring(0, hash);
        }
        String query = null;
        int mark = rest.indexOf('?');
        if (mark >= 0) {
            query = rest.substring(mark + 1);
            rest = rest.substring(0, mark);
        }
        String shownQuery = redactQueryString(query);
        String shownFragment = redactQueryString(fragment);
        if (java.util.Objects.equals(shownQuery, query)
                && java.util.Objects.equals(shownFragment, fragment)
                && (authority == null || authority.equals(shownAuthority))) {
            // Nothing matched, so the run is handed back byte-exact.
            return url;
        }
        StringBuilder out = new StringBuilder(prefix).append(rest);
        // On a rebuild an empty query or fragment loses its delimiter.
        if (shownQuery != null && !shownQuery.isEmpty()) {
            out.append('?').append(shownQuery);
        }
        if (shownFragment != null && !shownFragment.isEmpty()) {
            out.append('#').append(shownFragment);
        }
        return out.toString();
    }

    private static int indexOfAny(String text, int from, char... targets) {
        for (int i = from; i < text.length(); i++) {
            for (char target : targets) {
                if (text.charAt(i) == target) {
                    return i;
                }
            }
        }
        return text.length();
    }

    /** Replace an embedded password, keeping the user who owns it visible. */
    private static String redactUserinfo(String authority) {
        int at = authority.lastIndexOf('@');
        if (at < 0) {
            return authority;
        }
        String userinfo = authority.substring(0, at);
        int colon = userinfo.indexOf(':');
        if (colon < 0) {
            return authority;
        }
        return userinfo.substring(0, colon) + ":" + REDACTED + authority.substring(at);
    }

    /** Replace every secret-named parameter's value, the rest byte-exact: re-encoding is lossy. */
    private static String redactQueryString(String query) {
        if (query == null || query.isEmpty()) {
            return query;
        }
        String[] chunks = query.split("&", -1);
        boolean matched = false;
        for (int i = 0; i < chunks.length; i++) {
            int equals = chunks[i].indexOf('=');
            if (equals < 0) {
                continue;   // a parameter with no '=' hides nothing
            }
            String name = urlDecode(chunks[i].substring(0, equals));
            if (shouldRedact(name)) {
                chunks[i] = chunks[i].substring(0, equals) + "=" + REDACTED;
                matched = true;
            }
        }
        return matched ? String.join("&", chunks) : query;
    }

    private static String urlDecode(String text) {
        try {
            return java.net.URLDecoder.decode(text, java.nio.charset.StandardCharsets.UTF_8);
        } catch (IllegalArgumentException error) {
            return text;    // a malformed escape is judged as-is
        }
    }

    private static List<String> keyWords(String key) {
        List<String> words = new ArrayList<>();
        for (String word : KEY_WORD_BOUNDARY.split(String.valueOf(key))) {
            if (!word.isEmpty()) {
                words.add(word.toLowerCase(Locale.ROOT));
            }
        }
        return words;
    }
}
