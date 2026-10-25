package com.exsited.resources.integration_connections.integration_connections_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the integration connections bodies. */
public final class IntegrationConnectionsDto {

    /** The integration connections body. */
    public static final class IntegrationConnections extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/integration_connections");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private IntegrationConnectionsDto() {
    }
}
