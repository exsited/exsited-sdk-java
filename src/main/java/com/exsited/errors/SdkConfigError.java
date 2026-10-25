package com.exsited.errors;

/** Configuration is missing or invalid. */
public class SdkConfigError extends SdkError {

    private static final long serialVersionUID = 1L;

    /** Configuration the SDK refused to build a client from; the message lists every problem. */
    public SdkConfigError(String message) {
        super(message);
    }

    /** As above, keeping the failure that revealed it. */
    public SdkConfigError(String message, Throwable cause) {
        super(message, null, null, null, null, null, cause);
    }
}
