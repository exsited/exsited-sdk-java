package com.exsited.resources.invoice_terms;

/** Path templates and body keys for the invoice term endpoints. */
public final class InvoiceTermsUrls {

    private InvoiceTermsUrls() {}

    public static final String SETTINGS_INVOICE_TERMS = "/api/{api_version}/settings/invoice_terms";
    public static final String SETTINGS_INVOICE_TERM = "/api/{api_version}/settings/invoice_terms/{invoice_term_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_INVOICE_TERMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String INVOICE_TERM_KEY = "invoice_term";
}
