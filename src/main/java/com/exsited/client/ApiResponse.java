package com.exsited.client;

import com.exsited.Dto;
import com.exsited.Redaction;
import com.exsited.config.ResponseFormat;
import com.exsited.errors.SdkAuthError;
import com.exsited.errors.SdkError;
import com.exsited.errors.SdkUsageError;
import com.exsited.json.Json;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * What every SDK call returns: one HTTP exchange, what was sent and what came back.
 *
 * @param <T> the typed view the endpoint names; {@code Dto} when it names none.
 */
public final class ApiResponse<T extends Dto> {

    private static final int MAX_REPORTED_MESSAGES = 50;
    private static final int MAX_MESSAGE_DEPTH = 4;

    private final int statusCode;
    private final Object data;
    private final Map<String, String> headers;
    private final String contentType;
    private final ErrorType errorType;
    private final RequestInfo requestInfo;
    /** The body's own bytes, so {@link #getJson()} returns the API's text, not a re-encoding. */
    private final byte[] rawBody;
    /** Which member {@link #getData()} follows; never null once resolved. */
    private final String responseFormat;
    /** How {@link #getDto()} builds its view, or null when the call names no shape. */
    private final DtoFactory dtoFactory;
    /** The typed view the endpoint names, which wins over {@link #dtoFactory}. */
    private final Class<T> dtoType;

    // Each view is built once under lazyViews: its inputs may be live or caller state, so racing
    // readers could otherwise build differing views.
    private final Object lazyViews = new Object();
    private volatile String jsonText;
    private volatile boolean jsonTextRead;
    private volatile T dto;
    private volatile boolean dtoBuilt;

    /** One exchange, classified by its status; {@code data} is kept live and unredacted. */
    public ApiResponse(
            int statusCode,
            Object data,
            Map<String, String> headers,
            String contentType,
            RequestInfo requestInfo) {
        this(statusCode, data, headers, contentType, requestInfo, ErrorType.NONE);
    }

    /** The same, with the classification supplied, as the transport calls it. */
    public ApiResponse(
            int statusCode,
            Object data,
            Map<String, String> headers,
            String contentType,
            RequestInfo requestInfo,
            ErrorType errorType) {
        this(statusCode, data, headers, contentType, requestInfo, errorType, null, null, null);
    }

    /** The same, with the body's own bytes and how the body is presented. */
    public ApiResponse(
            int statusCode,
            Object data,
            Map<String, String> headers,
            String contentType,
            RequestInfo requestInfo,
            ErrorType errorType,
            byte[] rawBody,
            String responseFormat,
            DtoFactory dtoFactory) {
        this(statusCode, data, headers, contentType, requestInfo, errorType, rawBody,
                responseFormat, dtoFactory, null);
    }

    /** The same, naming the typed view the endpoint produces. */
    public ApiResponse(
            int statusCode,
            Object data,
            Map<String, String> headers,
            String contentType,
            RequestInfo requestInfo,
            ErrorType errorType,
            byte[] rawBody,
            String responseFormat,
            DtoFactory dtoFactory,
            Class<T> dtoType) {
        this.rawBody = rawBody;
        // Normalised through the one resolver: getData() compares exactly, so a raw "DTO" would
        // silently answer a map.
        this.responseFormat = responseFormat == null
                ? ResponseFormat.MAP.value()
                : ResponseFormat.parse(responseFormat).value();
        this.dtoFactory = dtoFactory;
        this.dtoType = dtoType;
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers == null ? Map.of() : Map.copyOf(headers);
        this.contentType = contentType;
        // && binds tighter, so a null errorType is always derived; regrouped, a success keeps null.
        boolean denied = bodyDeniesSuccess(data);
        this.errorType = errorType == null
                || errorType == ErrorType.NONE && (statusCode >= 300 || denied)
                ? ErrorType.classify(statusCode, denied) : errorType;
        this.requestInfo = requestInfo;
    }

    /** The HTTP status; a 2xx alone does not mean the call worked, see {@link #succeeded()}. */
    public int getStatusCode() {
        return statusCode;
    }

