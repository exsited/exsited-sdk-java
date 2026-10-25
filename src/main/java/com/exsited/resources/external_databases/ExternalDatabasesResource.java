package com.exsited.resources.external_databases;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseInsertRecord;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseSearch;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabaseUpdateRecordsUpdated;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto.ExternalDatabasesView;
import com.exsited.resources.external_databases.external_databases_dto.ExternalDatabasesDto;
import java.util.Map;

/**
 * Forwards every call to {@link ExternalDatabases}.
 *
 * @deprecated {@link ExternalDatabases} is bound to a client itself and is what
 *     {@code client.externalDatabases()} answers; use it instead.
 */
@Deprecated
public final class ExternalDatabasesResource implements BoundResource {

    private final ExternalDatabases externalDatabases;

    public ExternalDatabasesResource(ExsitedClient client) {
        this.externalDatabases = new ExternalDatabases(client);
    }

    @Override
    public ExsitedClient getClient() {
        return externalDatabases.getClient();
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabases(
            Map<String, Object> queryParams, RequestOptions options) {
        return externalDatabases.getExternalDatabases(queryParams, options);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabases(
            Map<String, Object> queryParams) {
        return externalDatabases.getExternalDatabases(queryParams);
    }

    public ApiResponse<ExternalDatabasesView> createExternalDatabase(Object payload, RequestOptions options) {
        return externalDatabases.createExternalDatabase(payload, options);
    }

    public ApiResponse<ExternalDatabasesView> createExternalDatabase(Object payload) {
        return externalDatabases.createExternalDatabase(payload);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabase(
            String dbName, Map<String, Object> queryParams, RequestOptions options) {
        return externalDatabases.getExternalDatabase(dbName, queryParams, options);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabase(
            String dbName, Map<String, Object> queryParams) {
        return externalDatabases.getExternalDatabase(dbName, queryParams);
    }

    public ApiResponse<ExternalDatabaseInsertRecord> createExternalDatabaseInsertRecord(
            String dbName, Object payload, RequestOptions options) {
        return externalDatabases.createExternalDatabaseInsertRecord(dbName, payload, options);
    }

    public ApiResponse<ExternalDatabaseInsertRecord> createExternalDatabaseInsertRecord(String dbName, Object payload) {
        return externalDatabases.createExternalDatabaseInsertRecord(dbName, payload);
    }

    public ApiResponse<ExternalDatabaseSearch> createExternalDatabaseSearch(
            String dbName, Object payload, RequestOptions options) {
        return externalDatabases.createExternalDatabaseSearch(dbName, payload, options);
    }

    public ApiResponse<ExternalDatabaseSearch> createExternalDatabaseSearch(String dbName, Object payload) {
        return externalDatabases.createExternalDatabaseSearch(dbName, payload);
    }

    public ApiResponse<ExternalDatabaseUpdateRecordsUpdated> updateExternalDatabaseUpdateRecords(
            String dbName, Object payload, RequestOptions options) {
        return externalDatabases.updateExternalDatabaseUpdateRecords(dbName, payload, options);
    }

    public ApiResponse<ExternalDatabaseUpdateRecordsUpdated> updateExternalDatabaseUpdateRecords(String dbName, Object payload) {
        return externalDatabases.updateExternalDatabaseUpdateRecords(dbName, payload);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabaseTable(
            String dbName, String tableName, Map<String, Object> queryParams, RequestOptions options) {
        return externalDatabases.getExternalDatabaseTable(dbName, tableName, queryParams, options);
    }

    public ApiResponse<ExternalDatabasesDto.ExternalDatabases> getExternalDatabaseTable(
            String dbName, String tableName, Map<String, Object> queryParams) {
        return externalDatabases.getExternalDatabaseTable(dbName, tableName, queryParams);
    }
}
