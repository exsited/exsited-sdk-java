package com.exsited.resources.timesheets.timesheets_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the timesheets bodies. */
public final class TimesheetsDto {

    /** The timesheet body. */
    public static final class Timesheet extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/timesheets");

        public TimesheetCreatedBy created_by;
        public String created_on;
        public String end_time;
        public String href;
        public String id;
        public TimesheetLabour labour;
        public TimesheetLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public TimesheetSaleOrder sale_order;
        public TimesheetSaleOrderItem sale_order_item;
        public String start_time;
        public String status;
        public Long version;
    }

    /** A page of Timesheet records. */
    public static final class TimesheetList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/timesheets");

        public List<Timesheet> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code create_timesheet} answers, under {@code timesheet}. */
    public static final class Timesheets extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/timesheets");

        @Override
        protected String envelopeKey() {
            return "timesheet";
        }

        public List<TimesheetsActivityLog> activity_logs;
        public TimesheetsCreatedBy created_by;
        public String created_on;
        public String end_time;
        public String href;
        public String id;
        public TimesheetsLabour labour;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public TimesheetsSaleOrder sale_order;
        public TimesheetsSaleOrderItem sale_order_item;
        public String start_time;
        public String status;
        public Long version;
    }

    /** What {@code update_timesheet} answers, under {@code timesheet}. */
    public static final class TimesheetUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/timesheets/{timesheet_id}");

        @Override
        protected String envelopeKey() {
            return "timesheet";
        }

        public List<Object> activity_logs;
        public TimesheetUpdatedCreatedBy created_by;
        public String created_on;
        public String end_time;
        public String href;
        public String id;
        public TimesheetUpdatedLabour labour;
        public TimesheetUpdatedLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public TimesheetUpdatedSaleOrder sale_order;
        public TimesheetUpdatedSaleOrderItem sale_order_item;
        public String start_time;
        public String status;
        public Long version;
    }

    /** A {@link Timesheets}'s {@code labour}. */
    public static final class TimesheetsLabour extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheets}'s {@code sale_order_item}. */
    public static final class TimesheetsSaleOrderItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheets}'s {@code sale_order}. */
    public static final class TimesheetsSaleOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheets}'s {@code created_by}. */
    public static final class TimesheetsCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link Timesheets}'s {@code activity_logs}. */
    public static final class TimesheetsActivityLog extends Dto {
        public String activity;
    }

    /** A {@link TimesheetUpdated}'s {@code labour}. */
    public static final class TimesheetUpdatedLabour extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link TimesheetUpdated}'s {@code sale_order_item}. */
    public static final class TimesheetUpdatedSaleOrderItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link TimesheetUpdated}'s {@code sale_order}. */
    public static final class TimesheetUpdatedSaleOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link TimesheetUpdated}'s {@code created_by}. */
    public static final class TimesheetUpdatedCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link TimesheetUpdated}'s {@code last_updated_by}. */
    public static final class TimesheetUpdatedLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Timesheet}'s {@code labour}. */
    public static final class TimesheetLabour extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheet}'s {@code sale_order_item}. */
    public static final class TimesheetSaleOrderItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheet}'s {@code sale_order}. */
    public static final class TimesheetSaleOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Timesheet}'s {@code created_by}. */
    public static final class TimesheetCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Timesheet}'s {@code last_updated_by}. */
    public static final class TimesheetLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    private TimesheetsDto() {
    }
}
