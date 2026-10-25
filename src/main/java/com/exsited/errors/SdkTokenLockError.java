package com.exsited.errors;

/**
 * The cross-process lock over the token cache could not be taken.
 *
 * <p>Raised rather than running the work without exclusion: failing closed costs this process
 * one extra authentication, while failing open could silently overwrite another process's
 * refresh token.
 */
public final class SdkTokenLockError extends SdkError {
    private static final long serialVersionUID = 1L;

    public SdkTokenLockError(String message) { super(message); }
}
