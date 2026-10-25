package com.exsited.resources.quote_orders;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.quote_orders.quote_orders_dto.QuoteOrdersDto.QuoteOrderInvoiceList;
import java.util.Map;

/** Quote order endpoints, under {@code /api/{api_version}/quote_orders}. */
public final class QuoteOrders implements BoundResource {

    private final ExsitedClient client;

    public QuoteOrders(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the invoices raised from a quote: {@code GET /quote_orders/{quote_order_id}/invoices};
     * an order that is not a quote answers {@code 404}.
     */
    public ApiResponse<QuoteOrderInvoiceList> getQuoteOrderInvoices(
            String quoteOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(quoteOrderId, "quote_order_id");
        return client.sendGetRequest(QuoteOrdersUrls.QUOTE_ORDER_INVOICES,
                reopen(options).pathParam("quote_order_id", quoteOrderId).queryParams(queryParams).build(), QuoteOrderInvoiceList.class);
    }

    public ApiResponse<QuoteOrderInvoiceList> getQuoteOrderInvoices(
            String quoteOrderId, Map<String, Object> queryParams) {
        return getQuoteOrderInvoices(quoteOrderId, queryParams, null);
    }

    public ApiResponse<QuoteOrderInvoiceList> getQuoteOrderInvoices(String quoteOrderId) {
        return getQuoteOrderInvoices(quoteOrderId, null, null);
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
