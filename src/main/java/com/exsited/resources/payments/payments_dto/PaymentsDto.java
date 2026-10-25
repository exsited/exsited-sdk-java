package com.exsited.resources.payments.payments_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the payments bodies. */
public final class PaymentsDto {

    /** The payment body. */
    public static final class Payment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/payments",
                "GET /api/{api_version}/orders/{order_id}/payments",
                "GET /api/{api_version}/payments");

        public PaymentAccount account;
        public PaymentCreatedBy created_by;
        public String created_on;
        public List<PaymentCreditApplied> credit_applied;
        public List<PaymentGiftCertificateApplied> gift_certificate_applied;
        public String id;
        public List<PaymentInvoice> invoices;
        /** 1 measured, null on 1. */
        public PaymentLastUpdatedBy last_updated_by;
        /** 1 measured, null on 1. */
        public String last_updated_on;
        public String object;
        public String origin;
        public List<PaymentPaymentApplied> payment_applied;
        public String payment_note;
        public String reconcile_status;
        public String status;
        public Long version;
    }

    /** A page of Payment records. */
    public static final class PaymentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/payments",
                "GET /api/{api_version}/orders/{order_id}/payments",
                "GET /api/{api_version}/payments");

        public List<Payment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The payment note body. */
    public static final class PaymentNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/{payment_id}/notes");

        public List<PaymentNoteAttachment> attachments;
        public String content;
        public PaymentNoteCreatedBy created_by;
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
        public PaymentNoteDetail payment;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PaymentNoteWatcher> watchers;
    }

    /** A page of PaymentNote records. */
    public static final class PaymentNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/{payment_id}/notes");

        public List<PaymentNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The payments note body. */
    public static final class PaymentsNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/notes");

        public List<PaymentsNoteAttachment> attachments;
        public String content;
        public PaymentsNoteCreatedBy created_by;
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
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PaymentsNoteWatcher> watchers;
    }

    /** A page of PaymentsNote records. */
    public static final class PaymentsNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/notes");

        public List<PaymentsNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The payment read body. */
    public static final class PaymentRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/{payment_id}",
                "PATCH /api/{api_version}/payments/{payment_id}");

        @Override
        protected String envelopeKey() {
            return "payment";
        }

        public PaymentReadAccount account;
        public List<Object> activity_logs;
        public PaymentReadCreatedBy created_by;
        public String created_on;
        public List<PaymentReadCreditApplied> credit_applied;
        public List<PaymentReadCustomAttribute> custom_attributes;
        public List<PaymentReadGiftCertificateApplied> gift_certificate_applied;
        public String id;
        public List<PaymentReadInvoice> invoices;
        public PaymentReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public List<PaymentReadPaymentApplied> payment_applied;
        public String payment_note;
        public String reconcile_status;
        public String status;
        public Long version;
    }

    /** The account payments body. */
    public static final class AccountPayments extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/payments");

        public List<Payment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The payment note read body. */
    public static final class PaymentNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/{payment_id}/notes/{payment_note_id}");

        @Override
        protected String envelopeKey() {
            return "payment";
        }

        public PaymentNoteReadDetail note;
    }

    /** What {@code get_payment_note_file} answers, under {@code file}. */
    public static final class PaymentNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/payments/{payment_id}/notes/{payment_note_id}/files/{payment_file_id}");

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

    /** What {@code create_payment_note} answers, under {@code note}. */
    public static final class PaymentNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/payments/{payment_id}/notes");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<PaymentNotesAttachment> attachments;
        public String content;
        public PaymentNotesCreatedBy created_by;
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
        public PaymentNotesDetail payment;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PaymentNotesWatcher> watchers;
    }

    /** A {@link PaymentRead}'s {@code account}. */
    public static final class PaymentReadAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PaymentRead}'s {@code payment_applied}. */
    public static final class PaymentReadPaymentApplied extends Dto {
        public String amount;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String method;
        public String processor;
        public String reference;
    }

    /** One entry of a {@link PaymentRead}'s {@code credit_applied}. */
    public static final class PaymentReadCreditApplied extends Dto {
        public String amount;
        public String code;
    }

    /** One entry of a {@link PaymentRead}'s {@code gift_certificate_applied}. */
    public static final class PaymentReadGiftCertificateApplied extends Dto {
        public String amount;
        public String code;
    }

    /** One entry of a {@link PaymentRead}'s {@code invoices}. */
    public static final class PaymentReadInvoice extends Dto {
        public String applied;
        public String due_date;
        public String id;
        public String issue_date;
        public String outstanding;
        public String total;
    }

    /** One entry of a {@link PaymentRead}'s {@code custom_attributes}. */
    public static final class PaymentReadCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link PaymentRead}'s {@code created_by}. */
    public static final class PaymentReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PaymentRead}'s {@code last_updated_by}. */
    public static final class PaymentReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PaymentNoteRead}'s {@code note}. */
    public static final class PaymentNoteReadDetail extends Dto {
        public List<PaymentNoteReadDetailAttachment> attachments;
        public String content;
        public PaymentNoteReadDetailCreatedBy created_by;
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
        public PaymentNoteReadDetailPayment payment;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PaymentNoteReadDetailWatcher> watchers;
    }

    /** A {@link PaymentNotes}'s {@code payment}. */
    public static final class PaymentNotesDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PaymentNotes}'s {@code attachments}. */
    public static final class PaymentNotesAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PaymentNotes}'s {@code watchers}. */
    public static final class PaymentNotesWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PaymentNotes}'s {@code created_by}. */
    public static final class PaymentNotesCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Payment}'s {@code account}. */
    public static final class PaymentAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link Payment}'s {@code payment_applied}. */
    public static final class PaymentPaymentApplied extends Dto {
        public String amount;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String method;
        public String processor;
        public String reference;
    }

    /** One entry of a {@link Payment}'s {@code credit_applied}. */
    public static final class PaymentCreditApplied extends Dto {
        public String amount;
        public String code;
    }

    /** One entry of a {@link Payment}'s {@code gift_certificate_applied}. */
    public static final class PaymentGiftCertificateApplied extends Dto {
        public String amount;
        public String code;
    }

    /** One entry of a {@link Payment}'s {@code invoices}. */
    public static final class PaymentInvoice extends Dto {
        public String applied;
        public String due_date;
        public String id;
        public String issue_date;
        public String outstanding;
        public String total;
    }

    /** A {@link Payment}'s {@code created_by}. */
    public static final class PaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Payment}'s {@code last_updated_by}. */
    public static final class PaymentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PaymentsNote}'s {@code attachments}. */
    public static final class PaymentsNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PaymentsNote}'s {@code watchers}. */
    public static final class PaymentsNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PaymentsNote}'s {@code created_by}. */
    public static final class PaymentsNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PaymentNote}'s {@code payment}. */
    public static final class PaymentNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PaymentNote}'s {@code attachments}. */
    public static final class PaymentNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PaymentNote}'s {@code watchers}. */
    public static final class PaymentNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PaymentNote}'s {@code created_by}. */
    public static final class PaymentNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PaymentNoteReadDetail}'s {@code payment}. */
    public static final class PaymentNoteReadDetailPayment extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PaymentNoteReadDetail}'s {@code attachments}. */
    public static final class PaymentNoteReadDetailAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PaymentNoteReadDetail}'s {@code watchers}. */
    public static final class PaymentNoteReadDetailWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PaymentNoteReadDetail}'s {@code created_by}. */
    public static final class PaymentNoteReadDetailCreatedBy extends Dto {
        public String name;
        public String object;
    }

    private PaymentsDto() {
    }
}
