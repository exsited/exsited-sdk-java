package com.exsited.resources.purchase_express;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_express.purchase_express_dto.PurchaseExpressDto;

/** Purchase express endpoints, under {@code /api/{api_version}/purchase_express}. */
public final class PurchaseExpress implements BoundResource {

    private final ExsitedClient client;

    public PurchaseExpress(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Create a purchase order, and its purchase invoice and payment if the body nests them, in one
     * call: {@code POST /purchase_express}, body {@code account.purchase_order}; {@code 201}.
     */
    public ApiResponse<PurchaseExpressDto.PurchaseExpress> createPurchaseExpress(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PurchaseExpressUrls.PURCHASE_EXPRESS,
                reopen(options).jsonBody(payload).build(), PurchaseExpressDto.PurchaseExpress.class);
    }

    public ApiResponse<PurchaseExpressDto.PurchaseExpress> createPurchaseExpress(Object payload) {
        return createPurchaseExpress(payload, null);
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
