package com.exsited.resources.return_vendor_authorizations.return_vendor_authorizations_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the return vendor authorization bodies. */
public final class ReturnVendorAuthorizationsDto {

    private ReturnVendorAuthorizationsDto() {}

    /** One return vendor authorization, as the listing returns it. */
    public static class ReturnVendorAuthorization extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/return_vendor_authorizations");

        public ReturnVendorAuthorizationAccount account;
        public String code;
        public ReturnVendorAuthorizationCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        public Long item_count;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String note;
        public String object;
        public ReturnVendorAuthorizationPurchaseOrder purchase_order;
        public String return_status;
        public String rva_date;
        public String status;
        public Long version;
    }

    /**
     * One page of {@code ReturnVendorAuthorization} records, under {@code data}; paged by cursor.
     */
    public static final class ReturnVendorAuthorizationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/return_vendor_authorizations");

        public List<ReturnVendorAuthorization> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /**
     * What {@code getReturnVendorAuthorization} answers, under {@code return_vendor_authorization}:
     * the listing's record with its {@code lines} and {@code receive_rvas}.
     */
    public static final class ReturnVendorAuthorizationView extends ReturnVendorAuthorization {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/return_vendor_authorizations/{rva_id}");

        @Override
        protected String envelopeKey() {
            return "return_vendor_authorization";
        }

        /** Empty in every record measured, so the entries are untyped. */
        public List<Object> lines;
        /** Empty in every record measured, so the entries are untyped. */
        public List<Object> receive_rvas;
    }

    /** A {@link ReturnVendorAuthorization}'s {@code account}. */
    public static final class ReturnVendorAuthorizationAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ReturnVendorAuthorization}'s {@code purchase_order}. */
    public static final class ReturnVendorAuthorizationPurchaseOrder extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link ReturnVendorAuthorization}'s {@code created_by}. */
    public static final class ReturnVendorAuthorizationCreatedBy extends Dto {
        public String name;
        public String object;
    }
}
