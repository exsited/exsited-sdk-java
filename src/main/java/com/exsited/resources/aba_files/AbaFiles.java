package com.exsited.resources.aba_files;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;

/** ABA file endpoints, under {@code /api/{api_version}/aba_file/generate}. */
public final class AbaFiles implements BoundResource {

    private final ExsitedClient client;

    public AbaFiles(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Generate an ABA file from transactions: {@code POST /aba_file/generate}. */
    public ApiResponse<Dto> generateAbaFile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AbaFilesUrls.ABA_FILE_GENERATE,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> generateAbaFile(
            Object payload) {
        return generateAbaFile(payload, null);
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
