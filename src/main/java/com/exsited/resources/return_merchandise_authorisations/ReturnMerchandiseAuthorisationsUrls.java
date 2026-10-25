package com.exsited.resources.return_merchandise_authorisations;

/** Path templates and body keys for the return merchandise authorisation endpoints. */
public final class ReturnMerchandiseAuthorisationsUrls {

    private ReturnMerchandiseAuthorisationsUrls() {}

    public static final String RETURN_MERCHANDISE_AUTHORISATIONS = "/api/{api_version}/return_merchandise_authorisations";
    public static final String RETURN_MERCHANDISE_AUTHORISATION = "/api/{api_version}/return_merchandise_authorisations/{rma_id}";
    public static final String INVOICE_RETURN_MERCHANDISE_AUTHORISATION_RECEIVE = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive";
    public static final String INVOICE_RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_UUID = "/api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive/{receive_uuid}";
    /**
     * The writes under one RMA take its {@code rma_} id and refuse a code; the reads under it take
     * its code and answer {@code 404} for an {@code rma_} id. So one path can need two identifiers,
     * and the {@code _BY_CODE} templates are the read side of it.
     */
    public static final String RETURN_MERCHANDISE_AUTHORISATION_APPROVE = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/approve";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_REJECT = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/reject";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_RECEIVE = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/receive";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTES = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/notes";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/notes/{note_id}";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILE = "/api/{api_version}/return_merchandise_authorisations/{rma_id}/notes/{note_id}/files/{file_id}";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_CODE = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/receive";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_UUID = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/receive/{receive_uuid}";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTES_BY_CODE = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/notes";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE_BY_CODE = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/notes/{note_id}";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILES_BY_CODE = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/notes/{note_id}/files";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILE_BY_CODE = "/api/{api_version}/return_merchandise_authorisations/{rma_code}/notes/{note_id}/files/{file_id}";

    /** The key a listing's records sit under. */
    public static final String INVOICE_RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_KEY = "receive_return_merchandise_authorisations";
    public static final String RETURN_MERCHANDISE_AUTHORISATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String INVOICE_KEY = "invoice";
    public static final String RECEIVE_RETURN_MERCHANDISE_AUTHORISATION_KEY = "receive_return_merchandise_authorisation";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_KEY = "return_merchandise_authorisation";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_CODE_KEY = "data";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTES_BY_CODE_KEY = "data";
    public static final String RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILES_BY_CODE_KEY = "data";
    public static final String RECEIVE_RMA_KEY = "receive_rma";
    public static final String NOTE_KEY = "note";
    public static final String FILE_KEY = "file";
}
