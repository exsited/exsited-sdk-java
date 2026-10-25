package com.exsited.resources.account_emails;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;

/** Account email endpoints, under {@code /api/{api_version}/accounts}. */
public final class AccountEmails implements BoundResource {

    private final ExsitedClient client;

    public AccountEmails(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Delete email: {@code DELETE /accounts/{account_id}/communications/emails/{email_uuid}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteAccountCommunicationsEmail(
            String accountId, String emailUuid, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(emailUuid, "email_uuid");
        return client.sendDeleteRequest(AccountEmailsUrls.ACCOUNT_COMMUNICATIONS_EMAIL,
                reopen(options).pathParam("account_id", accountId).pathParam("email_uuid", emailUuid).build(), null);
    }

    public ApiResponse<Dto> deleteAccountCommunicationsEmail(
            String accountId, String emailUuid) {
        return deleteAccountCommunicationsEmail(accountId, emailUuid, null);
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
