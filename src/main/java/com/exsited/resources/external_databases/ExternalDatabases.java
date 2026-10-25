package com.exsited.resources.external_databases;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseInsertRecord;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseSearch;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseUpdateRecordsUpdated;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabasesView;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto;
import java.util.Map;

/** External database endpoints, under {@code /api/{api_version}/external_databases}. */
public final class ExternalDatabases implements BoundResource {

    private final ExsitedClient client;

    public ExternalDatabases(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List databases: {@code GET /external_databases}. */
    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabases(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ExternalDatabasesUrls.EXTERNAL_DATABASES,
                reopen(options).queryParams(queryParams).build(), ExternalDatabasesDto.ExternalDatabases.class);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabases(
            Map<String, Object> queryParams) {
        return getExternalDatabases(queryParams, null);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabases() {
        return getExternalDatabases(null, null);
    }

    /**
     * Create a database and its tables: {@code POST /external_databases}.
     */
    public ApiResponse<ExternalDatabasesView> createExternalDatabase(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ExternalDatabasesUrls.EXTERNAL_DATABASES,
                reopen(options).jsonBody(payload).build(), ExternalDatabasesView.class);
    }

    public ApiResponse<ExternalDatabasesView> createExternalDatabase(
            Object payload) {
        return createExternalDatabase(payload, null);
    }

    /** Get single database: {@code GET /external_databases/{db_name}}. */
    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabase(
            String dbName, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(dbName, "db_name");
        return client.sendGetRequest(ExternalDatabasesUrls.EXTERNAL_DATABASE,
                reopen(options).pathParam("db_name", dbName).queryParams(queryParams).build(), ExternalDatabasesDto.ExternalDatabases.class);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabase(
            String dbName, Map<String, Object> queryParams) {
        return getExternalDatabase(dbName, queryParams, null);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabase(String dbName) {
        return getExternalDatabase(dbName, null, null);
    }

    /**
     * Insert records: {@code POST /external_databases/{db_name}/insert_records}.
     */
    public ApiResponse<ExternalDatabaseInsertRecord> createExternalDatabaseInsertRecord(
            String dbName, Object payload, RequestOptions options) {
        requireValue(dbName, "db_name");
        requirePayload(payload);
        return client.sendPostRequest(ExternalDatabasesUrls.EXTERNAL_DATABASE_INSERT_RECORDS,
                reopen(options).pathParam("db_name", dbName).jsonBody(payload).build(), ExternalDatabaseInsertRecord.class);
    }

    public ApiResponse<ExternalDatabaseInsertRecord> createExternalDatabaseInsertRecord(
            String dbName, Object payload) {
        return createExternalDatabaseInsertRecord(dbName, payload, null);
    }

    /**
     * Search records: {@code POST /external_databases/{db_name}/search}.
     */
    public ApiResponse<ExternalDatabaseSearch> createExternalDatabaseSearch(
            String dbName, Object payload, RequestOptions options) {
        requireValue(dbName, "db_name");
        requirePayload(payload);
        return client.sendPostRequest(ExternalDatabasesUrls.EXTERNAL_DATABASE_SEARCH,
                reopen(options).pathParam("db_name", dbName).jsonBody(payload).build(), ExternalDatabaseSearch.class);
    }

    public ApiResponse<ExternalDatabaseSearch> createExternalDatabaseSearch(
            String dbName, Object payload) {
        return createExternalDatabaseSearch(dbName, payload, null);
    }

    /**
     * Update records: {@code PATCH /external_databases/{db_name}/update_records}.
     */
    public ApiResponse<ExternalDatabaseUpdateRecordsUpdated> updateExternalDatabaseUpdateRecords(
            String dbName, Object payload, RequestOptions options) {
        requireValue(dbName, "db_name");
        requirePayload(payload);
        return client.sendPatchRequest(ExternalDatabasesUrls.EXTERNAL_DATABASE_UPDATE_RECORDS,
                reopen(options).pathParam("db_name", dbName).jsonBody(payload).build(), ExternalDatabaseUpdateRecordsUpdated.class);
    }

    public ApiResponse<ExternalDatabaseUpdateRecordsUpdated> updateExternalDatabaseUpdateRecords(
            String dbName, Object payload) {
        return updateExternalDatabaseUpdateRecords(dbName, payload, null);
    }

    /**
     * Get table columns: {@code GET /external_databases/{db_name}/tables/{table_name}}.
     */
    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabaseTable(
            String dbName, String tableName, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(dbName, "db_name");
        requireValue(tableName, "table_name");
        return client.sendGetRequest(ExternalDatabasesUrls.EXTERNAL_DATABASE_TABLE,
                reopen(options).pathParam("db_name", dbName).pathParam("table_name", tableName).queryParams(queryParams).build(), ExternalDatabasesDto.ExternalDatabases.class);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabaseTable(
            String dbName, String tableName, Map<String, Object> queryParams) {
        return getExternalDatabaseTable(dbName, tableName, queryParams, null);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabaseTable(String dbName, String tableName) {
        return getExternalDatabaseTable(dbName, tableName, null, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
