package com.exsited.resources.external_databases.external_databases_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the external databases bodies. */
public final class ExternalDatabasesDto {

    /** The external databases body. */
    public static final class ExternalDatabases extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/external_databases",
                "GET /api/{api_version}/external_databases/{db_name}",
                "GET /api/{api_version}/external_databases/{db_name}/tables/{table_name}");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code create_external_database} answers, under {@code external_database}. */
    public static final class ExternalDatabasesView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/external_databases");

        @Override
        protected String envelopeKey() {
            return "external_database";
        }

        public List<Object> activity_logs;
        public String message;
        public String name;
    }

    /**
     * What {@code create_external_database_insert_record} answers, under {@code external_database}.
     */
    public static final class ExternalDatabaseInsertRecord extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/external_databases/{db_name}/insert_records");

        @Override
        protected String envelopeKey() {
            return "external_database";
        }

        public List<Object> inserted_data;
        public String message;
        public Boolean success;
    }

    /** What {@code create_external_database_search} answers, under {@code external_database}. */
    public static final class ExternalDatabaseSearch extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/external_databases/{db_name}/search");

        @Override
        protected String envelopeKey() {
            return "external_database";
        }

        public Long count;
        public Long limit;
        public Map<String, Object> list;
        public Long offset;
        public Boolean success;
    }

    /**
     * What {@code update_external_database_update_records} answers, under {@code
     * external_database}.
     */
    public static final class ExternalDatabaseUpdateRecordsUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/external_databases/{db_name}/update_records");

        @Override
        protected String envelopeKey() {
            return "external_database";
        }

        public String message;
    }

    private ExternalDatabasesDto() {
    }
}
