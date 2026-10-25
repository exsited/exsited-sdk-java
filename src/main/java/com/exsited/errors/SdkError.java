package com.exsited.errors;

import com.exsited.client.ErrorType;
import com.exsited.client.FieldError;
import com.exsited.client.RequestInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Base of every SDK error, and the error for any non-2xx not more specifically classified. */
public class SdkError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** System property that opts the raw half of every diagnostic in, set to {@code full}. */
    public static final String ERROR_DETAIL_PROPERTY = "exsited.sdk.errorDetail";

    /** The one value of {@link #ERROR_DETAIL_PROPERTY} that opts detail in. */
    public static final String ERROR_DETAIL_FULL = "full";

    /** Whether {@link #ERROR_DETAIL_PROPERTY} is {@link #ERROR_DETAIL_FULL}, in any casing. */
    public static boolean showsFullDetail() {
        return ERROR_DETAIL_FULL.equalsIgnoreCase(
                System.getProperty(ERROR_DETAIL_PROPERTY, "structural"));
    }

    private final Integer statusCode;
    private final ErrorType errorType;
    /** The decoded body, redacted before it got here: nothing in this class redacts. */
    private final transient Object rawResponse;
    /** Response headers, redacted before they got here; empty rather than {@code null}. */
    private final transient Map<String, String> responseHeaders;
    private final transient RequestInfo requestInfo;
    /** Kept off the cause chain, so a printed stack trace cannot show a lower layer's text. */
    private final transient Throwable detailedCause;
    private final String errorCode;
    private final String requestId;
    private final String docUrl;
    private final transient List<FieldError> fieldErrors;
    private final transient List<String> errors = new ArrayList<>();

    /** The v4 envelope's extras, and every other error field, for the constructor that takes them by name. */
    public static final class Details {
        Integer statusCode;
        ErrorType errorType;
        Object rawResponse;
        Map<String, String> responseHeaders;
        RequestInfo requestInfo;
        Throwable cause;
        String errorCode;
        String requestId;
        String docUrl;
        List<FieldError> fieldErrors;

        public Details statusCode(Integer value) { this.statusCode = value; return this; }
        public Details errorType(ErrorType value) { this.errorType = value; return this; }

        /** By its wire spelling, e.g. {@code "authentication"}; an unknown one reads {@code UNKNOWN}. */
        public Details errorType(String value) {
            ErrorType named = ErrorType.fromValue(value);
            this.errorType = value == null ? null : named == null ? ErrorType.UNKNOWN : named;
            return this;
        }

        public Details rawResponse(Object value) { this.rawResponse = value; return this; }
        public Details responseHeaders(Map<String, String> value) { this.responseHeaders = value; return this; }
        public Details requestInfo(RequestInfo value) { this.requestInfo = value; return this; }
        public Details cause(Throwable value) { this.cause = value; return this; }
        public Details errorCode(String value) { this.errorCode = value; return this; }
        public Details requestId(String value) { this.requestId = value; return this; }
        public Details docUrl(String value) { this.docUrl = value; return this; }
        public Details fieldErrors(List<FieldError> value) { this.fieldErrors = value; return this; }
    }

    /** A failure with no HTTP response behind it; the message is not redacted here. */
    public SdkError(String message) {
        this(message, new Details());
    }

    /** A failure with no HTTP response, keeping its cause, whose text is withheld by default. */
    public SdkError(String message, Throwable cause) {
        this(message, new Details().cause(cause));
    }

    /** A failure with an HTTP response behind it; body and headers must arrive already redacted. */
    public SdkError(
            String message,
            Integer statusCode,
            ErrorType errorType,
            Object rawResponse,
            Map<String, String> responseHeaders,
            RequestInfo requestInfo) {
        this(message, statusCode, errorType, rawResponse, responseHeaders, requestInfo, null);
    }

    /** As above, keeping the cause: only the chain tells a reset from a TLS or DNS failure. */
    public SdkError(
            String message,
            Integer statusCode,
            ErrorType errorType,
            Object rawResponse,
            Map<String, String> responseHeaders,
            RequestInfo requestInfo,
            Throwable cause) {
        this(message, new Details().statusCode(statusCode).errorType(errorType)
                .rawResponse(rawResponse).responseHeaders(responseHeaders)
                .requestInfo(requestInfo).cause(cause));
    }

    /** Every field by name, the v4 envelope's extras included. */
    @SuppressWarnings("this-escape")
    public SdkError(String message, Details details) {
        super(message);
        this.detailedCause = details.cause;
        if (details.cause != null) {
            // initCause, not super(message, cause): a subclass setting its own cause keeps working.
            initCause(showsFullDetail() ? details.cause : StructuralCause.of(details.cause));
        }
        this.statusCode = details.statusCode;
        this.errorType = details.errorType;
        this.rawResponse = details.rawResponse;
        this.responseHeaders = details.responseHeaders == null
                ? Map.of()
                : Collections.unmodifiableMap(new LinkedHashMap<>(details.responseHeaders));
        this.requestInfo = details.requestInfo;
        this.errorCode = details.errorCode;
        this.requestId = details.requestId;
        this.docUrl = details.docUrl;
        this.fieldErrors = details.fieldErrors == null
                ? List.of()
                : Collections.unmodifiableList(new ArrayList<>(details.fieldErrors));
    }

    /** The HTTP status, or {@code null} when the call never completed. */
    public Integer getStatusCode() {
        return statusCode;
    }

    /** How the failure classifies, or {@code null} when it was not classified. */
    public ErrorType getErrorType() {
        return errorType;
    }

    /** The decoded response body, already redacted: for reading, not for re-sending. */
    public Object getRawResponse() {
        return rawResponse;
    }

    /** Response headers, already redacted; carried because an unfollowed 3xx has no body. */
    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    /** The request record for the call that failed, or {@code null}. */
    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    /** The native exception as raised: the explicit way past what {@link #getCause()} withholds. */
    public Throwable getDetailedCause() {
        return detailedCause;
    }

    /** The v4 envelope's machine-readable {@code code}, what to branch on; {@code null} if none. */
    public String getErrorCode() {
        return errorCode;
    }

    /** The v4 envelope's {@code request_id}: quote it when reporting a problem. */
    public String getRequestId() {
        return requestId;
    }

    /** The v4 envelope's {@code doc_url} for {@link #getErrorCode()}. */
    public String getDocUrl() {
        return docUrl;
    }

    /** The v4 envelope's per-field failures, unflattened; empty when the body names no field. */
    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    /** A cause chain reduced to its type names, on one line, so no lower layer's text returns. */
    public static final class StructuralCause extends Throwable {

        private static final long serialVersionUID = 1L;

        private StructuralCause(String message) {
            super(message, null, false, true);
        }

        /** Bounded at eight links: a cause chain can cycle, and a long chain says no more. */
        static StructuralCause of(Throwable failure) {
            StringBuilder chain = new StringBuilder();
            java.util.Set<Throwable> seen =
                    java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());
            Throwable link = failure;
            for (int depth = 0; link != null && depth < 8 && seen.add(link); depth++) {
                chain.append(depth == 0 ? "" : " caused by ").append(link.getClass().getName());
                link = link.getCause();
            }
            StructuralCause structural = new StructuralCause(chain.toString());
            structural.setStackTrace(failure.getStackTrace());
            return structural;
        }
    }

    /** Append one field-level message extracted from the response body; chainable. */
    public SdkError addError(String errorMessage) {
        errors.add(errorMessage);
        return this;
    }

    /** The field-level messages the API supplied, as an unmodifiable snapshot. */
    public List<String> getErrorMessages() {
        return Collections.unmodifiableList(new ArrayList<>(errors));
    }

    /** A suggestion for what to do about this failure, or {@code null} when there is none. */
    public String getHint() {
        return statusCode == null ? null : HINTS_BY_STATUS.get(statusCode);
    }

    /** The whole diagnostic as one block: structural line, attempts and timing, and a hint. */
    public String explain() {
        boolean detail = showsFullDetail();
        StringBuilder out = new StringBuilder(toString());
        if (requestInfo != null) {
            Map<String, Object> params = requestInfo.getQueryParams();
            if (detail && !params.isEmpty()) {
                out.append("\n  params  : ").append(flatten(params));
            }
            int sent = requestInfo.getAttemptCount();
            int allowed = requestInfo.getMaxAttempts();
            if (sent > 0 && allowed > 0) {
                out.append("\n  attempt : ").append(sent).append('/').append(allowed);
            }
            out.append("\n  elapsed : ").append(requestInfo.getElapsedSeconds()).append('s');
        }
        if (requestId != null) {
            out.append("\n  request : ").append(flatten(requestId));
        }
        String location = redirectTarget(responseHeaders);
        if (detail && location != null) {
            out.append("\n  location: ").append(flatten(location));
        }
        List<String> reported = getErrorMessages();
        if (detail && (reported.size() > 1
                || (!reported.isEmpty() && !toString().contains(reported.get(0))))) {
            out.append("\n  reported:");
            for (String message : reported) {
                out.append("\n    - ").append(flatten(message));
            }
        }
        String hint = getHint();
        if (hint != null) {
            out.append("\n  hint    : ").append(hint);
        }
        if (detail && rawResponse != null) {
            String body = flatten(rawResponse);
            if (body.length() > 500) {
                body = body.substring(0, 500) + "... +" + (body.length() - 500) + " chars";
            }
            out.append("\n  body    : ").append(body);
        }
        return out.toString();
    }

    /** One line naming the call by its path template, never the resolved path, then the reason. */
    @Override
    public String toString() {
        StringBuilder parts = new StringBuilder(getClass().getSimpleName());
        if (errorType != null) {
            parts.append('[').append(flatten(errorType)).append(']');
        }
        if (statusCode != null) {
            parts.append(' ').append(statusCode);
        }
        if (requestInfo != null) {
            String method = requestInfo.getHttpMethod();
            if (method != null) {
                parts.append(' ').append(flatten(method));
            }
            String template = requestInfo.getPathTemplate();
            if (template != null) {
                parts.append(' ').append(flatten(template));
            }
            String url = requestInfo.getUrl();
            if (showsFullDetail() && url != null) {
                parts.append(' ').append(flatten(shortenUrl(url)));
            }
            int sent = requestInfo.getAttemptCount();
            int allowed = requestInfo.getMaxAttempts();
            if (sent > 0 && allowed > 0) {
                parts.append(" attempt ").append(sent).append('/').append(allowed);
            }
            parts.append(' ').append(requestInfo.getElapsedSeconds()).append('s');
        }
        return parts + " - " + flatten(getMessage());
    }

    private static String shortenUrl(String url) {
        int marker = url.indexOf("://");
        if (marker < 0) {
            return url;
        }
        String remainder = url.substring(marker + 3);
        int path = remainder.indexOf('/');
        return path < 0 ? url : remainder.substring(path);
    }

    /** Render a server-controlled value as one escaped line, so it cannot forge a log line. */
    protected static String flatten(Object value) {
        String text = safeString(value);
        StringBuilder out = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character <= 0x1f || (character >= 0x7f && character <= 0x9f)
                    || character == LINE_SEPARATOR || character == PARAGRAPH_SEPARATOR) {
                out.append(escape(character));
            } else {
                out.append(character);
            }
        }
        return out.toString();
    }

    private static final char LINE_SEPARATOR = 0x2028;
    private static final char PARAGRAPH_SEPARATOR = 0x2029;

    private static String escape(char character) {
        return switch (character) {
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            default -> String.format("\\u%04x", (int) character);
        };
    }

    private static String safeString(Object value) {
        if (value == null) {
            return "null";
        }
        try {
            return String.valueOf(value);
        } catch (RuntimeException error) {
            return "<unrenderable " + value.getClass().getSimpleName() + ">";
        }
    }

    private static String redirectTarget(Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if ("location".equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static final String REDIRECT_HINT =
            "the API redirected and the SDK does not follow one - read the Location "
            + "header off the response and decide whether to re-issue the call against "
            + "it. The copy on this exception's responseHeaders has its secret query "
            + "parameters redacted, so it is for reading and not for re-issuing: call again "
            + "with raiseOnError off if you need the response itself";

    private static final Map<Integer, String> HINTS_BY_STATUS = buildHints();

    private static Map<Integer, String> buildHints() {
        Map<Integer, String> hints = new LinkedHashMap<>();
        for (int status : new int[] {301, 302, 303, 307, 308}) {
            hints.put(status, REDIRECT_HINT);
        }
        hints.put(400, "the API rejected the request itself - check the payload shape and "
                + "the query parameter names against the endpoint's documentation");
        hints.put(401, "credentials were rejected, or the token expired and could not be "
                + "renewed - check clientId, clientSecret and redirectUri");
        hints.put(403, "authenticated, but this client is not allowed to do that");
        hints.put(404, "no such record - check the id you passed actually exists");
        hints.put(405, "wrong HTTP method for this path");
        hints.put(409, "conflicts with existing data, or the record is locked");
        hints.put(429, "rate limited - slow down, or raise maxRetries so the SDK waits "
                + "and retries for you");
        hints.put(500, "the API failed. Retrying may help on a read; the SDK will not "
                + "retry a write on a 500, because it cannot tell whether the API "
                + "committed");
        return Map.copyOf(hints);
    }
}
