package com.exsited.resources.milestones;

/** Path templates and body keys for the milestone endpoints. */
public final class MilestonesUrls {

    private MilestonesUrls() {}

    public static final String MILESTONES = "/api/{api_version}/milestones";
    public static final String MILESTONES_ACCOUNT_BY_ID = "/api/{api_version}/milestones/account/{account_id}";
    public static final String MILESTONES_LABOUR_BY_ID = "/api/{api_version}/milestones/labour/{labour_id}";
    public static final String MILESTONES_EMPLOYEE_BY_CODE = "/api/{api_version}/milestones/employee/{employee_code}";
    public static final String MILESTONE = "/api/{api_version}/milestones/{milestone_uuid}";

    /** The key a listing's records sit under. */
    public static final String MILESTONES_ACCOUNT_BY_ID_KEY = "data";
    public static final String MILESTONES_KEY = "data";
    public static final String MILESTONES_EMPLOYEE_BY_CODE_KEY = "data";
    public static final String MILESTONE_KEY = "milestone";
}
