package com.exsited.resources.integration_connections;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.integration_connections.integration_connections_dto.IntegrationConnectionsDto;
import java.util.Map;

/**
 * Forwards every call to {@link IntegrationConnections}.
 *
 * @deprecated {@link IntegrationConnections} is bound to a client itself and is what
 *     {@code client.integrationConnections()} answers; use it instead.
 */
@Deprecated
public final class IntegrationConnectionsResource implements BoundResource {

    private final IntegrationConnections integrationConnections;

    public IntegrationConnectionsResource(ExsitedClient client) {
        this.integrationConnections = new IntegrationConnections(client);
    }

    @Override
    public ExsitedClient getClient() {
        return integrationConnections.getClient();
    }

    public ApiResponse<IntegrationConnectionsDto.IntegrationConnections> getIntegrationConnections(
            Map<String, Object> queryParams, RequestOptions options) {
        return integrationConnections.getIntegrationConnections(queryParams, options);
    }

    public ApiResponse<IntegrationConnectionsDto.IntegrationConnections> getIntegrationConnections(
            Map<String, Object> queryParams) {
        return integrationConnections.getIntegrationConnections(queryParams);
    }

    public ApiResponse<Dto> getIntegrationConnection(
            String integrationConnectionId, Map<String, Object> queryParams, RequestOptions options) {
        return integrationConnections.getIntegrationConnection(integrationConnectionId, queryParams, options);
    }

    public ApiResponse<Dto> getIntegrationConnection(
            String integrationConnectionId, Map<String, Object> queryParams) {
        return integrationConnections.getIntegrationConnection(integrationConnectionId, queryParams);
    }
}