    /** The body in the form this call resolved to: the only member that follows configuration. */
    public Object getData() {
        // Exact comparisons are safe: the constructor normalised the value.
        if (ResponseFormat.JSON.value().equals(responseFormat)) {
            return getJson();
        }
        if (ResponseFormat.DTO.value().equals(responseFormat)) {
            return getDto();
        }
        return data;
    }

    /** The decoded body itself, live and unredacted: mutating it changes this response. */
    public Object getMap() {
        return data;
    }

    /** The body's own text exactly as the API sent it, whatever {@code responseFormat} says. */
    public String getJson() {
        if (jsonTextRead) {
            return jsonText;
        }
        synchronized (lazyViews) {
            if (!jsonTextRead) {
                jsonText = materialiseJsonText();
                jsonTextRead = true;
            }
            return jsonText;
        }
    }

    /**
     * The typed view of the body, whatever {@code responseFormat} says; {@code null} if none.
     *
     * <p>Also {@code null} when the call failed: an error envelope is not a record of the named type.
     */
    public T getDto() {
        if (dtoBuilt) {
            return dto;
        }
        synchronized (lazyViews) {
            if (!dtoBuilt) {
                dto = buildDto();
                dtoBuilt = true;
            }
            return dto;
        }
    }

    private T buildDto() {
        // Null when the body is not a record, so a DTO may assume a mapping, or when the endpoint
        // names no shape: a fieldless DTO would look typed.
        if (!(data instanceof Map) || !succeeded()) {
            return null;
        }
        if (dtoType != null) {
            return Dto.fromMap(dtoType, data);
        }
        if (dtoFactory == null) {
            return null;
        }
        Object built = dtoFactory.buildDto(data);
        if (built != null && !(built instanceof Dto)) {
            throw new SdkUsageError("this call's dtoFactory built a "
                    + built.getClass().getSimpleName() + ", which is not a Dto - a factory "
                    + "builds the typed view, as in body -> Dto.fromMap(Account.class, body)");
        }
        @SuppressWarnings("unchecked")
        T view = (T) built;
        return view;
    }

    /** {@link #getDto()} as the type you expect, refused if the view is of another type. */
    public <V> V getDto(Class<V> dtoType) {
        Object view = getDto();
        if (view == null) {
            return null;
        }
        if (!dtoType.isInstance(view)) {
            throw new SdkUsageError("this response's DTO is a "
                    + view.getClass().getSimpleName() + ", not a " + dtoType.getSimpleName()
                    + " - the endpoint decides the shape, so ask for the one it produces");
        }
        return dtoType.cast(view);
    }

    /** Which member {@link #getData()} follows: {@code "map"}, {@code "json"} or {@code "dto"}. */
    public String getResponseFormat() {
        return responseFormat;
    }

    /** The same exchange presented another way, sharing its status, body and record. */
    public ApiResponse<T> withResponseView(String responseFormat, DtoFactory dtoFactory) {
        String format = responseFormat == null
                ? this.responseFormat : ResponseFormat.parse(responseFormat).value();
        DtoFactory factory = dtoFactory == null ? this.dtoFactory : dtoFactory;
        if (format.equals(this.responseFormat) && factory == this.dtoFactory) {
            return this;
        }
        return new ApiResponse<>(statusCode, data, headers, contentType, requestInfo, errorType,
                rawBody, format, factory, dtoType);
    }

    /** The same exchange with the typed view the endpoint names, which wins over a factory. */
    public <V extends Dto> ApiResponse<V> withResponseView(
            String responseFormat, DtoFactory dtoFactory, Class<V> dtoType) {
        String format = responseFormat == null
                ? this.responseFormat : ResponseFormat.parse(responseFormat).value();
        DtoFactory factory = dtoFactory == null ? this.dtoFactory : dtoFactory;
        return new ApiResponse<>(statusCode, data, headers, contentType, requestInfo, errorType,
                rawBody, format, factory, dtoType);
    }

    private String materialiseJsonText() {
        if (data instanceof byte[] || rawBody == null || rawBody.length == 0) {
            return null;
        }
        return new String(rawBody, textCharset());
    }

