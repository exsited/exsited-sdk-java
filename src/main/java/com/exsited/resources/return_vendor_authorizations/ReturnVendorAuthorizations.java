package com.exsited.resources.return_vendor_authorizations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.return_vendor_authorizations.return_vendor_authorizations_dto.ReturnVendorAuthorizationsDto.ReturnVendorAuthorizationList;
import com.exsited.resources.return_vendor_authorizations.return_vendor_authorizations_dto.ReturnVendorAuthorizationsDto.ReturnVendorAuthorizationView;
import java.util.Map;

/** Return vendor authorization endpoints, under {@code /api/{api_version}}. */
public final class ReturnVendorAuthorizations implements BoundResource {

    private final ExsitedClient client;

    public ReturnVendorAuthorizations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Create RVA: {@code POST /return_vendor_authorizations}, {@code
     * {"return_vendor_authorization": {"purchase_order", "lines": [{"purchase_order_line",
     * "rva_qty"}]}}}; answered {@code 201}.
     */
    public ApiResponse<ReturnVendorAuthorizationView> createReturnVendorAuthorization(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATIONS,
                reopen(options).jsonBody(payload).build(), ReturnVendorAuthorizationView.class);
    }

    public ApiResponse<ReturnVendorAuthorizationView> createReturnVendorAuthorization(Object payload) {
        return createReturnVendorAuthorization(payload, null);
    }

    /**
     * Delete draft RVA: {@code DELETE /return_vendor_authorizations/{rva_id}}, the {@code rva_} id
     * only; a code answers {@code 404}, and anything but a draft {@code 400}
     * {@code stock_committed}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnVendorAuthorization(
            String rvaId, RequestOptions options) {
        requireValue(rvaId, "rva_id");
        return client.sendDeleteRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATION,
                reopen(options).pathParam("rva_id", rvaId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnVendorAuthorization(String rvaId) {
        return deleteReturnVendorAuthorization(rvaId, null);
    }

    /**
     * Edit RVA header: {@code PATCH /return_vendor_authorizations/{rva_id}}, the {@code rva_} id or
     * the code, {@code {"return_vendor_authorization": {...}}} of {@code rva_date}, {@code note}
     * and {@code custom_attributes}.
     */
    public ApiResponse<ReturnVendorAuthorizationView> updateReturnVendorAuthorization(
            String rvaId, Object payload, RequestOptions options) {
        requireValue(rvaId, "rva_id");
        requirePayload(payload);
        return client.sendPatchRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATION,
                reopen(options).pathParam("rva_id", rvaId).jsonBody(payload).build(), ReturnVendorAuthorizationView.class);
    }

    public ApiResponse<ReturnVendorAuthorizationView> updateReturnVendorAuthorization(
            String rvaId, Object payload) {
        return updateReturnVendorAuthorization(rvaId, payload, null);
    }

    /**
     * Delete RVA note: {@code DELETE /return_vendor_authorizations/{rva_id}/notes/{note_id}}, the
     * {@code rva_} id; no route lists or creates an RVA's notes. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnVendorAuthorizationNote(
            String rvaId, String noteId, RequestOptions options) {
        requireValue(rvaId, "rva_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATION_NOTE,
                reopen(options).pathParam("rva_id", rvaId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnVendorAuthorizationNote(String rvaId, String noteId) {
        return deleteReturnVendorAuthorizationNote(rvaId, noteId, null);
    }

    /**
     * Delete RVA note attachment:
     * {@code DELETE /return_vendor_authorizations/{rva_id}/notes/{note_id}/files/{file_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnVendorAuthorizationNoteFile(
            String rvaId, String noteId, String fileId, RequestOptions options) {
        requireValue(rvaId, "rva_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATION_NOTE_FILE,
                reopen(options).pathParam("rva_id", rvaId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnVendorAuthorizationNoteFile(
            String rvaId, String noteId, String fileId) {
        return deleteReturnVendorAuthorizationNoteFile(rvaId, noteId, fileId, null);
    }

    /** List RVAs: {@code GET /return_vendor_authorizations}. */
    public ApiResponse<ReturnVendorAuthorizationList> getReturnVendorAuthorizations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATIONS,
                reopen(options).queryParams(queryParams).build(), ReturnVendorAuthorizationList.class);
    }

    public ApiResponse<ReturnVendorAuthorizationList> getReturnVendorAuthorizations(
            Map<String, Object> queryParams) {
        return getReturnVendorAuthorizations(queryParams, null);
    }

    public ApiResponse<ReturnVendorAuthorizationList> getReturnVendorAuthorizations() {
        return getReturnVendorAuthorizations(null, null);
    }

    /**
     * Get RVA: {@code GET /return_vendor_authorizations/{rva_id}}, the {@code rva_} id or the code.
     */
    public ApiResponse<ReturnVendorAuthorizationView> getReturnVendorAuthorization(
            String rvaId, RequestOptions options) {
        requireValue(rvaId, "rva_id");
        return client.sendGetRequest(ReturnVendorAuthorizationsUrls.RETURN_VENDOR_AUTHORIZATION,
                reopen(options).pathParam("rva_id", rvaId).build(), ReturnVendorAuthorizationView.class);
    }

    public ApiResponse<ReturnVendorAuthorizationView> getReturnVendorAuthorization(String rvaId) {
        return getReturnVendorAuthorization(rvaId, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
