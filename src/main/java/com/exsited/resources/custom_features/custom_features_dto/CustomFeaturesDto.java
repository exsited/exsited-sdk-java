package com.exsited.resources.custom_features.custom_features_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the custom features bodies. */
public final class CustomFeaturesDto {
    /** The custom feature body. */
    public static final class CustomFeature extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/custom_feature",
                "PATCH /api/{api_version}/custom_feature/{custom_feature_id}",
                "POST /api/{api_version}/custom_feature/{custom_feature_id}");

        public CustomFeatureDetail custom_feature;
        public String message;
    }

    /** A {@link CustomFeature}'s {@code custom_feature}. */
    public static final class CustomFeatureDetail extends Dto {
        public List<Object> activity_logs;
        public List<Object> child_custom_components;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object custom_component;
        public List<CustomFeatureDetailCustomForm> custom_forms;
        public List<Object> custom_objects;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String status;
        public String use_in;
        public Long version;
    }

    /** One entry of a {@link CustomFeatureDetail}'s {@code custom_forms}. */
    public static final class CustomFeatureDetailCustomForm extends Dto {
        public CustomFeatureDetailCustomFormConfiguration configuration;
    }

    /** A {@link CustomFeatureDetailCustomForm}'s {@code configuration}. */
    public static final class CustomFeatureDetailCustomFormConfiguration extends Dto {
        public CustomFeatureDetailCustomFormConfigurationColumn1 column_1;
        public CustomFeatureDetailCustomFormConfigurationColumn2 column_2;
        public CustomFeatureDetailCustomFormConfigurationSettings settings;
    }

    /** A {@link CustomFeatureDetailCustomFormConfiguration}'s {@code column_1}. */
    public static final class CustomFeatureDetailCustomFormConfigurationColumn1 extends Dto {
        public List<CustomFeatureDetailCustomFormConfigurationColumn1Attribute> attributes;
    }

    /** A {@link CustomFeatureDetailCustomFormConfiguration}'s {@code column_2}. */
    public static final class CustomFeatureDetailCustomFormConfigurationColumn2 extends Dto {
        public List<CustomFeatureDetailCustomFormConfigurationColumn2Attribute> attributes;
    }

    /** A {@link CustomFeatureDetailCustomFormConfiguration}'s {@code settings}. */
    public static final class CustomFeatureDetailCustomFormConfigurationSettings extends Dto {
        public String custom_form;
        public String form_layout;
    }

    /**
     * One entry of a {@link CustomFeatureDetailCustomFormConfigurationColumn1}'s {@code
     * attributes}.
     */
    public static final class CustomFeatureDetailCustomFormConfigurationColumn1Attribute extends Dto {
        public String data_type;
        public String display_name;
        public String name;
        public Boolean show_in_list_view;
        public String type;
    }

    /**
     * One entry of a {@link CustomFeatureDetailCustomFormConfigurationColumn2}'s {@code
     * attributes}.
     */
    public static final class CustomFeatureDetailCustomFormConfigurationColumn2Attribute extends Dto {
        public String name;
        public String type;
    }

    private CustomFeaturesDto() {
    }

    /** One page of what {@code getCustomFeatures} lists, under {@code data}. */
    public static final class CustomFeatureList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_feature");

        public List<CustomFeatureDetail> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
