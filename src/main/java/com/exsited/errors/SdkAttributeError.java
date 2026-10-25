package com.exsited.errors;

/** A name that does not exist on a client or on a bound resource. */
public class SdkAttributeError extends SdkUsageError {

    private static final long serialVersionUID = 1L;

    /** A lookup by name that matched nothing registered. */
    public SdkAttributeError(String message) {
        super(message);
    }
}
