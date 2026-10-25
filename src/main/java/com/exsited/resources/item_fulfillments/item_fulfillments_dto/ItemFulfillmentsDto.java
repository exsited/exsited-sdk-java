package com.exsited.resources.item_fulfillments.item_fulfillments_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the item fulfillments bodies. */
public final class ItemFulfillmentsDto {

    /** The item fulfillment body. */
    public static final class ItemFulfillment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_fulfillments");

        public ItemFulfillmentAccount account;
        public String code;
        public ItemFulfillmentCreatedBy created_by;
        public String created_on;
        public Map<String, Object> custom_attributes;
        public String date;
        /** never populated in 2 measured records. */
        public Object delivery_status;
        public Boolean fulfillment_completed;
        public String fulfillment_date;
        public String fulfillment_note;
        public String fulfillment_status;
        public List<ItemFulfillmentRow> fulfillments;
        public String href;
        public String id;
        public ItemFulfillmentInvoice invoice;
        public String invoice_id;
        public ItemFulfillmentLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String note;
        public String object;
        /** never populated in 2 measured records. */
        public Object order;
        public String order_id;
        public String status;
        public String tracking_number;
        public String uuid;
        public Long version;
    }

    /** A page of ItemFulfillment records. */
    public static final class ItemFulfillmentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_fulfillments");

        public List<ItemFulfillment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The item fulfillment read body. */
    public static final class ItemFulfillmentRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/item_fulfillments",
                "GET /api/{api_version}/invoices/{invoice_id}/item_fulfillments/{fulfillment_id}",
                "GET /api/{api_version}/item_fulfillments/{fulfillment_id}");

        @Override
        protected String envelopeKey() {
            return "item_fulfillment";
        }

        public ItemFulfillmentReadAccount account;
        public String code;
        public ItemFulfillmentReadCreatedBy created_by;
        public String created_on;
        public Map<String, Object> custom_attributes;
        /** never populated in 1 measured record. */
        public Object delivery_status;
        public Boolean fulfillment_completed;
        public String fulfillment_date;
        public String fulfillment_note;
        public String fulfillment_status;
        public List<ItemFulfillmentReadRow> fulfillments;
        public String href;
        public String id;
        public ItemFulfillmentReadInvoice invoice;
        public ItemFulfillmentReadLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** never populated in 1 measured record. */
        public Object order;
        public String status;
        public String tracking_number;
        public Long version;
    }

    /** A page of ItemFulfillmentRead records. */
    public static final class ItemFulfillmentReadList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/invoices/{invoice_id}/item_fulfillments");

        public List<ItemFulfillmentRead> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The item fulfillments body. */
    public static final class ItemFulfillments extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/item_fulfillments");

        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public List<Object> activity_logs;
        public ItemFulfillmentsItemFulfillment item_fulfillment;
    }

    /** The item fulfillment updated body. */
    public static final class ItemFulfillmentUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/item_fulfillments/{fulfillment_id}");

        public ItemFulfillmentUpdatedDetail item_fulfillment;
        public String note;
        public String status;
    }

    /** What {@code create_item_fulfillment_note} answers, under {@code note}. */
    public static final class ItemFulfillmentNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/item_fulfillments/{fulfillment_id}/notes");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public String href;
        public String id;
        public ItemFulfillmentNotesDetail item_fulfillment;
        public String object;
    }

    /** A {@link ItemFulfillmentRead}'s {@code account}. */
    public static final class ItemFulfillmentReadAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillmentRead}'s {@code invoice}. */
    public static final class ItemFulfillmentReadInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link ItemFulfillmentRead}'s {@code fulfillments}. */
    public static final class ItemFulfillmentReadRow extends Dto {
        public Long fulfillment_index;
        public Double fulfillment_qty;
        public Double fulfillment_sent_qty;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object inventory_tracking;
        public ItemFulfillmentReadRowItem item;
        public String object;
        public Double pending_return;
        public Double returned;
        public String serving_status;
        public String uom;
        public Long uom_precision;
    }

    /** A {@link ItemFulfillmentRead}'s {@code created_by}. */
    public static final class ItemFulfillmentReadCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillmentRead}'s {@code last_updated_by}. */
    public static final class ItemFulfillmentReadLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillments}'s {@code item_fulfillment}. */
    public static final class ItemFulfillmentsItemFulfillment extends Dto {
        public List<Object> activity_logs;
        public String code;
        public String fulfillment_date;
        /** Null in every record measured, so untyped. */
        public Object fulfillment_note;
        public String fulfillment_status;
        public String href;
        public String id;
        public String object;
        public String status;
        public Long version;
    }

    /** A {@link ItemFulfillmentUpdated}'s {@code item_fulfillment}. */
    public static final class ItemFulfillmentUpdatedDetail extends Dto {
        public String code;
        public String href;
        public String id;
        public String object;
        public String status;
        public Long version;
    }

    /** A {@link ItemFulfillmentNotes}'s {@code item_fulfillment}. */
    public static final class ItemFulfillmentNotesDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link ItemFulfillmentReadRow}'s {@code item}. */
    public static final class ItemFulfillmentReadRowItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillment}'s {@code account}. */
    public static final class ItemFulfillmentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillment}'s {@code invoice}. */
    public static final class ItemFulfillmentInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link ItemFulfillment}'s {@code fulfillments}. */
    public static final class ItemFulfillmentRow extends Dto {
        public Long fulfillment_index;
        public Double fulfillment_qty;
        public Double fulfillment_sent_qty;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object inventory_tracking;
        public ItemFulfillmentRowItem item;
        public String object;
        public Double pending_return;
        public Double returned;
        public String serving_status;
        public String uom;
        public Long uom_precision;
    }

    /** A {@link ItemFulfillment}'s {@code created_by}. */
    public static final class ItemFulfillmentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillment}'s {@code last_updated_by}. */
    public static final class ItemFulfillmentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ItemFulfillmentRow}'s {@code item}. */
    public static final class ItemFulfillmentRowItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private ItemFulfillmentsDto() {
    }

    /**
     * One fulfillment note as the notes listing and read answer it; the note is stored on the
     * fulfillment's sale invoice.
     */
    public static final class ItemFulfillmentNote extends Dto {
        public List<ItemFulfillmentNoteAttachment> attachments;
        public String content;
        public ItemFulfillmentCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public ItemFulfillmentNotesDetail item_fulfillment;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<ItemFulfillmentNoteWatcher> watchers;
    }

    /** One entry of a {@link ItemFulfillmentNote}'s {@code attachments}. */
    public static final class ItemFulfillmentNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link ItemFulfillmentNote}'s {@code watchers}. */
    public static final class ItemFulfillmentNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** One page of {@code ItemFulfillmentNote} records, under {@code data}. */
    public static final class ItemFulfillmentNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_fulfillments/{fulfillment_id}/notes");

        public List<ItemFulfillmentNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /**
     * What {@code getItemFulfillmentNote} answers: the note under {@code item_fulfillment.note}.
     */
    public static final class ItemFulfillmentNoteRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/item_fulfillments/{fulfillment_id}/notes/{note_id}");

        @Override
        protected String envelopeKey() {
            return "item_fulfillment";
        }

        public ItemFulfillmentNote note;
    }
}
