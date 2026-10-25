package com.exsited.resources.labours.labours_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the labours bodies. */
public final class LaboursDto {

    /** The labour body. */
    public static final class Labour extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labours",
                "GET /api/{api_version}/labours/available_for_booking",
                "GET /api/{api_version}/labours/{labour_uuid}",
                "POST /api/{api_version}/labours",
                "PATCH /api/{api_version}/labours/{labour_uuid}",
                "POST /api/{api_version}/labours/{labour_id}/cancel",
                "POST /api/{api_version}/labours/{labour_id}/reactivate");

        @Override
        protected String envelopeKey() {
            return "labour";
        }

        public List<Object> activity_logs;
        public String code;
        public LabourCreatedBy created_by;
        public String created_on;
        public LabourCustomForm custom_form;
        /** 2 measured, null on 2. */
        public String description;
        public String display_name;
        /** 2 measured, null on 2. */
        public String email_address;
        public String href;
        public String id;
        /** never populated in 4 measured records. */
        public Object image_name;
        public List<Object> labour_availability;
        public List<LabourLabourContact> labour_contacts;
        public List<LabourLabourCustomAvailability> labour_custom_availability;
        public LabourLabourProfile labour_profile;
        /** 2 measured, null on 2. */
        public LabourLastUpdatedBy last_updated_by;
        /** 2 measured, null on 2. */
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        /** 2 measured, null on 2. */
        public Boolean use_custom_availability;
        public Long version;
    }

    /** A page of Labour records. */
    public static final class LabourList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labours",
                "GET /api/{api_version}/labours/available_for_booking");

        public List<Labour> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The labour profile body. */
    public static final class LabourProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_profiles");

        public LabourProfileCreatedBy created_by;
        public String created_on;
        public LabourProfileCurrency currency;
        public String display_name;
        public String href;
        public String id;
        public List<LabourProfileRow> labours;
        public LabourProfileLastUpdatedBy last_updated_by;
        public String last_updated_on;
        /** never populated in 2 measured records. */
        public Object max_booking_limit;
        public String name;
        public String object;
        public List<LabourProfileSession> sessions;
        public String status;
        /** 1 measured, null on 1. */
        public LabourProfileTax tax;
        public Boolean tax_inclusive;
        public Long version;
    }

    /** A page of LabourProfile records. */
    public static final class LabourProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_profiles");

        public List<LabourProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The labour availability body. */
    public static class LabourAvailability extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_availability");

        public List<Object> available_list;
        public String code;
        public String display_name;
        /** 1 measured, null on 1. */
        public String email_address;
        public String id;
        public LabourAvailabilityLabourProfile labour_profile;
        public String name;
        public String object;
        public Map<String, Object> processed_schedule_list;
        public List<LabourAvailabilityScheduleList> schedule_list;
        public List<LabourAvailabilitySessionList> session_list;
        public String status;
        public List<Object> unavailable_list;
        public Boolean use_custom_availability;
        public Long version;
    }

    /** A page of LabourAvailability records. */
    public static final class LabourAvailabilityList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_availability");

        public List<LabourAvailability> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_labour_profile} answers, under {@code labour_profile}. */
    public static final class LabourProfileView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_profiles/{labour_profile_id}",
                "POST /api/{api_version}/labour_profiles");

        @Override
        protected String envelopeKey() {
            return "labour_profile";
        }

        public List<Object> activity_logs;
        public LabourProfileViewCreatedBy created_by;
        public String created_on;
        public LabourProfileViewCurrency currency;
        public String display_name;
        public String href;
        public String id;
        public List<LabourProfileViewRow> labours;
        public LabourProfileViewLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public Long max_booking_limit;
        public String name;
        public String object;
        public List<LabourProfileViewSession> sessions;
        public String status;
        public LabourProfileViewTax tax;
        public Boolean tax_inclusive;
        public Long version;
    }

    /** One page of what {@code get_labour_orders} lists, under {@code data}. */
    public static final class LabourOrderList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labours/{labour_uuid}/orders");

        public List<LabourOrder> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link LabourProfileView}'s {@code currency}. */
    public static final class LabourProfileViewCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourProfileView}'s {@code tax}. */
    public static final class LabourProfileViewTax extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link LabourProfileView}'s {@code labours}. */
    public static final class LabourProfileViewRow extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourProfileView}'s {@code sessions}. */
    public static class LabourProfileViewSession extends Dto {
        public Boolean allow_overtime;
        public List<LabourProfileViewSessionAvailability> availability;
        public String end_date;
        public String href;
        public String id;
        public String name;
        public String object;
        public List<LabourProfileViewSessionPrice> prices;
        public String start_date;
        public String status;
        public List<LabourProfileViewSessionWorkingHour> working_hours;
    }

    /** A {@link LabourProfileView}'s {@code created_by}. */
    public static final class LabourProfileViewCreatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourProfileView}'s {@code last_updated_by}. */
    public static final class LabourProfileViewLastUpdatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Labour}'s {@code labour_profile}. */
    public static final class LabourLabourProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Labour}'s {@code custom_form}. */
    public static final class LabourCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link Labour}'s {@code labour_contacts}. */
    public static final class LabourLabourContact extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String contact_type;
        public String country;
        public String designation;
        public String email_address;
        public LabourLabourContactFax fax;
        public String first_name;
        public String id;
        public String last_name;
        /** Null in every record measured, so untyped. */
        public Object middle_name;
        public LabourLabourContactMobile mobile;
        public String object;
        public LabourLabourContactPhone phone;
        public String post_code;
        public Boolean receive_email;
        public String salutation;
        public String state;
        public String status;
    }

    /** One entry of a {@link Labour}'s {@code labour_custom_availability}. */
    public static final class LabourLabourCustomAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
    }

    /** A {@link Labour}'s {@code created_by}. */
    public static final class LabourCreatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Labour}'s {@code last_updated_by}. */
    public static final class LabourLastUpdatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourOrderList}'s {@code data}. */
    public static final class LabourOrder extends Dto {
        public LabourOrderAccount account;
        public Boolean allow_contract;
        /** Null in every record measured, so untyped. */
        public Object billing_address;
        public LabourOrderBillingPreferences billing_preferences;
        public String billing_start_date;
        public List<LabourOrderCommunicationPreference> communication_preferences;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public LabourOrderCurrency currency;
        public LabourOrderCustomForm custom_form;
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
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object manager;
        public String name;
        public String next_billing_from_date;
        public String object;
        public String order_start_date;
        public String order_type;
        /** Null in every record measured, so untyped. */
        public Object origin;
        /** Null in every record measured, so untyped. */
        public Object original_total;
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
        /** Null in every record measured, so untyped. */
        public Object shipping_profile;
        public String status;
        public LabourOrderTimeZone time_zone;
        public Long version;
    }

    /** A {@link LabourAvailability}'s {@code labour_profile}. */
    public static final class LabourAvailabilityLabourProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourAvailability}'s {@code schedule_list}. */
    public static final class LabourAvailabilityScheduleList extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
    }

    /** One entry of a {@link LabourAvailability}'s {@code session_list}. */
    public static final class LabourAvailabilitySessionList extends Dto {
        public String end_date;
        public String name;
        public Map<String, Object> processed_schedule_list;
        public List<LabourAvailabilitySessionListScheduleList> schedule_list;
        public String start_date;
        public String uuid;
    }

    /** A {@link LabourProfile}'s {@code currency}. */
    public static final class LabourProfileCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourProfile}'s {@code tax}. */
    public static final class LabourProfileTax extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link LabourProfile}'s {@code labours}. */
    public static final class LabourProfileRow extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourProfile}'s {@code sessions}. */
    public static final class LabourProfileSession extends Dto {
        public Boolean allow_overtime;
        public List<LabourProfileSessionAvailability> availability;
        public String end_date;
        public String href;
        public String id;
        public String name;
        public String object;
        public List<LabourProfileSessionPrice> prices;
        public String start_date;
        public String status;
        public List<LabourProfileSessionWorkingHour> working_hours;
    }

    /** A {@link LabourProfile}'s {@code created_by}. */
    public static final class LabourProfileCreatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourProfile}'s {@code last_updated_by}. */
    public static final class LabourProfileLastUpdatedBy extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourProfileViewSession}'s {@code prices}. */
    public static final class LabourProfileViewSessionPrice extends Dto {
        public Boolean enabled_pro_rata;
        public List<LabourProfileViewSessionPriceHour> hours;
        public String id;
        public String object;
        public List<Object> peak_hourly;
        public Double price;
        public String price_type;
        public LabourProfileViewSessionPricePricingLevel pricing_level;
        public String status;
    }

    /** One entry of a {@link LabourProfileViewSession}'s {@code availability}. */
    public static final class LabourProfileViewSessionAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
        public String status;
    }

    /** One entry of a {@link LabourProfileViewSession}'s {@code working_hours}. */
    public static final class LabourProfileViewSessionWorkingHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public String start_time;
        public String status;
    }

    /** A {@link LabourLabourContact}'s {@code phone}. */
    public static final class LabourLabourContactPhone extends Dto {
        public String area_code;
        public String country_code;
        public Boolean do_not_call;
        public String full;
        public String number;
    }

    /** A {@link LabourLabourContact}'s {@code fax}. */
    public static final class LabourLabourContactFax extends Dto {
        public String area_code;
        public String country_code;
        public Boolean do_not_call;
        public String full;
        public String number;
    }

    /** A {@link LabourLabourContact}'s {@code mobile}. */
    public static final class LabourLabourContactMobile extends Dto {
        public String area_code;
        public String country_code;
        public Boolean do_not_call;
        public String full;
        public String number;
    }

    /** A {@link LabourOrder}'s {@code currency}. */
    public static final class LabourOrderCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourOrder}'s {@code time_zone}. */
    public static final class LabourOrderTimeZone extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourOrder}'s {@code custom_form}. */
    public static final class LabourOrderCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link LabourOrder}'s {@code account}. */
    public static final class LabourOrderAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourOrder}'s {@code communication_preferences}. */
    public static final class LabourOrderCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** A {@link LabourOrder}'s {@code billing_preferences}. */
    public static final class LabourOrderBillingPreferences extends Dto {
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
        public LabourOrderBillingPreferencesProformaProperties proforma_properties;
    }

    /** One entry of a {@link LabourAvailabilitySessionList}'s {@code schedule_list}. */
    public static final class LabourAvailabilitySessionListScheduleList extends Dto {
        public String available;
        public String end_time;
        public String preferred_day;
        public String start_time;
        public String uuid;
    }

    /** One entry of a {@link LabourProfileSession}'s {@code prices}. */
    public static final class LabourProfileSessionPrice extends Dto {
        public Boolean enabled_pro_rata;
        public List<LabourProfileSessionPriceHour> hours;
        public String id;
        public String object;
        public List<Object> peak_hourly;
        public Double price;
        public String price_type;
        public LabourProfileSessionPricePricingLevel pricing_level;
        public String status;
    }

    /** One entry of a {@link LabourProfileSession}'s {@code availability}. */
    public static final class LabourProfileSessionAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
        public String status;
    }

    /** One entry of a {@link LabourProfileSession}'s {@code working_hours}. */
    public static final class LabourProfileSessionWorkingHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public String start_time;
        public String status;
    }

    /** A {@link LabourProfileViewSessionPrice}'s {@code pricing_level}. */
    public static final class LabourProfileViewSessionPricePricingLevel extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourProfileViewSessionPrice}'s {@code hours}. */
    public static final class LabourProfileViewSessionPriceHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public Double price;
        public String start_time;
        public String status;
    }

    /** A {@link LabourOrderBillingPreferences}'s {@code proforma_properties}. */
    public static final class LabourOrderBillingPreferencesProformaProperties extends Dto {
        /** Null in every record measured, so untyped. */
        public Object acceptance_date;
        public Boolean acceptance_required;
        /** Null in every record measured, so untyped. */
        public Object acceptance_term;
        public Boolean payment_required;
        /** Null in every record measured, so untyped. */
        public Object terms_and_conditions;
    }

    /** A {@link LabourProfileSessionPrice}'s {@code pricing_level}. */
    public static final class LabourProfileSessionPricePricingLevel extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link LabourProfileSessionPrice}'s {@code hours}. */
    public static final class LabourProfileSessionPriceHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public Double price;
        public String start_time;
        public String status;
    }

    private LaboursDto() {
    }

    /**
     * What {@code getLabourCalendar} answers, under {@code labour_calendar}: a
     * {@link LabourAvailability} record for one labour.
     */
    public static final class LabourCalendar extends LabourAvailability {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labours/{labour_id}/availability");

        @Override
        protected String envelopeKey() {
            return "labour_calendar";
        }
    }

    /**
     * One page of a labour profile's sessions, under {@code data}, each a
     * {@link LabourProfileViewSession}.
     */
    public static final class LabourProfileSessionList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_profiles/{labour_profile_id}/sessions");

        public List<LabourProfileViewSession> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code getLabourProfileSession} answers, under {@code labour_profile_session}. */
    public static final class LabourProfileSessionView extends LabourProfileViewSession {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/labour_profiles/{labour_profile_id}/sessions/{session_id}");

        @Override
        protected String envelopeKey() {
            return "labour_profile_session";
        }
    }
}
