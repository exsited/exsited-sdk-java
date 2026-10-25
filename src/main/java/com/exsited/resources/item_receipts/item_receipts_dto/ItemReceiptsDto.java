package com.exsited.resources.item_receipts.item_receipts_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the item receipts bodies. */
public final class ItemReceiptsDto {

    /** The item receipt body. */
    public static final class ItemReceipt extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_receipts",
                "GET /api/{api_version}/item_receipts/{item_receipt_id}");

        @Override
        protected String envelopeKey() {
            return "item_receipt";
        }

        public String code;
        public ItemReceiptCreatedBy created_by;
        public String created_on;
        public String href;
        public String id;
        /** 1 measured, null on 1. */
        public ItemReceiptLastUpdatedBy last_updated_by;
        /** 1 measured, null on 1. */
        public String last_updated_on;
        public List<ItemReceiptLine> lines;
        public String object;
        public ItemReceiptPurchaseOrder purchase_order;
        public String receipt_date;
        /** 1 measured, null on 1. */
        public String receipt_note;
        public String receipt_status;
        public Boolean receive_completed;
        public String status;
        public String tracking_number;
        public Long version;
    }

    /** A page of ItemReceipt records. */
    public static final class ItemReceiptList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_receipts");

        public List<ItemReceipt> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The item receipts body. */
    public static final class ItemReceipts extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/item_receipts");

        @Override
        protected String envelopeKey() {
            return "item-receipt";
        }

        public List<Object> activity_logs;
        public String created_by;
        public String created_on;
        public String date;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String note;
        public String purchase_order_id;
        public String receipt_status;
        public List<Object> receipts;
        public String tracking_number;
        public String uuid;
        public String version;
    }

    /** The item receipt updated body. */
    public static final class ItemReceiptUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/item_receipts/{item_receipt_id}");

        @Override
        protected String envelopeKey() {
            return "item_receipt";
        }

        public String code;
        public ItemReceiptUpdatedCreatedBy created_by;
        public String created_on;
        public String date;
        public String href;
        public String id;
        public ItemReceiptUpdatedLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<ItemReceiptUpdatedLine> lines;
        public String note;
        public String object;
        public ItemReceiptUpdatedPurchaseOrder purchase_order;
        public String purchase_order_id;
        public String receipt_date;
        /** Null in every record measured, so untyped. */
        public Object receipt_note;
        public String receipt_status;
        public List<Object> receipts;
        public Boolean receive_completed;
        public String status;
        public String tracking_number;
        public String uuid;
        public Long version;
    }

    /** The purchase order item receipts body. */
    public static final class PurchaseOrderItemReceipts extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/purchase_orders/{purchase_order_id}/item_receipts");

        @Override
        protected String envelopeKey() {
            return "purchase_order";
        }

        public List<Object> item_receipts;
        public Map<String, Object> pagination;
    }

    /** A {@link ItemReceipt}'s {@code purchase_order}. */
    public static final class ItemReceiptPurchaseOrder extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link ItemReceipt}'s {@code lines}. */
    public static final class ItemReceiptLine extends Dto {
        public ItemReceiptLineDetail item;
        public Double pending_return_quantity;
        public ItemReceiptLinePurchaseOrderLine purchase_order_line;
        public Double receipt_quantity;
        public Double received_quantity;
        public Double returned_quantity;
        public ItemReceiptLineUom uom;
        public ItemReceiptLineWarehouse warehouse;
    }

    /** A {@link ItemReceipt}'s {@code created_by}. */
    public static final class ItemReceiptCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemReceipt}'s {@code last_updated_by}. */
    public static final class ItemReceiptLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdated}'s {@code purchase_order}. */
    public static final class ItemReceiptUpdatedPurchaseOrder extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link ItemReceiptUpdated}'s {@code lines}. */
    public static final class ItemReceiptUpdatedLine extends Dto {
        public ItemReceiptUpdatedLineDetail item;
        public Double pending_return_quantity;
        public ItemReceiptUpdatedLinePurchaseOrderLine purchase_order_line;
        public Double receipt_quantity;
        public Double received_quantity;
        public Double returned_quantity;
        public ItemReceiptUpdatedLineUom uom;
        public ItemReceiptUpdatedLineWarehouse warehouse;
    }

    /** A {@link ItemReceiptUpdated}'s {@code created_by}. */
    public static final class ItemReceiptUpdatedCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdated}'s {@code last_updated_by}. */
    public static final class ItemReceiptUpdatedLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptLine}'s {@code purchase_order_line}. */
    public static final class ItemReceiptLinePurchaseOrderLine extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptLine}'s {@code item}. */
    public static final class ItemReceiptLineDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptLine}'s {@code uom}. */
    public static final class ItemReceiptLineUom extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptLine}'s {@code warehouse}. */
    public static final class ItemReceiptLineWarehouse extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdatedLine}'s {@code purchase_order_line}. */
    public static final class ItemReceiptUpdatedLinePurchaseOrderLine extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdatedLine}'s {@code item}. */
    public static final class ItemReceiptUpdatedLineDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdatedLine}'s {@code uom}. */
    public static final class ItemReceiptUpdatedLineUom extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemReceiptUpdatedLine}'s {@code warehouse}. */
    public static final class ItemReceiptUpdatedLineWarehouse extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private ItemReceiptsDto() {
    }
}
