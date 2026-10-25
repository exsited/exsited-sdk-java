package com.exsited.client;

/** One field-level failure, as the v4 error envelope reports it. */
public final class FieldError {

    private final String field;
    private final String code;
    private final String message;
    private final String docUrl;

    /** One entry of the envelope's {@code errors[]}. */
    public FieldError(String field, String code, String message, String docUrl) {
        this.field = field;
        this.code = code;
        this.message = message;
        this.docUrl = docUrl;
    }

    /** The field the API refused. */
    public String getField() {
        return field;
    }

    /** The API's code for this refusal, or {@code null}. */
    public String getCode() {
        return code;
    }

    /** The API's prose for this refusal, or {@code null}. */
    public String getMessage() {
        return message;
    }

    /** Documentation for {@link #getCode()}, or {@code null}. */
    public String getDocUrl() {
        return docUrl;
    }

    @Override
    public String toString() {
        return field + (message == null ? "" : ": " + message);
    }
}
