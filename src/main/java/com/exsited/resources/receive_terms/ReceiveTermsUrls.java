package com.exsited.resources.receive_terms;

/** Path templates and body keys for the receive term endpoints. */
public final class ReceiveTermsUrls {

    private ReceiveTermsUrls() {}

    public static final String SETTINGS_RECEIVE_TERMS = "/api/{api_version}/settings/receive_terms";
    public static final String SETTINGS_RECEIVE_TERM = "/api/{api_version}/settings/receive_terms/{receive_term_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_RECEIVE_TERMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String RECEIVE_TERM_KEY = "receive_term";
}
