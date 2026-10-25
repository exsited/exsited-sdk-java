package com.exsited.client;

import com.exsited.Redaction;
import com.exsited.config.SdkConfig;
import com.exsited.json.Json;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/** Renders one exchange for a terminal; everything shown passes through the redactor first. */
public final class Console {

    /** Stands in where a status code would go for a request that never reached one. */
    static final String NO_RESPONSE_STATUS = "no response";

    private static final String MULTIPART_PREFIX = "<multipart: ";
    private static final String PAYLOAD_FILES_KEY = "files";
    private static final String PAYLOAD_FORM_KEY = "form";

    /** Built from the code point, so no literal control byte sits in this source. */
    private static final char ESC = 0x1b;
    private static final String ANSI_ESCAPE = String.valueOf(ESC);
    private static final String ANSI_ESCAPE_START = ESC + "[";
    private static final String ANSI_RESET = ESC + "[0m";
    private static final String ANSI_DIM = ESC + "[2m";

    private Console() {}

    /** The text to print for one exchange, per the config's print flags. */
    public static String renderExchange(ApiResponse<?> response, Object reportedBody,
            SdkConfig config) {
        return renderExchange(response, reportedBody, null, false, config);
    }

    /** As above, told whether the body carried {@code files} or went out form-encoded. */
    public static String renderExchange(ApiResponse<?> response, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, SdkConfig config) {
        // getMap(), not getData(): a diagnostic shows the body as it arrived, whatever the format.
        return render(response.getRequestInfo(), String.valueOf(response.getStatusCode()),
                response.succeeded(), response.getMap(), response.getHeaders(),
                reportedBody, files, formEncoded, config);
    }

    /** The text to print for a send that produced no response: a connection failure or timeout. */
    static String renderFailedExchange(RequestInfo info, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, SdkConfig config) {
        return render(info, NO_RESPONSE_STATUS, false, null, Map.of(),
                reportedBody, files, formEncoded, config);
    }

    private static String render(RequestInfo info, String statusCode, boolean succeeded,
            Object data, Map<String, String> responseHeaders, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, SdkConfig config) {
        Object payload = summarisePayload(reportedBody, files);

        if (SdkConfig.PRINT_FORMAT_COMPACT.equals(config.getPrintFormat())) {
            StringBuilder line = new StringBuilder("exsited | ")
                    .append(info.getClientName()).append(" | ")
                    .append(info.getHttpMethod()).append(" | ")
                    .append(info.getUrl()).append(" | ")
                    .append(statusCode).append(" | ")
                    .append(info.getAttemptCount()).append('/').append(info.getMaxAttempts())
                    .append(" | ").append(info.getElapsedSeconds()).append('s');
            if (config.isPrintRequestPayload()) {
                line.append(" | payload=")
                        .append(renderJsonValue(Redaction.redactValue(payload), true));
            }
            if (config.isPrintResponseBody()) {
                line.append(" | response=")
                        .append(renderJsonValue(Redaction.redactValue(data), true));
            }
            return encodeForConsole(truncate(neutralise(line.toString()), config.getPrintMaxChars()));
        }

        Colour colour = new Colour(config.isPrintColor() && stdoutSupportsColour());

        // No box, and no per-line gutter below, so a printed body copies out clean.
        StringBuilder out = new StringBuilder(colour.dim("Exsited  "
                + neutralise(info.getHttpMethod()) + "  " + statusCode + "  "
                + info.getElapsedSeconds() + "s"));
        if (config.isPrintApiInfo()) {
            row(out, "client", info.getClientName());
            row(out, "api version", info.getApiVersion());
            row(out, "method", info.getHttpMethod());
            row(out, "endpoint", info.getUrl());
            // Redacted again on the way out, scrubbing values captured by reference.
            row(out, "params",
                    renderJsonValue(Redaction.redactValue(info.getQueryParams()), true));
            out.append('\n').append(String.format("%-12s: ", "status"))
                    .append(colour.status(statusCode, succeeded));
            row(out, "attempt", info.getAttemptCount() + " / " + info.getMaxAttempts());
            row(out, "elapsed", info.getElapsedSeconds() + "s");
        }
        if (config.isPrintHeaders()) {
            section(out, "request headers", colour);
            headerRows(out, Redaction.redactMapping(info.getHeaders()));
            section(out, "response headers", colour);
            headerRows(out, Redaction.redactMapping(responseHeaders));
        }
        if (config.isPrintRequestPayload() && payload != null) {
            section(out, "payload", colour);
            block(out, renderJsonValue(Redaction.redactValue(payload), false));
        }
        if (config.isPrintResponseBody()) {
            section(out, "response", colour);
            block(out, renderJsonValue(Redaction.redactValue(data), false));
        }
        if (config.isPrintCurl()) {
            section(out, "curl (POSIX shell)", colour);
            block(out, buildCurlCommand(info, reportedBody, files, formEncoded, config));
        }
        return encodeForConsole(truncate(out.toString(), config.getPrintMaxChars()));
    }

