package com.exsited.resources.alerts.alerts_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the alerts bodies. */
public final class AlertsDto {

    /** The alerts body. */
    public static final class Alerts extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/alerts",
                "GET /api/{api_version}/alerts/account/{account_id}",
                "GET /api/{api_version}/alerts/employee/{employee_id}",
                "GET /api/{api_version}/alerts/labour/{labour_id}");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private AlertsDto() {
    }
}
