package com.exsited.resources.document_templates;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.document_templates.document_templates_dto.DocumentTemplatesDto.SettingsDocumentTemplate;
import com.exsited.resources.document_templates.document_templates_dto.DocumentTemplatesDto.SettingsDocumentTemplateList;
import java.util.Map;

/** Document template endpoints, under {@code /api/{api_version}/settings/document_templates}. */
public final class DocumentTemplates implements BoundResource {

    private final ExsitedClient client;

    public DocumentTemplates(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the document templates: {@code GET /settings/document_templates}. */
    public ApiResponse<SettingsDocumentTemplateList> getSettingsDocumentTemplates(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(DocumentTemplatesUrls.SETTINGS_DOCUMENT_TEMPLATES,
                reopen(options).queryParams(queryParams).build(), SettingsDocumentTemplateList.class);
    }

    public ApiResponse<SettingsDocumentTemplateList> getSettingsDocumentTemplates(
            Map<String, Object> queryParams) {
        return getSettingsDocumentTemplates(queryParams, null);
    }

    public ApiResponse<SettingsDocumentTemplateList> getSettingsDocumentTemplates() {
        return getSettingsDocumentTemplates(null, null);
    }

    /**
     * Create a document template; {@code name} and {@code document_template_type}, such as
     * {@code PDF_DOCUMENT}, are required: {@code POST /settings/document_templates}.
     */
    public ApiResponse<SettingsDocumentTemplate> createSettingsDocumentTemplate(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(DocumentTemplatesUrls.SETTINGS_DOCUMENT_TEMPLATES,
                reopen(options).jsonBody(payload).build(), SettingsDocumentTemplate.class);
    }

    public ApiResponse<SettingsDocumentTemplate> createSettingsDocumentTemplate(Object payload) {
        return createSettingsDocumentTemplate(payload, null);
    }

    /**
     * Read one document template by its {@code dt_} id:
     * {@code GET /settings/document_templates/{document_template_id}}.
     */
    public ApiResponse<SettingsDocumentTemplate> getSettingsDocumentTemplate(
            String documentTemplateId, RequestOptions options) {
        requireValue(documentTemplateId, "document_template_id");
        return client.sendGetRequest(DocumentTemplatesUrls.SETTINGS_DOCUMENT_TEMPLATE,
                reopen(options).pathParam("document_template_id", documentTemplateId).build(), SettingsDocumentTemplate.class);
    }

    public ApiResponse<SettingsDocumentTemplate> getSettingsDocumentTemplate(String documentTemplateId) {
        return getSettingsDocumentTemplate(documentTemplateId, null);
    }

    /**
     * Delete a document template:
     * {@code DELETE /settings/document_templates/{document_template_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsDocumentTemplate(
            String documentTemplateId, RequestOptions options) {
        requireValue(documentTemplateId, "document_template_id");
        return client.sendDeleteRequest(DocumentTemplatesUrls.SETTINGS_DOCUMENT_TEMPLATE,
                reopen(options).pathParam("document_template_id", documentTemplateId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsDocumentTemplate(String documentTemplateId) {
        return deleteSettingsDocumentTemplate(documentTemplateId, null);
    }

    /**
     * Update a document template's fields or its content:
     * {@code PATCH /settings/document_templates/{document_template_id}}.
     */
    public ApiResponse<SettingsDocumentTemplate> updateSettingsDocumentTemplate(
            String documentTemplateId, Object payload, RequestOptions options) {
        requireValue(documentTemplateId, "document_template_id");
        requirePayload(payload);
        return client.sendPatchRequest(DocumentTemplatesUrls.SETTINGS_DOCUMENT_TEMPLATE,
                reopen(options).pathParam("document_template_id", documentTemplateId).jsonBody(payload).build(), SettingsDocumentTemplate.class);
    }

    public ApiResponse<SettingsDocumentTemplate> updateSettingsDocumentTemplate(
            String documentTemplateId, Object payload) {
        return updateSettingsDocumentTemplate(documentTemplateId, payload, null);
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
