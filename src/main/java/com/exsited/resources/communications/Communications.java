package com.exsited.resources.communications;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;

/** Communication endpoints, under {@code /api/{api_version}/communications}. */
public final class Communications implements BoundResource {

    private final ExsitedClient client;

    public Communications(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Email a document: {@code POST /communications/send_email}; the body names one of
     * {@code invoice}, {@code purchase_invoice}, {@code account_statement} or {@code payment}, each
     * an {@code id} beside {@code to}, {@code cc}, {@code bcc}, {@code subject}, {@code body} and
     * {@code template}. Sends real mail. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> sendEmail(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CommunicationsUrls.COMMUNICATIONS_SEND_EMAIL,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> sendEmail(Object payload) {
        return sendEmail(payload, null);
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

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
