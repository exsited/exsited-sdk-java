package com.exsited.resources.proforma_invoices;

/**
 * Path templates and body keys for the pro-forma invoice endpoints. {@code {proforma_id}} is the
 * pro-forma invoice's code, which it answers as both {@code id} and {@code invoice_id}.
 */
public final class ProformaInvoicesUrls {

    private ProformaInvoicesUrls() {}

    public static final String PROFORMA_INVOICES = "/api/{api_version}/proforma_invoices";
    public static final String PROFORMA_INVOICE = "/api/{api_version}/proforma_invoices/{proforma_id}";
    public static final String PROFORMA_INVOICE_PAYMENTS = "/api/{api_version}/proforma_invoices/{proforma_id}/payments";

    /** The key a listing's records sit under. */
    public static final String PROFORMA_INVOICES_KEY = "data";

    /**
     * The envelope one record arrives under. A payment answers
     * {@code {object, payment_id, message}} under {@code payment}, not the payment itself.
     */
    public static final String PROFORMA_INVOICE_KEY = "proforma_invoice";
    public static final String PAYMENT_KEY = "payment";
}
