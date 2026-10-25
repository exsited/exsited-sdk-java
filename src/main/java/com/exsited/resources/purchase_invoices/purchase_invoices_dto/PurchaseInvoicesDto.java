package com.exsited.resources.purchase_invoices.purchase_invoices_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the purchase invoices bodies. */
public final class PurchaseInvoicesDto {

    /** The purchase invoice body. */
    public static final class PurchaseInvoice extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices",
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/information",
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public PurchaseInvoiceAccount account;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseInvoiceCreatedBy created_by;
        public String created_on;
        public PurchaseInvoiceCurrency currency;
        /** never populated in 2 measured records. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String issue_date;
        public PurchaseInvoiceLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** 1 measured, null on 1. */
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public PurchaseInvoicePurchaseOrder purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** A page of PurchaseInvoice records. */
    public static final class PurchaseInvoiceList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices",
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        public List<PurchaseInvoice> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase invoice purchase payment body. */
    public static final class PurchaseInvoicePurchasePayment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments");

        public PurchaseInvoicePurchasePaymentAccount account;
        public String code;
        public PurchaseInvoicePurchasePaymentCreatedBy created_by;
        public String created_on;
        public List<PurchaseInvoicePurchasePaymentCreditNoteApplication> credit_note_applications;
        public String credit_note_total;
        public PurchaseInvoicePurchasePaymentCurrency currency;
        public String href;
        public String id;
        public PurchaseInvoicePurchasePaymentLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchaseInvoicePurchasePaymentPaymentMethodApplication> payment_method_applications;
        public String payment_note;
        /** never populated in 2 measured records. */
        public Object payment_reference;
        public List<PurchaseInvoicePurchasePaymentPurchaseInvoiceApplication> purchase_invoice_applications;
        public String status;
        public Long version;
    }

    /** A page of PurchaseInvoicePurchasePayment records. */
    public static final class PurchaseInvoicePurchasePaymentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments");

        public List<PurchaseInvoicePurchasePayment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase invoice note body. */
    public static final class PurchaseInvoiceNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes");

        public List<PurchaseInvoiceNoteAttachment> attachments;
        public String content;
        public PurchaseInvoiceNoteCreatedBy created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object group;
        public String href;
        public String id;
        public PurchaseInvoiceNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public PurchaseInvoiceNoteDetail purchase_invoice;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PurchaseInvoiceNoteWatcher> watchers;
    }

    /** A page of PurchaseInvoiceNote records. */
    public static final class PurchaseInvoiceNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes");

        public List<PurchaseInvoiceNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase invoices note body. */
    public static final class PurchaseInvoicesNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/notes");

        public List<Object> attachments;
        public String content;
        public PurchaseInvoicesNoteCreatedBy created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object group;
        public String href;
        public String id;
        public PurchaseInvoicesNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PurchaseInvoicesNoteWatcher> watchers;
    }

    /** A page of PurchaseInvoicesNote records. */
    public static final class PurchaseInvoicesNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/notes");

        public List<PurchaseInvoicesNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase invoice read body. */
    public static final class PurchaseInvoiceRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}",
                "POST /api/{api_version}/purchase_invoices",
                "POST /api/{api_version}/purchase_invoices/{purchase_invoice_id}/amend",
                "POST /api/{api_version}/purchase_invoices/{purchase_invoice_id}/reactivate",
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public PurchaseInvoiceReadAccount account;
        public List<Object> activity_logs;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseInvoiceReadCreatedBy created_by;
        public String created_on;
        public PurchaseInvoiceReadCurrency currency;
        public List<PurchaseInvoiceReadCustomAttribute> custom_attributes;
        /** never populated in 1 measured record. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String issue_date;
        public PurchaseInvoiceReadKpis kpis;
        public PurchaseInvoiceReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<PurchaseInvoiceReadLine> lines;
        public String object;
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public PurchaseInvoiceReadPurchaseOrder purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** The account purchase invoices body. */
    public static final class AccountPurchaseInvoices extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/purchase_invoices",
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_credit_note_applications");

        public List<AccountPurchaseInvoicesData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase invoice information body. */
    public static final class PurchaseInvoiceInformation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/purchase_invoices/{purchase_invoice_id}/information");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public PurchaseInvoiceInformationAccount account;
        public List<Object> activity_logs;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseInvoiceInformationCreatedBy created_by;
        public String created_on;
        public PurchaseInvoiceInformationCurrency currency;
        public List<Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String invoice_note;
        public String issue_date;
        public PurchaseInvoiceInformationKpis kpis;
        public PurchaseInvoiceInformationLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<PurchaseInvoiceInformationLine> lines;
        public String object;
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        /** Null in every record measured, so untyped. */
        public Object purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** The purchase invoice cancel body. */
    public static final class PurchaseInvoiceCancel extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_invoices/{purchase_invoice_id}/cancel");

        public List<Object> activity_logs;
        public String event_uuid;
        public PurchaseInvoiceCancelDetail purchase_invoice;
    }

    /** The purchase invoice notes body. */
    public static final class PurchaseInvoiceNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes");

        @Override
        protected String envelopeKey() {
            return "watcherList";
        }

        public List<Object> external_watchers;
        public List<Object> internal_watchers;
        public PurchaseInvoiceNotesNote note;
    }

    /** The purchase invoice lines body. */
    public static final class PurchaseInvoiceLines extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/lines");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public List<PurchaseInvoiceLinesRow> lines;
        public String object;
    }

    /** The purchase invoice line body. */
    public static final class PurchaseInvoiceLine extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/lines/{purchase_invoice_line_uuid}");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public PurchaseInvoiceLineDetail line;
        public String object;
    }

    /** The purchase invoice note read body. */
    public static final class PurchaseInvoiceNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}",
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public List<PurchaseInvoiceNoteReadData> data;
        public PurchaseInvoiceNoteReadDetail note;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_purchase_invoice_note_file} answers, under {@code file}. */
    public static final class PurchaseInvoiceNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files/{file_id}");

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

    /** What {@code update_purchase_invoice_note} answers, under {@code note}. */
    public static final class PurchaseInvoiceNoteUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<Object> attachments;
        public String content;
        public PurchaseInvoiceNoteUpdatedCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public PurchaseInvoiceNoteUpdatedLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public PurchaseInvoiceNoteUpdatedDetail purchase_invoice;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<Object> watchers;
    }

    /** One entry of a {@link AccountPurchaseInvoices}'s {@code data}. */
    public static final class AccountPurchaseInvoicesData extends Dto {
        public AccountPurchaseInvoicesDataDetail account;
        public String alternate_due_date;
        public String alternate_issue_date;
        public String amount;
        public AccountPurchaseInvoicesDataCreatedBy created_by;
        public String created_on;
        public AccountPurchaseInvoicesDataCurrency currency;
        /** Null in every record measured, so untyped. */
        public Object custom_form;
        public String due_date;
        public String href;
        public String id;
        public String issue_date;
        public AccountPurchaseInvoicesDataLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public AccountPurchaseInvoicesDataPurchaseCreditNote purchase_credit_note;
        public AccountPurchaseInvoicesDataPurchaseOrder purchase_order;
        public AccountPurchaseInvoicesDataPurchasePayment purchase_payment;
        public String remaining_balance;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code currency}. */
    public static final class PurchaseInvoiceReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code account}. */
    public static final class PurchaseInvoiceReadAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code purchase_order}. */
    public static final class PurchaseInvoiceReadPurchaseOrder extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code created_by}. */
    public static final class PurchaseInvoiceReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceRead}'s {@code custom_attributes}. */
    public static final class PurchaseInvoiceReadCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** One entry of a {@link PurchaseInvoiceRead}'s {@code lines}. */
    public static final class PurchaseInvoiceReadLine extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseInvoiceReadLineItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseInvoiceRead}'s {@code kpis}. */
    public static final class PurchaseInvoiceReadKpis extends Dto {
        public String credit_applied;
        public String credit_issued;
        /** Null in every record measured, so untyped. */
        public Object deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_amended_on;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_payment_date;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String outstanding;
        public String overdue;
        public String payment_applied;
        /** Null in every record measured, so untyped. */
        public Object voided_on;
    }

    /** A {@link PurchaseInvoice}'s {@code currency}. */
    public static final class PurchaseInvoiceCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoice}'s {@code account}. */
    public static final class PurchaseInvoiceAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoice}'s {@code purchase_order}. */
    public static final class PurchaseInvoicePurchaseOrder extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseInvoice}'s {@code created_by}. */
    public static final class PurchaseInvoiceCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoice}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceLines}'s {@code lines}. */
    public static final class PurchaseInvoiceLinesRow extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseInvoiceLinesRowItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseInvoiceLine}'s {@code line}. */
    public static final class PurchaseInvoiceLineDetail extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseInvoiceLineDetailItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseInvoiceNoteRead}'s {@code note}. */
    public static final class PurchaseInvoiceNoteReadDetail extends Dto {
        public List<PurchaseInvoiceNoteReadDetailAttachment> attachments;
        public String content;
        public PurchaseInvoiceNoteReadDetailCreatedBy created_by;
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
        public PurchaseInvoiceNoteReadDetailPurchaseInvoice purchase_invoice;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchaseInvoiceNoteReadDetailWatcher> watchers;
    }

    /** One entry of a {@link PurchaseInvoiceNoteRead}'s {@code data}. */
    public static final class PurchaseInvoiceNoteReadData extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link PurchaseInvoiceInformation}'s {@code currency}. */
    public static final class PurchaseInvoiceInformationCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceInformation}'s {@code account}. */
    public static final class PurchaseInvoiceInformationAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceInformation}'s {@code created_by}. */
    public static final class PurchaseInvoiceInformationCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceInformation}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceInformationLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceInformation}'s {@code lines}. */
    public static final class PurchaseInvoiceInformationLine extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseInvoiceInformationLineItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseInvoiceInformation}'s {@code kpis}. */
    public static final class PurchaseInvoiceInformationKpis extends Dto {
        public String credit_applied;
        public String credit_issued;
        /** Null in every record measured, so untyped. */
        public Object deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_amended_on;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_payment_date;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String outstanding;
        public String overdue;
        public String payment_applied;
        /** Null in every record measured, so untyped. */
        public Object voided_on;
    }

    /** A {@link PurchaseInvoiceCancel}'s {@code purchase_invoice}. */
    public static final class PurchaseInvoiceCancelDetail extends Dto {
        public PurchaseInvoiceCancelDetailAccount account;
        public List<Object> activity_logs;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseInvoiceCancelDetailCreatedBy created_by;
        public String created_on;
        public PurchaseInvoiceCancelDetailCurrency currency;
        public List<Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String issue_date;
        public PurchaseInvoiceCancelDetailKpis kpis;
        public PurchaseInvoiceCancelDetailLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<PurchaseInvoiceCancelDetailLine> lines;
        public String object;
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        /** Null in every record measured, so untyped. */
        public Object purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** A {@link PurchaseInvoiceNotes}'s {@code note}. */
    public static final class PurchaseInvoiceNotesNote extends Dto {
        public List<PurchaseInvoiceNotesNoteAttachment> attachments;
        public String content;
        public PurchaseInvoiceNotesNoteCreatedBy created_by;
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
        public PurchaseInvoiceNotesNoteDetail purchase_invoice;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchaseInvoiceNotesNoteWatcher> watchers;
    }

    /** A {@link PurchaseInvoiceNoteUpdated}'s {@code purchase_invoice}. */
    public static final class PurchaseInvoiceNoteUpdatedDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseInvoiceNoteUpdated}'s {@code created_by}. */
    public static final class PurchaseInvoiceNoteUpdatedCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceNoteUpdated}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceNoteUpdatedLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code currency}. */
    public static final class AccountPurchaseInvoicesDataCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code account}. */
    public static final class AccountPurchaseInvoicesDataDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code purchase_order}. */
    public static final class AccountPurchaseInvoicesDataPurchaseOrder extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code created_by}. */
    public static final class AccountPurchaseInvoicesDataCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code last_updated_by}. */
    public static final class AccountPurchaseInvoicesDataLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code purchase_credit_note}. */
    public static final class AccountPurchaseInvoicesDataPurchaseCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseInvoicesData}'s {@code purchase_payment}. */
    public static final class AccountPurchaseInvoicesDataPurchasePayment extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoicesNote}'s {@code watchers}. */
    public static final class PurchaseInvoicesNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseInvoicesNote}'s {@code created_by}. */
    public static final class PurchaseInvoicesNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoicesNote}'s {@code last_updated_by}. */
    public static final class PurchaseInvoicesNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceReadLine}'s {@code item}. */
    public static final class PurchaseInvoiceReadLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceLinesRow}'s {@code item}. */
    public static final class PurchaseInvoiceLinesRowItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceLineDetail}'s {@code item}. */
    public static final class PurchaseInvoiceLineDetailItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoicePurchasePayment}'s {@code account}. */
    public static final class PurchaseInvoicePurchasePaymentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoicePurchasePayment}'s {@code currency}. */
    public static final class PurchaseInvoicePurchasePaymentCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * One entry of a {@link PurchaseInvoicePurchasePayment}'s {@code payment_method_applications}.
     */
    public static final class PurchaseInvoicePurchasePaymentPaymentMethodApplication extends Dto {
        public String amount;
        public PurchaseInvoicePurchasePaymentPaymentMethodApplicationDetail payment_method;
        public PurchaseInvoicePurchasePaymentPaymentMethodApplicationPaymentProcessor payment_processor;
        public String reference;
    }

    /** One entry of a {@link PurchaseInvoicePurchasePayment}'s {@code credit_note_applications}. */
    public static final class PurchaseInvoicePurchasePaymentCreditNoteApplication extends Dto {
        public String amount;
        public PurchaseInvoicePurchasePaymentCreditNoteApplicationPurchaseCreditNote purchase_credit_note;
    }

    /**
     * One entry of a {@link PurchaseInvoicePurchasePayment}'s {@code
     * purchase_invoice_applications}.
     */
    public static final class PurchaseInvoicePurchasePaymentPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchaseInvoicePurchasePaymentPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchaseInvoicePurchasePayment}'s {@code created_by}. */
    public static final class PurchaseInvoicePurchasePaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoicePurchasePayment}'s {@code last_updated_by}. */
    public static final class PurchaseInvoicePurchasePaymentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceNote}'s {@code purchase_invoice}. */
    public static final class PurchaseInvoiceNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceNote}'s {@code attachments}. */
    public static final class PurchaseInvoiceNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseInvoiceNote}'s {@code watchers}. */
    public static final class PurchaseInvoiceNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseInvoiceNote}'s {@code created_by}. */
    public static final class PurchaseInvoiceNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceNote}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceNoteReadDetail}'s {@code purchase_invoice}. */
    public static final class PurchaseInvoiceNoteReadDetailPurchaseInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceNoteReadDetail}'s {@code attachments}. */
    public static final class PurchaseInvoiceNoteReadDetailAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseInvoiceNoteReadDetail}'s {@code watchers}. */
    public static final class PurchaseInvoiceNoteReadDetailWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseInvoiceNoteReadDetail}'s {@code created_by}. */
    public static final class PurchaseInvoiceNoteReadDetailCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceInformationLine}'s {@code item}. */
    public static final class PurchaseInvoiceInformationLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceCancelDetail}'s {@code currency}. */
    public static final class PurchaseInvoiceCancelDetailCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceCancelDetail}'s {@code account}. */
    public static final class PurchaseInvoiceCancelDetailAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceCancelDetail}'s {@code created_by}. */
    public static final class PurchaseInvoiceCancelDetailCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseInvoiceCancelDetail}'s {@code last_updated_by}. */
    public static final class PurchaseInvoiceCancelDetailLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceCancelDetail}'s {@code lines}. */
    public static final class PurchaseInvoiceCancelDetailLine extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseInvoiceCancelDetailLineItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseInvoiceCancelDetail}'s {@code kpis}. */
    public static final class PurchaseInvoiceCancelDetailKpis extends Dto {
        public String credit_applied;
        public String credit_issued;
        /** Null in every record measured, so untyped. */
        public Object deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_amended_on;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_payment_date;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String outstanding;
        public String overdue;
        public String payment_applied;
        /** Null in every record measured, so untyped. */
        public Object voided_on;
    }

    /** A {@link PurchaseInvoiceNotesNote}'s {@code purchase_invoice}. */
    public static final class PurchaseInvoiceNotesNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseInvoiceNotesNote}'s {@code attachments}. */
    public static final class PurchaseInvoiceNotesNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseInvoiceNotesNote}'s {@code watchers}. */
    public static final class PurchaseInvoiceNotesNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseInvoiceNotesNote}'s {@code created_by}. */
    public static final class PurchaseInvoiceNotesNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /**
     * A {@link PurchaseInvoicePurchasePaymentPaymentMethodApplication}'s {@code payment_method}.
     */
    public static final class PurchaseInvoicePurchasePaymentPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * A {@link PurchaseInvoicePurchasePaymentPaymentMethodApplication}'s {@code payment_processor}.
     */
    public static final class PurchaseInvoicePurchasePaymentPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * A {@link PurchaseInvoicePurchasePaymentCreditNoteApplication}'s {@code purchase_credit_note}.
     */
    public static final class PurchaseInvoicePurchasePaymentCreditNoteApplicationPurchaseCreditNote extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /**
     * A {@link PurchaseInvoicePurchasePaymentPurchaseInvoiceApplication}'s {@code
     * purchase_invoice}.
     */
    public static final class PurchaseInvoicePurchasePaymentPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseInvoiceCancelDetailLine}'s {@code item}. */
    public static final class PurchaseInvoiceCancelDetailLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private PurchaseInvoicesDto() {
    }
}
