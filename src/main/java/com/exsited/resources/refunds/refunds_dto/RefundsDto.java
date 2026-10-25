package com.exsited.resources.refunds.refunds_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the refunds bodies. */
public final class RefundsDto {

    /** The refund body. */
    public static final class Refund extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/refunds",
                "GET /api/{api_version}/refunds/{refund_id}");

        @Override
        protected String envelopeKey() {
            return "refund";
        }

        public RefundAccount account;
        public String amount;
        public String created_by;
        public String created_on;
        public RefundCreditNote credit_note;
        public List<Object> custom_attributes;
        /** never populated in 2 measured records. */
        public Object gateway_response;
        public String id;
        public String issue_date;
        /** 1 measured, null on 1. */
        public String last_updated_by;
        /** 1 measured, null on 1. */
        public String last_updated_on;
        /** never populated in 2 measured records. */
        public Object note;
        public String object;
        public String payment_method;
        public String payment_processor;
        public String reconcile_status;
        /** 1 measured, null on 1. */
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A page of Refund records. */
    public static final class RefundList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/refunds");

        public List<Refund> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The refund updated body. */
    public static final class RefundUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/credit_notes/{credit_note_id}/refunds",
                "PATCH /api/{api_version}/refunds/{refund_id}");

        @Override
        protected String envelopeKey() {
            return "refund";
        }

        public RefundUpdatedAccount account;
        public String amount;
        public String created_by;
        public String created_on;
        public RefundUpdatedCreditNote credit_note;
        public String credit_note_id;
        public List<Object> custom_attributes;
        public String gateway_response;
        public String id;
        public String issue_date;
        public String last_updated_by;
        public String last_updated_on;
        public String note;
        public String object;
        public String payment_method;
        public String payment_processor;
        public String reconcile_status;
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** The account refunds body. */
    public static final class AccountRefunds extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/refunds");

        public List<AccountRefundsData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link AccountRefunds}'s {@code data}. */
    public static final class AccountRefundsData extends Dto {
        public AccountRefundsDataDetail account;
        public String amount;
        public String created_by;
        public String created_on;
        public AccountRefundsDataCreditNote credit_note;
        public List<Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String id;
        public String issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object note;
        public String object;
        public String payment_method;
        public String payment_processor;
        public String reconcile_status;
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A {@link Refund}'s {@code account}. */
    public static final class RefundAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Refund}'s {@code credit_note}. */
    public static final class RefundCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link RefundUpdated}'s {@code account}. */
    public static final class RefundUpdatedAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link RefundUpdated}'s {@code credit_note}. */
    public static final class RefundUpdatedCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountRefundsData}'s {@code account}. */
    public static final class AccountRefundsDataDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountRefundsData}'s {@code credit_note}. */
    public static final class AccountRefundsDataCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private RefundsDto() {
    }

    /** One note on a refund, under {@code note}. */
    public static final class RefundNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/refunds/{refund_id}/notes",
                "GET /api/{api_version}/refunds/{refund_id}/notes",
                "GET /api/{api_version}/refunds/{refund_id}/notes/{note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<RefundNoteAttachment> attachments;
        public String content;
        public RefundNoteCreatedBy created_by;
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
        public RefundNoteDetail refund;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<RefundNoteWatcher> watchers;
    }

    /** One page of {@code RefundNote} records, under {@code data}. */
    public static final class RefundNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/refunds/{refund_id}/notes");

        public List<RefundNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One attachment's descriptor, under {@code file}. */
    public static final class RefundNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/refunds/{refund_id}/notes/{note_id}/files/{file_id}");

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

    /** A {@link RefundNote}'s {@code refund}. */
    public static final class RefundNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link RefundNote}'s {@code attachments}. */
    public static final class RefundNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link RefundNote}'s {@code watchers}. */
    public static final class RefundNoteWatcher extends Dto {
        /** Null in every record measured, so untyped. */
        public Object email;
        public String name;
        public String type;
    }

    /** A {@link RefundNote}'s {@code created_by}. */
    public static final class RefundNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }
}
