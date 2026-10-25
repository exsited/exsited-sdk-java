package com.exsited.resources.orders.orders_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the orders bodies. */
public final class OrdersDto {

    /** The order body. */
    public static final class Order extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders");

        public OrderAccount account;
        public Boolean allow_contract;
        public OrderBillingAddress billing_address;
        public OrderBillingPreferencesEntry billing_preferences;
        public String billing_start_date;
        public String billing_start_day_of_month;
        public List<OrderCommunicationPreference> communication_preferences;
        public OrderContractProperties contract_properties;
        /** never populated in 4 measured records. */
        public Object created_by;
        public String created_on;
        public OrderCurrency currency;
        public OrderCustomForm custom_form;
        public String customer_purchase_order_id;
        /** 2 measured, null on 2. */
        public String description;
        public OrderDiscountProfile discount_profile;
        public String display_name;
        public String id;
        /** 2 measured, null on 2. */
        public String invoice_note;
        /** never populated in 4 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        /** 2 measured, null on 2. */
        public OrderManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        /** 2 measured, null on 2. */
        public String origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        public OrderReferralAccount referral_account;
        public OrderSalesRep sales_rep;
        public OrderShippingAddress shipping_address;
        public Double shipping_cost;
        public OrderShippingProfile shipping_profile;
        public String status;
        public OrderTimeZone time_zone;
        public Long version;
    }

    /** A page of Order records. */
    public static final class OrderList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders");

        public List<Order> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The order note body. */
    public static final class OrderNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/notes");

        public List<OrderNoteAttachment> attachments;
        public String content;
        public OrderNoteCreatedBy created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object group;
        public String href;
        public String id;
        public OrderNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public OrderNoteDetail order;
        /** never populated in 2 measured records. */
        public Object user;
        public Long version;
        public List<OrderNoteWatcher> watchers;
    }

    /** A page of OrderNote records. */
    public static final class OrderNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/notes");

        public List<OrderNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The order read body. */
    public static final class OrderRead extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}",
                "GET /api/{api_version}/orders/{order_id}/lines",
                "POST /api/{api_version}/orders",
                "POST /api/{api_version}/orders/{order_id}/activate_draft",
                "PATCH /api/{api_version}/orders/{order_id}/billing_preferences",
                "PUT /api/{api_version}/orders/{order_id}/billing_preferences",
                "POST /api/{api_version}/orders/{order_id}/cancel",
                "POST /api/{api_version}/orders/{order_id}/change",
                "POST /api/{api_version}/orders/{order_id}/convert_to_order",
                "PATCH /api/{api_version}/orders/{order_id}/information",
                "POST /api/{api_version}/orders/{order_id}/reactivate",
                "POST /api/{api_version}/orders/{order_id}/relinquish",
                "PATCH /api/{api_version}/orders/{order_id}/lines/{line_uuid}",
                "PATCH /api/{api_version}/orders/{order_id}/lines/{line_uuid}/billing_preferences",
                "PUT /api/{api_version}/orders/{order_id}/lines/{line_uuid}/billing_preferences");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public OrderReadAccount account;
        public List<OrderReadActivityLog> activity_logs;
        public Boolean allow_contract;
        public OrderReadBillingAddress billing_address;
        public OrderReadBillingPreferences billing_preferences;
        public String billing_start_date;
        public String billing_start_day_of_month;
        public List<OrderReadCommunicationPreference> communication_preferences;
        /** Sent for a contract order, on the create answer and on {@code getOrder}. */
        public OrderReadContractProperties contract_properties;
        /** never populated in 1 measured record. */
        public Object created_by;
        public String created_on;
        public OrderReadCurrency currency;
        public List<OrderReadCustomAttribute> custom_attributes;
        public OrderReadCustomForm custom_form;
        public String customer_purchase_order_id;
        public String description;
        public OrderReadDiscountProfile discount_profile;
        public String display_name;
        public String id;
        public String invoice_note;
        public OrderReadKpis kpis;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public List<OrderReadLine> lines;
        public OrderReadManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        public String origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        /** never populated in 1 measured record. */
        public Object referral_account;
        /** never populated in 1 measured record. */
        public Object sales_rep;
        public OrderReadShippingAddress shipping_address;
        public Double shipping_cost;
        public List<Object> shipping_line_items;
        /** never populated in 1 measured record. */
        public Object shipping_profile;
        public String status;
        public String subtotal;
        public String tax;
        public OrderReadTimeZone time_zone;
        public String total;
        public Long version;
    }

    /** The order information body. */
    public static final class OrderInformation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/information");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public OrderInformationAccount account;
        public Boolean allow_contract;
        public OrderInformationBillingAddress billing_address;
        public String billing_start_date;
        public String billing_start_day_of_month;
        public List<OrderInformationCommunicationPreference> communication_preferences;
        /** never populated in 1 measured record. */
        public Object created_by;
        public String created_on;
        public OrderInformationCurrency currency;
        public OrderInformationCustomForm custom_form;
        /** never populated in 1 measured record. */
        public Object customer_purchase_order_id;
        public String description;
        public OrderInformationDiscountProfile discount_profile;
        public String display_name;
        public String id;
        public String invoice_note;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public OrderInformationManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        public String origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        /** never populated in 1 measured record. */
        public Object referral_account;
        /** never populated in 1 measured record. */
        public Object sales_rep;
        public OrderInformationShippingAddress shipping_address;
        public Double shipping_cost;
        /** never populated in 1 measured record. */
        public Object shipping_profile;
        public String status;
        public OrderInformationTimeZone time_zone;
        public Long version;
    }

    /** The account orders body. */
    public static final class AccountOrders extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/orders",
                "GET /api/{api_version}/orders/{order_id}/custom_objects",
                "GET /api/{api_version}/orders/{order_id}/custom_objects_instances",
                "GET /api/{api_version}/orders/{order_id}/item_fulfillments");

        public List<AccountOrdersData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The order billing preferences body. */
    public static final class OrderBillingPreferences extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/billing_preferences");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public OrderBillingPreferencesDetail billing_preferences;
        public String object;
    }

    /** What {@code get_order_payment_methods} answers, under {@code order}. */
    public static final class OrderPaymentMethods extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/payment_methods");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public List<OrderPaymentMethodsRow> payment_methods;
    }

    /** What {@code get_order_pdf} answers, under {@code order}. */
    public static final class OrderPdf extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/pdf");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public OrderPdfDetail pdf;
    }

    /** What {@code get_order_line} answers, under {@code order_line}. */
    public static final class OrderLine extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/lines/{line_uuid}");

        @Override
        protected String envelopeKey() {
            return "order_line";
        }

        public String charge_item_uuid;
        public String expected_delivery_date;
        public String id;
        public OrderLineItem item;
        public OrderLineItemAccountingCode item_accounting_code;
        public String item_charge_type;
        public List<Object> item_custom_attributes;
        public String item_description;
        public String item_invoice_note;
        public Long item_order_quantity;
        public OrderLineItemPriceSnapshot item_price_snapshot;
        public OrderLineItemPriceTax item_price_tax;
        public OrderLineItemProperties item_properties;
        public OrderLineItemSaleTaxConfiguration item_sale_tax_configuration;
        public String item_type;
        public String object;
        public OrderLinePreOrderStockDetails pre_order_stock_details;
        public Double shipping_cost;
        public String subtotal;
        public String tax;
        public Boolean tax_exempt_when_sold;
        public String total;
        public Long version;
    }

    /** What {@code get_order_note} answers, under {@code note}. */
    public static final class OrderNoteView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/notes/{order_note_id}",
                "POST /api/{api_version}/orders/{order_id}/notes",
                "PATCH /api/{api_version}/orders/{order_id}/notes/{order_note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<OrderNoteViewAttachment> attachments;
        public String content;
        public OrderNoteViewCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public OrderNoteViewLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public OrderNoteViewDetail order;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<OrderNoteViewWatcher> watchers;
    }

    /** What {@code get_order_payment_method} answers, under {@code order}. */
    public static final class OrderPaymentMethod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/payment_methods/{payment_method_ref}");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public OrderPaymentMethodDetail payment_method;
    }

    /** One page of what {@code get_order_note_files} lists, under {@code data}. */
    public static final class OrderNoteFileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/notes/{order_note_id}/files");

        public List<OrderNoteFileEntry> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_order_note_file} answers, under {@code file}. */
    public static final class OrderNoteFile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/notes/{order_note_id}/files/{order_file_id}");

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

    /** What {@code create_order_discard_draft} answers, under {@code order}. */
    public static final class OrderDiscardDraft extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/orders/{order_id}/discard_draft");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public String message;
    }

    /** What {@code createOrderExtendTrial} answers, under {@code order}. */
    public static final class OrderExtendTrial extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/orders/{order_id}/extend_trial");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public String message;
    }

    /** One entry of a {@link AccountOrders}'s {@code data}. */
    public static final class AccountOrdersData extends Dto {
        public AccountOrdersDataDetail account;
        public Boolean allow_contract;
        public AccountOrdersDataBillingAddress billing_address;
        public AccountOrdersDataBillingPreferences billing_preferences;
        public String billing_start_date;
        public String billing_start_day_of_month;
        public List<AccountOrdersDataCommunicationPreference> communication_preferences;
        public AccountOrdersDataContractProperties contract_properties;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public AccountOrdersDataCurrency currency;
        public AccountOrdersDataCustomForm custom_form;
        public String customer_purchase_order_id;
        public String description;
        public AccountOrdersDataDiscountProfile discount_profile;
        public String display_name;
        public String id;
        public String invoice_note;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public AccountOrdersDataManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        public String origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        /** Null in every record measured, so untyped. */
        public Object referral_account;
        /** Null in every record measured, so untyped. */
        public Object sales_rep;
        public AccountOrdersDataShippingAddress shipping_address;
        public Double shipping_cost;
        /** Null in every record measured, so untyped. */
        public Object shipping_profile;
        public String status;
        public AccountOrdersDataTimeZone time_zone;
        public Long version;
    }

    /** A {@link OrderRead}'s {@code currency}. */
    public static final class OrderReadCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderRead}'s {@code time_zone}. */
    public static final class OrderReadTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderRead}'s {@code custom_form}. */
    public static final class OrderReadCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderRead}'s {@code manager}. */
    public static final class OrderReadManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderRead}'s {@code account}. */
    public static final class OrderReadAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderRead}'s {@code discount_profile}. */
    public static final class OrderReadDiscountProfile extends Dto {
        public String amount;
        public String href;
        public String id;
        public String name;
        public String object;
        public String redemption_code;
        public String type;
    }

    /** A {@link OrderRead}'s {@code billing_address}. */
    public static final class OrderReadBillingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** A {@link OrderRead}'s {@code shipping_address}. */
    public static final class OrderReadShippingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link OrderRead}'s {@code communication_preferences}. */
    public static final class OrderReadCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link OrderRead}'s {@code contract_properties}, sent for a contract order. */
    public static final class OrderReadContractProperties extends Dto {
        public Boolean allow_downgrade;
        public Boolean allow_early_termination;
        public Boolean allow_postponement;
        public Boolean allow_trial;
        public Boolean allow_upgrade;
        public Boolean apply_early_termination_charge;
        public String auto_renewal_ends_on;
        public Boolean auto_renewal_require_customer_acceptance;
        public String auto_renewal_term;
        public String early_termination_charge_type;
        public String early_termination_minimum_period;
        public String initial_contract_term;
        /** A string, {@code "true"} or {@code "false"}, not a boolean. */
        public String renew_automatically;
        public Boolean require_customer_acceptance;
        public Boolean requires_payment_method;
        public Boolean start_contract_after_trial_ends;
        public String termination_accounting_code;
        public String termination_fixed_charge;
        public String termination_notice_period;
        public String trial_end_date;
        public String trial_period;
        public Boolean trial_requires_payment_method;
    }

    /** A {@link OrderRead}'s {@code billing_preferences}. */
    public static final class OrderReadBillingPreferences extends Dto {
        public String billing_period;
        public String communication_profile;
        public Boolean consolidate_invoice;
        public String consolidate_key;
        public String fulfillment_mode;
        public String fulfillment_term;
        public String invoice_mode;
        public String invoice_term;
        public String payment_mode;
        public String payment_processor;
        public String payment_term;
        public String payment_term_alignment;
        public OrderReadBillingPreferencesProformaProperties proforma_properties;
    }

    /** One entry of a {@link OrderRead}'s {@code custom_attributes}. */
    public static final class OrderReadCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        /**
         * A string or a list of strings, as the API sends it: a {@code STRING} attribute
         * answers a string, which a list declaration would read as null.
         */
        public Object value;
    }

    /** One entry of a {@link OrderRead}'s {@code lines}. */
    public static final class OrderReadLine extends Dto {
        public OrderReadLineBooking booking;
        public String charge_item_uuid;
        public String expected_delivery_date;
        public String id;
        public OrderReadLineItem item;
        public OrderReadLineItemAccountingCode item_accounting_code;
        public String item_charge_type;
        public List<OrderReadLineItemCustomAttribute> item_custom_attributes;
        public String item_description;
        public String item_invoice_note;
        public Long item_order_quantity;
        public OrderReadLineItemPriceSnapshot item_price_snapshot;
        public OrderReadLineItemPriceTax item_price_tax;
        public OrderReadLineItemProperties item_properties;
        public OrderReadLineItemSaleTaxConfiguration item_sale_tax_configuration;
        public String item_type;
        public String object;
        public OrderReadLinePreOrderStockDetails pre_order_stock_details;
        public Double shipping_cost;
        public String subtotal;
        public String tax;
        public Boolean tax_exempt_when_sold;
        public String total;
        public Long version;
    }

    /** A {@link OrderRead}'s {@code kpis}. */
    public static final class OrderReadKpis extends Dto {
        /** Null in every record measured, so untyped. */
        public Object created_on;
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
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
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

    /** One entry of a {@link OrderRead}'s {@code activity_logs}. */
    public static final class OrderReadActivityLog extends Dto {
        public String activity;
    }

    /** A {@link OrderBillingPreferences}'s {@code billing_preferences}. */
    public static final class OrderBillingPreferencesDetail extends Dto {
        public String billing_period;
        public String communication_profile;
        public Boolean consolidate_invoice;
        public String consolidate_key;
        public String fulfillment_mode;
        public String fulfillment_term;
        public String invoice_mode;
        public String invoice_term;
        public String payment_mode;
        public String payment_processor;
        public String payment_term;
        public String payment_term_alignment;
        public OrderBillingPreferencesDetailProformaProperties proforma_properties;
    }

    /** A {@link OrderInformation}'s {@code currency}. */
    public static final class OrderInformationCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderInformation}'s {@code time_zone}. */
    public static final class OrderInformationTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderInformation}'s {@code custom_form}. */
    public static final class OrderInformationCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderInformation}'s {@code manager}. */
    public static final class OrderInformationManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderInformation}'s {@code account}. */
    public static final class OrderInformationAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderInformation}'s {@code discount_profile}. */
    public static final class OrderInformationDiscountProfile extends Dto {
        public String amount;
        public String href;
        public String id;
        public String name;
        public String object;
        public String redemption_code;
        public String type;
    }

    /** A {@link OrderInformation}'s {@code billing_address}. */
    public static final class OrderInformationBillingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** A {@link OrderInformation}'s {@code shipping_address}. */
    public static final class OrderInformationShippingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link OrderInformation}'s {@code communication_preferences}. */
    public static final class OrderInformationCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** One entry of a {@link OrderPaymentMethods}'s {@code payment_methods}. */
    public static final class OrderPaymentMethodsRow extends Dto {
        public String created_by;
        public String created_on;
        @WireName("default")
        public Boolean isDefault;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object last_used_at;
        public String object;
        public Long payment_count;
        public OrderPaymentMethodsRowProcessor processor;
        public String processor_type;
        public String reference;
        public Boolean restricted_to_orders;
        public String specified_order_ids;
        public String status;
        public Long version;
    }

    /** A {@link OrderPdf}'s {@code pdf}. */
    public static final class OrderPdfDetail extends Dto {
        public String download_url;
        public Long file_size;
        public String id;
        public String object;
        public String preview_url;
    }

    /** A {@link OrderLine}'s {@code item}. */
    public static final class OrderLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String uuid;
    }

    /** A {@link OrderLine}'s {@code item_price_snapshot}. */
    public static final class OrderLineItemPriceSnapshot extends Dto {
        public OrderLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link OrderLine}'s {@code item_price_tax}. */
    public static final class OrderLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link OrderLine}'s {@code item_sale_tax_configuration}. */
    public static final class OrderLineItemSaleTaxConfiguration extends Dto {
        public String sale_price_is_based_on;
        public OrderLineItemSaleTaxConfigurationTaxCode tax_code;
    }

    /** A {@link OrderLine}'s {@code item_accounting_code}. */
    public static final class OrderLineItemAccountingCode extends Dto {
        public String sales_revenue;
    }

    /** A {@link OrderLine}'s {@code pre_order_stock_details}. */
    public static final class OrderLinePreOrderStockDetails extends Dto {
        public String pending_reserve;
        public String reserved;
        public String sold;
    }

    /** A {@link OrderLine}'s {@code item_properties}. */
    public static final class OrderLineItemProperties extends Dto {
        public String charging_and_billing_alignment;
        public String charging_start_date;
        public String pro_rata_partial_pricing_period;
    }

    /** A {@link OrderNoteView}'s {@code order}. */
    public static final class OrderNoteViewDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link OrderNoteView}'s {@code attachments}. */
    public static final class OrderNoteViewAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link OrderNoteView}'s {@code watchers}. */
    public static final class OrderNoteViewWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link OrderNoteView}'s {@code created_by}. */
    public static final class OrderNoteViewCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link OrderNoteView}'s {@code last_updated_by}. */
    public static final class OrderNoteViewLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link OrderPaymentMethod}'s {@code payment_method}. */
    public static final class OrderPaymentMethodDetail extends Dto {
        public String created_by;
        public String created_on;
        @WireName("default")
        public Boolean isDefault;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object last_used_at;
        public String object;
        public Long payment_count;
        public OrderPaymentMethodDetailProcessor processor;
        public String processor_type;
        public String reference;
        public Boolean restricted_to_orders;
        public String specified_order_ids;
        public String status;
        public Long version;
    }

    /** One entry of a {@link OrderNoteFileList}'s {@code data}. */
    public static final class OrderNoteFileEntry extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link AccountOrdersData}'s {@code currency}. */
    public static final class AccountOrdersDataCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersData}'s {@code time_zone}. */
    public static final class AccountOrdersDataTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersData}'s {@code custom_form}. */
    public static final class AccountOrdersDataCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersData}'s {@code manager}. */
    public static final class AccountOrdersDataManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersData}'s {@code account}. */
    public static final class AccountOrdersDataDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersData}'s {@code discount_profile}. */
    public static final class AccountOrdersDataDiscountProfile extends Dto {
        public String amount;
        public String href;
        public String id;
        public String name;
        public String object;
        public String redemption_code;
        public String type;
    }

    /** A {@link AccountOrdersData}'s {@code billing_address}. */
    public static final class AccountOrdersDataBillingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** A {@link AccountOrdersData}'s {@code shipping_address}. */
    public static final class AccountOrdersDataShippingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link AccountOrdersData}'s {@code communication_preferences}. */
    public static final class AccountOrdersDataCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link AccountOrdersData}'s {@code billing_preferences}. */
    public static final class AccountOrdersDataBillingPreferences extends Dto {
        public String billing_period;
        public String communication_profile;
        public Boolean consolidate_invoice;
        public String consolidate_key;
        public String fulfillment_mode;
        public String fulfillment_term;
        public String invoice_mode;
        public String invoice_term;
        public String payment_mode;
        public String payment_processor;
        public String payment_term;
        public String payment_term_alignment;
        public AccountOrdersDataBillingPreferencesProformaProperties proforma_properties;
    }

    /** A {@link AccountOrdersData}'s {@code contract_properties}. */
    public static final class AccountOrdersDataContractProperties extends Dto {
        public Boolean allow_downgrade;
        public Boolean allow_downgrade_charge;
        public Boolean allow_early_termination;
        public Boolean allow_postponement;
        public Boolean allow_trial;
        public Boolean allow_upgrade;
        public String auto_renewal_ends_on;
        public Boolean auto_renewal_require_customer_acceptance;
        public String auto_renewal_term;
        public String downgrade_charge_fixed;
        public String downgrade_charge_type;
        public String initial_contract_term;
        public String period_before_downgrade;
        public String renew_automatically;
        public Boolean require_customer_acceptance;
        public Boolean requires_payment_method;
        public String termination_accounting_code;
        public String termination_notice_period;
    }

    /** A {@link Order}'s {@code currency}. */
    public static final class OrderCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code time_zone}. */
    public static final class OrderTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code custom_form}. */
    public static final class OrderCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code manager}. */
    public static final class OrderManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code sales_rep}. */
    public static final class OrderSalesRep extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code shipping_profile}. */
    public static final class OrderShippingProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code account}. */
    public static final class OrderAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Order}'s {@code referral_account}. */
    public static final class OrderReferralAccount extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link Order}'s {@code discount_profile}. */
    public static final class OrderDiscountProfile extends Dto {
        public String amount;
        public String href;
        public String id;
        public String name;
        public String object;
        public String redemption_code;
        public String type;
    }

    /** A {@link Order}'s {@code billing_address}. */
    public static final class OrderBillingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** A {@link Order}'s {@code shipping_address}. */
    public static final class OrderShippingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link Order}'s {@code communication_preferences}. */
    public static final class OrderCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link Order}'s {@code billing_preferences}. */
    public static final class OrderBillingPreferencesEntry extends Dto {
        public String billing_period;
        public String communication_profile;
        public Boolean consolidate_invoice;
        public String consolidate_key;
        public String fulfillment_mode;
        public String fulfillment_term;
        public String invoice_mode;
        public String invoice_term;
        public String payment_mode;
        public String payment_processor;
        public String payment_term;
        public String payment_term_alignment;
        public OrderBillingPreferencesEntryProformaProperties proforma_properties;
    }

    /** A {@link Order}'s {@code contract_properties}. */
    public static final class OrderContractProperties extends Dto {
        public Boolean allow_downgrade;
        public Boolean allow_downgrade_charge;
        public Boolean allow_early_termination;
        public Boolean allow_postponement;
        public Boolean allow_trial;
        public Boolean allow_upgrade;
        public String auto_renewal_ends_on;
        public Boolean auto_renewal_require_customer_acceptance;
        public String auto_renewal_term;
        public String downgrade_charge_fixed;
        public String downgrade_charge_type;
        public String initial_contract_term;
        public String period_before_downgrade;
        public String renew_automatically;
        public Boolean require_customer_acceptance;
        public Boolean requires_payment_method;
        public String termination_accounting_code;
        public String termination_notice_period;
    }

    /** A {@link OrderReadBillingPreferences}'s {@code proforma_properties}. */
    public static final class OrderReadBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link OrderReadLine}'s {@code item}. */
    public static final class OrderReadLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String uuid;
    }

    /** A {@link OrderReadLine}'s {@code item_price_snapshot}. */
    public static final class OrderReadLineItemPriceSnapshot extends Dto {
        public OrderReadLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link OrderReadLine}'s {@code item_price_tax}. */
    public static final class OrderReadLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link OrderReadLine}'s {@code item_sale_tax_configuration}. */
    public static final class OrderReadLineItemSaleTaxConfiguration extends Dto {
        public String sale_price_is_based_on;
        public OrderReadLineItemSaleTaxConfigurationTaxCode tax_code;
    }

    /** A {@link OrderReadLine}'s {@code item_accounting_code}. */
    public static final class OrderReadLineItemAccountingCode extends Dto {
        public Object sales_revenue;
    }

    /** One entry of a {@link OrderReadLine}'s {@code item_custom_attributes}. */
    public static final class OrderReadLineItemCustomAttribute extends Dto {
        public String name;
        /** A string or a list of strings, as {@link OrderReadCustomAttribute#value} is. */
        public Object value;
    }

    /** A {@link OrderReadLine}'s {@code pre_order_stock_details}. */
    public static final class OrderReadLinePreOrderStockDetails extends Dto {
        public String pending_reserve;
        public String reserved;
        public String sold;
    }

    /** A {@link OrderReadLine}'s {@code item_properties}. */
    public static final class OrderReadLineItemProperties extends Dto {
        public String billing_mode;
        public String charging_and_billing_alignment;
        public String charging_period;
        public String charging_start_date;
        public String pro_rata_partial_charging_period;
        public String pro_rata_partial_pricing_period;
    }

    /** A {@link OrderReadLine}'s {@code booking}. */
    public static final class OrderReadLineBooking extends Dto {
        public String booking_end_time;
        public String booking_start_time;
        public String labour_uuid;
    }

    /** A {@link OrderBillingPreferencesDetail}'s {@code proforma_properties}. */
    public static final class OrderBillingPreferencesDetailProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link OrderNote}'s {@code order}. */
    public static final class OrderNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link OrderNote}'s {@code attachments}. */
    public static final class OrderNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link OrderNote}'s {@code watchers}. */
    public static final class OrderNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link OrderNote}'s {@code created_by}. */
    public static final class OrderNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link OrderNote}'s {@code last_updated_by}. */
    public static final class OrderNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link OrderPaymentMethodsRow}'s {@code processor}. */
    public static final class OrderPaymentMethodsRowProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class OrderLineItemPriceSnapshotPricingRule extends Dto {
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

    /** A {@link OrderLineItemSaleTaxConfiguration}'s {@code tax_code}. */
    public static final class OrderLineItemSaleTaxConfigurationTaxCode extends Dto {
        public String code;
        public String link;
        public String rate;
        public String uuid;
    }

    /** A {@link OrderPaymentMethodDetail}'s {@code processor}. */
    public static final class OrderPaymentMethodDetailProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link AccountOrdersDataBillingPreferences}'s {@code proforma_properties}. */
    public static final class AccountOrdersDataBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link OrderBillingPreferencesEntry}'s {@code proforma_properties}. */
    public static final class OrderBillingPreferencesEntryProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        public String acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link OrderReadLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class OrderReadLineItemPriceSnapshotPricingRule extends Dto {
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

    /** A {@link OrderReadLineItemSaleTaxConfiguration}'s {@code tax_code}. */
    public static final class OrderReadLineItemSaleTaxConfigurationTaxCode extends Dto {
        public String code;
        public String link;
        public Object rate;
        public String uuid;
    }

    private OrdersDto() {
    }

    /** What {@code getOrderKpis} answers, under {@code order_kpis}. */
    public static final class OrderKpis extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/kpis");

        @Override
        protected String envelopeKey() {
            return "order_kpis";
        }

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

    /** One custom object with the order's instances of it, under {@code custom_object}. */
    public static final class OrderCustomObject extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/custom_objects/{custom_object_id}",
                "GET /api/{api_version}/orders/{order_id}/custom_objects_instances/{custom_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_object";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object custom_component;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public String href;
        public String id;
        public List<Object> instances;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public PaginationDto pagination;
        public String relation;
        public String use_in;
    }

    /** What {@code getOrderItemFulfillment} answers, under {@code item_fulfillment}. */
    public static final class OrderItemFulfillment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/item_fulfillments/{fulfillment_id}");

        @Override
        protected String envelopeKey() {
            return "item_fulfillment";
        }

        public OrderItemFulfillmentAccount account;
        public String code;
        public OrderItemFulfillmentCreatedBy created_by;
        public String created_on;
        public Map<String, Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object delivery_status;
        public Boolean fulfillment_completed;
        public String fulfillment_date;
        /** Null in every record measured, so untyped. */
        public Object fulfillment_note;
        public String fulfillment_status;
        public List<OrderItemFulfillmentFulfillment> fulfillments;
        public String href;
        public String id;
        public OrderItemFulfillmentInvoice invoice;
        public OrderItemFulfillmentLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object order;
        public String status;
        public String tracking_number;
        public Long version;
    }

    /** What {@code getOrderPayment} answers, under {@code payment}. */
    public static final class OrderPayment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/payments/{payment_id}");

        @Override
        protected String envelopeKey() {
            return "payment";
        }

        public OrderPaymentAccount account;
        public OrderPaymentCreatedBy created_by;
        public String created_on;
        public List<Object> credit_applied;
        public List<Object> custom_attributes;
        public List<Object> gift_certificate_applied;
        public String id;
        public List<OrderPaymentInvoice> invoices;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public List<OrderPaymentPaymentApplied> payment_applied;
        public String payment_note;
        public String reconcile_status;
        public String status;
        public Long version;
    }

    /** What {@code getOrderQuotePartialInvoices} answers, under {@code order}. */
    public static final class OrderQuotePartialInvoices extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/orders/{order_id}/quote_partial_invoices");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public List<Object> partial_invoices;
    }

    /** A {@link OrderItemFulfillment}'s {@code account}. */
    public static final class OrderItemFulfillmentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderItemFulfillment}'s {@code created_by}. */
    public static final class OrderItemFulfillmentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link OrderItemFulfillment}'s {@code fulfillments}. */
    public static final class OrderItemFulfillmentFulfillment extends Dto {
        public Long fulfillment_index;
        public Double fulfillment_qty;
        public Double fulfillment_sent_qty;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object inventory_tracking;
        public OrderItemFulfillmentFulfillmentItem item;
        public String object;
        public Double pending_return;
        /** Null in every record measured, so untyped. */
        public Object returned;
        public String serving_status;
        /** Null in every record measured, so untyped. */
        public Object uom;
        /** Null in every record measured, so untyped. */
        public Object uom_precision;
    }

    /** A {@link OrderItemFulfillmentFulfillment}'s {@code item}. */
    public static final class OrderItemFulfillmentFulfillmentItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderItemFulfillment}'s {@code invoice}. */
    public static final class OrderItemFulfillmentInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link OrderItemFulfillment}'s {@code last_updated_by}. */
    public static final class OrderItemFulfillmentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link OrderPayment}'s {@code account}. */
    public static final class OrderPaymentAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link OrderPayment}'s {@code created_by}. */
    public static final class OrderPaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link OrderPayment}'s {@code invoices}. */
    public static final class OrderPaymentInvoice extends Dto {
        public String applied;
        public String due_date;
        public String id;
        public String issue_date;
        public String outstanding;
        public String total;
    }

    /** One entry of a {@link OrderPayment}'s {@code payment_applied}. */
    public static final class OrderPaymentPaymentApplied extends Dto {
        public String amount;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String method;
        public String processor;
        public String reference;
    }
}
