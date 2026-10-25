package com.exsited.resources.inventory_adjustments.inventory_adjustments_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the inventory adjustments bodies. */
public final class InventoryAdjustmentsDto {

    /** The inventory adjustment body. */
    public static final class InventoryAdjustment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/inventory_adjustments",
                "GET /api/{api_version}/inventory_adjustments/{inventory_adjustment_id}",
                "GET /api/{api_version}/items/{item_id}/inventory_adjustments");

        @Override
        protected String envelopeKey() {
            return "inventory_adjustment";
        }

        public String adjustment_amount;
        /** 1 measured, null on 1. */
        public String adjustment_type;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String href;
        public String id;
        public String item_id;
        public String item_name;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public String quantity_available;
        public String quantity_on_hand;
        public String quantity_on_order;
        public String quantity_on_purchase_return;
        public String quantity_on_reserve;
        public String quantity_on_return;
        public String quantity_promised;
        /** 1 measured, null on 1. */
        public String reference;
        public String status;
        public String warehouse_name;
    }

    /** A page of InventoryAdjustment records. */
    public static final class InventoryAdjustmentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/inventory_adjustments",
                "GET /api/{api_version}/items/{item_id}/inventory_adjustments");

        public List<InventoryAdjustment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private InventoryAdjustmentsDto() {
    }
}
