package com.exsited.resources.credit_note_applications.credit_note_applications_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the credit note applications bodies. */
public final class CreditNoteApplicationsDto {

    /** The credit note application body. */
    public static final class CreditNoteApplication extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_note_applications");

        public Double amount;
        public CreditNoteApplicationCreatedBy created_by;
        public String created_on;
        public CreditNoteApplicationDetail credit_note;
        public String credit_note_id;
        public String date;
        public String href;
        public String id;
        public String issue_date;
        /** never populated in 66 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public CreditNoteApplicationPayment payment;
        public String payment_id;
        @WireName("primaryId")
        public Long primaryId;
        public CreditNoteApplicationRefund refund;
        public String refund_id;
        public Double remaining_balance;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A page of CreditNoteApplication records. */
    public static final class CreditNoteApplicationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_note_applications");

        public List<CreditNoteApplication> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The credit note application read body. */
    public static final class CreditNoteApplicationRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/credit_note_applications/{credit_note_application_id}");

        @Override
        protected String envelopeKey() {
            return "credit_note_application";
        }

        public Double amount;
        public CreditNoteApplicationReadCreatedBy created_by;
        public String created_on;
        public CreditNoteApplicationReadDetail credit_note;
        public String href;
        public String id;
        public String issue_date;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public CreditNoteApplicationReadPayment payment;
        @WireName("primaryId")
        public Long primaryId;
        public CreditNoteApplicationReadRefund refund;
        public Double remaining_balance;
        public String status;
        public Long version;
    }

    /** The invoice credit note applications body. */
    public static final class InvoiceCreditNoteApplications extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/credit_note_applications");

        public List<CreditNoteApplication> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link CreditNoteApplicationRead}'s {@code credit_note}. */
    public static final class CreditNoteApplicationReadDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplicationRead}'s {@code payment}. */
    public static final class CreditNoteApplicationReadPayment extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplicationRead}'s {@code refund}. */
    public static final class CreditNoteApplicationReadRefund extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplicationRead}'s {@code created_by}. */
    public static final class CreditNoteApplicationReadCreatedBy extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplication}'s {@code credit_note}. */
    public static final class CreditNoteApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplication}'s {@code payment}. */
    public static final class CreditNoteApplicationPayment extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplication}'s {@code refund}. */
    public static final class CreditNoteApplicationRefund extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link CreditNoteApplication}'s {@code created_by}. */
    public static final class CreditNoteApplicationCreatedBy extends Dto {
        public String id;
        public String name;
        public String object;
    }

    private CreditNoteApplicationsDto() {
    }
}
