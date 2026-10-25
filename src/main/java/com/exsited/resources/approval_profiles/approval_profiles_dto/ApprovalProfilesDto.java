package com.exsited.resources.approval_profiles.approval_profiles_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the approval profile bodies. */
public final class ApprovalProfilesDto {

    private ApprovalProfilesDto() {}

    /** The settings approval profile body, under {@code approval_profile}. */
    public static final class SettingsApprovalProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/approval_profiles",
                "GET /api/{api_version}/settings/approval_profiles/{approval_profile_id}");

        @Override
        protected String envelopeKey() {
            return "approval_profile";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
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
        public String status;
    }

    /** One page of {@code SettingsApprovalProfile} records, under {@code data}. */
    public static final class SettingsApprovalProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/approval_profiles");

        public List<SettingsApprovalProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
