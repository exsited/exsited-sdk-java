package com.exsited.resources.custom_fields;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_fields.custom_fields_dto.CustomFieldsDto.SettingsCustomField;
import com.exsited.resources.custom_fields.custom_fields_dto.CustomFieldsDto.SettingsCustomFieldList;
import java.util.Map;

/** Custom field endpoints, under {@code /api/{api_version}/settings/custom_fields}. */
public final class CustomFields implements BoundResource {

    private final ExsitedClient client;

    public CustomFields(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the custom fields: {@code GET /settings/custom_fields}. */
    public ApiResponse<SettingsCustomFieldList> getSettingsCustomFields(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomFieldsUrls.SETTINGS_CUSTOM_FIELDS,
                reopen(options).queryParams(queryParams).build(), SettingsCustomFieldList.class);
    }

    public ApiResponse<SettingsCustomFieldList> getSettingsCustomFields(Map<String, Object> queryParams) {
        return getSettingsCustomFields(queryParams, null);
    }

    public ApiResponse<SettingsCustomFieldList> getSettingsCustomFields() {
        return getSettingsCustomFields(null, null);
    }

    /**
     * Read one custom field by its {@code cfld_} id:
     * {@code GET /settings/custom_fields/{custom_field_id}}.
     */
    public ApiResponse<SettingsCustomField> getSettingsCustomField(
            String customFieldId, RequestOptions options) {
        requireValue(customFieldId, "custom_field_id");
        return client.sendGetRequest(CustomFieldsUrls.SETTINGS_CUSTOM_FIELD,
                reopen(options).pathParam("custom_field_id", customFieldId).build(), SettingsCustomField.class);
    }

    public ApiResponse<SettingsCustomField> getSettingsCustomField(String customFieldId) {
        return getSettingsCustomField(customFieldId, null);
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
}
