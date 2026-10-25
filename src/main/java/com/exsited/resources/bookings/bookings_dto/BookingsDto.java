package com.exsited.resources.bookings.bookings_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the bookings bodies. */
public final class BookingsDto {

    /** The booking body. */
    public static final class Booking extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking");

        public BookingAccount account;
        public Boolean allow_contract;
        public BookingBillingAddress billing_address;
        public BookingBillingPreferences billing_preferences;
        public String billing_start_date;
        public String billing_start_day_of_month;
        public List<BookingCommunicationPreference> communication_preferences;
        public BookingContractProperties contract_properties;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public BookingCurrency currency;
        public BookingCustomForm custom_form;
        public String customer_purchase_order_id;
        /** 1 measured, null on 1. */
        public String description;
        public BookingDiscountProfile discount_profile;
        public String display_name;
        public String id;
        /** 1 measured, null on 1. */
        public String invoice_note;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        /** 1 measured, null on 1. */
        public BookingManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        /** 1 measured, null on 1. */
        public String origin;
        public String original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        public BookingReferralAccount referral_account;
        public BookingSalesRep sales_rep;
        public BookingShippingAddress shipping_address;
        public Double shipping_cost;
        public BookingShippingProfile shipping_profile;
        public String status;
        public BookingTimeZone time_zone;
        public Long version;
    }

    /** A page of Booking records. */
    public static final class BookingList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking");

        public List<Booking> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The booking by id body. */
    public static final class BookingById extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}",
                "GET /api/{api_version}/booking/{booking_id}/information",
                "GET /api/{api_version}/booking/{booking_id}/lines",
                "PATCH /api/{api_version}/booking/{booking_id}/information",
                "PATCH /api/{api_version}/booking/{booking_id}/billing_preferences",
                "PATCH /api/{api_version}/booking/{booking_id}/lines/{line_uuid}",
                "PATCH /api/{api_version}/booking/{booking_id}/lines/{line_uuid}/billing_preferences");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public BookingByIdAccount account;
        /** Empty in every record measured; sent by the writes, not by the reads. */
        public List<Object> activity_logs;
        public Boolean allow_contract;
        public BookingByIdBillingAddress billing_address;
        public BookingByIdBillingPreferences billing_preferences;
        public String billing_start_date;
        public List<BookingByIdCommunicationPreference> communication_preferences;
        /** never populated in 1 measured record. */
        public Object created_by;
        public String created_on;
        public BookingByIdCurrency currency;
        public List<BookingByIdCustomAttribute> custom_attributes;
        public BookingByIdCustomForm custom_form;
        public String customer_purchase_order_id;
        public String description;
        /** never populated in 1 measured record. */
        public Object discount_profile;
        public String display_name;
        public String id;
        public String invoice_note;
        public BookingByIdKpis kpis;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public List<BookingByIdLine> lines;
        public BookingByIdManager manager;
        public String name;
        public String next_billing_from_date;
        public String next_billing_from_date_utc;
        public String object;
        public String order_start_date;
        public String order_type;
        public String origin;
        /** never populated in 1 measured record. */
        public Object original_total;
        public Boolean pre_order;
        public Boolean price_tax_inclusive;
        public Boolean quote_converted;
        public Boolean quote_order;
        /** never populated in 1 measured record. */
        public Object referral_account;
        /** never populated in 1 measured record. */
        public Object sales_rep;
        /** never populated in 1 measured record. */
        public Object shipping_address;
        public Double shipping_cost;
        public List<Object> shipping_line_items;
        /** never populated in 1 measured record. */
        public Object shipping_profile;
        public String status;
        public String subtotal;
        public String tax;
        public BookingByIdTimeZone time_zone;
        public String total;
        public Long version;
    }

    /** A {@link BookingById}'s {@code currency}. */
    public static final class BookingByIdCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingById}'s {@code time_zone}. */
    public static final class BookingByIdTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingById}'s {@code custom_form}. */
    public static final class BookingByIdCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingById}'s {@code manager}. */
    public static final class BookingByIdManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingById}'s {@code account}. */
    public static final class BookingByIdAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingById}'s {@code billing_address}. */
    public static final class BookingByIdBillingAddress extends Dto {
        public String address_line_1;
        public String address_line_2;
        /** Null in every record measured, so untyped. */
        public Object address_line_3;
        /** Null in every record measured, so untyped. */
        public Object address_line_4;
        /** Null in every record measured, so untyped. */
        public Object address_line_5;
        public String city;
        public String country;
        public String name;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link BookingById}'s {@code communication_preferences}. */
    public static final class BookingByIdCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link BookingById}'s {@code billing_preferences}. */
    public static final class BookingByIdBillingPreferences extends Dto {
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
        public BookingByIdBillingPreferencesProformaProperties proforma_properties;
    }

    /** One entry of a {@link BookingById}'s {@code custom_attributes}. */
    public static final class BookingByIdCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public Object value;
    }

    /** One entry of a {@link BookingById}'s {@code lines}. */
    public static final class BookingByIdLine extends Dto {
        public String charge_item_uuid;
        public String expected_delivery_date;
        public String id;
        public BookingByIdLineItem item;
        public BookingByIdLineItemAccountingCode item_accounting_code;
        public String item_charge_type;
        public List<Object> item_custom_attributes;
        public String item_description;
        public String item_invoice_note;
        public Long item_order_quantity;
        public BookingByIdLineItemPriceSnapshot item_price_snapshot;
        public BookingByIdLineItemPriceTax item_price_tax;
        public BookingByIdLineItemProperties item_properties;
        public BookingByIdLineItemSaleTaxConfiguration item_sale_tax_configuration;
        public String item_type;
        public String object;
        public BookingByIdLinePreOrderStockDetails pre_order_stock_details;
        public Double shipping_cost;
        public String subtotal;
        public String tax;
        public Boolean tax_exempt_when_sold;
        public String total;
        public Long version;
    }

    /** A {@link BookingById}'s {@code kpis}. */
    public static final class BookingByIdKpis extends Dto {
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

    /** A {@link Booking}'s {@code currency}. */
    public static final class BookingCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code time_zone}. */
    public static final class BookingTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code custom_form}. */
    public static final class BookingCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code manager}. */
    public static final class BookingManager extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code sales_rep}. */
    public static final class BookingSalesRep extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code shipping_profile}. */
    public static final class BookingShippingProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code account}. */
    public static final class BookingAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Booking}'s {@code referral_account}. */
    public static final class BookingReferralAccount extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link Booking}'s {@code discount_profile}. */
    public static final class BookingDiscountProfile extends Dto {
        public String amount;
        public String href;
        public String id;
        public String name;
        public String object;
        public String redemption_code;
        public String type;
    }

    /** A {@link Booking}'s {@code billing_address}. */
    public static final class BookingBillingAddress extends Dto {
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

    /** A {@link Booking}'s {@code shipping_address}. */
    public static final class BookingShippingAddress extends Dto {
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

    /** One entry of a {@link Booking}'s {@code communication_preferences}. */
    public static final class BookingCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link Booking}'s {@code billing_preferences}. */
    public static final class BookingBillingPreferences extends Dto {
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
        public BookingBillingPreferencesProformaProperties proforma_properties;
    }

    /** A {@link Booking}'s {@code contract_properties}. */
    public static final class BookingContractProperties extends Dto {
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

    /** A {@link BookingByIdBillingPreferences}'s {@code proforma_properties}. */
    public static final class BookingByIdBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link BookingByIdLine}'s {@code item}. */
    public static final class BookingByIdLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String uuid;
    }

    /** A {@link BookingByIdLine}'s {@code item_price_snapshot}. */
    public static final class BookingByIdLineItemPriceSnapshot extends Dto {
        public BookingByIdLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link BookingByIdLine}'s {@code item_price_tax}. */
    public static final class BookingByIdLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link BookingByIdLine}'s {@code item_sale_tax_configuration}. */
    public static final class BookingByIdLineItemSaleTaxConfiguration extends Dto {
        public String sale_price_is_based_on;
        public BookingByIdLineItemSaleTaxConfigurationTaxCode tax_code;
    }

    /** A {@link BookingByIdLine}'s {@code item_accounting_code}. */
    public static final class BookingByIdLineItemAccountingCode extends Dto {
        public Object sales_revenue;
    }

    /** A {@link BookingByIdLine}'s {@code pre_order_stock_details}. */
    public static final class BookingByIdLinePreOrderStockDetails extends Dto {
        public String pending_reserve;
        public String reserved;
        public String sold;
    }

    /** A {@link BookingByIdLine}'s {@code item_properties}. */
    public static final class BookingByIdLineItemProperties extends Dto {
        public String billing_mode;
        public String charging_and_billing_alignment;
        public String charging_start_date;
        public String pro_rata_partial_pricing_period;
    }

    /** A {@link BookingBillingPreferences}'s {@code proforma_properties}. */
    public static final class BookingBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link BookingByIdLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class BookingByIdLineItemPriceSnapshotPricingRule extends Dto {
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

    /** A {@link BookingByIdLineItemSaleTaxConfiguration}'s {@code tax_code}. */
    public static final class BookingByIdLineItemSaleTaxConfigurationTaxCode extends Dto {
        public String code;
        public String link;
        public Object rate;
        public String uuid;
    }

    private BookingsDto() {
    }

    /** One page of every order's notes, under {@code data}. */
    public static final class BookingNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/notes");

        public List<BookingNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code getBookingBillingPreferences} answers, under {@code order}. */
    public static final class BookingBillingPreferencesView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/billing_preferences");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public BookingBillingPreferencesDetail billing_preferences;
        public String object;
    }

    /** One page of the custom objects orders use, under {@code data}. */
    public static final class BookingCustomObjectList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/custom_objects",
                "GET /api/{api_version}/booking/{booking_id}/custom_objects_instances");

        public List<BookingCustomObjectEntry> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One custom object with the booking's instances of it, under {@code custom_object}. */
    public static final class BookingCustomObject extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/custom_objects/{custom_object_id}",
                "GET /api/{api_version}/booking/{booking_id}/custom_objects_instances/{custom_object_id}");

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

    /** One page of a booking's invoices, under {@code data}. */
    public static final class BookingInvoiceList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/invoices");

        public List<BookingInvoice> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code getBookingItemFulfillment} answers, under {@code item_fulfillment}. */
    public static final class BookingItemFulfillment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/item_fulfillments/{fulfillment_id}");

        @Override
        protected String envelopeKey() {
            return "item_fulfillment";
        }

        public BookingItemFulfillmentAccount account;
        public String code;
        public BookingItemFulfillmentCreatedBy created_by;
        public String created_on;
        public Map<String, Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object delivery_status;
        public Boolean fulfillment_completed;
        public String fulfillment_date;
        /** Null in every record measured, so untyped. */
        public Object fulfillment_note;
        public String fulfillment_status;
        public List<BookingItemFulfillmentFulfillment> fulfillments;
        public String href;
        public String id;
        public BookingItemFulfillmentInvoice invoice;
        public BookingItemFulfillmentLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object order;
        public String status;
        public String tracking_number;
        public Long version;
    }

    /** What {@code getBookingKpis} answers, under {@code order_kpis}. */
    public static final class BookingKpis extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/kpis");

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

    /** What {@code getBookingPaymentMethods} answers, under {@code order}. */
    public static final class BookingPaymentMethods extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/payment_methods");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public List<BookingPaymentMethodsPaymentMethod> payment_methods;
    }

    /** What {@code getBookingPaymentMethod} answers, under {@code order}. */
    public static final class BookingPaymentMethod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/payment_methods/{payment_method_ref}");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public BookingPaymentMethodPaymentMethod payment_method;
    }

    /** One page of a booking's payments, under {@code data}. */
    public static final class BookingPaymentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/payments");

        public List<BookingPayment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code getBookingPayment} answers, under {@code payment}. */
    public static final class BookingPaymentView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/payments/{payment_id}");

        @Override
        protected String envelopeKey() {
            return "payment";
        }

        public BookingPaymentViewAccount account;
        public BookingPaymentViewCreatedBy created_by;
        public String created_on;
        public List<Object> credit_applied;
        public List<Object> custom_attributes;
        public List<Object> gift_certificate_applied;
        public String id;
        public List<BookingPaymentViewInvoice> invoices;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public List<BookingPaymentViewPaymentApplied> payment_applied;
        public String payment_note;
        public String reconcile_status;
        public String status;
        public Long version;
    }

    /** What {@code getBookingPdf} answers, under {@code order}. */
    public static final class BookingPdf extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/pdf");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public BookingPdfPdf pdf;
    }

    /** What {@code getBookingQuotePartialInvoices} answers, under {@code order}. */
    public static final class BookingQuotePartialInvoices extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/quote_partial_invoices");

        @Override
        protected String envelopeKey() {
            return "order";
        }

        public List<Object> partial_invoices;
    }

    /** One entry of a {@link BookingNoteList}'s {@code data}. */
    public static final class BookingNote extends Dto {
        public List<BookingNoteAttachment> attachments;
        public String content;
        public BookingNoteCreatedBy created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public BookingNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<BookingNoteWatcher> watchers;
    }

    /** One entry of a {@link BookingNote}'s {@code attachments}. */
    public static final class BookingNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link BookingNote}'s {@code created_by}. */
    public static final class BookingNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link BookingNote}'s {@code last_updated_by}. */
    public static final class BookingNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingNote}'s {@code watchers}. */
    public static final class BookingNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link BookingBillingPreferencesView}'s {@code billing_preferences}. */
    public static final class BookingBillingPreferencesDetail extends Dto {
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
        public BookingBillingPreferencesDetailProformaProperties proforma_properties;
    }

    /** A {@link BookingBillingPreferencesDetail}'s {@code proforma_properties}. */
    public static final class BookingBillingPreferencesDetailProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** One entry of a {@link BookingCustomObjectList}'s {@code data}. */
    public static final class BookingCustomObjectEntry extends Dto {
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
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String use_in;
    }

    /** One entry of a {@link BookingInvoiceList}'s {@code data}. */
    public static final class BookingInvoice extends Dto {
        public BookingInvoiceAccount account;
        public String alternate_billing_end_date;
        public String alternate_billing_start_date;
        public String alternate_due_date;
        public String alternate_issue_date;
        public String billing_end_date;
        public String billing_start_date;
        public BookingInvoiceCreatedBy created_by;
        public String created_on;
        public BookingInvoiceCurrency currency;
        public BookingInvoiceCustomForm custom_form;
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
        public BookingInvoiceOrder order;
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

    /** A {@link BookingInvoice}'s {@code account}. */
    public static final class BookingInvoiceAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingInvoice}'s {@code created_by}. */
    public static final class BookingInvoiceCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link BookingInvoice}'s {@code currency}. */
    public static final class BookingInvoiceCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingInvoice}'s {@code custom_form}. */
    public static final class BookingInvoiceCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingInvoice}'s {@code order}. */
    public static final class BookingInvoiceOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingItemFulfillment}'s {@code account}. */
    public static final class BookingItemFulfillmentAccount extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingItemFulfillment}'s {@code created_by}. */
    public static final class BookingItemFulfillmentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingItemFulfillment}'s {@code fulfillments}. */
    public static final class BookingItemFulfillmentFulfillment extends Dto {
        public Long fulfillment_index;
        public Double fulfillment_qty;
        public Double fulfillment_sent_qty;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object inventory_tracking;
        public BookingItemFulfillmentFulfillmentItem item;
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

    /** A {@link BookingItemFulfillmentFulfillment}'s {@code item}. */
    public static final class BookingItemFulfillmentFulfillmentItem extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingItemFulfillment}'s {@code invoice}. */
    public static final class BookingItemFulfillmentInvoice extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link BookingItemFulfillment}'s {@code last_updated_by}. */
    public static final class BookingItemFulfillmentLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingPaymentMethods}'s {@code payment_methods}. */
    public static final class BookingPaymentMethodsPaymentMethod extends Dto {
        public String created_by;
        public String created_on;
        @WireName("default")
        public Boolean isDefault;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String last_used_at;
        public String object;
        public Long payment_count;
        public BookingPaymentMethodsPaymentMethodProcessor processor;
        public String processor_type;
        public String reference;
        public Boolean restricted_to_orders;
        public String specified_order_ids;
        public String status;
        public Long version;
    }

    /** A {@link BookingPaymentMethodsPaymentMethod}'s {@code processor}. */
    public static final class BookingPaymentMethodsPaymentMethodProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingPaymentMethod}'s {@code payment_method}. */
    public static final class BookingPaymentMethodPaymentMethod extends Dto {
        public String created_by;
        public String created_on;
        @WireName("default")
        public Boolean isDefault;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String last_used_at;
        public String object;
        public Long payment_count;
        public BookingPaymentMethodPaymentMethodProcessor processor;
        public String processor_type;
        public String reference;
        public Boolean restricted_to_orders;
        public String specified_order_ids;
        public String status;
        public Long version;
    }

    /** A {@link BookingPaymentMethodPaymentMethod}'s {@code processor}. */
    public static final class BookingPaymentMethodPaymentMethodProcessor extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingPaymentList}'s {@code data}. */
    public static final class BookingPayment extends Dto {
        public BookingPaymentAccount account;
        public BookingPaymentCreatedBy created_by;
        public String created_on;
        public List<Object> credit_applied;
        public List<Object> gift_certificate_applied;
        public String id;
        public List<BookingPaymentInvoice> invoices;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object origin;
        public List<BookingPaymentPaymentApplied> payment_applied;
        public String payment_note;
        public String reconcile_status;
        public String status;
        public Long version;
    }

    /** A {@link BookingPayment}'s {@code account}. */
    public static final class BookingPaymentAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingPayment}'s {@code created_by}. */
    public static final class BookingPaymentCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingPayment}'s {@code invoices}. */
    public static final class BookingPaymentInvoice extends Dto {
        public String applied;
        public String due_date;
        public String id;
        public String issue_date;
        public String outstanding;
        public String total;
    }

    /** One entry of a {@link BookingPayment}'s {@code payment_applied}. */
    public static final class BookingPaymentPaymentApplied extends Dto {
        public String amount;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String method;
        public String processor;
        public String reference;
    }

    /** A {@link BookingPaymentView}'s {@code account}. */
    public static final class BookingPaymentViewAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link BookingPaymentView}'s {@code created_by}. */
    public static final class BookingPaymentViewCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link BookingPaymentView}'s {@code invoices}. */
    public static final class BookingPaymentViewInvoice extends Dto {
        public String applied;
        public String due_date;
        public String id;
        public String issue_date;
        public String outstanding;
        public String total;
    }

    /** One entry of a {@link BookingPaymentView}'s {@code payment_applied}. */
    public static final class BookingPaymentViewPaymentApplied extends Dto {
        public String amount;
        /** Null in every record measured, so untyped. */
        public Object gateway_response;
        public String method;
        public String processor;
        public String reference;
    }

    /** A {@link BookingPdf}'s {@code pdf}. */
    public static final class BookingPdfPdf extends Dto {
        public String download_url;
        public Long file_size;
        public String id;
        public String object;
        public String preview_url;
    }

    /** What {@code getBookingLine} answers, under {@code order_line}. */
    public static final class BookingLine extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/booking/{booking_id}/lines/{line_uuid}");

        @Override
        protected String envelopeKey() {
            return "order_line";
        }

        public String charge_item_uuid;
        public String expected_delivery_date;
        public String id;
        public BookingLineItem item;
        public BookingLineItemAccountingCode item_accounting_code;
        public String item_charge_type;
        public List<Object> item_custom_attributes;
        public String item_description;
        public String item_invoice_note;
        public Long item_order_quantity;
        public BookingLineItemPriceSnapshot item_price_snapshot;
        public BookingLineItemPriceTax item_price_tax;
        public BookingLineItemProperties item_properties;
        public BookingLineItemSaleTaxConfiguration item_sale_tax_configuration;
        public String item_type;
        public String object;
        public BookingLinePreOrderStockDetails pre_order_stock_details;
        public Double shipping_cost;
        public String subtotal;
        public String tax;
        public Boolean tax_exempt_when_sold;
        public String total;
        public Long version;
    }

    /** A {@link BookingLine}'s {@code item}. */
    public static final class BookingLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String uuid;
    }

    /** A {@link BookingLine}'s {@code item_accounting_code}. */
    public static final class BookingLineItemAccountingCode extends Dto {
        public String sales_revenue;
    }

    /** A {@link BookingLine}'s {@code item_price_snapshot}. */
    public static final class BookingLineItemPriceSnapshot extends Dto {
        public BookingLineItemPriceSnapshotPricingRule pricing_rule;
    }

    /** A {@link BookingLineItemPriceSnapshot}'s {@code pricing_rule}. */
    public static final class BookingLineItemPriceSnapshotPricingRule extends Dto {
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

    /** A {@link BookingLine}'s {@code item_price_tax}. */
    public static final class BookingLineItemPriceTax extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public Double rate;
    }

    /** A {@link BookingLine}'s {@code item_properties}. */
    public static final class BookingLineItemProperties extends Dto {
        public String billing_mode;
        public String charging_and_billing_alignment;
        public String charging_start_date;
        public String pro_rata_partial_pricing_period;
    }

    /** A {@link BookingLine}'s {@code item_sale_tax_configuration}. */
    public static final class BookingLineItemSaleTaxConfiguration extends Dto {
        public String sale_price_is_based_on;
        public BookingLineItemSaleTaxConfigurationTaxCode tax_code;
    }

    /** A {@link BookingLineItemSaleTaxConfiguration}'s {@code tax_code}. */
    public static final class BookingLineItemSaleTaxConfigurationTaxCode extends Dto {
        public String code;
        public String link;
        public String rate;
        public String uuid;
    }

    /** A {@link BookingLine}'s {@code pre_order_stock_details}. */
    public static final class BookingLinePreOrderStockDetails extends Dto {
        public String pending_reserve;
        public String reserved;
        public String sold;
    }
}
