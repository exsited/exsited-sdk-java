package com.exsited.resources.reports;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsAccountsAccountOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsInvoicesInvoiceOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsOrdersOrderOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsPurchaseInvoicesPurchaseInvoiceOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsPurchaseOrdersPurchaseOrderOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview;
import com.exsited.resources.reports.reports_dto.ReportsDto.ReportsTransactionsPaymentAndRefundOverview;
import java.util.Map;

/** Report endpoints, under {@code /api/{api_version}/reports}. */
public final class Reports implements BoundResource {

    private final ExsitedClient client;

    public Reports(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Account overview report: {@code GET /reports/accounts/account_overview}. */
    public ApiResponse<ReportsAccountsAccountOverview> getReportsAccountsAccountOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_ACCOUNTS_ACCOUNT_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsAccountsAccountOverview.class);
    }

    public ApiResponse<ReportsAccountsAccountOverview> getReportsAccountsAccountOverview(
            Map<String, Object> queryParams) {
        return getReportsAccountsAccountOverview(queryParams, null);
    }

    public ApiResponse<ReportsAccountsAccountOverview> getReportsAccountsAccountOverview() {
        return getReportsAccountsAccountOverview(null, null);
    }

    /**
     * Invoice overview report: {@code GET /reports/invoices/invoice_overview}.
     */
    public ApiResponse<ReportsInvoicesInvoiceOverview> getReportsInvoicesInvoiceOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_INVOICES_INVOICE_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsInvoicesInvoiceOverview.class);
    }

    public ApiResponse<ReportsInvoicesInvoiceOverview> getReportsInvoicesInvoiceOverview(
            Map<String, Object> queryParams) {
        return getReportsInvoicesInvoiceOverview(queryParams, null);
    }

    public ApiResponse<ReportsInvoicesInvoiceOverview> getReportsInvoicesInvoiceOverview() {
        return getReportsInvoicesInvoiceOverview(null, null);
    }

    /**
     * Order overview report: {@code GET /reports/orders/order_overview}.
     */
    public ApiResponse<ReportsOrdersOrderOverview> getReportsOrdersOrderOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_ORDERS_ORDER_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsOrdersOrderOverview.class);
    }

    public ApiResponse<ReportsOrdersOrderOverview> getReportsOrdersOrderOverview(
            Map<String, Object> queryParams) {
        return getReportsOrdersOrderOverview(queryParams, null);
    }

    public ApiResponse<ReportsOrdersOrderOverview> getReportsOrdersOrderOverview() {
        return getReportsOrdersOrderOverview(null, null);
    }

    /**
     * Purchase invoice overview report:
     * {@code GET /reports/purchase_invoices/purchase_invoice_overview}.
     */
    public ApiResponse<ReportsPurchaseInvoicesPurchaseInvoiceOverview> getReportsPurchaseInvoicesPurchaseInvoiceOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_PURCHASE_INVOICES_PURCHASE_INVOICE_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsPurchaseInvoicesPurchaseInvoiceOverview.class);
    }

    public ApiResponse<ReportsPurchaseInvoicesPurchaseInvoiceOverview> getReportsPurchaseInvoicesPurchaseInvoiceOverview(
            Map<String, Object> queryParams) {
        return getReportsPurchaseInvoicesPurchaseInvoiceOverview(queryParams, null);
    }

    public ApiResponse<ReportsPurchaseInvoicesPurchaseInvoiceOverview> getReportsPurchaseInvoicesPurchaseInvoiceOverview() {
        return getReportsPurchaseInvoicesPurchaseInvoiceOverview(null, null);
    }

    /**
     * Purchase order overview report:
     * {@code GET /reports/purchase_orders/purchase_order_overview}.
     */
    public ApiResponse<ReportsPurchaseOrdersPurchaseOrderOverview> getReportsPurchaseOrdersPurchaseOrderOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_PURCHASE_ORDERS_PURCHASE_ORDER_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsPurchaseOrdersPurchaseOrderOverview.class);
    }

    public ApiResponse<ReportsPurchaseOrdersPurchaseOrderOverview> getReportsPurchaseOrdersPurchaseOrderOverview(
            Map<String, Object> queryParams) {
        return getReportsPurchaseOrdersPurchaseOrderOverview(queryParams, null);
    }

    public ApiResponse<ReportsPurchaseOrdersPurchaseOrderOverview> getReportsPurchaseOrdersPurchaseOrderOverview() {
        return getReportsPurchaseOrdersPurchaseOrderOverview(null, null);
    }

    /**
     * Purchase payment and refund overview report:
     * {@code GET /reports/purchase_transactions/purchase_payment_and_refund_overview}.
     */
    public ApiResponse<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview> getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_PURCHASE_TRANSACTIONS_PURCHASE_PAYMENT_AND_REFUND_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview.class);
    }

    public ApiResponse<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview> getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(
            Map<String, Object> queryParams) {
        return getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(queryParams, null);
    }

    public ApiResponse<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview> getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview() {
        return getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(null, null);
    }

    /**
     * Payment and refund overview report:
     * {@code GET /reports/transactions/payment_and_refund_overview}.
     */
    public ApiResponse<ReportsTransactionsPaymentAndRefundOverview> getReportsTransactionsPaymentAndRefundOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReportsUrls.REPORTS_TRANSACTIONS_PAYMENT_AND_REFUND_OVERVIEW,
                reopen(options).queryParams(queryParams).build(), ReportsTransactionsPaymentAndRefundOverview.class);
    }

    public ApiResponse<ReportsTransactionsPaymentAndRefundOverview> getReportsTransactionsPaymentAndRefundOverview(
            Map<String, Object> queryParams) {
        return getReportsTransactionsPaymentAndRefundOverview(queryParams, null);
    }

    public ApiResponse<ReportsTransactionsPaymentAndRefundOverview> getReportsTransactionsPaymentAndRefundOverview() {
        return getReportsTransactionsPaymentAndRefundOverview(null, null);
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
}
