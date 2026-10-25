package com.exsited.resources.purchase_payments.purchase_payments_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the purchase payments bodies. */
public final class PurchasePaymentsDto {

    /** The purchase payment body. */
    public static final class PurchasePayment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments");

        public PurchasePaymentAccount account;
        public String code;
        public PurchasePaymentCreatedBy created_by;
        public String created_on;
        public List<PurchasePaymentCreditNoteApplication> credit_note_applications;
        public String credit_note_total;
        public PurchasePaymentCurrency currency;
        public List<Object> custom_attributes;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public PurchasePaymentLastUpdatedBy last_updated_by;
        /** 257 measured, null on 1. */
        public String last_updated_on;
        public String object;
        /** 256 measured, null on 2. */
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchasePaymentPaymentMethodApplication> payment_method_applications;
        /** 1 measured, null on 1. */
        public String payment_note;
        /** never populated in 2 measured records. */
        public Object payment_reference;
        public List<Object> purchase_credit_applied;
        public List<PurchasePaymentPurchaseInvoiceApplication> purchase_invoice_applications;
        public List<Object> purchase_invoices;
        public String purchase_order_id;
        public List<Object> purchase_payment_applied;
        public String purchase_payment_date;
        public String purchase_payment_note;
        public String status;
        public String total_applied;
        public String uuid;
        public Long version;
    }

    /** A page of PurchasePayment records. */
    public static final class PurchasePaymentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments");

        public List<PurchasePayment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase payment read body. */
    public static final class PurchasePaymentRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments",
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_payments",
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_payment";
        }

        public PurchasePaymentReadAccount account;
        public String code;
        public PurchasePaymentReadCreatedBy created_by;
        public String created_on;
        public List<PurchasePaymentReadCreditNoteApplication> credit_note_applications;
        public String credit_note_total;
        public PurchasePaymentReadCurrency currency;
        public String href;
        public String id;
        public PurchasePaymentReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchasePaymentReadPaymentMethodApplication> payment_method_applications;
        public String payment_note;
        /** never populated in 1 measured record. */
        public Object payment_reference;
        public List<PurchasePaymentReadPurchaseInvoiceApplication> purchase_invoice_applications;
        public String status;
        public Long version;
    }

    /** The purchase payment note body. */
    public static final class PurchasePaymentNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}/notes");

        public List<PurchasePaymentNoteAttachment> attachments;
        public String content;
        public PurchasePaymentNoteCreatedBy created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object group;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        /** never populated in 2 measured records. */
        public Object last_updated_on;
        public String object;
        public PurchasePaymentNoteDetail purchase_payment;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PurchasePaymentNoteWatcher> watchers;
    }

    /** A page of PurchasePaymentNote records. */
    public static final class PurchasePaymentNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}/notes");

        public List<PurchasePaymentNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase payment updated body. */
    public static final class PurchasePaymentUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/purchase_payments/{purchase_payment_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_payment";
        }

        public PurchasePaymentUpdatedAccount account;
        public List<Object> activity_logs;
        public String code;
        public PurchasePaymentUpdatedCreatedBy created_by;
        public String created_on;
        public List<Object> credit_note_applications;
        public String credit_note_total;
        public PurchasePaymentUpdatedCurrency currency;
        public List<Object> custom_attributes;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public PurchasePaymentUpdatedLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchasePaymentUpdatedPaymentMethodApplication> payment_method_applications;
        public String payment_note;
        /** Null in every record measured, so untyped. */
        public Object payment_reference;
        public List<Object> purchase_credit_applied;
        public List<PurchasePaymentUpdatedPurchaseInvoiceApplication> purchase_invoice_applications;
        public List<Object> purchase_invoices;
        public String purchase_order_id;
        public List<Object> purchase_payment_applied;
        public String purchase_payment_date;
        public String purchase_payment_note;
        public String status;
        public String total_applied;
        public String uuid;
        public Long version;
    }

    /** The purchase payments body. */
    public static final class PurchasePayments extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments",
                "POST /api/{api_version}/purchase_payments");

        @Override
        protected String envelopeKey() {
            return "purchase_payment";
        }

        public PurchasePaymentsAccount account;
        public List<PurchasePaymentsActivityLog> activity_logs;
        public String code;
        public PurchasePaymentsCreatedBy created_by;
        public String created_on;
        public List<PurchasePaymentsCreditNoteApplication> credit_note_applications;
        public String credit_note_total;
        public PurchasePaymentsCurrency currency;
        public List<Object> custom_attributes;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchasePaymentsPaymentMethodApplication> payment_method_applications;
        public String payment_note;
        /** Null in every record measured, so untyped. */
        public Object payment_reference;
        public List<Object> purchase_credit_applied;
        public List<PurchasePaymentsPurchaseInvoiceApplication> purchase_invoice_applications;
        public List<Object> purchase_invoices;
        public String purchase_order_id;
        public List<Object> purchase_payment_applied;
        public String purchase_payment_date;
        public String purchase_payment_note;
        public String status;
        public String total_applied;
        public String uuid;
        public Long version;
    }

    /** The account purchase payments body. */
    public static final class AccountPurchasePayments extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/purchase_payments");

        public List<PurchasePayment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase payment notes body. */
    public static final class PurchasePaymentNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_payments/{purchase_payment_id}/notes");

        @Override
        protected String envelopeKey() {
            return "files";
        }

        public String link;
        public String name;
        public PurchasePaymentNotesNote note;
        public String uuid;
        public String version;
    }

    /** The purchase payment note read body. */
    public static final class PurchasePaymentNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}",
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files");

        @Override
        protected String envelopeKey() {
            return "purchase_payment";
        }

        public List<PurchasePaymentNoteReadData> data;
        public PurchasePaymentNoteReadDetail note;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_purchase_payment_note_file} answers, under {@code file}. */
    public static final class PurchasePaymentNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files/{file_id}");

        @Override
        protected String envelopeKey() {
            return "file";
        }

        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link PurchasePaymentRead}'s {@code account}. */
    public static final class PurchasePaymentReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentRead}'s {@code currency}. */
    public static final class PurchasePaymentReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchasePaymentRead}'s {@code payment_method_applications}. */
    public static final class PurchasePaymentReadPaymentMethodApplication extends Dto {
        public String amount;
        public PurchasePaymentReadPaymentMethodApplicationDetail payment_method;
        public PurchasePaymentReadPaymentMethodApplicationPaymentProcessor payment_processor;
        public String reference;
    }

    /** One entry of a {@link PurchasePaymentRead}'s {@code credit_note_applications}. */
    public static final class PurchasePaymentReadCreditNoteApplication extends Dto {
        public String amount;
        public PurchasePaymentReadCreditNoteApplicationPurchaseCreditNote purchase_credit_note;
    }

    /** One entry of a {@link PurchasePaymentRead}'s {@code purchase_invoice_applications}. */
    public static final class PurchasePaymentReadPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchasePaymentReadPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchasePaymentRead}'s {@code created_by}. */
    public static final class PurchasePaymentReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentRead}'s {@code last_updated_by}. */
    public static final class PurchasePaymentReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentNoteRead}'s {@code note}. */
    public static final class PurchasePaymentNoteReadDetail extends Dto {
        public List<PurchasePaymentNoteReadDetailAttachment> attachments;
        public String content;
        public PurchasePaymentNoteReadDetailCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public PurchasePaymentNoteReadDetailPurchasePayment purchase_payment;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchasePaymentNoteReadDetailWatcher> watchers;
    }

    /** One entry of a {@link PurchasePaymentNoteRead}'s {@code data}. */
    public static final class PurchasePaymentNoteReadData extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link PurchasePayments}'s {@code account}. */
    public static final class PurchasePaymentsAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePayments}'s {@code currency}. */
    public static final class PurchasePaymentsCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchasePayments}'s {@code payment_method_applications}. */
    public static final class PurchasePaymentsPaymentMethodApplication extends Dto {
        public String amount;
        public PurchasePaymentsPaymentMethodApplicationDetail payment_method;
        public PurchasePaymentsPaymentMethodApplicationPaymentProcessor payment_processor;
        public String reference;
    }

    /** One entry of a {@link PurchasePayments}'s {@code credit_note_applications}. */
    public static final class PurchasePaymentsCreditNoteApplication extends Dto {
        public String amount;
        public PurchasePaymentsCreditNoteApplicationPurchaseCreditNote purchase_credit_note;
    }

    /** One entry of a {@link PurchasePayments}'s {@code purchase_invoice_applications}. */
    public static final class PurchasePaymentsPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchasePaymentsPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchasePayments}'s {@code created_by}. */
    public static final class PurchasePaymentsCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchasePayments}'s {@code activity_logs}. */
    public static final class PurchasePaymentsActivityLog extends Dto {
        public String activity;
    }

    /** A {@link PurchasePaymentUpdated}'s {@code account}. */
    public static final class PurchasePaymentUpdatedAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentUpdated}'s {@code currency}. */
    public static final class PurchasePaymentUpdatedCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchasePaymentUpdated}'s {@code payment_method_applications}. */
    public static final class PurchasePaymentUpdatedPaymentMethodApplication extends Dto {
        public String amount;
        public PurchasePaymentUpdatedPaymentMethodApplicationDetail payment_method;
        public PurchasePaymentUpdatedPaymentMethodApplicationPaymentProcessor payment_processor;
        /** Null in every record measured, so untyped. */
        public Object reference;
    }

    /** One entry of a {@link PurchasePaymentUpdated}'s {@code purchase_invoice_applications}. */
    public static final class PurchasePaymentUpdatedPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchasePaymentUpdatedPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchasePaymentUpdated}'s {@code created_by}. */
    public static final class PurchasePaymentUpdatedCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentUpdated}'s {@code last_updated_by}. */
    public static final class PurchasePaymentUpdatedLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentNotes}'s {@code note}. */
    public static final class PurchasePaymentNotesNote extends Dto {
        public List<PurchasePaymentNotesNoteAttachment> attachments;
        public String content;
        public PurchasePaymentNotesNoteCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public PurchasePaymentNotesNoteDetail purchase_payment;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchasePaymentNotesNoteWatcher> watchers;
    }

    /** A {@link PurchasePayment}'s {@code account}. */
    public static final class PurchasePaymentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePayment}'s {@code currency}. */
    public static final class PurchasePaymentCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchasePayment}'s {@code payment_method_applications}. */
    public static final class PurchasePaymentPaymentMethodApplication extends Dto {
        public String amount;
        public PurchasePaymentPaymentMethodApplicationDetail payment_method;
        public PurchasePaymentPaymentMethodApplicationPaymentProcessor payment_processor;
        public String reference;
    }

    /** One entry of a {@link PurchasePayment}'s {@code credit_note_applications}. */
    public static final class PurchasePaymentCreditNoteApplication extends Dto {
        public String amount;
        public PurchasePaymentCreditNoteApplicationPurchaseCreditNote purchase_credit_note;
    }

    /** One entry of a {@link PurchasePayment}'s {@code purchase_invoice_applications}. */
    public static final class PurchasePaymentPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchasePaymentPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchasePayment}'s {@code created_by}. */
    public static final class PurchasePaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePayment}'s {@code last_updated_by}. */
    public static final class PurchasePaymentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentReadPaymentMethodApplication}'s {@code payment_method}. */
    public static final class PurchasePaymentReadPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentReadPaymentMethodApplication}'s {@code payment_processor}. */
    public static final class PurchasePaymentReadPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentReadCreditNoteApplication}'s {@code purchase_credit_note}. */
    public static final class PurchasePaymentReadCreditNoteApplicationPurchaseCreditNote extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentReadPurchaseInvoiceApplication}'s {@code purchase_invoice}. */
    public static final class PurchasePaymentReadPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentNote}'s {@code purchase_payment}. */
    public static final class PurchasePaymentNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchasePaymentNote}'s {@code attachments}. */
    public static final class PurchasePaymentNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchasePaymentNote}'s {@code watchers}. */
    public static final class PurchasePaymentNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchasePaymentNote}'s {@code created_by}. */
    public static final class PurchasePaymentNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentNoteReadDetail}'s {@code purchase_payment}. */
    public static final class PurchasePaymentNoteReadDetailPurchasePayment extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchasePaymentNoteReadDetail}'s {@code attachments}. */
    public static final class PurchasePaymentNoteReadDetailAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchasePaymentNoteReadDetail}'s {@code watchers}. */
    public static final class PurchasePaymentNoteReadDetailWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchasePaymentNoteReadDetail}'s {@code created_by}. */
    public static final class PurchasePaymentNoteReadDetailCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentsPaymentMethodApplication}'s {@code payment_method}. */
    public static final class PurchasePaymentsPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentsPaymentMethodApplication}'s {@code payment_processor}. */
    public static final class PurchasePaymentsPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentsCreditNoteApplication}'s {@code purchase_credit_note}. */
    public static final class PurchasePaymentsCreditNoteApplicationPurchaseCreditNote extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentsPurchaseInvoiceApplication}'s {@code purchase_invoice}. */
    public static final class PurchasePaymentsPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentUpdatedPaymentMethodApplication}'s {@code payment_method}. */
    public static final class PurchasePaymentUpdatedPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentUpdatedPaymentMethodApplication}'s {@code payment_processor}. */
    public static final class PurchasePaymentUpdatedPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentUpdatedPurchaseInvoiceApplication}'s {@code purchase_invoice}. */
    public static final class PurchasePaymentUpdatedPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentNotesNote}'s {@code purchase_payment}. */
    public static final class PurchasePaymentNotesNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchasePaymentNotesNote}'s {@code attachments}. */
    public static final class PurchasePaymentNotesNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchasePaymentNotesNote}'s {@code watchers}. */
    public static final class PurchasePaymentNotesNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchasePaymentNotesNote}'s {@code created_by}. */
    public static final class PurchasePaymentNotesNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentPaymentMethodApplication}'s {@code payment_method}. */
    public static final class PurchasePaymentPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentPaymentMethodApplication}'s {@code payment_processor}. */
    public static final class PurchasePaymentPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchasePaymentCreditNoteApplication}'s {@code purchase_credit_note}. */
    public static final class PurchasePaymentCreditNoteApplicationPurchaseCreditNote extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentPurchaseInvoiceApplication}'s {@code purchase_invoice}. */
    public static final class PurchasePaymentPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    private PurchasePaymentsDto() {
    }

    /** What {@code updatePurchasePaymentNote} answers, under {@code note}. */
    public static final class PurchasePaymentNoteUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<PurchasePaymentNoteUpdatedAttachment> attachments;
        public String content;
        public PurchasePaymentNoteUpdatedUser created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public PurchasePaymentNoteUpdatedUser last_updated_by;
        public String last_updated_on;
        public String object;
        public PurchasePaymentNoteUpdatedPurchasePayment purchase_payment;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> watchers;
    }

    /** One entry of a {@link PurchasePaymentNoteUpdated}'s {@code attachments}. */
    public static final class PurchasePaymentNoteUpdatedAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link PurchasePaymentNoteUpdated}'s {@code purchase_payment}. */
    public static final class PurchasePaymentNoteUpdatedPurchasePayment extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchasePaymentNoteUpdated}'s {@code created_by} and {@code last_updated_by}. */
    public static final class PurchasePaymentNoteUpdatedUser extends Dto {
        public String name;
        public String object;
    }
}
