package com.exsited.resources.webhooks;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;

/** Webhook endpoints, under {@code /api/{api_version}/webhooks}. */
public final class Webhooks implements BoundResource {

    private final ExsitedClient client;

    public Webhooks(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * One webhook delivery: {@code GET /webhooks/{webhook_id}}, the {@code whk_} id a delivery
     * carries; no route lists them, so {@code getDto()} is null and the body is read through
     * {@code getMap()}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getWebhook(
            String webhookId, RequestOptions options) {
        requireValue(webhookId, "webhook_id");
        return client.sendGetRequest(WebhooksUrls.WEBHOOK,
                reopen(options).pathParam("webhook_id", webhookId).build(), null);
    }

    public ApiResponse<Dto> getWebhook(String webhookId) {
        return getWebhook(webhookId, null);
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
