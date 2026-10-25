package com.exsited.resources.external_databases;

/** Path templates and body keys for the external database endpoints. */
public final class ExternalDatabasesUrls {

    private ExternalDatabasesUrls() {}

    public static final String EXTERNAL_DATABASES = "/api/{api_version}/external_databases";
    public static final String EXTERNAL_DATABASE = "/api/{api_version}/external_databases/{db_name}";
    public static final String EXTERNAL_DATABASE_INSERT_RECORDS = "/api/{api_version}/external_databases/{db_name}/insert_records";
    public static final String EXTERNAL_DATABASE_SEARCH = "/api/{api_version}/external_databases/{db_name}/search";
    public static final String EXTERNAL_DATABASE_UPDATE_RECORDS = "/api/{api_version}/external_databases/{db_name}/update_records";
    public static final String EXTERNAL_DATABASE_TABLE = "/api/{api_version}/external_databases/{db_name}/tables/{table_name}";

    /** The key a listing's records sit under. */
    public static final String EXTERNAL_DATABASES_KEY = "data";
    public static final String EXTERNAL_DATABASE_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String EXTERNAL_DATABASE_RECORD_KEY = "external_database";
}