    /** Describe what was sent without reading a byte of any upload. */
    public static Object summarisePayload(Object body, Map<String, Object> files) {
        List<String[]> entries = multipartEntries(files);
        if (entries.isEmpty()) {
            return body;
        }
        List<String> names = new ArrayList<>();
        for (String[] entry : entries) {
            names.add(entry[0] + "=" + entry[1]);
        }
        String summary = MULTIPART_PREFIX + String.join(", ", names) + ">";
        if (body == null) {
            return summary;
        }
        Map<String, Object> both = new LinkedHashMap<>();
        both.put(PAYLOAD_FILES_KEY, summary);
        both.put(PAYLOAD_FORM_KEY, body);
        return both;
    }

    /** A copy-pasteable, shell-quoted curl for the call, every secret redacted to {@code ***}. */
    public static String buildCurlCommand(ApiResponse<?> response, Object reportedBody,
            SdkConfig config) {
        return buildCurlCommand(response.getRequestInfo(), reportedBody, null, false, config);
    }

    /** As above, from the request record alone, so a send with no response can be reproduced. */
    public static String buildCurlCommand(RequestInfo info, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, SdkConfig config) {
        // Neutralised as well as quoted: quoting stops shell injection but not ANSI.
        StringBuilder curl = new StringBuilder("curl -X ")
                .append(shellQuote(neutralise(info.getHttpMethod())))
                .append(' ').append(shellQuote(neutralise(withQuery(info))));
        if (!config.isVerifySsl()) {
            curl.append(" \\\n  -k");
        }
        // Redacted again rather than trusted, so the line is always safe to paste.
        Redaction.redactMapping(info.getHeaders()).entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> curl.append(" \\\n  -H ")
                        .append(shellQuote(neutralise(entry.getKey() + ": " + entry.getValue()))));

