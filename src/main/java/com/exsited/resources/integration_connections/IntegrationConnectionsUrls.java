package com.exsited.resources.integration_connections;

/** Path templates and body keys for the integration connection endpoints. */
public final class IntegrationConnectionsUrls {

    private IntegrationConnectionsUrls() {}

    public static final String INTEGRATION_CONNECTIONS = "/api/{api_version}/integration_connections";
    public static final String INTEGRATION_CONNECTION = "/api/{api_version}/integration_connections/{integration_connection_id}";

    /** The key a listing's records sit under. */
    public static final String INTEGRATION_CONNECTIONS_KEY = "data";
}
