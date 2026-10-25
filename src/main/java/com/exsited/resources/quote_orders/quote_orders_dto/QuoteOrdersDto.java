package com.exsited.resources.quote_orders.quote_orders_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the quote order bodies. */
public final class QuoteOrdersDto {

    private QuoteOrdersDto() {}

    /** One page of a quote's invoices, under {@code data}. */
    public static final class QuoteOrderInvoiceList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/quote_orders/{quote_order_id}/invoices");

        public List<QuoteOrderInvoice> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link QuoteOrderInvoiceList}'s {@code data}. */
    public static final class QuoteOrderInvoice extends Dto {
        public QuoteOrderInvoiceAccount account;
        public String alternate_billing_end_date;
        public String alternate_billing_start_date;
        public String alternate_due_date;
        public String alternate_issue_date;
        public String billing_end_date;
        public String billing_start_date;
        public QuoteOrderInvoiceCreatedBy created_by;
        public String created_on;
        public QuoteOrderInvoiceCurrency currency;
        public QuoteOrderInvoiceCustomForm custom_form;
        /** Null in every record measured, so untyped. */
        public Object customer_purchase_order_id;
        public Double discount_amount;
        public String due;
        public String due_date;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object invoice_note;
        public String issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_payment;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public QuoteOrderInvoiceOrder order;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public String paid;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public String status;
        public String subtotal;
        public String tax;
        public String tax_total;
        public String total;
        public String type;
        public Long version;
    }

    /** A {@link QuoteOrderInvoice}'s {@code account}. */
    public static final class QuoteOrderInvoiceAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link QuoteOrderInvoice}'s {@code created_by}. */
    public static final class QuoteOrderInvoiceCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link QuoteOrderInvoice}'s {@code currency}. */
    public static final class QuoteOrderInvoiceCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link QuoteOrderInvoice}'s {@code custom_form}. */
    public static final class QuoteOrderInvoiceCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link QuoteOrderInvoice}'s {@code order}. */
    public static final class QuoteOrderInvoiceOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }
}
