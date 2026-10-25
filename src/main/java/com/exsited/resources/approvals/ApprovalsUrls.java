package com.exsited.resources.approvals;

/** Path templates and body keys for the approval endpoints. */
public final class ApprovalsUrls {

    private ApprovalsUrls() {}

    public static final String APPROVALS_ACCOUNT = "/api/{api_version}/approvals/account";
    public static final String APPROVALS_SALEORDER = "/api/{api_version}/approvals/saleOrder";
    public static final String APPROVALS_ACCOUNT_BY_UUID = "/api/{api_version}/approvals/account/{account_uuid}";
    /** The entity is a body field here, not a path segment, so one route serves all 16. */
    public static final String APPROVALS_APPROVE = "/api/{api_version}/approvals/approve";
    public static final String APPROVALS_REJECT = "/api/{api_version}/approvals/reject";

    /** The key a listing's records sit under. */
    public static final String APPROVALS_ACCOUNT_KEY = "data";
    public static final String APPROVALS_SALEORDER_KEY = "data";
    public static final String APPROVAL_KEY = "approval";
}
