package com.exsited.resources.communication_profiles.communication_profiles_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the communication profile bodies. */
public final class CommunicationProfilesDto {

    private CommunicationProfilesDto() {}

    /** The settings communication profile body, under {@code communication_profile}. */
    public static final class SettingsCommunicationProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/communication_profiles",
                "GET /api/{api_version}/settings/communication_profiles/{communication_profile_id}");

        @Override
        protected String envelopeKey() {
            return "communication_profile";
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
    }

    /** One page of {@code SettingsCommunicationProfile} records, under {@code data}. */
    public static final class SettingsCommunicationProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/communication_profiles");

        public List<SettingsCommunicationProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
