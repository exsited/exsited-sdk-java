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

/**
 * Forwards every call to {@link Reports}.
 *
 * @deprecated {@link Reports} is bound to a client itself and is what
 *     {@code client.reports()} answers; use it instead.
 */
@Deprecated
public final class ReportsResource implements BoundResource {

    private final Reports reports;

    public ReportsResource(ExsitedClient client) {
        this.reports = new Reports(client);
    }

    @Override
    public ExsitedClient getClient() {
        return reports.getClient();
    }

    public ApiResponse<ReportsAccountsAccountOverview> getReportsAccountsAccountOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsAccountsAccountOverview(queryParams, options);
    }

    public ApiResponse<ReportsAccountsAccountOverview> getReportsAccountsAccountOverview(
            Map<String, Object> queryParams) {
        return reports.getReportsAccountsAccountOverview(queryParams);
    }

    public ApiResponse<ReportsInvoicesInvoiceOverview> getReportsInvoicesInvoiceOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsInvoicesInvoiceOverview(queryParams, options);
    }

    public ApiResponse<ReportsInvoicesInvoiceOverview> getReportsInvoicesInvoiceOverview(Map<String, Object> queryParams) {
        return reports.getReportsInvoicesInvoiceOverview(queryParams);
    }

    public ApiResponse<ReportsOrdersOrderOverview> getReportsOrdersOrderOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsOrdersOrderOverview(queryParams, options);
    }

    public ApiResponse<ReportsOrdersOrderOverview> getReportsOrdersOrderOverview(Map<String, Object> queryParams) {
        return reports.getReportsOrdersOrderOverview(queryParams);
    }

    public ApiResponse<ReportsPurchaseInvoicesPurchaseInvoiceOverview> getReportsPurchaseInvoicesPurchaseInvoiceOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsPurchaseInvoicesPurchaseInvoiceOverview(queryParams, options);
    }

    public ApiResponse<ReportsPurchaseInvoicesPurchaseInvoiceOverview> getReportsPurchaseInvoicesPurchaseInvoiceOverview(
            Map<String, Object> queryParams) {
        return reports.getReportsPurchaseInvoicesPurchaseInvoiceOverview(queryParams);
    }

    public ApiResponse<ReportsPurchaseOrdersPurchaseOrderOverview> getReportsPurchaseOrdersPurchaseOrderOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsPurchaseOrdersPurchaseOrderOverview(queryParams, options);
    }

    public ApiResponse<ReportsPurchaseOrdersPurchaseOrderOverview> getReportsPurchaseOrdersPurchaseOrderOverview(
            Map<String, Object> queryParams) {
        return reports.getReportsPurchaseOrdersPurchaseOrderOverview(queryParams);
    }

    public ApiResponse<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview> getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(queryParams, options);
    }

    public ApiResponse<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview> getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(
            Map<String, Object> queryParams) {
        return reports.getReportsPurchaseTransactionsPurchasePaymentAndRefundOverview(queryParams);
    }

    public ApiResponse<ReportsTransactionsPaymentAndRefundOverview> getReportsTransactionsPaymentAndRefundOverview(
            Map<String, Object> queryParams, RequestOptions options) {
        return reports.getReportsTransactionsPaymentAndRefundOverview(queryParams, options);
    }

    public ApiResponse<ReportsTransactionsPaymentAndRefundOverview> getReportsTransactionsPaymentAndRefundOverview(
            Map<String, Object> queryParams) {
        return reports.getReportsTransactionsPaymentAndRefundOverview(queryParams);
    }
}
