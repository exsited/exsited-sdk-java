package com.exsited.resources.purchase_express.purchase_express_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the purchase express bodies. */
public final class PurchaseExpressDto {

    private PurchaseExpressDto() {}

    /** What {@code createPurchaseExpress} answers, under {@code purchase_express}. */
    public static final class PurchaseExpress extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/purchase_express");

        @Override
        protected String envelopeKey() {
            return "purchase_express";
        }

        public PurchaseExpressAccount account;
        public List<Object> activity_logs;
        public String bill_total;
        public String code;
        public PurchaseExpressCreatedBy created_by;
        public String created_on;
        public PurchaseExpressCurrency currency;
        public List<Object> custom_attributes;
        public PurchaseExpressCustomForm custom_form;
        public String due_date;
        public String due_total;
        public Object expected_completion_date;
        public String href;
        public String id;
        public Object is_price_tax_inclusive;
        public String issue_date;
        public PurchaseExpressKpis kpis;
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public String operational_status;
        public Object origin;
        public String paid_amount;
        public Object purchase_order_note;
        public Object sale_order;
        public String status;
        public String subtotal;
        public Object supplier_invoice_id;
        public String tax_total;
        public String total;
        public Long version;
        public Object warehouse;
    }

    /** A {@link PurchaseExpress}'s {@code account}. */
    public static final class PurchaseExpressAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseExpress}'s {@code created_by}. */
    public static final class PurchaseExpressCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link PurchaseExpress}'s {@code currency}. */
    public static final class PurchaseExpressCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseExpress}'s {@code custom_form}. */
    public static final class PurchaseExpressCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link PurchaseExpress}'s {@code kpis}. */
    public static final class PurchaseExpressKpis extends Dto {
        public String estimated_total;
        public Object issue_date;
        public Object last_cancelled_on;
        public Object last_changed_on;
        public Object last_deleted_on;
        public Object last_invoice_issue_date;
        public String last_invoice_total;
        public Object last_reactivated_on;
        public String object;
        public String total_expense;
        public String total_outstanding;
        public String total_overdue;
        public Long total_purchase_invoice;
    }
}
