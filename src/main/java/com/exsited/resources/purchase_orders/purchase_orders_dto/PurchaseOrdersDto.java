package com.exsited.resources.purchase_orders.purchase_orders_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the purchase orders bodies. */
public final class PurchaseOrdersDto {

    /** The purchase order line body. */
    public static final class PurchaseOrderLine extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/lines");

        public String created_on;
        public Double flat_discount;
        public String href;
        public String id;
        public Boolean is_price_tax_inclusive;
        public Boolean is_tax_exempt;
        public PurchaseOrderLineItem item;
        public PurchaseOrderLineItemAccountingCode item_accounting_code;
        public Double item_discount_amount;
        public String item_expected_completion_date;
        /** never populated in 1 measured record. */
        public Object item_invoice_note;
        public PurchaseOrderLineItemPriceSnapshot item_price_snapshot;
        public PurchaseOrderLineItemPriceTax item_price_tax;
        public Boolean item_price_tax_exempt;
        public PurchaseOrderLineItemPurchaseAccountingCode item_purchase_accounting_code;
        public PurchaseOrderLineItemPurchaseTaxConfiguration item_purchase_tax_configuration;
        public String last_updated_on;
        public String name;
        public String object;
        public Double price;
        /** never populated in 1 measured record. */
        public Object purchase_line_note;
        public Long quantity;
        public Long sort_index;
        public String status;
        public String subtotal;
        public PurchaseOrderLineTax tax;
        public String tax_total;
        public String total;
        public PurchaseOrderLineUom uom;
        public Long version;
        public PurchaseOrderLineWarehouse warehouse;
    }

    /** A page of PurchaseOrderLine records. */
    public static final class PurchaseOrderLineList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/lines");

        public List<PurchaseOrderLine> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order body. */
    public static final class PurchaseOrder extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders");

        public PurchaseOrderAccount account;
        public String bill_total;
        public String code;
        public PurchaseOrderCreatedBy created_by;
        public String created_on;
        public PurchaseOrderCurrency currency;
        public List<Object> custom_attributes;
        public PurchaseOrderCustomForm custom_form;
        public String due_date;
        public String due_total;
        public String expected_completion_date;
        public String href;
        public String id;
        public Boolean is_price_tax_inclusive;
        public String issue_date;
        public PurchaseOrderLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String operational_status;
        public String origin;
        public String paid_amount;
        public String purchase_order_note;
        /** never populated in 2 measured records. */
        public Object sale_order;
        public String status;
        public String subtotal;
        public String supplier_invoice_id;
        public String tax_total;
        public String total;
        public Long version;
        /** never populated in 2 measured records. */
        public Object warehouse;
    }

    /** A page of PurchaseOrder records. */
    public static final class PurchaseOrderList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders");

        public List<PurchaseOrder> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order purchase invoice body. */
    public static final class PurchaseOrderPurchaseInvoice extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        public PurchaseOrderPurchaseInvoiceAccount account;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseOrderPurchaseInvoiceCreatedBy created_by;
        public String created_on;
        public PurchaseOrderPurchaseInvoiceCurrency currency;
        /** never populated in 2 measured records. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String issue_date;
        public PurchaseOrderPurchaseInvoiceLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public PurchaseOrderPurchaseInvoiceDetail purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** A page of PurchaseOrderPurchaseInvoice records. */
    public static final class PurchaseOrderPurchaseInvoiceList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        public List<PurchaseOrderPurchaseInvoice> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order purchase payment body. */
    public static final class PurchaseOrderPurchasePayment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_payments");

        public PurchaseOrderPurchasePaymentAccount account;
        public String code;
        public PurchaseOrderPurchasePaymentCreatedBy created_by;
        public String created_on;
        public List<PurchaseOrderPurchasePaymentCreditNoteApplication> credit_note_applications;
        public String credit_note_total;
        public PurchaseOrderPurchasePaymentCurrency currency;
        public String href;
        public String id;
        /** 2 measured, null on 2. */
        public PurchaseOrderPurchasePaymentLastUpdatedBy last_updated_by;
        /** 2 measured, null on 2. */
        public String last_updated_on;
        public String object;
        public String origin;
        public String paid_total;
        public String payment_date;
        public List<PurchaseOrderPurchasePaymentPaymentMethodApplication> payment_method_applications;
        /** 2 measured, null on 2. */
        public String payment_note;
        /** never populated in 4 measured records. */
        public Object payment_reference;
        public List<PurchaseOrderPurchasePaymentPurchaseInvoiceApplication> purchase_invoice_applications;
        public String status;
        public Long version;
    }

    /** A page of PurchaseOrderPurchasePayment records. */
    public static final class PurchaseOrderPurchasePaymentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_payments");

        public List<PurchaseOrderPurchasePayment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase orders note body. */
    public static final class PurchaseOrdersNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/notes");

        public List<Object> attachments;
        public String content;
        public PurchaseOrdersNoteCreatedBy created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object group;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        /** never populated in 2 measured records. */
        public Object last_updated_on;
        public String object;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<PurchaseOrdersNoteWatcher> watchers;
    }

    /** A page of PurchaseOrdersNote records. */
    public static final class PurchaseOrdersNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/notes");

        public List<PurchaseOrdersNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order read body. */
    public static final class PurchaseOrderRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}",
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/change",
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/cancel",
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/reactivate");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public PurchaseOrderReadAccount account;
        public List<Object> activity_logs;
        public String bill_total;
        public String code;
        public PurchaseOrderReadCreatedBy created_by;
        public String created_on;
        public PurchaseOrderReadCurrency currency;
        public List<PurchaseOrderReadCustomAttribute> custom_attributes;
        public PurchaseOrderReadCustomForm custom_form;
        public String due_date;
        public String due_total;
        public String expected_completion_date;
        public String href;
        public String id;
        public Boolean is_price_tax_inclusive;
        public String issue_date;
        public PurchaseOrderReadKpis kpis;
        public PurchaseOrderReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String operational_status;
        public String origin;
        public String paid_amount;
        public String purchase_order_note;
        /** never populated in 1 measured record. */
        public Object sale_order;
        public String status;
        public String subtotal;
        public String supplier_invoice_id;
        public String tax_total;
        public String total;
        public Long version;
        /** never populated in 1 measured record. */
        public Object warehouse;
    }

    /** The purchase order updated body. */
    public static final class PurchaseOrderUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/purchase_orders/{purchase_order_id}");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public PurchaseOrderUpdatedAccount account;
        public String account_id;
        public List<Object> activity_logs;
        public String bill_total;
        public String code;
        public PurchaseOrderUpdatedCreatedBy created_by;
        public String created_on;
        public PurchaseOrderUpdatedCurrency currency;
        public List<Object> custom_attributes;
        public PurchaseOrderUpdatedCustomForm custom_form;
        public List<Object> custom_objects;
        public String due_date;
        public String due_total;
        public String expected_completion_date;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object is_price_tax_inclusive;
        public String issue_date;
        public PurchaseOrderUpdatedKpis kpis;
        public PurchaseOrderUpdatedLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<Object> lines;
        public String object;
        public String operational_status;
        public String origin;
        public String paid_amount;
        public String price_tax_inclusive;
        public String purchase_order_note;
        /** Null in every record measured, so untyped. */
        public Object sale_order;
        public String sale_order_id;
        public String status;
        public String subtotal;
        public String supplier_invoice_id;
        public String tax;
        public String tax_total;
        public String total;
        public String uuid;
        public Long version;
        /** Null in every record measured, so untyped. */
        public Object warehouse;
    }

    /** The purchase orders body. */
    public static final class PurchaseOrders extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_orders");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public PurchaseOrdersAccount account;
        public String account_id;
        public List<Object> activity_logs;
        public String bill_total;
        public String code;
        public PurchaseOrdersCreatedBy created_by;
        public String created_on;
        public PurchaseOrdersCurrency currency;
        public List<Object> custom_attributes;
        public PurchaseOrdersCustomForm custom_form;
        public List<Object> custom_objects;
        public String due_date;
        public String due_total;
        public String expected_completion_date;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object is_price_tax_inclusive;
        public String issue_date;
        public PurchaseOrdersKpis kpis;
        public String last_updated_by;
        public String last_updated_on;
        public List<Object> lines;
        public String object;
        public String operational_status;
        public String origin;
        public String paid_amount;
        public String price_tax_inclusive;
        public String purchase_order_note;
        /** Null in every record measured, so untyped. */
        public Object sale_order;
        public String status;
        public String subtotal;
        public String supplier_invoice_id;
        public String tax;
        public String tax_total;
        public String total;
        public String uuid;
        public Long version;
        /** Null in every record measured, so untyped. */
        public Object warehouse;
    }

    /** The purchase order information body. */
    public static final class PurchaseOrderInformation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/information");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public String bill_total;
        public String code;
        public PurchaseOrderInformationCreatedBy created_by;
        public String created_on;
        public List<PurchaseOrderInformationCustomAttribute> custom_attributes;
        public PurchaseOrderInformationCustomForm custom_form;
        public String due_date;
        public String due_total;
        public String expected_completion_date;
        public String href;
        public String id;
        public Boolean is_price_tax_inclusive;
        public String issue_date;
        public PurchaseOrderInformationLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public String operational_status;
        public String origin;
        public String paid_amount;
        public String purchase_order_note;
        public String status;
        public String subtotal;
        public String supplier_invoice_id;
        public String tax_total;
        public String total;
        public Long version;
    }

    /** The account purchase orders body. */
    public static final class AccountPurchaseOrders extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/purchase_orders");

        public List<PurchaseOrder> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order notes body. */
    public static final class PurchaseOrderNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/notes");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public List<PurchaseOrderNotesData> data;
        public List<Object> notes;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order note body. */
    public static final class PurchaseOrderNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}",
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public List<PurchaseOrderNoteData> data;
        public PurchaseOrderNoteDetail note;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The purchase order line read body. */
    public static final class PurchaseOrderLineRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/lines/{purchase_order_line_uuid}");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public Map<String, Object> line;
        public PurchaseOrderLineReadDetail purchase_order_line;
    }

    /** What {@code get_purchase_order_note_file} answers, under {@code file}. */
    public static final class PurchaseOrderNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files/{purchase_order_file_id}");

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

    /** What {@code create_purchase_order_note} answers, under {@code note}. */
    public static final class PurchaseOrderNotesView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/notes");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<PurchaseOrderNotesViewAttachment> attachments;
        public String content;
        public PurchaseOrderNotesViewCreatedBy created_by;
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
        public PurchaseOrderNotesViewDetail purchase_order;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchaseOrderNotesViewWatcher> watchers;
    }

    /**
     * What {@code create_purchase_order_purchase_invoice} answers, under {@code purchase_invoice}.
     */
    public static final class PurchaseOrderPurchaseInvoices extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices");

        @Override
        protected String envelopeKey() {
            return "purchase_invoice";
        }

        public PurchaseOrderPurchaseInvoicesAccount account;
        public List<Object> activity_logs;
        public String alternate_due_date;
        public String alternate_issue_date;
        public PurchaseOrderPurchaseInvoicesCreatedBy created_by;
        public String created_on;
        public PurchaseOrderPurchaseInvoicesCurrency currency;
        public List<Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object custom_form;
        public String due_date;
        public String id;
        public String issue_date;
        public PurchaseOrderPurchaseInvoicesKpis kpis;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public List<PurchaseOrderPurchaseInvoicesLine> lines;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public PurchaseOrderPurchaseInvoicesDetail purchase_order;
        public String status;
        public String subtotal;
        public String tax;
        public String total;
        public String uuid;
        public Long version;
    }

    /** A {@link PurchaseOrderRead}'s {@code account}. */
    public static final class PurchaseOrderReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderRead}'s {@code currency}. */
    public static final class PurchaseOrderReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderRead}'s {@code custom_attributes}. */
    public static final class PurchaseOrderReadCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link PurchaseOrderRead}'s {@code custom_form}. */
    public static final class PurchaseOrderReadCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderRead}'s {@code kpis}. */
    public static class PurchaseOrderReadKpis extends Dto {
        public String estimated_total;
        /** Null in every record measured, so untyped. */
        public Object issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_changed_on;
        /** Null in every record measured, so untyped. */
        public Object last_deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_invoice_issue_date;
        public String last_invoice_total;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String total_expense;
        public String total_outstanding;
        public String total_overdue;
        public Long total_purchase_invoice;
    }

    /** A {@link PurchaseOrderRead}'s {@code created_by}. */
    public static final class PurchaseOrderReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderRead}'s {@code last_updated_by}. */
    public static final class PurchaseOrderReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderInformation}'s {@code custom_attributes}. */
    public static final class PurchaseOrderInformationCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link PurchaseOrderInformation}'s {@code custom_form}. */
    public static final class PurchaseOrderInformationCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderInformation}'s {@code created_by}. */
    public static final class PurchaseOrderInformationCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderInformation}'s {@code last_updated_by}. */
    public static final class PurchaseOrderInformationLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineRead}'s {@code purchase_order_line}. */
    public static final class PurchaseOrderLineReadDetail extends Dto {
        public String created_on;
        public Double flat_discount;
        public String href;
        public String id;
        public Boolean is_price_tax_inclusive;
        public Boolean is_tax_exempt;
        public PurchaseOrderLineReadDetailItem item;
        public PurchaseOrderLineReadDetailItemAccountingCode item_accounting_code;
        public Double item_discount_amount;
        public String item_expected_completion_date;
        /** Null in every record measured, so untyped. */
        public Object item_invoice_note;
        public PurchaseOrderLineReadDetailItemPriceSnapshot item_price_snapshot;
        public PurchaseOrderLineReadDetailItemPriceTax item_price_tax;
        public Boolean item_price_tax_exempt;
        public PurchaseOrderLineReadDetailItemPurchaseAccountingCode item_purchase_accounting_code;
        public PurchaseOrderLineReadDetailItemPurchaseTaxConfiguration item_purchase_tax_configuration;
        public String last_updated_on;
        public String name;
        public String object;
        public Double price;
        /** Null in every record measured, so untyped. */
        public Object purchase_line_note;
        public Long quantity;
        public Long sort_index;
        public String status;
        public String subtotal;
        public PurchaseOrderLineReadDetailTax tax;
        public String tax_total;
        public String total;
        public PurchaseOrderLineReadDetailUom uom;
        public Long version;
        public PurchaseOrderLineReadDetailWarehouse warehouse;
    }

    /** One entry of a {@link PurchaseOrderNotes}'s {@code data}. */
    public static final class PurchaseOrderNotesData extends Dto {
        public List<PurchaseOrderNotesDataAttachment> attachments;
        public String content;
        public PurchaseOrderNotesDataCreatedBy created_by;
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
        public PurchaseOrderNotesDataDetail purchase_order;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchaseOrderNotesDataWatcher> watchers;
    }

    /** A {@link PurchaseOrderNote}'s {@code note}. */
    public static final class PurchaseOrderNoteDetail extends Dto {
        public List<PurchaseOrderNoteDetailAttachment> attachments;
        public String content;
        public PurchaseOrderNoteDetailCreatedBy created_by;
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
        public PurchaseOrderNoteDetailPurchaseOrder purchase_order;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<PurchaseOrderNoteDetailWatcher> watchers;
    }

    /** One entry of a {@link PurchaseOrderNote}'s {@code data}. */
    public static final class PurchaseOrderNoteData extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link PurchaseOrders}'s {@code account}. */
    public static final class PurchaseOrdersAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrders}'s {@code currency}. */
    public static final class PurchaseOrdersCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrders}'s {@code custom_form}. */
    public static final class PurchaseOrdersCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrders}'s {@code kpis}. */
    public static final class PurchaseOrdersKpis extends Dto {
        public String estimated_total;
        /** Null in every record measured, so untyped. */
        public Object issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_changed_on;
        /** Null in every record measured, so untyped. */
        public Object last_deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_invoice_issue_date;
        public String last_invoice_total;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String total_expense;
        public String total_outstanding;
        public String total_overdue;
        public Long total_purchase_invoice;
    }

    /** A {@link PurchaseOrders}'s {@code created_by}. */
    public static final class PurchaseOrdersCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code account}. */
    public static final class PurchaseOrderUpdatedAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code currency}. */
    public static final class PurchaseOrderUpdatedCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code custom_form}. */
    public static final class PurchaseOrderUpdatedCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code kpis}. */
    public static final class PurchaseOrderUpdatedKpis extends Dto {
        public String estimated_total;
        /** Null in every record measured, so untyped. */
        public Object issue_date;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_changed_on;
        /** Null in every record measured, so untyped. */
        public Object last_deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_invoice_issue_date;
        public String last_invoice_total;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String total_expense;
        public String total_outstanding;
        public String total_overdue;
        public Long total_purchase_invoice;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code created_by}. */
    public static final class PurchaseOrderUpdatedCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderUpdated}'s {@code last_updated_by}. */
    public static final class PurchaseOrderUpdatedLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderNotesView}'s {@code purchase_order}. */
    public static final class PurchaseOrderNotesViewDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderNotesView}'s {@code attachments}. */
    public static final class PurchaseOrderNotesViewAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseOrderNotesView}'s {@code watchers}. */
    public static final class PurchaseOrderNotesViewWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseOrderNotesView}'s {@code created_by}. */
    public static final class PurchaseOrderNotesViewCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoices}'s {@code currency}. */
    public static final class PurchaseOrderPurchaseInvoicesCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoices}'s {@code account}. */
    public static final class PurchaseOrderPurchaseInvoicesAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoices}'s {@code purchase_order}. */
    public static final class PurchaseOrderPurchaseInvoicesDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoices}'s {@code created_by}. */
    public static final class PurchaseOrderPurchaseInvoicesCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderPurchaseInvoices}'s {@code lines}. */
    public static final class PurchaseOrderPurchaseInvoicesLine extends Dto {
        public String accounting_code;
        public String id;
        public PurchaseOrderPurchaseInvoicesLineItem item;
        public String object;
        public Long quantity;
        public String subtotal;
        public String tax;
        public String total;
        public String uom;
        public Long version;
        public String warehouse;
    }

    /** A {@link PurchaseOrderPurchaseInvoices}'s {@code kpis}. */
    public static final class PurchaseOrderPurchaseInvoicesKpis extends Dto {
        public String credit_applied;
        public String credit_issued;
        /** Null in every record measured, so untyped. */
        public Object deleted_on;
        /** Null in every record measured, so untyped. */
        public Object last_amended_on;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_on;
        /** Null in every record measured, so untyped. */
        public Object last_payment_date;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_on;
        public String object;
        public String outstanding;
        public String overdue;
        public String payment_applied;
        /** Null in every record measured, so untyped. */
        public Object voided_on;
    }

    /** A {@link PurchaseOrder}'s {@code account}. */
    public static final class PurchaseOrderAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrder}'s {@code currency}. */
    public static final class PurchaseOrderCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrder}'s {@code custom_form}. */
    public static final class PurchaseOrderCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrder}'s {@code created_by}. */
    public static final class PurchaseOrderCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrder}'s {@code last_updated_by}. */
    public static final class PurchaseOrderLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link PurchaseOrdersNote}'s {@code watchers}. */
    public static final class PurchaseOrdersNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseOrdersNote}'s {@code created_by}. */
    public static final class PurchaseOrdersNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLine}'s {@code item}. */
    public static final class PurchaseOrderLineItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLine}'s {@code uom}. */
    public static final class PurchaseOrderLineUom extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLine}'s {@code warehouse}. */
    public static final class PurchaseOrderLineWarehouse extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLine}'s {@code tax}. */
    public static final class PurchaseOrderLineTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLine}'s {@code item_price_snapshot}. */
    public static final class PurchaseOrderLineItemPriceSnapshot extends Dto {
        public PurchaseOrderLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link PurchaseOrderLine}'s {@code item_price_tax}. */
    public static final class PurchaseOrderLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link PurchaseOrderLine}'s {@code item_purchase_tax_configuration}. */
    public static final class PurchaseOrderLineItemPurchaseTaxConfiguration extends Dto {
        public Boolean purchase_price_is_tax_inclusive;
        public PurchaseOrderLineItemPurchaseTaxConfigurationTaxCode tax_code;
        public Boolean tax_exempt_when_purchase;
    }

    /** A {@link PurchaseOrderLine}'s {@code item_accounting_code}. */
    public static final class PurchaseOrderLineItemAccountingCode extends Dto {
        public String cost_of_goods_sold;
    }

    /** A {@link PurchaseOrderLine}'s {@code item_purchase_accounting_code}. */
    public static final class PurchaseOrderLineItemPurchaseAccountingCode extends Dto {
        public String cost_of_goods_sold;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item}. */
    public static final class PurchaseOrderLineReadDetailItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code uom}. */
    public static final class PurchaseOrderLineReadDetailUom extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code warehouse}. */
    public static final class PurchaseOrderLineReadDetailWarehouse extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code tax}. */
    public static final class PurchaseOrderLineReadDetailTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item_price_snapshot}. */
    public static final class PurchaseOrderLineReadDetailItemPriceSnapshot extends Dto {
        public PurchaseOrderLineReadDetailItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item_price_tax}. */
    public static final class PurchaseOrderLineReadDetailItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item_purchase_tax_configuration}. */
    public static final class PurchaseOrderLineReadDetailItemPurchaseTaxConfiguration extends Dto {
        public Boolean purchase_price_is_tax_inclusive;
        public PurchaseOrderLineReadDetailItemPurchaseTaxConfigurationTaxCode tax_code;
        public Boolean tax_exempt_when_purchase;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item_accounting_code}. */
    public static final class PurchaseOrderLineReadDetailItemAccountingCode extends Dto {
        public String cost_of_goods_sold;
    }

    /** A {@link PurchaseOrderLineReadDetail}'s {@code item_purchase_accounting_code}. */
    public static final class PurchaseOrderLineReadDetailItemPurchaseAccountingCode extends Dto {
        public String cost_of_goods_sold;
    }

    /** A {@link PurchaseOrderPurchaseInvoice}'s {@code currency}. */
    public static final class PurchaseOrderPurchaseInvoiceCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoice}'s {@code account}. */
    public static final class PurchaseOrderPurchaseInvoiceAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoice}'s {@code purchase_order}. */
    public static final class PurchaseOrderPurchaseInvoiceDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoice}'s {@code created_by}. */
    public static final class PurchaseOrderPurchaseInvoiceCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoice}'s {@code last_updated_by}. */
    public static final class PurchaseOrderPurchaseInvoiceLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchasePayment}'s {@code account}. */
    public static final class PurchaseOrderPurchasePaymentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchasePayment}'s {@code currency}. */
    public static final class PurchaseOrderPurchasePaymentCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * One entry of a {@link PurchaseOrderPurchasePayment}'s {@code payment_method_applications}.
     */
    public static final class PurchaseOrderPurchasePaymentPaymentMethodApplication extends Dto {
        public String amount;
        public PurchaseOrderPurchasePaymentPaymentMethodApplicationDetail payment_method;
        public PurchaseOrderPurchasePaymentPaymentMethodApplicationPaymentProcessor payment_processor;
        public String reference;
    }

    /** One entry of a {@link PurchaseOrderPurchasePayment}'s {@code credit_note_applications}. */
    public static final class PurchaseOrderPurchasePaymentCreditNoteApplication extends Dto {
        public String amount;
        public PurchaseOrderPurchasePaymentCreditNoteApplicationPurchaseCreditNote purchase_credit_note;
    }

    /**
     * One entry of a {@link PurchaseOrderPurchasePayment}'s {@code purchase_invoice_applications}.
     */
    public static final class PurchaseOrderPurchasePaymentPurchaseInvoiceApplication extends Dto {
        public String applied;
        public String due_date;
        public String issue_date;
        public String outstanding;
        public PurchaseOrderPurchasePaymentPurchaseInvoiceApplicationDetail purchase_invoice;
        public String total;
    }

    /** A {@link PurchaseOrderPurchasePayment}'s {@code created_by}. */
    public static final class PurchaseOrderPurchasePaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchasePayment}'s {@code last_updated_by}. */
    public static final class PurchaseOrderPurchasePaymentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderNotesData}'s {@code purchase_order}. */
    public static final class PurchaseOrderNotesDataDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderNotesData}'s {@code attachments}. */
    public static final class PurchaseOrderNotesDataAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseOrderNotesData}'s {@code watchers}. */
    public static final class PurchaseOrderNotesDataWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseOrderNotesData}'s {@code created_by}. */
    public static final class PurchaseOrderNotesDataCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderNoteDetail}'s {@code purchase_order}. */
    public static final class PurchaseOrderNoteDetailPurchaseOrder extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link PurchaseOrderNoteDetail}'s {@code attachments}. */
    public static final class PurchaseOrderNoteDetailAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link PurchaseOrderNoteDetail}'s {@code watchers}. */
    public static final class PurchaseOrderNoteDetailWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link PurchaseOrderNoteDetail}'s {@code created_by}. */
    public static final class PurchaseOrderNoteDetailCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderPurchaseInvoicesLine}'s {@code item}. */
    public static final class PurchaseOrderPurchaseInvoicesLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseOrderLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class PurchaseOrderLineItemPriceSnapshotPricingRule extends Dto {
        public Double price;
        public String price_type;
        public String pricing_version;
        public String uom;
        public String uuid;
        public String version;
    }

    /** A {@link PurchaseOrderLineItemPurchaseTaxConfiguration}'s {@code tax_code}. */
    public static final class PurchaseOrderLineItemPurchaseTaxConfigurationTaxCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String rate;
    }

    /** A {@link PurchaseOrderLineReadDetailItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class PurchaseOrderLineReadDetailItemPriceSnapshotPricingRule extends Dto {
        public Double price;
        public String price_type;
        public String pricing_version;
        public String uom;
        public String uuid;
        public String version;
    }

    /** A {@link PurchaseOrderLineReadDetailItemPurchaseTaxConfiguration}'s {@code tax_code}. */
    public static final class PurchaseOrderLineReadDetailItemPurchaseTaxConfigurationTaxCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String rate;
    }

    /** A {@link PurchaseOrderPurchasePaymentPaymentMethodApplication}'s {@code payment_method}. */
    public static final class PurchaseOrderPurchasePaymentPaymentMethodApplicationDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * A {@link PurchaseOrderPurchasePaymentPaymentMethodApplication}'s {@code payment_processor}.
     */
    public static final class PurchaseOrderPurchasePaymentPaymentMethodApplicationPaymentProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * A {@link PurchaseOrderPurchasePaymentCreditNoteApplication}'s {@code purchase_credit_note}.
     */
    public static final class PurchaseOrderPurchasePaymentCreditNoteApplicationPurchaseCreditNote extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /**
     * A {@link PurchaseOrderPurchasePaymentPurchaseInvoiceApplication}'s {@code purchase_invoice}.
     */
    public static final class PurchaseOrderPurchasePaymentPurchaseInvoiceApplicationDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    private PurchaseOrdersDto() {
    }

    /**
     * What {@code getPurchaseOrderKpis} answers, under {@code purchase_order_kpis}: the block a
     * purchase order carries as {@code kpis}. Measured on 2 purchase orders.
     */
    public static final class PurchaseOrderKpis extends PurchaseOrderReadKpis {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/kpis");

        @Override
        protected String envelopeKey() {
            return "purchase_order_kpis";
        }
    }
}
