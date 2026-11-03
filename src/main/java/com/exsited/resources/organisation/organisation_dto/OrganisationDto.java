package com.exsited.resources.organisation.organisation_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the organisation bodies. */
public final class OrganisationDto {

    private OrganisationDto() {}

    /** The root organisation body, under {@code organization}. */
    public static final class SettingsOrganisation extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/organisation");

        @Override
        protected String envelopeKey() {
            return "organization";
        }

        public Object address_line1;
        public Object address_line2;
        public Object address_line3;
        public Object address_line4;
        public Object address_line5;
        public Object city;
        public Object created_by;
        public String created_on;
        public Object description;
        public Object details;
        public String display_name;
        public Object email;
        public Object fax;
        public Object fax_area_code;
        public Object fax_country_code;
        public String href;
        public String id;
        public Object last_updated_by;
        public String last_updated_on;
        public Object mobile;
        public Object mobile_area_code;
        public Object mobile_country_code;
        public String name;
        public List<Object> nodes;
        public String object;
        public SettingsOrganisationParentOrganization parent_organization;
        public Object phone;
        public Object phone_area_code;
        public Object phone_country_code;
        public Object post_code;
        public Object state;
        public String status;
        public String type;
        public Object website;
    }

    /** A {@link SettingsOrganisation}'s {@code parent_organization}. */
    public static final class SettingsOrganisationParentOrganization extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }
}
