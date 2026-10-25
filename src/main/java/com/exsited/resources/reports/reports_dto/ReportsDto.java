package com.exsited.resources.reports.reports_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import java.util.List;

/** Typed views over the reports bodies. */
public final class ReportsDto {

    /** The reports accounts account overview body. */
    public static final class ReportsAccountsAccountOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/accounts/account_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsAccountsAccountOverviewDetail accounts;
    }

    /** What {@code get_reports_invoices_invoice_overview} answers, under {@code report}. */
    public static final class ReportsInvoicesInvoiceOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/invoices/invoice_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsInvoicesInvoiceOverviewDetail invoices;
    }

    /** What {@code get_reports_orders_order_overview} answers, under {@code report}. */
    public static final class ReportsOrdersOrderOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/orders/order_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsOrdersOrderOverviewDetail orders;
    }

    /**
     * What {@code get_reports_purchase_invoices_purchase_invoice_overview} answers, under {@code
     * report}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/purchase_invoices/purchase_invoice_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetail purchase_invoices;
    }

    /**
     * What {@code get_reports_purchase_orders_purchase_order_overview} answers, under {@code
     * report}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/purchase_orders/purchase_order_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsPurchaseOrdersPurchaseOrderOverviewDetail purchase_orders;
    }

    /**
     * What {@code get_reports_purchase_transactions_purchase_payment_and_refund_overview} answers,
     * under {@code report}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/purchase_transactions/purchase_payment_and_refund_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetail purchase_payment_and_refund_overview;
    }

    /**
     * What {@code get_reports_transactions_payment_and_refund_overview} answers, under {@code
     * report}.
     */
    public static final class ReportsTransactionsPaymentAndRefundOverview extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/reports/transactions/payment_and_refund_overview");

        @Override
        protected String envelopeKey() {
            return "report";
        }

        public ReportsTransactionsPaymentAndRefundOverviewDetail payment_and_refund_overview;
    }

    /** A {@link ReportsAccountsAccountOverview}'s {@code accounts}. */
    public static final class ReportsAccountsAccountOverviewDetail extends Dto {
        public List<ReportsAccountsAccountOverviewDetailAccountOverview> account_overview;
    }

    /** A {@link ReportsInvoicesInvoiceOverview}'s {@code invoices}. */
    public static final class ReportsInvoicesInvoiceOverviewDetail extends Dto {
        public List<ReportsInvoicesInvoiceOverviewDetailInvoiceOverview> invoice_overview;
    }

    /** A {@link ReportsOrdersOrderOverview}'s {@code orders}. */
    public static final class ReportsOrdersOrderOverviewDetail extends Dto {
        public List<ReportsOrdersOrderOverviewDetailOrderOverview> order_overview;
    }

    /** A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverview}'s {@code purchase_invoices}. */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetail extends Dto {
        public List<ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview> purchase_invoice_overview;
    }

    /** A {@link ReportsPurchaseOrdersPurchaseOrderOverview}'s {@code purchase_orders}. */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetail extends Dto {
        public List<ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview> purchase_order_overview;
    }

    /**
     * A {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverview}'s {@code
     * purchase_payment_and_refund_overview}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetail extends Dto {
        public List<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePayment> purchase_payments;
        public List<ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefund> purchase_refunds;
    }

    /**
     * A {@link ReportsTransactionsPaymentAndRefundOverview}'s {@code payment_and_refund_overview}.
     */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetail extends Dto {
        public List<ReportsTransactionsPaymentAndRefundOverviewDetailPayment> payments;
        public List<ReportsTransactionsPaymentAndRefundOverviewDetailRefund> refunds;
    }

    /** One entry of a {@link ReportsAccountsAccountOverviewDetail}'s {@code account_overview}. */
    public static final class ReportsAccountsAccountOverviewDetailAccountOverview extends Dto {
        public String cancelled;
        public String date;
        public String deleted;
        public String net_gain;
        @WireName("new")
        public String newValue;
        public String reactivated;
    }

    /** One entry of a {@link ReportsInvoicesInvoiceOverviewDetail}'s {@code invoice_overview}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverview extends Dto {
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewAmended amended;
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewCancelled cancelled;
        public String date;
        public String invoice_total;
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewNetGain net_gain;
        @WireName("new")
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewNew newValue;
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewReactivated reactivated;
        public ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewVoided voided;
    }

    /** One entry of a {@link ReportsOrdersOrderOverviewDetail}'s {@code order_overview}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverview extends Dto {
        public ReportsOrdersOrderOverviewDetailOrderOverviewCancelled cancelled;
        public ReportsOrdersOrderOverviewDetailOrderOverviewChanged changed;
        public String date;
        public ReportsOrdersOrderOverviewDetailOrderOverviewDeleted deleted;
        public ReportsOrdersOrderOverviewDetailOrderOverviewNetGain net_gain;
        @WireName("new")
        public ReportsOrdersOrderOverviewDetailOrderOverviewNew newValue;
        public String order_total;
        public ReportsOrdersOrderOverviewDetailOrderOverviewReactivated reactivated;
    }

    /**
     * One entry of a {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetail}'s {@code
     * purchase_invoice_overview}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview extends Dto {
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewAmended amended;
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewCancelled cancelled;
        public String date;
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewNetGain net_gain;
        @WireName("new")
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewNew newValue;
        public String purchase_invoice_total;
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewReactivated reactivated;
        public ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewVoided voided;
    }

    /**
     * One entry of a {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetail}'s {@code
     * purchase_order_overview}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview extends Dto {
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewCancelled cancelled;
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewChanged changed;
        public String date;
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewDeleted deleted;
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewNetPurchases net_purchases;
        @WireName("new")
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewNew newValue;
        public String purchase_order_total;
        public ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewReactivated reactivated;
    }

    /**
     * One entry of a {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetail}'s
     * {@code purchase_payments}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePayment extends Dto {
        public String date;
        public ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePaymentDeleted deleted;
        @WireName("new")
        public ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePaymentNew newValue;
        public String purchase_payment_applied;
    }

    /**
     * One entry of a {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetail}'s
     * {@code purchase_refunds}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefund extends Dto {
        public String date;
        public ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefundDeleted deleted;
        @WireName("new")
        public ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefundNew newValue;
    }

    /**
     * One entry of a {@link ReportsTransactionsPaymentAndRefundOverviewDetail}'s {@code payments}.
     */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailPayment extends Dto {
        public String date;
        public ReportsTransactionsPaymentAndRefundOverviewDetailPaymentDeleted deleted;
        @WireName("new")
        public ReportsTransactionsPaymentAndRefundOverviewDetailPaymentNew newValue;
        public String payment_applied;
    }

    /**
     * One entry of a {@link ReportsTransactionsPaymentAndRefundOverviewDetail}'s {@code refunds}.
     */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailRefund extends Dto {
        public String date;
        public ReportsTransactionsPaymentAndRefundOverviewDetailRefundDeleted deleted;
        @WireName("new")
        public ReportsTransactionsPaymentAndRefundOverviewDetailRefundNew newValue;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code new}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewNew extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code cancelled}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewCancelled extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code reactivated}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewReactivated extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code voided}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewVoided extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code amended}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewAmended extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsInvoicesInvoiceOverviewDetailInvoiceOverview}'s {@code net_gain}. */
    public static final class ReportsInvoicesInvoiceOverviewDetailInvoiceOverviewNetGain extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code new}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewNew extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code cancelled}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewCancelled extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code reactivated}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewReactivated extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code changed}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewChanged extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code deleted}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewDeleted extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsOrdersOrderOverviewDetailOrderOverview}'s {@code net_gain}. */
    public static final class ReportsOrdersOrderOverviewDetailOrderOverviewNetGain extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code new}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewNew extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code cancelled}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewCancelled extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code reactivated}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewReactivated extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code voided}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewVoided extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code amended}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewAmended extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverview}'s
     * {@code net_gain}.
     */
    public static final class ReportsPurchaseInvoicesPurchaseInvoiceOverviewDetailPurchaseInvoiceOverviewNetGain extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * new}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewNew extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * cancelled}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewCancelled extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * reactivated}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewReactivated extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * changed}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewChanged extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * deleted}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewDeleted extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverview}'s {@code
     * net_purchases}.
     */
    public static final class ReportsPurchaseOrdersPurchaseOrderOverviewDetailPurchaseOrderOverviewNetPurchases extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePayment}'s
     * {@code new}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePaymentNew extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePayment}'s
     * {@code deleted}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchasePaymentDeleted extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefund}'s
     * {@code new}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefundNew extends Dto {
        public String amount;
        public String count;
    }

    /**
     * A {@link ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefund}'s
     * {@code deleted}.
     */
    public static final class ReportsPurchaseTransactionsPurchasePaymentAndRefundOverviewDetailPurchaseRefundDeleted extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsTransactionsPaymentAndRefundOverviewDetailPayment}'s {@code new}. */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailPaymentNew extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsTransactionsPaymentAndRefundOverviewDetailPayment}'s {@code deleted}. */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailPaymentDeleted extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsTransactionsPaymentAndRefundOverviewDetailRefund}'s {@code new}. */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailRefundNew extends Dto {
        public String amount;
        public String count;
    }

    /** A {@link ReportsTransactionsPaymentAndRefundOverviewDetailRefund}'s {@code deleted}. */
    public static final class ReportsTransactionsPaymentAndRefundOverviewDetailRefundDeleted extends Dto {
        public String amount;
        public String count;
    }

    private ReportsDto() {
    }
}
