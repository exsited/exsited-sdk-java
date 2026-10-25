package com.exsited.resources.invoices;

import java.util.List;

/**
 * Path templates, body keys and query names for the invoice endpoints. {@code {invoice_id}} is
 * the record's {@code id}, never its {@code uuid}; the RMA path is spelled
 * {@code authorisations}.
 */
public final class InvoicesUrls {

    private InvoicesUrls() {}

    public static final String INVOICES = "/api/{api_version}/invoices";
    public static final String INVOICE = "/api/{api_version}/invoices/{invoice_id}";
    public static final String INVOICE_AMEND = "/api/{api_version}/invoices/{invoice_id}/amend";
    public static final String INVOICE_CANCEL = "/api/{api_version}/invoices/{invoice_id}/cancel";
    public static final String INVOICE_INFORMATION = "/api/{api_version}/invoices/{invoice_id}/information";
    public static final String INVOICE_ITEM_FULFILLMENTS = "/api/{api_version}/invoices/{invoice_id}/item_fulfillments";
    public static final String INVOICE_NOTES = "/api/{api_version}/invoices/{invoice_id}/notes";
    public static final String INVOICE_PDF = "/api/{api_version}/invoices/{invoice_id}/pdf";
    public static final String INVOICE_REACTIVATE = "/api/{api_version}/invoices/{invoice_id}/reactivate";
    public static final String INVOICE_RETURN_MERCHANDISE_AUTHORISATIONS = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations";
    public static final String ORDER_INVOICES = "/api/{api_version}/orders/{order_id}/invoices";
    public static final String INVOICE_ITEM_FULFILLMENT = "/api/{api_version}/invoices/{invoice_id}/item_fulfillments/{fulfillment_id}";
    public static final String INVOICE_NOTE = "/api/{api_version}/invoices/{invoice_id}/notes/{invoice_note_id}";
    public static final String INVOICE_RETURN_MERCHANDISE_AUTHORISATION = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_id}";
    public static final String INVOICE_NOTE_FILES = "/api/{api_version}/invoices/{invoice_id}/notes/{invoice_note_id}/files";
    public static final String INVOICE_NOTE_FILE = "/api/{api_version}/invoices/{invoice_id}/notes/{invoice_note_id}/files/{invoice_file_id}";
    public static final String INVOICE_KPIS = "/api/{api_version}/invoices/{invoice_id}/kpis";
    /**
     * The lines, and one line. The server ignores {@code {line_id}}: it answers the first line
     * whatever the id says.
     */
    public static final String INVOICE_LINES = "/api/{api_version}/invoices/{invoice_id}/lines";
    public static final String INVOICE_LINE = "/api/{api_version}/invoices/{invoice_id}/lines/{line_id}";
    /**
     * One credit note application through an invoice. The server does not scope it: any application
     * id answers under any invoice that exists.
     */
    public static final String INVOICE_CREDIT_NOTE_APPLICATION = "/api/{api_version}/invoices/{invoice_id}/credit_note_applications/{credit_note_application_id}";
    public static final String INVOICE_CUSTOM_OBJECTS = "/api/{api_version}/invoices/{invoice_id}/custom_objects";
    public static final String INVOICE_CUSTOM_OBJECT = "/api/{api_version}/invoices/{invoice_id}/custom_objects/{custom_object_id}";
    public static final String INVOICE_CUSTOM_OBJECT_INSTANCE = "/api/{api_version}/invoices/{invoice_id}/custom_objects/{custom_object_id}/instances/{instance_id}";
    /** British spelling, with an {@code s}. */
    public static final String INVOICE_RMAS = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations";
    public static final String INVOICE_RMA = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_id}";

    /** The key a listing's records sit under. */
    public static final String INVOICES_KEY = "data";
    public static final String INVOICE_ITEM_FULFILLMENTS_KEY = "data";
    public static final String INVOICE_NOTES_KEY = "data";
    public static final String INVOICE_RMAS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILE_KEY = "file";
    public static final String INVOICE_KEY = "invoice";
    public static final String ITEM_FULFILLMENT_KEY = "item_fulfillment";
    public static final String NOTE_KEY = "note";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_KEY = "return_merchandise_authorisation";
    public static final String INVOICE_CUSTOM_OBJECTS_KEY = "data";
    public static final String INVOICE_KPIS_KEY = "invoice_kpis";
    public static final String CREDIT_NOTE_APPLICATION_KEY = "credit_note_application";

    /**
     * The names that shape the listing, which pages by cursor; every other name is a
     * {@code filter_<field>} or {@code filter_<field>_<operator>}, the money and date fields
     * taking the range operators too. A bare {@code filter} expression is {@code 400}.
     */
    public static final List<String> INVOICES_QUERY_PARAMS = List.of("limit", "cursor");
}
