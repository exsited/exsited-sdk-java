package com.exsited.resources.return_vendor_authorizations;

/** Path templates and body keys for the return vendor authorization endpoints. */
public final class ReturnVendorAuthorizationsUrls {

    private ReturnVendorAuthorizationsUrls() {}

    public static final String RETURN_VENDOR_AUTHORIZATIONS = "/api/{api_version}/return_vendor_authorizations";
    public static final String RETURN_VENDOR_AUTHORIZATION = "/api/{api_version}/return_vendor_authorizations/{rva_id}";
    /** No route lists or creates an RVA's notes, so these two can only remove one known already. */
    public static final String RETURN_VENDOR_AUTHORIZATION_NOTE = "/api/{api_version}/return_vendor_authorizations/{rva_id}/notes/{note_id}";
    public static final String RETURN_VENDOR_AUTHORIZATION_NOTE_FILE = "/api/{api_version}/return_vendor_authorizations/{rva_id}/notes/{note_id}/files/{file_id}";

    /** The key a listing's records sit under. */
    public static final String RETURN_VENDOR_AUTHORIZATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String RETURN_VENDOR_AUTHORIZATION_KEY = "return_vendor_authorization";
}
