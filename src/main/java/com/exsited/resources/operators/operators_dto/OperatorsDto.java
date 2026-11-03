package com.exsited.resources.operators.operators_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the operator bodies. */
public final class OperatorsDto {

    private OperatorsDto() {}

    /** The operator body, under {@code operator}. */
    public static final class SettingsOperator extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/operators",
                "GET /api/{api_version}/settings/operators/{operator_id}",
                "POST /api/{api_version}/settings/operators",
                "PATCH /api/{api_version}/settings/operators/{operator_id}");

        @Override
        protected String envelopeKey() {
            return "operator";
        }

        public Object created_by;
        public String created_on;
        public Object description;
        public String display_name;
        public String email;
        public String href;
        public String id;
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
    }

    /** One page of {@code SettingsOperator} records, under {@code data}. */
    public static final class SettingsOperatorList extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/operators");

        public List<SettingsOperator> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
