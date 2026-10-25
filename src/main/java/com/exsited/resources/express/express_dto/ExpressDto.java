package com.exsited.resources.express.express_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the express bodies. */
public final class ExpressDto {

    /** What {@code create_express} answers, under {@code express}. */
    public static final class Express extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/express");

        @Override
        protected String envelopeKey() {
            return "express";
        }

        public ExpressAccount account;
        public List<ExpressActivityLog> activity_logs;
        public Boolean allow_contract;
        /** Null in every record measured, so untyped. */
        public Object billing_address;
        public ExpressBillingPreferences billing_preferences;
        public String billing_start_date;
        public List<Object> communication_preferences;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public ExpressCurrency currency;
        public List<Object> custom_attributes;
        public ExpressCustomForm custom_form;
        /** Null in every record measured, so untyped. */
        public Object customer_purchase_order_id;
        /** Null in every record measured, so untyped. */
        public Object description;
        /** Null in every record measured, so untyped. */
        public Object discount_profile;
        public String display_name;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object invoice_note;
        public ExpressKpis kpis;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public List<ExpressLine> lines;
        /** Null in every record measured, so untyped. */
        public Object manager;
        public String name;
        public String next_billing_from_date;
        public String object;
        public String order_start_date;
        public String order_type;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        /** Null in every record measured, so untyped. */
        public Object referral_account;
        /** Null in every record measured, so untyped. */
        public Object sales_rep;
        /** Null in every record measured, so untyped. */
        public Object shipping_address;
        public Double shipping_cost;
        public List<Object> shipping_line_items;
        /** Null in every record measured, so untyped. */
        public Object shipping_profile;
        public String status;
        public String subtotal;
        public String tax;
        public ExpressTimeZone time_zone;
        public String total;
        public Long version;
    }

    /** A {@link Express}'s {@code currency}. */
    public static final class ExpressCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Express}'s {@code time_zone}. */
    public static final class ExpressTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Express}'s {@code custom_form}. */
    public static final class ExpressCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Express}'s {@code account}. */
    public static final class ExpressAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Express}'s {@code billing_preferences}. */
    public static final class ExpressBillingPreferences extends Dto {
        public String billing_period;
        public String communication_profile;
        public Boolean consolidate_invoice;
        public String fulfillment_mode;
        public String fulfillment_term;
        public String invoice_mode;
        public String invoice_term;
        public String payment_mode;
        public String payment_processor;
        public String payment_term;
        public String payment_term_alignment;
        public ExpressBillingPreferencesProformaProperties proforma_properties;
    }

    /** One entry of a {@link Express}'s {@code lines}. */
    public static final class ExpressLine extends Dto {
        public String charge_item_uuid;
        public String expected_delivery_date;
        public String id;
        public ExpressLineItem item;
        public ExpressLineItemAccountingCode item_accounting_code;
        public String item_charge_type;
        public List<Object> item_custom_attributes;
        public String item_description;
        public String item_invoice_note;
        public Long item_order_quantity;
        public ExpressLineItemPriceSnapshot item_price_snapshot;
        public ExpressLineItemPriceTax item_price_tax;
        public ExpressLineItemSaleTaxConfiguration item_sale_tax_configuration;
        public String item_type;
        public String object;
        public ExpressLinePreOrderStockDetails pre_order_stock_details;
        public Double shipping_cost;
        public String subtotal;
        public String tax;
        public Boolean tax_exempt_when_sold;
        public String total;
        public Long version;
    }

    /** A {@link Express}'s {@code kpis}. */
    public static final class ExpressKpis extends Dto {
        /** Null in every record measured, so untyped. */
        public Object deleted_at;
        public String estimated_total;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_at;
        /** Null in every record measured, so untyped. */
        public Object last_changed_at;
        /** Null in every record measured, so untyped. */
        public Object last_invoice_issue_date;
        public String last_invoice_total;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_at;
        public String monthly_recurring_revenue;
        /** Null in every record measured, so untyped. */
        public Object next_invoice_issue_date;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object start_date;
        public String total_collected;
        public String total_due;
        public Long total_invoice;
        public String total_outstanding;
        public String total_revenue;
    }

    /** One entry of a {@link Express}'s {@code activity_logs}. */
    public static final class ExpressActivityLog extends Dto {
        public String activity;
    }

    /** A {@link ExpressBillingPreferences}'s {@code proforma_properties}. */
    public static final class ExpressBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link ExpressLine}'s {@code item}. */
    public static final class ExpressLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String uuid;
    }

    /** A {@link ExpressLine}'s {@code item_price_snapshot}. */
    public static final class ExpressLineItemPriceSnapshot extends Dto {
        public ExpressLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link ExpressLine}'s {@code item_price_tax}. */
    public static final class ExpressLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link ExpressLine}'s {@code item_sale_tax_configuration}. */
    public static final class ExpressLineItemSaleTaxConfiguration extends Dto {
        public String sale_price_is_based_on;
        public ExpressLineItemSaleTaxConfigurationTaxCode tax_code;
    }

    /** A {@link ExpressLine}'s {@code item_accounting_code}. */
    public static final class ExpressLineItemAccountingCode extends Dto {
        public String sales_revenue;
    }

    /** A {@link ExpressLine}'s {@code pre_order_stock_details}. */
    public static final class ExpressLinePreOrderStockDetails extends Dto {
        public String pending_reserve;
        public String reserved;
        public String sold;
    }

    /** A {@link ExpressLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class ExpressLineItemPriceSnapshotPricingRule extends Dto {
        public Double price;
        public String price_period;
        public String price_type;
        public String pricing_level;
        public String pricing_method;
        public String pricing_schedule;
        public String uom;
        public String uuid;
        public String version;
        public String warehouse;
    }

    /** A {@link ExpressLineItemSaleTaxConfiguration}'s {@code tax_code}. */
    public static final class ExpressLineItemSaleTaxConfigurationTaxCode extends Dto {
        public String code;
        public String link;
        public String rate;
        public String uuid;
    }

    private ExpressDto() {
    }
}
