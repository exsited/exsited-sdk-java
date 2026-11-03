package com.exsited.resources.operators;

/** Path templates and body keys for the operator endpoints. */
public final class OperatorsUrls {

    private OperatorsUrls() {}

    public static final String SETTINGS_OPERATORS = "/api/{api_version}/settings/operators";
    public static final String SETTINGS_OPERATOR = "/api/{api_version}/settings/operators/{operator_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_OPERATORS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String OPERATOR_KEY = "operator";
}
