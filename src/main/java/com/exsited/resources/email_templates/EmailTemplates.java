package com.exsited.resources.email_templates;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.email_templates.email_templates_dto.EmailTemplatesDto.SettingsEmailTemplate;
import com.exsited.resources.email_templates.email_templates_dto.EmailTemplatesDto.SettingsEmailTemplateList;
import java.util.Map;

/** Email template endpoints, under {@code /api/{api_version}/settings/email_templates}. */
public final class EmailTemplates implements BoundResource {

    private final ExsitedClient client;

    public EmailTemplates(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the email templates shared across the instance: {@code GET /settings/email_templates}.
     */
    public ApiResponse<SettingsEmailTemplateList> getSettingsEmailTemplates(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(EmailTemplatesUrls.SETTINGS_EMAIL_TEMPLATES,
                reopen(options).queryParams(queryParams).build(), SettingsEmailTemplateList.class);
    }

    public ApiResponse<SettingsEmailTemplateList> getSettingsEmailTemplates(Map<String, Object> queryParams) {
        return getSettingsEmailTemplates(queryParams, null);
    }

    public ApiResponse<SettingsEmailTemplateList> getSettingsEmailTemplates() {
        return getSettingsEmailTemplates(null, null);
    }

    /**
     * Create an email template; {@code name} is required, and {@code cc} and {@code bcc} are lists
     * of addresses: {@code POST /settings/email_templates}.
     */
    public ApiResponse<SettingsEmailTemplate> createSettingsEmailTemplate(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(EmailTemplatesUrls.SETTINGS_EMAIL_TEMPLATES,
                reopen(options).jsonBody(payload).build(), SettingsEmailTemplate.class);
    }

    public ApiResponse<SettingsEmailTemplate> createSettingsEmailTemplate(Object payload) {
        return createSettingsEmailTemplate(payload, null);
    }

    /**
     * Read one email template by its {@code et_} id:
     * {@code GET /settings/email_templates/{email_template_id}}.
     */
    public ApiResponse<SettingsEmailTemplate> getSettingsEmailTemplate(
            String emailTemplateId, RequestOptions options) {
        requireValue(emailTemplateId, "email_template_id");
        return client.sendGetRequest(EmailTemplatesUrls.SETTINGS_EMAIL_TEMPLATE,
                reopen(options).pathParam("email_template_id", emailTemplateId).build(), SettingsEmailTemplate.class);
    }

    public ApiResponse<SettingsEmailTemplate> getSettingsEmailTemplate(String emailTemplateId) {
        return getSettingsEmailTemplate(emailTemplateId, null);
    }

    /**
     * Delete an email template: {@code DELETE /settings/email_templates/{email_template_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsEmailTemplate(
            String emailTemplateId, RequestOptions options) {
        requireValue(emailTemplateId, "email_template_id");
        return client.sendDeleteRequest(EmailTemplatesUrls.SETTINGS_EMAIL_TEMPLATE,
                reopen(options).pathParam("email_template_id", emailTemplateId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsEmailTemplate(String emailTemplateId) {
        return deleteSettingsEmailTemplate(emailTemplateId, null);
    }

    /**
     * Update an email template's fields or its content:
     * {@code PATCH /settings/email_templates/{email_template_id}}.
     */
    public ApiResponse<SettingsEmailTemplate> updateSettingsEmailTemplate(
            String emailTemplateId, Object payload, RequestOptions options) {
        requireValue(emailTemplateId, "email_template_id");
        requirePayload(payload);
        return client.sendPatchRequest(EmailTemplatesUrls.SETTINGS_EMAIL_TEMPLATE,
                reopen(options).pathParam("email_template_id", emailTemplateId).jsonBody(payload).build(), SettingsEmailTemplate.class);
    }

    public ApiResponse<SettingsEmailTemplate> updateSettingsEmailTemplate(
            String emailTemplateId, Object payload) {
        return updateSettingsEmailTemplate(emailTemplateId, payload, null);
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
