package com.exsited.resources.rental_assets.rental_assets_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the rental assets bodies. */
public final class RentalAssetsDto {

    /** The rental asset body. */
    public static final class RentalAsset extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_assets",
                "GET /api/{api_version}/rental_assets/{rental_asset_uuid}",
                "POST /api/{api_version}/rental_assets",
                "PATCH /api/{api_version}/rental_assets/{rental_asset_uuid}",
                "POST /api/{api_version}/rental_assets/{rental_asset_id}/cancel",
                "POST /api/{api_version}/rental_assets/{rental_asset_id}/reactivate");

        @Override
        protected String envelopeKey() {
            return "rental_asset";
        }

        public List<Object> activity_logs;
        public String code;
        public RentalAssetCreatedBy created_by;
        public String created_on;
        public List<Object> custom_attributes;
        public RentalAssetCustomForm custom_form;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object image_name;
        /** never populated in 2 measured records. */
        public Object item;
        public RentalAssetLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public List<Object> rental_asset_availability;
        public List<RentalAssetRentalAssetCustomAvailability> rental_asset_custom_availability;
        public RentalAssetRentalAssetProfile rental_asset_profile;
        public String status;
        /** 1 measured, null on 1. */
        public Boolean use_custom_availability;
        public Long version;
    }

    /** A page of RentalAsset records. */
    public static final class RentalAssetList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_assets");

        public List<RentalAsset> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The rental asset profile body. */
    public static final class RentalAssetProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_asset_profiles");

        public RentalAssetProfileCreatedBy created_by;
        public String created_on;
        public RentalAssetProfileCurrency currency;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_tax_inclusive;
        public RentalAssetProfileLastUpdatedBy last_updated_by;
        public String last_updated_on;
        /** never populated in 2 measured records. */
        public Object max_booking_limit;
        public String name;
        public String object;
        public List<RentalAssetProfileRentalAssetProfileSession> rental_asset_profile_sessions;
        public List<RentalAssetProfileRow> rental_assets;
        public String status;
        /** never populated in 2 measured records. */
        public Object tax;
        public Long version;
    }

    /** A page of RentalAssetProfile records. */
    public static final class RentalAssetProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_asset_profiles");

        public List<RentalAssetProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The rental asset availability body. */
    public static final class RentalAssetAvailability extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_asset_availability");

        public List<Object> available_list;
        public String code;
        public String display_name;
        public String id;
        public String name;
        public String object;
        public Map<String, Object> processed_schedule_list;
        public RentalAssetAvailabilityRentalAssetProfile rental_asset_profile;
        public List<RentalAssetAvailabilityScheduleList> schedule_list;
        public List<RentalAssetAvailabilitySessionList> session_list;
        public String status;
        public List<Object> unavailable_list;
        public Boolean use_custom_availability;
        public Long version;
    }

    /** A page of RentalAssetAvailability records. */
    public static final class RentalAssetAvailabilityList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_asset_availability");

        public List<RentalAssetAvailability> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_rental_asset_profile} answers, under {@code rental_asset_profile}. */
    public static final class RentalAssetProfileView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/rental_asset_profiles/{rental_asset_profile_id}",
                "POST /api/{api_version}/rental_asset_profiles");

        @Override
        protected String envelopeKey() {
            return "rental_asset_profile";
        }

        public List<Object> activity_logs;
        public RentalAssetProfileViewCreatedBy created_by;
        public String created_on;
        public RentalAssetProfileViewCurrency currency;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_tax_inclusive;
        public RentalAssetProfileViewLastUpdatedBy last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object max_booking_limit;
        public String name;
        public String object;
        public List<RentalAssetProfileViewRentalAssetProfileSession> rental_asset_profile_sessions;
        public List<RentalAssetProfileViewRow> rental_assets;
        public String status;
        /** Null in every record measured, so untyped. */
        public Object tax;
        public Long version;
    }

    /** A {@link RentalAssetProfileView}'s {@code currency}. */
    public static final class RentalAssetProfileViewCurrency extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link RentalAssetProfileView}'s {@code rental_assets}. */
    public static final class RentalAssetProfileViewRow extends Dto {
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object image_name;
        public String name;
        public String object;
        public String status;
        public Boolean use_custom_availability;
    }

    /** One entry of a {@link RentalAssetProfileView}'s {@code rental_asset_profile_sessions}. */
    public static final class RentalAssetProfileViewRentalAssetProfileSession extends Dto {
        public String end_date;
        public String id;
        public String name;
        public String object;
        public List<RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionAvailability> rental_asset_profile_session_availability;
        public List<RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionPrice> rental_asset_profile_session_prices;
        public List<RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionWorkingHour> rental_asset_profile_session_working_hours;
        public String start_date;
        public String status;
    }

    /** A {@link RentalAssetProfileView}'s {@code created_by}. */
    public static final class RentalAssetProfileViewCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link RentalAssetProfileView}'s {@code last_updated_by}. */
    public static final class RentalAssetProfileViewLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link RentalAsset}'s {@code rental_asset_profile}. */
    public static final class RentalAssetRentalAssetProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link RentalAsset}'s {@code custom_form}. */
    public static final class RentalAssetCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link RentalAsset}'s {@code rental_asset_custom_availability}. */
    public static final class RentalAssetRentalAssetCustomAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
        public String status;
    }

    /** A {@link RentalAsset}'s {@code created_by}. */
    public static final class RentalAssetCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link RentalAsset}'s {@code last_updated_by}. */
    public static final class RentalAssetLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link RentalAssetAvailability}'s {@code rental_asset_profile}. */
    public static final class RentalAssetAvailabilityRentalAssetProfile extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link RentalAssetAvailability}'s {@code session_list}. */
    public static final class RentalAssetAvailabilitySessionList extends Dto {
        public String end_date;
        public String name;
        public Map<String, Object> processed_schedule_list;
        public List<RentalAssetAvailabilitySessionListScheduleList> schedule_list;
        public String start_date;
        public String uuid;
    }

    /** One entry of a {@link RentalAssetAvailability}'s {@code schedule_list}. */
    public static final class RentalAssetAvailabilityScheduleList extends Dto {
        public String available;
        public String end_time;
        public String preferred_day;
        public String start_time;
        public String uuid;
    }

    /** A {@link RentalAssetProfile}'s {@code currency}. */
    public static final class RentalAssetProfileCurrency extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link RentalAssetProfile}'s {@code rental_assets}. */
    public static final class RentalAssetProfileRow extends Dto {
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object image_name;
        public String name;
        public String object;
        public String status;
        public Boolean use_custom_availability;
    }

    /** One entry of a {@link RentalAssetProfile}'s {@code rental_asset_profile_sessions}. */
    public static final class RentalAssetProfileRentalAssetProfileSession extends Dto {
        public String end_date;
        public String id;
        public String name;
        public String object;
        public List<RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionAvailability> rental_asset_profile_session_availability;
        public List<RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionPrice> rental_asset_profile_session_prices;
        public List<RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionWorkingHour> rental_asset_profile_session_working_hours;
        public String start_date;
        public String status;
    }

    /** A {@link RentalAssetProfile}'s {@code created_by}. */
    public static final class RentalAssetProfileCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link RentalAssetProfile}'s {@code last_updated_by}. */
    public static final class RentalAssetProfileLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /**
     * One entry of a {@link RentalAssetProfileViewRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_prices}.
     */
    public static final class RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionPrice extends Dto {
        public String id;
        public Boolean is_enabled_pro_rata;
        public String object;
        public Double price;
        public String price_type;
        /** Null in every record measured, so untyped. */
        public Object pricing_level;
        public List<Object> rental_asset_profile_session_price_hours;
        public List<Object> rental_asset_profile_session_price_peak_hourly;
        public String status;
    }

    /**
     * One entry of a {@link RentalAssetProfileViewRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_availability}.
     */
    public static final class RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
        public String status;
    }

    /**
     * One entry of a {@link RentalAssetProfileViewRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_working_hours}.
     */
    public static final class RentalAssetProfileViewRentalAssetProfileSessionRentalAssetProfileSessionWorkingHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public String start_time;
        public String status;
    }

    /** One entry of a {@link RentalAssetAvailabilitySessionList}'s {@code schedule_list}. */
    public static final class RentalAssetAvailabilitySessionListScheduleList extends Dto {
        public String available;
        public String end_time;
        public String preferred_day;
        public String start_time;
        public String uuid;
    }

    /**
     * One entry of a {@link RentalAssetProfileRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_prices}.
     */
    public static final class RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionPrice extends Dto {
        public String id;
        public Boolean is_enabled_pro_rata;
        public String object;
        public Double price;
        public String price_type;
        /** Null in every record measured, so untyped. */
        public Object pricing_level;
        public List<Object> rental_asset_profile_session_price_hours;
        public List<Object> rental_asset_profile_session_price_peak_hourly;
        public String status;
    }

    /**
     * One entry of a {@link RentalAssetProfileRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_availability}.
     */
    public static final class RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionAvailability extends Dto {
        public Boolean available;
        public String end_time;
        public String id;
        public String object;
        public String preferred_day;
        public String start_time;
        public String status;
    }

    /**
     * One entry of a {@link RentalAssetProfileRentalAssetProfileSession}'s {@code
     * rental_asset_profile_session_working_hours}.
     */
    public static final class RentalAssetProfileRentalAssetProfileSessionRentalAssetProfileSessionWorkingHour extends Dto {
        public String end_time;
        public String id;
        public String object;
        public String start_time;
        public String status;
    }

    private RentalAssetsDto() {
    }
}
