package com.exsited.resources.custom_forms;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_forms.custom_forms_dto.CustomFormsDto.SettingsCustomForm;
import com.exsited.resources.custom_forms.custom_forms_dto.CustomFormsDto.SettingsCustomFormList;
import java.util.Map;

/** Custom form endpoints, under {@code /api/{api_version}/settings/custom_forms}. */
public final class CustomForms implements BoundResource {

    private final ExsitedClient client;

    public CustomForms(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the custom forms: {@code GET /settings/custom_forms}. */
    public ApiResponse<SettingsCustomFormList> getSettingsCustomForms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomFormsUrls.SETTINGS_CUSTOM_FORMS,
                reopen(options).queryParams(queryParams).build(), SettingsCustomFormList.class);
    }

    public ApiResponse<SettingsCustomFormList> getSettingsCustomForms(Map<String, Object> queryParams) {
        return getSettingsCustomForms(queryParams, null);
    }

    public ApiResponse<SettingsCustomFormList> getSettingsCustomForms() {
        return getSettingsCustomForms(null, null);
    }

    /**
     * Create a custom form; {@code name} and {@code setting_group}, a setting group name such as
     * {@code ACCOUNT}, are required: {@code POST /settings/custom_forms}.
     */
    public ApiResponse<SettingsCustomForm> createSettingsCustomForm(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CustomFormsUrls.SETTINGS_CUSTOM_FORMS,
                reopen(options).jsonBody(payload).build(), SettingsCustomForm.class);
    }

    public ApiResponse<SettingsCustomForm> createSettingsCustomForm(Object payload) {
        return createSettingsCustomForm(payload, null);
    }

    /**
     * Read one custom form by its {@code cf_} id:
     * {@code GET /settings/custom_forms/{custom_form_id}}.
     */
    public ApiResponse<SettingsCustomForm> getSettingsCustomForm(
            String customFormId, RequestOptions options) {
        requireValue(customFormId, "custom_form_id");
        return client.sendGetRequest(CustomFormsUrls.SETTINGS_CUSTOM_FORM,
                reopen(options).pathParam("custom_form_id", customFormId).build(), SettingsCustomForm.class);
    }

    public ApiResponse<SettingsCustomForm> getSettingsCustomForm(String customFormId) {
        return getSettingsCustomForm(customFormId, null);
    }

    /**
     * Delete a custom form that is not system generated:
     * {@code DELETE /settings/custom_forms/{custom_form_id}}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsCustomForm(
            String customFormId, RequestOptions options) {
        requireValue(customFormId, "custom_form_id");
        return client.sendDeleteRequest(CustomFormsUrls.SETTINGS_CUSTOM_FORM,
                reopen(options).pathParam("custom_form_id", customFormId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsCustomForm(String customFormId) {
        return deleteSettingsCustomForm(customFormId, null);
    }

    /**
     * Update a custom form's name, display name, description or users:
     * {@code PATCH /settings/custom_forms/{custom_form_id}}.
     */
    public ApiResponse<SettingsCustomForm> updateSettingsCustomForm(
            String customFormId, Object payload, RequestOptions options) {
        requireValue(customFormId, "custom_form_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomFormsUrls.SETTINGS_CUSTOM_FORM,
                reopen(options).pathParam("custom_form_id", customFormId).jsonBody(payload).build(), SettingsCustomForm.class);
    }

    public ApiResponse<SettingsCustomForm> updateSettingsCustomForm(String customFormId, Object payload) {
        return updateSettingsCustomForm(customFormId, payload, null);
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
