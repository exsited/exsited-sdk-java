package com.exsited.resources.uom.uom_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the unit of measurement bodies. */
public final class UomDto {

    private UomDto() {}

    /** The settings unit of measurement body, under {@code uom}. */
    public static final class SettingsUom extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/uom",
                "GET /api/{api_version}/settings/uom/{uom_id}");

        @Override
        protected String envelopeKey() {
            return "uom";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object custom_type;
        public Long decimal_places;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String rounding;
        public String status;
        public String type;
        public String type_display_text;
    }

    /** One page of {@code SettingsUom} records, under {@code data}. */
    public static final class SettingsUomList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/uom");

        public List<SettingsUom> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
