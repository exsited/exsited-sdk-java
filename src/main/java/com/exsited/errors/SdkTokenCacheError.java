package com.exsited.errors;

/**
 * The token cache file is there and cannot be used.
 *
 * <p>Distinct from an absent cache: every write rebuilds the whole file from what the read
 * returned, so reading an unparseable file as empty would delete every other namespace's
 * entry. A write that cannot read the file refuses instead.
 */
public final class SdkTokenCacheError extends SdkError {
    private static final long serialVersionUID = 1L;

    public SdkTokenCacheError(String message) { super(message); }
}
