package com.exsited.resources.countries.countries_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the country bodies. */
public final class CountriesDto {

    private CountriesDto() {}

    /** The settings country body, under {@code country}. */
    public static final class SettingsCountry extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/countries",
                "GET /api/{api_version}/settings/countries/{country_id}",
                "PATCH /api/{api_version}/settings/countries/{country_id}");

        @Override
        protected String envelopeKey() {
            return "country";
        }

        public String code;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object default_area_code;
        public String default_city;
        public String default_post_code;
        public String default_state;
        public String description;
        public String display_name;
        public Boolean flag_exists;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsCountry} records, under {@code data}. */
    public static final class SettingsCountryList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/countries");

        public List<SettingsCountry> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
