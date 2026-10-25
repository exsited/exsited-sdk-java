package com.exsited.resources.gift_certificates.gift_certificates_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the gift certificates bodies. */
public final class GiftCertificatesDto {

    /** The gift certificate body. */
    public static final class GiftCertificate extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates",
                "GET /api/{api_version}/gift_certificates/{gc_id}",
                "POST /api/{api_version}/gift_certificates",
                "PATCH /api/{api_version}/gift_certificates/{gc_id}");

        @Override
        protected String envelopeKey() {
            return "gift_certificate";
        }

        public GiftCertificateAccountingCode accounting_code;
        public List<GiftCertificateActivityLog> activity_logs;
        public List<GiftCertificateAllocation> allocations;
        public String amount;
        public String code;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public GiftCertificateCurrency currency;
        public String expiry_date;
        public String href;
        public String id;
        public String issue_date;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        /** never populated in 2 measured records. */
        public Object linked_item;
        public String object;
        public String remaining_balance;
        public String status;
        public List<GiftCertificateTransaction> transactions;
        public String used_amount;
        public Long version;
    }

    /** A page of GiftCertificate records. */
    public static final class GiftCertificateList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates");

        public List<GiftCertificate> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The gift certificate transaction body. */
    public static class GiftCertificateTransaction extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates/{gc_id}/transactions");

        public GiftCertificateTransactionAccountingCode accounting_code;
        public String amount;
        /** never populated in 1 measured record. */
        public Object created_by;
        public String created_on;
        public GiftCertificateTransactionCurrency currency;
        public String date;
        public String href;
        public String id;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        /** never populated in 1 measured record. */
        public Object reference;
        public String status;
        public String type;
        public Long version;
    }

    /** A page of GiftCertificateTransaction records. */
    public static final class GiftCertificateTransactionList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates/{gc_id}/transactions");

        public List<GiftCertificateTransaction> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The gift certificate allocations body. */
    public static final class GiftCertificateAllocations extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates/{gc_id}/allocations");

        public List<GiftCertificateAllocationsData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link GiftCertificate}'s {@code currency}. */
    public static final class GiftCertificateCurrency extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link GiftCertificate}'s {@code accounting_code}. */
    public static final class GiftCertificateAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link GiftCertificate}'s {@code allocations}. */
    public static class GiftCertificateAllocation extends Dto {
        public GiftCertificateAllocationAccount account;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String date;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public String status;
        public String type;
        public Long version;
    }

    /** One entry of a {@link GiftCertificate}'s {@code activity_logs}. */
    public static final class GiftCertificateActivityLog extends Dto {
        public String activity;
    }

    /** One entry of a {@link GiftCertificateAllocations}'s {@code data}. */
    public static final class GiftCertificateAllocationsData extends Dto {
        public GiftCertificateAllocationsDataAccount account;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String date;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public String status;
        public String type;
        public Long version;
    }

    /** A {@link GiftCertificateAllocation}'s {@code account}. */
    public static final class GiftCertificateAllocationAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link GiftCertificateTransaction}'s {@code currency}. */
    public static final class GiftCertificateTransactionCurrency extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link GiftCertificateTransaction}'s {@code accounting_code}. */
    public static final class GiftCertificateTransactionAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link GiftCertificateAllocationsData}'s {@code account}. */
    public static final class GiftCertificateAllocationsDataAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private GiftCertificatesDto() {
    }

    /**
     * One allocation, under {@code gift_certificate_allocation}: the fields an allocation carries
     * in {@code allocations}.
     */
    public static final class GiftCertificateAllocationRead extends GiftCertificateAllocation {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates/{gc_id}/allocations/{allocation_id}");

        @Override
        protected String envelopeKey() {
            return "gift_certificate_allocation";
        }
    }

    /**
     * One transaction, under {@code gift_certificate_transaction}: the fields a transaction carries
     * in {@code transactions}.
     */
    public static final class GiftCertificateTransactionRead extends GiftCertificateTransaction {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/gift_certificates/{gc_id}/transactions/{transaction_id}");

        @Override
        protected String envelopeKey() {
            return "gift_certificate_transaction";
        }
    }
}
