package com.exsited.http;

import java.util.Locale;

/**
 * Whether a {@code Content-Type} labels a JSON body, for every place that asks.
 *
 * <p>Exact equality with {@code application/json}, or the RFC 6839 structured suffix
 * {@code +json} under {@code application/}: {@code application/vnd.api+json} is JSON, and
 * {@code application/jsonp}, which a substring test would accept, is not.
 */
public final class MediaType {

    private MediaType() {}

    public static final String JSON = "application/json";

    private static final String JSON_TREE = "application/";
    private static final String JSON_SUFFIX = "+json";

    /**
     * The {@code type/subtype} a header names, with its parameters dropped and
     * its case folded. Parameters are dropped <b>before</b> any comparison, which
     * is what lets the comparisons be exact.
     */
    public static String mediaTypeOf(String contentType) {
        if (contentType == null) {
            return "";
        }
        int semicolon = contentType.indexOf(';');
        String head = semicolon < 0 ? contentType : contentType.substring(0, semicolon);
        return head.strip().toLowerCase(Locale.ROOT);
    }

    /**
     * Whether this header labels a JSON body.
     *
     * <p>Total: anything that is not a media type answers false rather than
     * throwing, because this runs on every response.
     */
    public static boolean isJson(String contentType) {
        String mediaType = mediaTypeOf(contentType);
        if (mediaType.equals(JSON)) {
            return true;
        }
        return mediaType.startsWith(JSON_TREE)
                && mediaType.endsWith(JSON_SUFFIX)
                // "application/+json" has no subtype before the suffix, so it names
                // nothing at all.
                && mediaType.length() > JSON_TREE.length() + JSON_SUFFIX.length();
    }

    /**
     * Whether this header labels a body that is text at all.
     *
     * <p>{@code text/*} plus every JSON type. Anything else is bytes: a note
     * attachment may be a PNG, and decoding those bytes as text produces mojibake
     * that looks like data.
     */
    public static boolean isTextual(String contentType) {
        return isJson(contentType) || mediaTypeOf(contentType).startsWith("text/");
    }
}
