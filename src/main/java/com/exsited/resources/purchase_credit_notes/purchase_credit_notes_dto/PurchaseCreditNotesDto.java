package com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the purchase credit notes bodies. */
public final class PurchaseCreditNotesDto {

    /** The purchase credit note body. */
    public static final class PurchaseCreditNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_notes");

        public PurchaseCreditNoteAccount account;
        public String amount;
        public String code;
        public PurchaseCreditNoteCreatedBy created_by;
        public String created_on;
        public PurchaseCreditNoteCurrency currency;
        public List<Object> custom_attributes;
        public String date;
        public String href;
        public String id;
        public String initial_amount;
        public String issue_date;
        public PurchaseCreditNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String payment_id;
        public List<PurchaseCreditNotePurchaseCreditNoteRow> purchase_credit_note_rows;
        public PurchaseCreditNotePurchasePayment purchase_payment;
        public String refund_amount;
        public Boolean refundable;
        public String remaining_amount;
        public String remaining_balance;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A page of PurchaseCreditNote records. */
    public static final class PurchaseCreditNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_notes");

        public List<PurchaseCreditNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase credit note read body. */
    public static final class PurchaseCreditNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}",
                "PATCH /api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_credit_note";
        }

        public PurchaseCreditNoteReadAccount account;
        public String code;
        public PurchaseCreditNoteReadCreatedBy created_by;
        public String created_on;
        public PurchaseCreditNoteReadCurrency currency;
        public String href;
        public String id;
        public String initial_amount;
        public String issue_date;
        public PurchaseCreditNoteReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public List<PurchaseCreditNoteReadPurchaseCreditNoteRow> purchase_credit_note_rows;
        public PurchaseCreditNoteReadPurchasePayment purchase_payment;
        public String refund_amount;
        public Boolean refundable;
        public String remaining_amount;
        public Object status;
        public Long version;
    }

    /** A {@link PurchaseCreditNoteRead}'s {@code account}. */
    public static final class PurchaseCreditNoteReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteRead}'s {@code currency}. */
    public static final class PurchaseCreditNoteReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteRead}'s {@code purchase_payment}. */
    public static final class PurchaseCreditNoteReadPurchasePayment extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseCreditNoteRead}'s {@code purchase_credit_note_rows}. */
    public static final class PurchaseCreditNoteReadPurchaseCreditNoteRow extends Dto {
        public String credit_note_from_blank_method;
        public String credit_note_from_credit_note;
        public String credit_note_from_payment_method;
        public String credit_note_total;
        public PurchaseCreditNoteReadPurchaseCreditNoteRowPaymentMethod payment_method;
        public PurchaseCreditNoteReadPurchaseCreditNoteRowPaymentProcessor payment_processor;
        public String refund_from_blank_method;
        public String refund_from_credit_note;
        public String refund_from_payment_method;
        public String refund_total;
    }

    /** A {@link PurchaseCreditNoteRead}'s {@code created_by}. */
    public static final class PurchaseCreditNoteReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteRead}'s {@code last_updated_by}. */
    public static final class PurchaseCreditNoteReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNote}'s {@code account}. */
    public static final class PurchaseCreditNoteAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNote}'s {@code currency}. */
    public static final class PurchaseCreditNoteCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNote}'s {@code purchase_payment}. */
    public static final class PurchaseCreditNotePurchasePayment extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseCreditNote}'s {@code purchase_credit_note_rows}. */
    public static final class PurchaseCreditNotePurchaseCreditNoteRow extends Dto {
        public String credit_note_from_blank_method;
        public String credit_note_from_credit_note;
        public String credit_note_from_payment_method;
        public String credit_note_total;
        public PurchaseCreditNotePurchaseCreditNoteRowPaymentMethod payment_method;
        public PurchaseCreditNotePurchaseCreditNoteRowPaymentProcessor payment_processor;
        public String refund_from_blank_method;
        public String refund_from_credit_note;
        public String refund_from_payment_method;
        public String refund_total;
    }

    /** A {@link PurchaseCreditNote}'s {@code created_by}. */
    public static final class PurchaseCreditNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNote}'s {@code last_updated_by}. */
    public static final class PurchaseCreditNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteReadPurchaseCreditNoteRow}'s {@code payment_method}. */
    public static final class PurchaseCreditNoteReadPurchaseCreditNoteRowPaymentMethod extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteReadPurchaseCreditNoteRow}'s {@code payment_processor}. */
    public static final class PurchaseCreditNoteReadPurchaseCreditNoteRowPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNotePurchaseCreditNoteRow}'s {@code payment_method}. */
    public static final class PurchaseCreditNotePurchaseCreditNoteRowPaymentMethod extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNotePurchaseCreditNoteRow}'s {@code payment_processor}. */
    public static final class PurchaseCreditNotePurchaseCreditNoteRowPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private PurchaseCreditNotesDto() {
    }

    /**
     * What {@code createPurchaseCreditNote} answers, under {@code purchase_credit_note}: a shorter
     * body than the reads, and {@code status} an object where they send a string.
     */
    public static final class PurchaseCreditNoteCreated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_credit_notes");

        @Override
        protected String envelopeKey() {
            return "purchase_credit_note";
        }

        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> activity_logs;
        public String code;
        public String href;
        public String id;
        public String initial_amount;
        public String issue_date;
        public String object;
        public String remaining_amount;
        public PurchaseCreditNoteCreatedStatus status;
        public Long version;
    }

    /** A {@link PurchaseCreditNoteCreated}'s {@code status}. */
    public static final class PurchaseCreditNoteCreatedStatus extends Dto {
        public String key;
        public String value;
    }
}
