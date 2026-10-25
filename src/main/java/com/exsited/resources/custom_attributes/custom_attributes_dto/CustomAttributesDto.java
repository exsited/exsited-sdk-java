package com.exsited.resources.custom_attributes.custom_attributes_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the custom attributes bodies. */
public final class CustomAttributesDto {

    /** The custom attribute body. */
    public static final class CustomAttribute extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_attributes",
                "GET /api/{api_version}/custom_attributes/{custom_attribute_id}",
                "PATCH /api/{api_version}/custom_attributes/{custom_attribute_id}");

        @Override
        protected String envelopeKey() {
            return "custom_attribute";
        }

        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public Boolean encrypt_data;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object include_seconds;
        public Boolean is_mandatory;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public Long max_length;
        /** never populated in 2 measured records. */
        public Object max_size;
        /** never populated in 2 measured records. */
        public Object max_value;
        /** never populated in 2 measured records. */
        public Object meta_data;
        /** never populated in 2 measured records. */
        public Object min_value;
        public String name;
        public String object;
        public List<CustomAttributeOption> options;
        public String status;
        /** never populated in 2 measured records. */
        public Object time_format;
        public String type;
        /** never populated in 2 measured records. */
        public Object unit;
        /** never populated in 2 measured records. */
        public Object uom;
        public List<CustomAttributeUseIn> use_in;
        public Long version;
    }

    /** A page of CustomAttribute records. */
    public static final class CustomAttributeList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_attributes");

        public List<CustomAttribute> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The custom attributes body. */
    public static final class CustomAttributes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/custom_attributes");

        @Override
        protected String envelopeKey() {
            return "custom_attribute";
        }

        public List<CustomAttributesActivityLog> activity_logs;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public Boolean encrypt_data;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object include_seconds;
        public Boolean is_mandatory;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public Long max_length;
        /** Null in every record measured, so untyped. */
        public Object max_size;
        /** Null in every record measured, so untyped. */
        public Object max_value;
        public String meta_data;
        /** Null in every record measured, so untyped. */
        public Object min_value;
        public String name;
        public String object;
        public List<CustomAttributesOption> options;
        public String status;
        /** Null in every record measured, so untyped. */
        public Object time_format;
        public String type;
        /** Null in every record measured, so untyped. */
        public Object unit;
        /** Null in every record measured, so untyped. */
        public Object uom;
        public List<CustomAttributesUseIn> use_in;
        public String uuid;
        public Long version;
    }

    /** One entry of a {@link CustomAttribute}'s {@code options}. */
    public static final class CustomAttributeOption extends Dto {
        public Long display_order;
        public String name;
    }

    /** One entry of a {@link CustomAttribute}'s {@code use_in}. */
    public static final class CustomAttributeUseIn extends Dto {
        public List<Object> associated_account_groups;
        public List<Object> associated_item_groups;
        public List<Object> associated_user_groups;
        public Boolean enabled;
        public Boolean required;
        public String resource;
        public Boolean unique;
    }

    /** One entry of a {@link CustomAttributes}'s {@code options}. */
    public static final class CustomAttributesOption extends Dto {
        public Long display_order;
        public String name;
    }

    /** One entry of a {@link CustomAttributes}'s {@code use_in}. */
    public static final class CustomAttributesUseIn extends Dto {
        public List<Object> associated_account_groups;
        public List<Object> associated_item_groups;
        public List<Object> associated_user_groups;
        public Boolean enabled;
        public Boolean required;
        public String resource;
        public Boolean unique;
    }

    /** One entry of a {@link CustomAttributes}'s {@code activity_logs}. */
    public static final class CustomAttributesActivityLog extends Dto {
        public String activity;
    }

    private CustomAttributesDto() {
    }
}
