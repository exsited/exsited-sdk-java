package com.exsited.resources.credit_notes.credit_notes_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the credit notes bodies. */
public final class CreditNotesDto {

    /** The credit note body. */
    public static final class CreditNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes");

        public CreditNoteAccount account;
        public String account_id;
        public String amount;
        public String code;
        public CreditNoteCreatedBy created_by;
        public String created_on;
        public CreditNoteCurrency currency;
        public List<CreditNoteCustomAttribute> custom_attributes;
        public List<Object> custom_objects;
        public String date;
        public String href;
        public String id;
        public String initial_amount;
        public String invoice_id;
        public List<CreditNoteInvoice> invoices;
        public String issue_date;
        public CreditNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public CreditNotePayment payment;
        public String payment_id;
        public String refund_amount;
        public Boolean refundable;
        public String remaining_amount;
        public String remaining_balance;
        public String status;
        public String updated_by;
        public String updated_on;
        public String uuid;
        public Long version;
    }

    /** A page of CreditNote records. */
    public static final class CreditNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes",
                "GET /api/{api_version}/accounts/{account_id}/credit_notes");

        public List<CreditNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The credit note read body. */
    public static final class CreditNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes/{credit_note_id}",
                "PATCH /api/{api_version}/credit_notes/{credit_note_id}");

        @Override
        protected String envelopeKey() {
            return "credit_note";
        }

        public CreditNoteReadAccount account;
        public String code;
        public CreditNoteReadCreatedBy created_by;
        public String created_on;
        public CreditNoteReadCurrency currency;
        public List<CreditNoteReadCustomAttribute> custom_attributes;
        public String href;
        public String id;
        public String initial_amount;
        public List<CreditNoteReadInvoice> invoices;
        public String issue_date;
        public CreditNoteReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public CreditNoteReadPayment payment;
        public String refund_amount;
        public Boolean refundable;
        public String remaining_amount;
        public String status;
        public Long version;
    }

    /** A {@link CreditNoteRead}'s {@code account}. */
    public static final class CreditNoteReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteRead}'s {@code currency}. */
    public static final class CreditNoteReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteRead}'s {@code payment}. */
    public static final class CreditNoteReadPayment extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link CreditNoteRead}'s {@code invoices}. */
    public static final class CreditNoteReadInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link CreditNoteRead}'s {@code custom_attributes}. */
    public static final class CreditNoteReadCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public Object value;
    }

    /** A {@link CreditNoteRead}'s {@code created_by}. */
    public static final class CreditNoteReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link CreditNoteRead}'s {@code last_updated_by}. */
    public static final class CreditNoteReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link CreditNote}'s {@code account}. */
    public static final class CreditNoteAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNote}'s {@code currency}. */
    public static final class CreditNoteCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNote}'s {@code payment}. */
    public static final class CreditNotePayment extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link CreditNote}'s {@code invoices}. */
    public static final class CreditNoteInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link CreditNote}'s {@code custom_attributes}. */
    public static final class CreditNoteCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public Object value;
    }

    /** A {@link CreditNote}'s {@code created_by}. */
    public static final class CreditNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link CreditNote}'s {@code last_updated_by}. */
    public static final class CreditNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    private CreditNotesDto() {
    }

    /** A credit note's balance rows, under {@code credit_note}. */
    public static final class CreditNoteItems extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes/{credit_note_id}/items");

        @Override
        protected String envelopeKey() {
            return "credit_note";
        }

        public String href;
        public List<CreditNoteItem> items;
        public String object;
    }

    /**
     * One entry of a {@link CreditNoteItems}'s {@code items}: what was credited and refunded, by
     * source, each amount a string.
     */
    public static final class CreditNoteItem extends Dto {
        public String credit_note_from_blank_method;
        public String credit_note_from_credit_note;
        public String credit_note_from_gift_certificate;
        public String credit_note_from_invoice_credit;
        public String credit_note_from_payment_method;
        public String credit_note_total;
        /** Null in every record measured, so untyped. */
        public Object invoice;
        public String object;
        public String refund_from_blank_method;
        public String refund_from_credit_note;
        public String refund_from_gift_certificate;
        public String refund_from_invoice_credit;
        public String refund_from_payment_method;
        public String refund_total;
    }

    /** A credit note's applications, under {@code credit_note}. */
    public static final class CreditNoteApplications extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes/{credit_note_id}/credit_note_applications");

        @Override
        protected String envelopeKey() {
            return "credit_note";
        }

        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> applications;
        public String href;
        public String object;
    }

    /** One note on a credit note, under {@code note}. */
    public static final class CreditNoteNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes/{credit_note_id}/notes",
                "GET /api/{api_version}/credit_notes/{credit_note_id}/notes/{note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> attachments;
        public String content;
        public CreditNoteNoteCreatedBy created_by;
        public String created_on;
        public CreditNoteNoteDetail credit_note;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> watchers;
    }

    /** One page of {@code CreditNoteNote} records, under {@code data}. */
    public static final class CreditNoteNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_notes/{credit_note_id}/notes");

        public List<CreditNoteNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link CreditNoteNote}'s {@code credit_note}. */
    public static final class CreditNoteNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link CreditNoteNote}'s {@code created_by}. */
    public static final class CreditNoteNoteCreatedBy extends Dto {
        public String id;
        public String name;
        public String object;
    }
}
