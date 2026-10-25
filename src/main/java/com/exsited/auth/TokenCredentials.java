package com.exsited.auth;

import com.exsited.errors.SdkUsageError;

import java.util.regex.Pattern;

/**
 * What a credential is allowed to look like, checked and never coerced.
 *
 * <p>Two grammars. An access token becomes the value of an {@code Authorization: Bearer}
 * header, so it must be an RFC 6750 section 2.1 {@code b64token}. A refresh token is a field
 * of a token request, so it is RFC 6749 section 5.1 visible text, which keeps punctuation the
 * Bearer grammar refuses and this server issues. A value that is not text is refused as a
 * type, not stringified.
 *
 * <p>No message here shows the value or names the offending character: these messages reach
 * a log, and the value is a credential.
 */
public final class TokenCredentials {

    private TokenCredentials() {}

    /**
     * RFC 6750 section 2.1 {@code b64token}: what may stand after {@code Bearer}
     * in an {@code Authorization} header. The {@code =} is padding and is allowed
     * only at the end, which is why this is matched whole rather than searched
     * for.
     */
    private static final Pattern BEARER_CREDENTIAL = Pattern.compile("[A-Za-z0-9\\-._~+/]+=*");

    /**
     * RFC 6749 section 5.1 with Appendix A.17, {@code 1*VSCHAR}: what may be sent as a field of
     * a token request.
     */
    private static final Pattern REFRESH_CREDENTIAL = Pattern.compile("[\\x20-\\x7e]+");

    /**
     * The access token, unchanged, or a refusal.
     *
     * @throws SdkUsageError it is not text, or not a Bearer credential
     */
    public static String checkAccessCredential(Object value) {
        if (!(value instanceof String text)) {
            throw new SdkUsageError("accessToken: expected the credential as text, got "
                    + (value == null ? "null" : value.getClass().getSimpleName())
                    + ". A token is not converted from another type: what would be sent is "
                    + "that type's rendering, which is not a credential");
        }
        // matches(), not find(): a grammar satisfied by part of the value is not
        // satisfied. "pad=in=middle" contains a b64token and is not one.
        if (!BEARER_CREDENTIAL.matcher(text).matches()) {
            throw new SdkUsageError("accessToken: is not a Bearer credential. RFC 6750 "
                    + "allows letters, digits and - . _ ~ + / with optional = padding at the "
                    + "end, and this SDK puts the value straight into an Authorization "
                    + "header; the value itself is not shown");
        }
        return text;
    }

    /**
     * The refresh token, unchanged, or a refusal.
     *
     * @throws SdkUsageError it is not text, or carries something a token
     *     request cannot send
     */
    public static String checkRefreshCredential(Object value) {
        if (!(value instanceof String text)) {
            throw new SdkUsageError("refreshToken: expected the credential as text, got "
                    + (value == null ? "null" : value.getClass().getSimpleName()));
        }
        if (!REFRESH_CREDENTIAL.matcher(text).matches()) {
            throw new SdkUsageError("refreshToken: is not a token-request value. RFC 6749 "
                    + "allows the visible ASCII range and a space, and this one carries "
                    + "something outside it - a control character, or a character with no "
                    + "defined encoding on this request; the value itself is not shown");
        }
        return text;
    }
}
