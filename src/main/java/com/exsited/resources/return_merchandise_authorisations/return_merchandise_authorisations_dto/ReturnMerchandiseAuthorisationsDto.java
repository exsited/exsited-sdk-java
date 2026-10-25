package com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the return merchandise authorisations bodies. */
public final class ReturnMerchandiseAuthorisationsDto {

    /** The return merchandise authorisation body. */
    public static final class ReturnMerchandiseAuthorisation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/return_merchandise_authorisations");

        public ReturnMerchandiseAuthorisationAccount account;
        public String code;
        public ReturnMerchandiseAuthorisationCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        public Long item_count;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        /** never populated in 2 measured records. */
        public Object last_updated_on;
        public String note;
        public String object;
        public String return_status;
        public String rma_date;
        public ReturnMerchandiseAuthorisationSaleInvoice sale_invoice;
        /** never populated in 2 measured records. */
        public Object sale_order;
        public String status;
        public Long version;
        /** never populated in 2 measured records. */
        public Object warehouse;
    }

    /** A page of ReturnMerchandiseAuthorisation records. */
    public static final class ReturnMerchandiseAuthorisationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/return_merchandise_authorisations");

        public List<ReturnMerchandiseAuthorisation> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The invoice return merchandise authorisation receive body. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceive extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive",
                "GET /api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive/{receive_uuid}");

        @Override
        protected String envelopeKey() {
            return "receive_return_merchandise_authorisation";
        }

        public String created_by;
        public String created_on;
        public String id;
        public List<Object> receive_returns;
        public InvoiceReturnMerchandiseAuthorisationReceiveReceiveRma receive_rma;
        public String uuid;
    }

    /** A page of InvoiceReturnMerchandiseAuthorisationReceive records. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceiveList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive");

        public List<InvoiceReturnMerchandiseAuthorisationReceive> receive_return_merchandise_authorisations;
        public PaginationDto pagination;
        public List<InvoiceReturnMerchandiseAuthorisationReceiveListData> data;
        public String object;
        public String url;
    }

    /** The return merchandise authorisation read body. */
    public static final class ReturnMerchandiseAuthorisationRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations/{rma_id}",
                "GET /api/{api_version}/return_merchandise_authorisations/{rma_id}");

        @Override
        protected String envelopeKey() {
            return "return_merchandise_authorisation";
        }

        public ReturnMerchandiseAuthorisationReadAccount account;
        public String code;
        public ReturnMerchandiseAuthorisationReadCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        public Long item_count;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        /** never populated in 1 measured record. */
        public Object last_updated_on;
        public List<Object> lines;
        public String note;
        public String object;
        public List<ReturnMerchandiseAuthorisationReadReceiveRma> receive_rmas;
        public String return_status;
        public String rma_date;
        public ReturnMerchandiseAuthorisationReadSaleInvoice sale_invoice;
        /** never populated in 1 measured record. */
        public Object sale_order;
        public String status;
        public Long version;
        /** never populated in 1 measured record. */
        public Object warehouse;
    }

    /** The invoice return merchandise authorisations body. */
    public static final class InvoiceReturnMerchandiseAuthorisations extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/return_merchandise_authorisations");

        public List<ReturnMerchandiseAuthorisation> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The return merchandise authorisations body. */
    public static final class ReturnMerchandiseAuthorisations extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/return_merchandise_authorisations");

        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public ReturnMerchandiseAuthorisationsReturnMerchandiseAuthorisation return_merchandise_authorisation;
    }

    /** A {@link ReturnMerchandiseAuthorisationRead}'s {@code account}. */
    public static final class ReturnMerchandiseAuthorisationReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ReturnMerchandiseAuthorisationRead}'s {@code sale_invoice}. */
    public static final class ReturnMerchandiseAuthorisationReadSaleInvoice extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link ReturnMerchandiseAuthorisationRead}'s {@code receive_rmas}. */
    public static final class ReturnMerchandiseAuthorisationReadReceiveRma extends Dto {
        public String code;
        public ReturnMerchandiseAuthorisationReadReceiveRmaCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public Double quantity_received;
        public String status;
    }

    /** A {@link ReturnMerchandiseAuthorisationRead}'s {@code created_by}. */
    public static final class ReturnMerchandiseAuthorisationReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link InvoiceReturnMerchandiseAuthorisationReceiveList}'s {@code data}. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceiveListData extends Dto {
        public String code;
        public InvoiceReturnMerchandiseAuthorisationReceiveListDataCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public Double quantity_received;
        public String status;
    }

    /** A {@link InvoiceReturnMerchandiseAuthorisationReceive}'s {@code receive_rma}. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceiveReceiveRma extends Dto {
        public String code;
        public InvoiceReturnMerchandiseAuthorisationReceiveReceiveRmaCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public Double quantity_received;
        public String status;
    }

    /** A {@link ReturnMerchandiseAuthorisations}'s {@code return_merchandise_authorisation}. */
    public static final class ReturnMerchandiseAuthorisationsReturnMerchandiseAuthorisation extends Dto {
        public List<Object> activity_logs;
        public String code;
        public String href;
        public String id;
        public String object;
        public String status;
        public Long version;
    }

    /** A {@link ReturnMerchandiseAuthorisation}'s {@code account}. */
    public static final class ReturnMerchandiseAuthorisationAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ReturnMerchandiseAuthorisation}'s {@code sale_invoice}. */
    public static final class ReturnMerchandiseAuthorisationSaleInvoice extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** A {@link ReturnMerchandiseAuthorisation}'s {@code created_by}. */
    public static final class ReturnMerchandiseAuthorisationCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ReturnMerchandiseAuthorisationReadReceiveRma}'s {@code created_by}. */
    public static final class ReturnMerchandiseAuthorisationReadReceiveRmaCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link InvoiceReturnMerchandiseAuthorisationReceiveListData}'s {@code created_by}. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceiveListDataCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link InvoiceReturnMerchandiseAuthorisationReceiveReceiveRma}'s {@code created_by}. */
    public static final class InvoiceReturnMerchandiseAuthorisationReceiveReceiveRmaCreatedBy extends Dto {
        public String name;
        public String object;
    }

    private ReturnMerchandiseAuthorisationsDto() {
    }
}
