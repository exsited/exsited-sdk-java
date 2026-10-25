package com.exsited.resources.alert_responsibility.alert_responsibility_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the alert responsibility bodies. */
public final class AlertResponsibilityDto {

    private AlertResponsibilityDto() {}

    /**
     * The alert responsibility body, under {@code alert_responsibility}; {@code activity_logs}
     * comes on the create answer only.
     */
    public static final class AlertResponsibility extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/alert_responsibility",
                "GET /api/{api_version}/alert_responsibility/{alert_responsibility_id}",
                "POST /api/{api_version}/alert_responsibility",
                "PATCH /api/{api_version}/alert_responsibility/{alert_responsibility_id}");

        @Override
        protected String envelopeKey() {
            return "alert_responsibility";
        }

        public List<Object> activity_logs;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code AlertResponsibility} records, under {@code data}. */
    public static final class AlertResponsibilityList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/alert_responsibility");

        public List<AlertResponsibility> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
