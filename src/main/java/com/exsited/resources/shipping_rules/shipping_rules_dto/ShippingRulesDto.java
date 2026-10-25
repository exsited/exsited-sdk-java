package com.exsited.resources.shipping_rules.shipping_rules_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the shipping rule bodies. */
public final class ShippingRulesDto {

    private ShippingRulesDto() {}

    /** The settings shipping rule body, under {@code shipping_rule}. */
    public static final class SettingsShippingRule extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_rules",
                "GET /api/{api_version}/settings/shipping_rules/{shipping_rule_id}",
                "POST /api/{api_version}/settings/shipping_rules",
                "PATCH /api/{api_version}/settings/shipping_rules/{shipping_rule_id}");

        @Override
        protected String envelopeKey() {
            return "shipping_rule";
        }

        public SettingsShippingRuleConfiguration configuration;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object shipping_class;
        public String status;
        public Long version;
    }

    /** One page of {@code SettingsShippingRule} records, under {@code data}. */
    public static final class SettingsShippingRuleList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_rules");

        public List<SettingsShippingRule> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link SettingsShippingRule}'s {@code configuration}. */
    public static final class SettingsShippingRuleConfiguration extends Dto {
        /** Null in every record measured, so untyped. */
        public Object rate;
        /** Empty in every record measured, so untyped. */
        public List<Object> zones;
    }
}
