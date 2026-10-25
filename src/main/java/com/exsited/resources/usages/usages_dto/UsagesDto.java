package com.exsited.resources.usages.usages_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the usages bodies. */
public final class UsagesDto {

    /** The usage body. */
    public static final class Usage extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/usages",
                "GET /api/{api_version}/usages/{usage_id}");

        @Override
        protected String envelopeKey() {
            return "usage";
        }

        public UsageChargeItem charge_item;
        public String charging_end_date;
        public String charging_period;
        public String charging_start_date;
        public UsageCreatedBy created_by;
        public String created_on;
        public List<UsageCustomAttribute> custom_attributes;
        public String end_time;
        public String href;
        public String id;
        public UsageLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        public Double quantity;
        /** never populated in 2 measured records. */
        public Object source;
        public String start_time;
        public String status;
        public String type;
        /** never populated in 2 measured records. */
        public Object uom;
        public Long version;
    }

    /** A page of Usage records. */
    public static final class UsageList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/usages");

        public List<Usage> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The usages body. */
    public static final class Usages extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/usages");

        @Override
        protected String envelopeKey() {
            return "usage";
        }

        public List<Object> activity_logs;
        public String charge_item_name;
        public String charge_item_uuid;
        public String charge_status;
        public String charging_period;
        public String created_by;
        public String created_on;
        public List<Object> custom_attributes;
        public String end_time;
        public String event_id;
        public List<Object> failed;
        public String href;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public String object;
        public String quantity;
        public String source;
        public String start_time;
        public List<Object> success;
        public String type;
        public String uom;
        public UsagesDetail usages;
        public String uuid;
        public String version;
    }

    /** The usage updated body. */
    public static final class UsageUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/usages/{usage_id}",
                "PUT /api/{api_version}/usages/{usage_id}");

        public String note;
        public String status;
        public UsageUpdatedDetail usage;
    }

    /** A {@link Usage}'s {@code charge_item}. */
    public static final class UsageChargeItem extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link Usage}'s {@code custom_attributes}. */
    public static final class UsageCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** A {@link Usage}'s {@code created_by}. */
    public static final class UsageCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Usage}'s {@code last_updated_by}. */
    public static final class UsageLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Usages}'s {@code usages}. */
    public static final class UsagesDetail extends Dto {
        public List<Object> activity_logs;
        public List<UsagesDetailFailed> failed;
        public List<UsagesDetailSuccess> success;
    }

    /** A {@link UsageUpdated}'s {@code usage}. */
    public static final class UsageUpdatedDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link UsagesDetail}'s {@code success}. */
    public static final class UsagesDetailSuccess extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link UsagesDetail}'s {@code failed}. */
    public static final class UsagesDetailFailed extends Dto {
        public String charge_item_id;
        public String charging_period;
        public Map<String, Object> error;
    }

    private UsagesDto() {
    }
}
