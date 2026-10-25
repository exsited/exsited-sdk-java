package com.exsited.resources.approvals.approvals_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the approvals bodies. */
public final class ApprovalsDto {

    /** The approvals account body. */
    public static final class ApprovalsAccount extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/approvals/account");

        public String created_on;
        /** never populated in 2 measured records. */
        public Object description;
        public String display_name;
        public String entity;
        public String event;
        public String href;
        public String id;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** A page of ApprovalsAccount records. */
    public static final class ApprovalsAccountList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/approvals/account");

        public List<ApprovalsAccount> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The approvals saleorder body. */
    public static final class ApprovalsSaleorder extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/approvals/saleOrder");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private ApprovalsDto() {
    }
}
