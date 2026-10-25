package com.exsited.errors;

import com.exsited.client.ErrorType;
import com.exsited.client.RequestInfo;

import java.util.Map;

/** Authentication failed: a rejected grant, a 401 surviving one refresh-and-replay, or a 403. */
public class SdkAuthError extends SdkError {

    private static final long serialVersionUID = 1L;

    /** A credential failure with no HTTP response behind it, such as a rejected token grant. */
    public SdkAuthError(String message) {
        super(message);
    }

    /** A credential failure carrying the response that reported it, already redacted. */
    public SdkAuthError(
            String message,
            Integer statusCode,
            ErrorType errorType,
            Object rawResponse,
            Map<String, String> responseHeaders,
            RequestInfo requestInfo) {
        super(message, statusCode, errorType, rawResponse, responseHeaders, requestInfo);
    }

    /** Every field by name, the v4 envelope's extras included. */
    public SdkAuthError(String message, Details details) {
        super(message, details);
    }
}
