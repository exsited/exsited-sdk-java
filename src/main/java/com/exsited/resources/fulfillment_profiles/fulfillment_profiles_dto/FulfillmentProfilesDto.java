package com.exsited.resources.fulfillment_profiles.fulfillment_profiles_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the fulfillment profile bodies. */
public final class FulfillmentProfilesDto {

    private FulfillmentProfilesDto() {}

    /** The settings fulfillment profile body, under {@code fulfillment_profile}. */
    public static final class SettingsFulfillmentProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/fulfillment_profiles",
                "GET /api/{api_version}/settings/fulfillment_profiles/{fulfillment_profile_id}",
                "POST /api/{api_version}/settings/fulfillment_profiles",
                "PATCH /api/{api_version}/settings/fulfillment_profiles/{fulfillment_profile_id}");

        @Override
        protected String envelopeKey() {
            return "fulfillment_profile";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** One page of {@code SettingsFulfillmentProfile} records, under {@code data}. */
    public static final class SettingsFulfillmentProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/fulfillment_profiles");

        public List<SettingsFulfillmentProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
