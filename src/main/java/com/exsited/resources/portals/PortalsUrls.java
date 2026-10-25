package com.exsited.resources.portals;

/** Path templates and body keys for the portal endpoints. */
public final class PortalsUrls {

    private PortalsUrls() {}

    public static final String PORTAL_ACTIVATE = "/api/{api_version}/portal/activate";
    public static final String PORTAL_CHANGE_PASSWORD = "/api/{api_version}/portal/change_password";
    public static final String PORTAL_LOGIN = "/api/{api_version}/portal/login";
    public static final String PORTAL_RESET = "/api/{api_version}/portal/reset";
    public static final String PORTAL_RESET_LINK = "/api/{api_version}/portal/reset_link";
    public static final String PORTAL_VALIDATE = "/api/{api_version}/portal/validate";
    public static final String PORTAL_CHANGE = "/api/{api_version}/portal/change";

    /** The envelope one record arrives under. */
    public static final String PORTAL_KEY = "portal";
}
