package com.exsited.resources.integration_connections;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.integration_connections.integration_connections_dto.IntegrationConnectionsDto;
import java.util.Map;

/** Integration connection endpoints, under {@code /api/{api_version}/integration_connections}. */
public final class IntegrationConnections implements BoundResource {

    private final ExsitedClient client;

    public IntegrationConnections(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List connections: {@code GET /integration_connections}. Filter by status with
     * {@code filter_status} ({@code QueryParams.filterParam("status")}): ACTIVE, INACTIVE or
     * DISCONNECTED; v4 ignores a plain {@code status}.
     */
    public ApiResponse<IntegrationConnectionsDto.IntegrationConnections> getIntegrationConnections(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(IntegrationConnectionsUrls.INTEGRATION_CONNECTIONS,
                reopen(options).queryParams(queryParams).build(), IntegrationConnectionsDto.IntegrationConnections.class);
    }

    public ApiResponse<IntegrationConnectionsDto.IntegrationConnections> getIntegrationConnections(
            Map<String, Object> queryParams) {
        return getIntegrationConnections(queryParams, null);
    }

    public ApiResponse<IntegrationConnectionsDto.IntegrationConnections> getIntegrationConnections() {
        return getIntegrationConnections(null, null);
    }

    /**
     * Get single: {@code GET /integration_connections/{integration_connection_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> getIntegrationConnection(
            String integrationConnectionId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(integrationConnectionId, "integration_connection_id");
        return client.sendGetRequest(IntegrationConnectionsUrls.INTEGRATION_CONNECTION,
                reopen(options).pathParam("integration_connection_id", integrationConnectionId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getIntegrationConnection(
            String integrationConnectionId, Map<String, Object> queryParams) {
        return getIntegrationConnection(integrationConnectionId, queryParams, null);
    }

    public ApiResponse<Dto> getIntegrationConnection(String integrationConnectionId) {
        return getIntegrationConnection(integrationConnectionId, null, null);
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
}
