package com.exsited.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A small, strict JSON reader and writer, written so the SDK has no dependency that could
 * conflict with a consumer's own JSON library.
 *
 * <p>Values map as: object to {@link LinkedHashMap} (insertion-ordered, so a round trip keeps
 * the API's key order), array to {@link ArrayList}, string to {@code String}, an integral
 * number to {@code Long} when it fits and {@link java.math.BigInteger} when it does not, any
 * other number to {@code Double}, and {@code true}/{@code false}/{@code null} to
 * {@code Boolean}/{@code null}.
 *
 * <p>Strict on input: trailing commas, unquoted keys, comments and trailing content after the
 * document are all errors, so a proxy that mangled a body is not hidden.
 */
public final class Json {

    private Json() {}

    /** Thrown for input that is not valid JSON. */
    public static final class JsonException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public JsonException(String message) {
            super(message);
        }
    }

    /** Parse one JSON document. */
    public static Object parse(String text) {
        if (text == null) {
            throw new JsonException("no JSON to parse");
        }
        Parser parser = new Parser(text);
        parser.skipWhitespace();
        Object value = parser.readValue();
        parser.skipWhitespace();
        if (!parser.atEnd()) {
            throw new JsonException("trailing content at position " + parser.position);
        }
        return value;
    }

    /**
     * Serialise a value.
     *
     * <p>Compact, with no space after {@code :} or {@code ,}. A payload built by
     * the caller is serialised here; a payload handed over as a string is sent
     * byte-for-byte and never reaches this method.
     */
    public static String write(Object value) {
        StringBuilder out = new StringBuilder();
        writeValue(value, out, Style.COMPACT, 0);
        return out.toString();
    }

    /** One line with {@code ", "} and {@code ": "} between entries, the shape a redacted body is shown in. */
    public static String writeSpaced(Object value) {
        StringBuilder out = new StringBuilder();
        writeValue(value, out, Style.SPACED, 0);
        return out.toString();
    }

    /** Two-space indentation, LF breaks, {@code ": "} after a key, empty containers tight. */
    public static String writeIndented(Object value) {
        StringBuilder out = new StringBuilder();
        writeValue(value, out, Style.INDENTED, 0);
        return out.toString();
    }

    /** Whether the whole text is one JSON object or array. */
    public static boolean isDocument(String text) {
        if (text == null) {
            return false;
        }
        String leading = text.stripLeading();
        if (!leading.startsWith("{") && !leading.startsWith("[")) {
            return false;
        }
        try {
            Object parsed = parse(text);
            return parsed instanceof Map || parsed instanceof List;
        } catch (JsonException | StackOverflowError notOneDocument) {
            return false;
        }
    }

    /** A valid document on one line, every token copied as written, spaced as {@link #writeSpaced}. */
    public static String collapse(String document) {
        StringBuilder out = new StringBuilder(document.length());
        boolean inString = false;
        for (int index = 0; index < document.length(); index++) {
            char c = document.charAt(index);
            if (inString) {
                out.append(c);
                if (c == '\\' && index + 1 < document.length()) {
                    out.append(document.charAt(++index));
                } else if (c == '"') {
                    inString = false;
                }
                continue;
            }
            switch (c) {
                case '"' -> {
                    inString = true;
                    out.append(c);
                }
                case ' ', '\t', '\n', '\r', '\uFEFF' -> { }
                case ',' -> out.append(", ");
                case ':' -> out.append(": ");
                default -> out.append(c);
            }
        }
        return out.toString();
    }

    private enum Style { COMPACT, SPACED, INDENTED }

    private static void writeValue(Object value, StringBuilder out, Style style, int depth) {
        if (value == null) {
            out.append("null");
        } else if (value instanceof String) {
            writeString((String) value, out);
        } else if (value instanceof Boolean) {
            out.append(value.toString());
        } else if (value instanceof Number) {
            writeNumber((Number) value, out);
        } else if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            if (map.isEmpty()) {
                out.append("{}");
                return;
            }
            out.append('{');
            boolean first = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!first) {
                    out.append(style == Style.SPACED ? ", " : ",");
                }
                first = false;
                breakLine(out, style, depth + 1);
                writeString(String.valueOf(entry.getKey()), out);
                out.append(style == Style.COMPACT ? ":" : ": ");
                writeValue(entry.getValue(), out, style, depth + 1);
            }
            breakLine(out, style, depth);
            out.append('}');
        } else if (value instanceof Iterable) {
            writeItems(((Iterable<?>) value).iterator(), out, style, depth);
        } else if (value.getClass().isArray()) {
            List<Object> items = new ArrayList<>();
            int length = java.lang.reflect.Array.getLength(value);
            for (int index = 0; index < length; index++) {
                items.add(java.lang.reflect.Array.get(value, index));
            }
            writeItems(items.iterator(), out, style, depth);
        } else {
            throw new JsonException("cannot serialise " + value.getClass().getName());
        }
    }

    private static void writeItems(java.util.Iterator<?> items, StringBuilder out, Style style, int depth) {
        if (!items.hasNext()) {
            out.append("[]");
            return;
        }
        out.append('[');
        boolean first = true;
        while (items.hasNext()) {
            if (!first) {
                out.append(style == Style.SPACED ? ", " : ",");
            }
            first = false;
            breakLine(out, style, depth + 1);
            writeValue(items.next(), out, style, depth + 1);
        }
        breakLine(out, style, depth);
        out.append(']');
    }

    private static void breakLine(StringBuilder out, Style style, int depth) {
        if (style == Style.INDENTED) {
            out.append('\n').append("  ".repeat(depth));
        }
    }

    private static void writeNumber(Number number, StringBuilder out) {
        if (number instanceof Double || number instanceof Float) {
            double d = number.doubleValue();
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                // Neither is JSON. Refused rather than written as null, which
                // would send a different value than the caller asked for.
                throw new JsonException("cannot serialise a non-finite number: " + d);
            }
            if (d == Math.rint(d) && Math.abs(d) < 1e15) {
                out.append((long) d);
                return;
            }
        }
        out.append(number.toString());
    }

    private static void writeString(String text, StringBuilder out) {
        out.append('"');
        for (int index = 0; index < text.length(); index++) {
            char c = text.charAt(index);
            switch (c) {
                case '"':
                    out.append("\\\"");
                    break;
                case '\\':
                    out.append("\\\\");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\r':
                    out.append("\\r");
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                case '\b':
                    out.append("\\b");
                    break;
                case '\f':
                    out.append("\\f");
                    break;
                default:
                    if (c < 0x20) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
            }
        }
        out.append('"');
    }

    private static final class Parser {
        private final String text;
        private int position;

        Parser(String text) {
            this.text = text;
        }

        boolean atEnd() {
            return position >= text.length();
        }

        void skipWhitespace() {
            while (position < text.length()) {
                char c = text.charAt(position);
                if (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '﻿') {
                    position++;
                } else {
                    break;
                }
            }
        }

        Object readValue() {
            if (atEnd()) {
                throw new JsonException("unexpected end of input");
            }
            char c = text.charAt(position);
            switch (c) {
                case '{':
                    return readObject();
                case '[':
                    return readArray();
                case '"':
                    return readString();
                case 't':
                    expect("true");
                    return Boolean.TRUE;
                case 'f':
                    expect("false");
                    return Boolean.FALSE;
                case 'n':
                    expect("null");
                    return null;
                default:
                    return readNumber();
            }
        }

        private Map<String, Object> readObject() {
            Map<String, Object> out = new LinkedHashMap<>();
            position++;
            skipWhitespace();
            if (!atEnd() && text.charAt(position) == '}') {
                position++;
                return out;
            }
            while (true) {
                skipWhitespace();
                if (atEnd() || text.charAt(position) != '"') {
                    throw new JsonException("expected a quoted key at position " + position);
                }
                String key = readString();
                skipWhitespace();
                if (atEnd() || text.charAt(position) != ':') {
                    throw new JsonException("expected ':' at position " + position);
                }
                position++;
                skipWhitespace();
                out.put(key, readValue());
                skipWhitespace();
                if (atEnd()) {
                    throw new JsonException("unterminated object");
                }
                char c = text.charAt(position);
                if (c == ',') {
                    position++;
                    continue;
                }
                if (c == '}') {
                    position++;
                    return out;
                }
                throw new JsonException("expected ',' or '}' at position " + position);
            }
        }

        private List<Object> readArray() {
            List<Object> out = new ArrayList<>();
            position++;
            skipWhitespace();
            if (!atEnd() && text.charAt(position) == ']') {
                position++;
                return out;
            }
            while (true) {
                skipWhitespace();
                out.add(readValue());
                skipWhitespace();
                if (atEnd()) {
                    throw new JsonException("unterminated array");
                }
                char c = text.charAt(position);
                if (c == ',') {
                    position++;
                    continue;
                }
                if (c == ']') {
                    position++;
                    return out;
                }
                throw new JsonException("expected ',' or ']' at position " + position);
            }
        }

        private String readString() {
            StringBuilder out = new StringBuilder();
            position++;
            while (true) {
                if (atEnd()) {
                    throw new JsonException("unterminated string");
                }
                char c = text.charAt(position++);
                if (c == '"') {
                    return out.toString();
                }
                if (c != '\\') {
                    out.append(c);
                    continue;
                }
                if (atEnd()) {
                    throw new JsonException("unterminated escape");
                }
                char escape = text.charAt(position++);
                switch (escape) {
                    case '"': out.append('"'); break;
                    case '\\': out.append('\\'); break;
                    case '/': out.append('/'); break;
                    case 'b': out.append('\b'); break;
                    case 'f': out.append('\f'); break;
                    case 'n': out.append('\n'); break;
                    case 'r': out.append('\r'); break;
                    case 't': out.append('\t'); break;
                    case 'u':
                        if (position + 4 > text.length()) {
                            throw new JsonException("truncated \\u escape");
                        }
                        out.append((char) Integer.parseInt(text.substring(position, position + 4), 16));
                        position += 4;
                        break;
                    default:
                        throw new JsonException("unknown escape \\" + escape);
                }
            }
        }

        private Object readNumber() {
            int start = position;
            if (text.charAt(position) == '-' || text.charAt(position) == '+') {
                position++;
            }
            boolean fractional = false;
            while (!atEnd()) {
                char c = text.charAt(position);
                if (c >= '0' && c <= '9') {
                    position++;
                } else if (c == '.' || c == 'e' || c == 'E' || c == '-' || c == '+') {
                    fractional = fractional || c == '.' || c == 'e' || c == 'E';
                    position++;
                } else {
                    break;
                }
            }
            String raw = text.substring(start, position);
            if (raw.isEmpty()) {
                throw new JsonException("expected a value at position " + start);
            }
            try {
                if (!fractional) {
                    try {
                        return Long.valueOf(raw);
                    } catch (NumberFormatException tooWide) {
                        // An integer beyond Long: BigInteger keeps the exact digits rather
                        // than failing the whole body over one field. A DTO field declared
                        // Long reads null for it, as for any value of another type.
                        return new java.math.BigInteger(raw);
                    }
                }
                return Double.valueOf(raw);
            } catch (NumberFormatException error) {
                throw new JsonException("not a number: " + raw);
            }
        }

        private void expect(String literal) {
            if (!text.startsWith(literal, position)) {
                throw new JsonException("expected " + literal + " at position " + position);
            }
            position += literal.length();
        }
    }
}
