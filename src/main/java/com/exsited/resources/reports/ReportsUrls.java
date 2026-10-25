package com.exsited.resources.reports;

/** Path templates and body keys for the report endpoints. */
public final class ReportsUrls {

    private ReportsUrls() {}

    public static final String REPORTS_ACCOUNTS_ACCOUNT_OVERVIEW = "/api/{api_version}/reports/accounts/account_overview";
    public static final String REPORTS_INVOICES_INVOICE_OVERVIEW = "/api/{api_version}/reports/invoices/invoice_overview";
    public static final String REPORTS_ORDERS_ORDER_OVERVIEW = "/api/{api_version}/reports/orders/order_overview";
    public static final String REPORTS_PURCHASE_INVOICES_PURCHASE_INVOICE_OVERVIEW = "/api/{api_version}/reports/purchase_invoices/purchase_invoice_overview";
    public static final String REPORTS_PURCHASE_ORDERS_PURCHASE_ORDER_OVERVIEW = "/api/{api_version}/reports/purchase_orders/purchase_order_overview";
    public static final String REPORTS_PURCHASE_TRANSACTIONS_PURCHASE_PAYMENT_AND_REFUND_OVERVIEW = "/api/{api_version}/reports/purchase_transactions/purchase_payment_and_refund_overview";
    public static final String REPORTS_TRANSACTIONS_PAYMENT_AND_REFUND_OVERVIEW = "/api/{api_version}/reports/transactions/payment_and_refund_overview";

    /** The envelope one record arrives under. */
    public static final String REPORT_KEY = "report";
}
