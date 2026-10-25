package com.exsited.resources.account_emails;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;

/**
 * Forwards every call to {@link AccountEmails}.
 *
 * @deprecated {@link AccountEmails} is bound to a client itself and is what
 *     {@code client.accountEmails()} answers; use it instead.
 */
@Deprecated
public final class AccountEmailsResource implements BoundResource {

    private final AccountEmails accountEmails;

    public AccountEmailsResource(ExsitedClient client) {
        this.accountEmails = new AccountEmails(client);
    }

    @Override
    public ExsitedClient getClient() {
        return accountEmails.getClient();
    }

    public ApiResponse<Dto> deleteAccountCommunicationsEmail(
            String accountId, String emailUuid, RequestOptions options) {
        return accountEmails.deleteAccountCommunicationsEmail(accountId, emailUuid, options);
    }

    public ApiResponse<Dto> deleteAccountCommunicationsEmail(String accountId, String emailUuid) {
        return accountEmails.deleteAccountCommunicationsEmail(accountId, emailUuid);
    }
}