    /** The declared charset, or UTF-8, which RFC 8259 says a JSON body is. */
    private java.nio.charset.Charset textCharset() {
        String lowered = contentType == null ? "" : contentType.toLowerCase(java.util.Locale.ROOT);
        int marker = lowered.indexOf("charset=");
        if (marker >= 0) {
            String name = lowered.substring(marker + "charset=".length())
                    .split("[;\\s]")[0].replace("\"", "");
            try {
                return java.nio.charset.Charset.forName(name);
            } catch (Exception unusable) {
                return StandardCharsets.UTF_8;
            }
        }
        return StandardCharsets.UTF_8;
    }

    /** The response headers, unmodifiable and not redacted: redact before printing or storing. */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /** The response {@code Content-Type} with its parameters, or {@code null} if none. */
    public String getContentType() {
        return contentType;
    }

    /** How the failure classifies; {@link ErrorType#NONE} when status and body agree. */
    public ErrorType getErrorType() {
        return errorType;
    }

    /** What was sent, redacted and detached at construction; {@code null} if built without one. */
    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    /** Whether the call succeeded: a 2xx status and a body that does not name itself an error. */
    public boolean succeeded() {
        return statusCode >= 200 && statusCode < 300 && !bodyDeniesSuccess(data);
    }

    /** Whether a v4 body names itself {@code "object": "error"}, whatever the status says. */
    private static boolean bodyDeniesSuccess(Object data) {
        return data instanceof Map<?, ?> body
                && body.get("object") instanceof String name
                && name.strip().equalsIgnoreCase("error");
    }

    /** The v4 envelope's machine-readable {@code code}, else its {@code error}; null on success. */
    public String getErrorCode() {
        String code = errorField("code");
        return code != null ? code : errorField("error");
    }

    /** The v4 envelope's {@code request_id}; null on success. */
    public String getRequestId() {
        return errorField("request_id");
    }

    /** The v4 envelope's {@code doc_url}; null on success. */
    public String getDocUrl() {
        return errorField("doc_url");
    }

    /** The v4 envelope's per-field failures: only {@code errors[]} entries naming a field. */
    public List<FieldError> getFieldErrors() {
        List<FieldError> out = new ArrayList<>();
        if (succeeded() || !(data instanceof Map<?, ?> body)
                || !(body.get("errors") instanceof List<?> entries)) {
            return out;
        }
        for (Object entry : entries) {
            if (!(entry instanceof Map<?, ?> record)) {
                continue;
            }
            Object field = record.get("field");
            if (field == null || String.valueOf(field).isBlank()) {
                continue;
            }
            out.add(new FieldError(String.valueOf(field), asText(record.get("code")),
                    asText(record.get("message")), asText(record.get("doc_url"))));
        }
        return out;
    }

    /** Human-readable problems from the most specific tier present: errors, message, error. */
    public List<String> getErrorMessages() {
        if (succeeded()) {
            return List.of();
        }
        Object body = data;
        if (!(body instanceof Map)) {
            body = parseJsonBody(body);
        }
        if (!(body instanceof Map<?, ?> envelope)) {
            return List.of();
        }
        List<String> messages = readErrorEntries(envelope.get("errors"));
        if (messages.isEmpty()) {
            messages = readMessageValue(envelope.get("message"), 0);
        }
        if (messages.isEmpty()) {
            messages = readMessageValue(envelope.get("error"), 0);
            if (messages.isEmpty()) {
                messages = readMessageValue(envelope.get("code"), 0);
            }
        }
        if (messages.isEmpty()) {
            // RFC 7807 (application/problem+json): an intermediary's envelope.
            messages = readMessageValue(envelope.get("detail"), 0);
            if (messages.isEmpty()) {
                messages = readMessageValue(envelope.get("title"), 0);
            }
        }
        return capMessages(messages);
    }

    /** Raise when the call failed, otherwise return this response for chaining. */
    public ApiResponse<T> raiseForStatus() {
        if (succeeded()) {
            return this;
        }
        boolean detail = SdkError.showsFullDetail();
        List<String> messages = detail ? getErrorMessages() : List.of();
        String summary = messages.isEmpty() ? "HTTP " + statusCode : messages.get(0);
        SdkError.Details details = new SdkError.Details()
                .statusCode(statusCode)
                .errorType(errorType)
                .rawResponse(detail ? Redaction.redactValue(data) : null)
                .responseHeaders(detail ? Redaction.redactMapping(headers) : null)
                .requestInfo(requestInfo)
                // The v4 envelope's identifiers name the failure, never a record; its field
                // messages may echo a value, so they are gated like every other message.
                .errorCode(getErrorCode())
                .requestId(getRequestId())
                .docUrl(getDocUrl())
                .fieldErrors(detail ? getFieldErrors() : null);
        SdkError error = (statusCode == 401 || statusCode == 403)
                ? new SdkAuthError(summary, details)
                : new SdkError(summary, details);
        for (String message : messages) {
            error.addError(message);
        }
        throw error;
    }

