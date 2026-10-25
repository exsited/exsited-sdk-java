package com.exsited.resources.milestones.milestones_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the milestones bodies. */
public final class MilestonesDto {
    /** The milestones body. */
    public static final class Milestones extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/milestones",
                "GET /api/{api_version}/milestones/account/{account_id}",
                "GET /api/{api_version}/milestones/labour/{labour_id}");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private MilestonesDto() {
    }
}
