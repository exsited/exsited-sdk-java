package com.exsited.resources.purchase_refunds.purchase_refunds_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the purchase refunds bodies. */
public final class PurchaseRefundsDto {

    /** The purchase refunds body. */
    public static final class PurchaseRefunds extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_refunds");

        public String alternate_date;
        public String amount;
        public String created_by;
        public String created_on;
        public String credit_note_id;
        public List<Object> custom_attributes;
        public List<PurchaseRefundsData> data;
        public String date;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String note;
        public String object;
        public PaginationDto pagination;
        public String payment_method;
        public String payment_processor;
        public String reference;
        public String status;
        public String url;
        public String uuid;
        public String version;
    }

    /** The purchase refund body. */
    public static final class PurchaseRefund extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_refunds/{purchase_refund_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_refund";
        }

        public PurchaseRefundAccount account;
        public String alternate_date;
        public String amount;
        public PurchaseRefundCreatedBy created_by;
        public String created_on;
        public PurchaseRefundCreditNote credit_note;
        public String credit_note_id;
        public List<PurchaseRefundCustomAttribute> custom_attributes;
        public String date;
        public String id;
        public String issue_date;
        public String last_updated_by;
        public String last_updated_on;
        public String note;
        public String object;
        public String payment_method;
        public String payment_processor;
        /** never populated in 2 measured records. */
        public Object purchase_refund;
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** The account purchase refunds body. */
    public static final class AccountPurchaseRefunds extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/purchase_refunds");

        public List<AccountPurchaseRefundsData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link AccountPurchaseRefunds}'s {@code data}. */
    public static final class AccountPurchaseRefundsData extends Dto {
        public AccountPurchaseRefundsDataDetail account;
        public String amount;
        public AccountPurchaseRefundsDataCreatedBy created_by;
        public String created_on;
        public AccountPurchaseRefundsDataCreditNote credit_note;
        public List<AccountPurchaseRefundsDataCustomAttribute> custom_attributes;
        public String id;
        public String issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String note;
        public String object;
        public String payment_method;
        public String payment_processor;
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** One entry of a {@link PurchaseRefunds}'s {@code data}. */
    public static final class PurchaseRefundsData extends Dto {
        public PurchaseRefundsDataAccount account;
        public String amount;
        public PurchaseRefundsDataCreatedBy created_by;
        public String created_on;
        public PurchaseRefundsDataCreditNote credit_note;
        public List<PurchaseRefundsDataCustomAttribute> custom_attributes;
        public String id;
        public String issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String note;
        public String object;
        public String payment_method;
        public String payment_processor;
        public String reference;
        public String status;
        public String uuid;
        public Long version;
    }

    /** A {@link PurchaseRefund}'s {@code account}. */
    public static final class PurchaseRefundAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseRefund}'s {@code credit_note}. */
    public static final class PurchaseRefundCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseRefund}'s {@code custom_attributes}. */
    public static final class PurchaseRefundCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link PurchaseRefund}'s {@code created_by}. */
    public static final class PurchaseRefundCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseRefundsData}'s {@code account}. */
    public static final class AccountPurchaseRefundsDataDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountPurchaseRefundsData}'s {@code credit_note}. */
    public static final class AccountPurchaseRefundsDataCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link AccountPurchaseRefundsData}'s {@code custom_attributes}. */
    public static final class AccountPurchaseRefundsDataCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link AccountPurchaseRefundsData}'s {@code created_by}. */
    public static final class AccountPurchaseRefundsDataCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseRefundsData}'s {@code account}. */
    public static final class PurchaseRefundsDataAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseRefundsData}'s {@code credit_note}. */
    public static final class PurchaseRefundsDataCreditNote extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseRefundsData}'s {@code custom_attributes}. */
    public static final class PurchaseRefundsDataCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link PurchaseRefundsData}'s {@code created_by}. */
    public static final class PurchaseRefundsDataCreatedBy extends Dto {
        public String name;
        public String object;
    }

    private PurchaseRefundsDto() {
    }
}
