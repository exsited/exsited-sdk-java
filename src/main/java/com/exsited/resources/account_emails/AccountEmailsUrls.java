package com.exsited.resources.account_emails;

/** Path templates and body keys for the account email endpoints. */
public final class AccountEmailsUrls {

    private AccountEmailsUrls() {}

    public static final String ACCOUNT_COMMUNICATIONS_EMAIL = "/api/{api_version}/accounts/{account_id}/communications/emails/{email_uuid}";

    /** The envelope one record arrives under. */
    public static final String EMAIL_KEY = "email";
}