    /** Read one top-level string off a failed call's envelope: a success's own {@code code} is not one. */
    private String errorField(String key) {
        if (succeeded() || !(data instanceof Map<?, ?> body)) {
            return null;
        }
        Object value = body.get(key);
        return value instanceof String text && !text.isBlank() ? text : null;
    }

    private static String asText(Object value) {
        return value == null ? null : String.valueOf(value);
    }

    private static Object parseJsonBody(Object body) {
        String text;
        if (body instanceof byte[] bytes) {
            text = new String(bytes, StandardCharsets.UTF_8);
        } else if (body instanceof String given) {
            text = given;
        } else {
            return null;
        }
        // U+FEFF, a byte-order mark: strip() does not treat it as whitespace.
        text = text.strip().replace("\uFEFF", "").strip();
        if (!text.startsWith("{") && !text.startsWith("[")) {
            return null;    // a PDF or a PNG never reaches the parser
        }
        try {
            Object parsed = Json.parse(text);
            return parsed instanceof Map ? parsed : null;
        } catch (RuntimeException | StackOverflowError error) {
            return null;
        }
    }

    /** Read the {@code errors} tier, whichever of its three wild shapes arrived. */
    private static List<String> readErrorEntries(Object entries) {
        List<String> messages = new ArrayList<>();
        if (entries instanceof Map<?, ?> byField) {
            for (Map.Entry<?, ?> entry : byField.entrySet()) {
                prefixField(entry.getKey(), readMessageValue(entry.getValue(), 0), messages);
            }
            return messages;
        }
        if (entries instanceof List<?> list) {
            for (Object entry : list) {
                if (entry instanceof Map<?, ?> item) {
                    Object text = item.get("message");
                    if (text == null) {
                        text = item.get("code");
                    }
                    prefixField(item.get("field"), readMessageValue(text, 0), messages);
                } else {
                    messages.addAll(readMessageValue(entry, 0));
                }
            }
            return messages;
        }
        if (entries instanceof String only) {
            return readMessageValue(only, 0);
        }
        return messages;
    }

    /** Turn any value from an error body into messages; blanks dropped, depth-guarded. */
    private static List<String> readMessageValue(Object value, int depth) {
        if (value == null || depth > MAX_MESSAGE_DEPTH) {
            return List.of();
        }
        if (value instanceof String message) {
            return message.strip().isEmpty() ? List.of() : List.of(message);
        }
        if (value instanceof Map<?, ?> nested) {
            Object text = nested.get("message");
            if (text == null) {
                text = nested.get("code");
            }
            return readMessageValue(text, depth + 1);
        }
        if (value instanceof List<?> list) {
            List<String> messages = new ArrayList<>();
            for (Object item : list) {
                messages.addAll(readMessageValue(item, depth + 1));
            }
            return messages;
        }
        String text = String.valueOf(value);
        return text.strip().isEmpty() ? List.of() : List.of(text);
    }

    /** Tested for presence, not truthiness: a bulk operation's row 0 is a real field. */
    private static void prefixField(Object fieldName, List<String> fieldMessages, List<String> out) {
        String name = fieldName == null ? "" : String.valueOf(fieldName).strip();
        for (String message : fieldMessages) {
            out.add(name.isEmpty() ? message : name + ": " + message);
        }
    }

    private static List<String> capMessages(List<String> messages) {
        if (messages.size() <= MAX_REPORTED_MESSAGES) {
            return messages;
        }
        List<String> capped = new ArrayList<>(messages.subList(0, MAX_REPORTED_MESSAGES));
        capped.add("+" + (messages.size() - MAX_REPORTED_MESSAGES) + " more problems");
        return capped;
    }
}
