package com.exsited.resources.express;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.express.express_dto.ExpressDto;

/** Express endpoints, under {@code /api/{api_version}/express}. */
public final class Express implements BoundResource {

    private final ExsitedClient client;

    public Express(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** {@code POST /express}. */
    public ApiResponse<ExpressDto.Express> createExpress(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ExpressUrls.EXPRESS,
                reopen(options).jsonBody(payload).build(), ExpressDto.Express.class);
    }

    public ApiResponse<ExpressDto.Express> createExpress(
            Object payload) {
        return createExpress(payload, null);
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
