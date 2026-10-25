package com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the purchase credit note applications bodies. */
public final class PurchaseCreditNoteApplicationsDto {

    /** The purchase credit note application body. */
    public static final class PurchaseCreditNoteApplication extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_note_applications");

        public String amount;
        public PurchaseCreditNoteApplicationCreatedBy created_by;
        public String created_on;
        public String credit_note_id;
        public String date;
        public String href;
        public String id;
        public String issue_date;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public String payment_id;
        public PurchaseCreditNoteApplicationDetail purchase_credit_note;
        public PurchaseCreditNoteApplicationPurchasePayment purchase_payment;
        public String remaining_balance;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A page of PurchaseCreditNoteApplication records. */
    public static final class PurchaseCreditNoteApplicationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_note_applications");

        public List<PurchaseCreditNoteApplication> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase credit note application read body. */
    public static final class PurchaseCreditNoteApplicationRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_credit_note_applications/{purchase_credit_note_application_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_credit_note_application";
        }

        public String amount;
        public PurchaseCreditNoteApplicationReadCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        public String issue_date;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public PurchaseCreditNoteApplicationReadDetail purchase_credit_note;
        public PurchaseCreditNoteApplicationReadPurchasePayment purchase_payment;
        public String remaining_balance;
        public String status;
        public Long version;
    }

    /** A {@link PurchaseCreditNoteApplicationRead}'s {@code purchase_credit_note}. */
    public static final class PurchaseCreditNoteApplicationReadDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteApplicationRead}'s {@code purchase_payment}. */
    public static final class PurchaseCreditNoteApplicationReadPurchasePayment extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteApplicationRead}'s {@code created_by}. */
    public static final class PurchaseCreditNoteApplicationReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteApplication}'s {@code purchase_credit_note}. */
    public static final class PurchaseCreditNoteApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteApplication}'s {@code purchase_payment}. */
    public static final class PurchaseCreditNoteApplicationPurchasePayment extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseCreditNoteApplication}'s {@code created_by}. */
    public static final class PurchaseCreditNoteApplicationCreatedBy extends Dto {
        public String name;
        public String object;
    }

    private PurchaseCreditNoteApplicationsDto() {
    }
}