        List<String[]> entries = multipartEntries(files);
        // Neutralised as well as quoted: an escape in a filename could erase the line above it.
        for (String[] entry : entries) {
            curl.append(" \\\n  -F ")
                    .append(shellQuote(neutralise(entry[0]) + "=@" + neutralise(entry[1])));
        }
        if (!entries.isEmpty()) {
            // Form fields ride in the same multipart body, so they are -F too.
            for (Map.Entry<String, Object> field : formFields(reportedBody)) {
                curl.append(" \\\n  -F ").append(shellQuote(
                        neutralise(field.getKey()) + "=" + neutralise(String.valueOf(field.getValue()))));
            }
        } else if (reportedBody != null) {
            List<Map.Entry<String, Object>> fields =
                    formEncoded ? formFields(reportedBody) : List.of();
            if (!fields.isEmpty()) {
                // --data-urlencode per field, so curl sets the form type itself; JSON here would
                // reach the API as a single field name.
                for (Map.Entry<String, Object> field : fields) {
                    curl.append(" \\\n  --data-urlencode ").append(shellQuote(
                            neutralise(field.getKey()) + "="
                                    + neutralise(String.valueOf(field.getValue()))));
                }
            } else {
                if (!formEncoded && !hasContentType(info.getHeaders())) {
                    // curl defaults --data-raw to form encoding; added only when the caller set no
                    // content type, since curl honours the last -H.
                    curl.append(" \\\n  -H ").append(shellQuote("Content-Type: application/json"));
                }
                curl.append(" \\\n  --data-raw ")
                        .append(shellQuote(curlBody(Redaction.redactValue(reportedBody))));
            }
        }
        return curl.toString();
    }

    private static String curlBody(Object shown) {
        if (shown instanceof CharSequence text && Json.isDocument(text.toString())) {
            return neutralise(Json.collapse(text.toString()));
        }
        return renderJsonValue(shown, true);
    }

    /** Asked of the transport, so the curl line reproduces the field and file names sent. */
    private static List<String[]> multipartEntries(Map<String, Object> files) {
        List<String[]> entries = new ArrayList<>();
        if (files == null) {
            return entries;
        }
        for (Map.Entry<String, Object> entry : files.entrySet()) {
            entries.add(new String[] {entry.getKey(),
                    com.exsited.http.Transport.multipartFilename(
                            entry.getKey(), entry.getValue())});
        }
        return entries;
    }

    /** Redacted name/value pairs for a form body; anything else has no fields. */
    private static List<Map.Entry<String, Object>> formFields(Object body) {
        List<Map.Entry<String, Object>> fields = new ArrayList<>();
        if (!(body instanceof Map<?, ?>)) {
            return fields;
        }
        if (Redaction.redactValue(body) instanceof Map<?, ?> redacted) {
            for (Map.Entry<?, ?> entry : redacted.entrySet()) {
                fields.add(Map.entry(String.valueOf(entry.getKey()),
                        entry.getValue() == null ? "" : entry.getValue()));
            }
        }
        return fields;
    }

    /** The URL for the curl line, with the query parameters appended. */
    private static String withQuery(RequestInfo info) {
        if (info.getQueryParams().isEmpty()) {
            return info.getUrl();
        }
        StringBuilder query = new StringBuilder(info.getUrl());
        char separator = info.getUrl().contains("?") ? '&' : '?';
        for (Map.Entry<String, Object> entry : info.getQueryParams().entrySet()) {
            for (Object value : entry.getValue() instanceof List<?> list
                    ? list : List.of(entry.getValue())) {
                query.append(separator)
                        .append(formEncode(entry.getKey()))
                        .append('=')
                        .append(formEncode(
                                Redaction.redactText(String.valueOf(value))));
                separator = '&';
            }
        }
        return query.toString();
    }

    private static String formEncode(String text) {
        return java.net.URLEncoder.encode(text, StandardCharsets.UTF_8)
                .replace("%7E", "~");
    }

    private static boolean hasContentType(Map<String, String> headers) {
        return headers.keySet().stream().anyMatch(name -> name.equalsIgnoreCase("Content-Type"));
    }

    /** One {@code label:value} row, its value neutralised so it cannot forge a second row. */
    private static void row(StringBuilder out, String label, Object value) {
        out.append('\n').append(String.format("%-12s: ", label))
                .append(neutraliseControls(String.valueOf(value), false));
    }

    /** Header rows sorted by name, so two runs of one call print the same block. */
    private static void headerRows(StringBuilder out, Map<String, String> headers) {
        headers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> headerRow(out, entry.getKey(), entry.getValue()));
    }

    private static void headerRow(StringBuilder out, String name, Object value) {
        out.append('\n').append(String.format("%-14s: ", neutraliseControls(name, false)))
                .append(neutraliseControls(String.valueOf(value), false));
    }

    /** A plain {@code -- label --} rule, not a box tee, so the blocks below stay copy-pasteable. */
    private static void section(StringBuilder out, String label, Colour colour) {
        String rule = boxGlyphs().get("h");
        String prefix = rule + rule + " " + label + " ";
        out.append('\n').append(colour.dim(
                prefix + rule.repeat(Math.max(4, 62 - prefix.length()))));
    }

    /** A raw payload, response or curl block, with no gutter so it copies cleanly. */
    private static void block(StringBuilder out, String text) {
        for (String line : text.split("\n", -1)) {
            out.append('\n').append(line);
        }
    }

    /** Render a value as JSON, then neutralise it: the JSON writer escapes C0 controls but not C1. */
    public static String renderJsonValue(Object value, boolean compact) {
        if (value == null) {
            return "<empty>";
        }
        if (value instanceof byte[] bytes) {
            return "<" + bytes.length + " bytes>";   // summarised, never dumped
        }
        if (value instanceof CharSequence text) {
            // Shown as text, not a quoted JSON string, which would fold its newlines into one.
            String raw = text.toString();
            if (!compact) {
                return neutralise(raw);     // real lines; neutralise keeps '\n'
            }
            if (Json.isDocument(raw)) {
                // Compact means one line, so a break (only ever between tokens) becomes a space.
                return neutralise(singleLine(raw));
            }
            // Compact is one line: escaped as a nested JSON string, its quotes sliced off.
            String quoted = Json.write(raw);
            return neutralise(quoted.substring(1, quoted.length() - 1));
        }
        try {
            return neutralise(compact ? Json.write(value) : Json.writeIndented(value));
        } catch (RuntimeException unwritable) {
            return neutralise(String.valueOf(value));
        }
    }

    /** The same text on one line: CR, LF and CRLF each become a single space. */
    private static String singleLine(String text) {
        return text.replace("\r\n", " ").replace('\r', ' ').replace('\n', ' ');
    }

    /** Characters a POSIX shell needs no quoting for. */
    private static final Pattern SHELL_UNSAFE = Pattern.compile("[^A-Za-z0-9_@%+=:,./-]");

    /** POSIX shell quoting for one curl fragment, applied only when the fragment needs it. */
    static String shellQuote(String fragment) {
        if (fragment.isEmpty()) {
            return "''";
        }
        if (!SHELL_UNSAFE.matcher(fragment).find()) {
            return fragment;
        }
        return "'" + fragment.replace("'", "'\"'\"'") + "'";
    }

    /** C0 (newline excepted), DEL, C1, U+2028 and U+2029 never reach a terminal as themselves. */
    static String neutralise(String text) {
        return neutraliseControls(text, true);
    }

    private static boolean stdoutSupportsColour() {
        String noColour = System.getenv("NO_COLOR");
        if (noColour != null && !noColour.isEmpty()) {
            return false;
        }
        if ("dumb".equals(System.getenv("TERM"))) {
            return false;
        }
        java.io.Console console = System.console();
        if (console == null) {
            return false;
        }
        if (IS_TERMINAL != null) {
            try {
                return (Boolean) IS_TERMINAL.invoke(console);
            } catch (ReflectiveOperationException useTheNullCheck) {
                return true;
            }
        }
        return true;
    }

    /** {@code java.io.Console.isTerminal()}, where the runtime has it (JDK 22+). */
    private static final java.lang.reflect.Method IS_TERMINAL = findIsTerminal();

    private static java.lang.reflect.Method findIsTerminal() {
        try {
            return java.io.Console.class.getMethod("isTerminal");
        } catch (NoSuchMethodException jdk21OrOlder) {
            return null;
        }
    }

    /** ANSI helpers that no-op when colour is off or stdout is not a terminal. */
    private static final class Colour {
        private final boolean enabled;

        Colour(boolean enabled) {
            this.enabled = enabled;
        }

        String dim(String text) {
            return enabled ? ANSI_DIM + text + ANSI_RESET : text;
        }

        String status(String text, boolean succeeded) {
            return enabled ? ANSI_ESCAPE_START + (succeeded ? "32" : "31") + "m" + text + ANSI_RESET
                    : text;
        }
    }

    /** Cut to {@code maxChars} with the notice included: its room comes out of the budget. */
    private static String truncate(String text, int maxChars) {
        if (maxChars <= 0 || text.length() <= maxChars) {
            return text;
        }
        String ellipsis = boxGlyphs().get("ellipsis");
        int reserved = (ellipsis + " +" + text.length() + " chars").length();
        if (text.indexOf(ANSI_ESCAPE) >= 0) {
            reserved += ANSI_RESET.length();
        }
        int cut = Math.max(0, maxChars - reserved);
        return closeColourRun(text.substring(0, cut))
                + ellipsis + " +" + (text.length() - cut) + " chars";
    }

    /** Repair an ANSI sequence a cut landed inside, so the terminal is never left dim. */
    private static String closeColourRun(String text) {
        int marker = text.lastIndexOf(ANSI_ESCAPE);
        if (marker < 0) {
            return text;
        }
        String cut = text.indexOf('m', marker) < 0 ? text.substring(0, marker) : text;
        int last = cut.lastIndexOf(ANSI_ESCAPE_START);
        if (last >= 0 && !cut.startsWith(ANSI_RESET, last)) {
            return cut + ANSI_RESET;
        }
        return cut;
    }

    private static Map<String, String> mapping(
            String top, String bottom, String tee, String vertical, String horizontal, String ellipsis) {
        Map<String, String> glyphs = new LinkedHashMap<>();
        glyphs.put("top", top);
        glyphs.put("bottom", bottom);
        glyphs.put("tee", tee);
        glyphs.put("v", vertical);
        glyphs.put("h", horizontal);
        glyphs.put("ellipsis", ellipsis);
        return Collections.unmodifiableMap(glyphs);
    }

    private static final Map<String, String> UNICODE_GLYPHS =
            mapping("┌", "└", "├", "│", "─", "…");

    private static final Map<String, String> ASCII_GLYPHS =
            mapping("+", "+", "+", "|", "-", "...");

    /** Box glyphs this console can print; a cp1252 console turns the rule into {@code ?}. */
    public static Map<String, String> boxGlyphs() {
        return boxGlyphs(consoleCharset());
    }

    /** The glyph set a stream in this charset can carry, as a fresh mapping. */
    public static Map<String, String> boxGlyphs(Charset charset) {
        return new LinkedHashMap<>(
                encoderFor(charset).canEncode(glyphProbe()) ? UNICODE_GLYPHS : ASCII_GLYPHS);
    }

    /** Every glyph the probe must be able to encode, printed or not. */
    public static String glyphProbe() {
        return String.join("", UNICODE_GLYPHS.values());
    }

    /** Escape what the charset cannot carry as {@code \\uXXXX}, rather than letting it print {@code ?}. */
    public static String encodeForConsole(String text, Charset charset) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        CharsetEncoder encoder = encoderFor(charset);
        if (encoder.canEncode(text)) {
            return text;
        }
        StringBuilder out = new StringBuilder(text.length());
        int index = 0;
        while (index < text.length()) {
            int units = Character.isHighSurrogate(text.charAt(index))
                    && index + 1 < text.length()
                    && Character.isLowSurrogate(text.charAt(index + 1)) ? 2 : 1;
            CharSequence piece = text.subSequence(index, index + units);
            if (encoder.canEncode(piece)) {
                out.append(piece);
            } else {
                for (int offset = 0; offset < units; offset++) {
                    out.append(String.format(Locale.ROOT, "\\u%04x", (int) text.charAt(index + offset)));
                }
            }
            index += units;
        }
        return out.toString();
    }

    /** {@link #encodeForConsole(String, Charset)} for this process's stdout. */
    public static String encodeForConsole(String text) {
        return encodeForConsole(text, consoleCharset());
    }

    private static final char LINE_SEPARATOR = 0x2028;
    private static final char PARAGRAPH_SEPARATOR = 0x2029;

    /** Escape what a terminal would obey rather than show; a block keeps its newlines. */
    public static String neutraliseControls(String text, boolean keepNewlines) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        StringBuilder out = null;
        for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            if (!mustEscape(character, keepNewlines)) {
                if (out != null) {
                    out.append(character);
                }
                continue;
            }
            if (out == null) {
                out = new StringBuilder(text.length() + 8).append(text, 0, index);
            }
            out.append(String.format(Locale.ROOT, "\\u%04x", (int) character));
        }
        return out == null ? text : out.toString();
    }

    /** A single-line field: its newlines are escaped too. */
    public static String neutraliseControls(String text) {
        return neutraliseControls(text, false);
    }

    private static boolean mustEscape(char character, boolean keepNewlines) {
        if (character == '\n') {
            return !keepNewlines;
        }
        return character <= 0x1f
                || (character >= 0x7f && character <= 0x9f)
                || character == LINE_SEPARATOR
                || character == PARAGRAPH_SEPARATOR;
    }

    /** The charset this process's stdout encodes with. */
    public static Charset consoleCharset() {
        return System.out.charset();
    }

    private static CharsetEncoder encoderFor(Charset charset) {
        Charset usable = charset != null && charset.canEncode() ? charset : StandardCharsets.US_ASCII;
        return usable.newEncoder();
    }
}
