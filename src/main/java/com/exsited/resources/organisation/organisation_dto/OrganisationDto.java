package com.exsited.resources.organisation.organisation_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the organisation bodies. */
public final class OrganisationDto {

    private OrganisationDto() {}

    /** The root organisation body, under {@code organization}. */
    public static final class SettingsOrganisation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/organisation");

        @Override
        protected String envelopeKey() {
            return "organization";
        }

        /** Null in every record measured, so untyped. */
        public Object address_line1;
        /** Null in every record measured, so untyped. */
        public Object address_line2;
        /** Null in every record measured, so untyped. */
        public Object address_line3;
        /** Null in every record measured, so untyped. */
        public Object address_line4;
        /** Null in every record measured, so untyped. */
        public Object address_line5;
        /** Null in every record measured, so untyped. */
        public Object city;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        /** Null in every record measured, so untyped. */
        public Object details;
        public String display_name;
        /** Null in every record measured, so untyped. */
        public Object email;
        /** Null in every record measured, so untyped. */
        public Object fax;
        /** Null in every record measured, so untyped. */
        public Object fax_area_code;
        /** Null in every record measured, so untyped. */
        public Object fax_country_code;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object mobile;
        /** Null in every record measured, so untyped. */
        public Object mobile_area_code;
        /** Null in every record measured, so untyped. */
        public Object mobile_country_code;
        public String name;
        /** Empty in every record measured, so untyped. */
        public List<Object> nodes;
        public String object;
        public SettingsOrganisationParentOrganization parent_organization;
        /** Null in every record measured, so untyped. */
        public Object phone;
        /** Null in every record measured, so untyped. */
        public Object phone_area_code;
        /** Null in every record measured, so untyped. */
        public Object phone_country_code;
        /** Null in every record measured, so untyped. */
        public Object post_code;
        /** Null in every record measured, so untyped. */
        public Object state;
        public String status;
        public String type;
        /** Null in every record measured, so untyped. */
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
