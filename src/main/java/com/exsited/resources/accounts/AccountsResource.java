package com.exsited.resources.accounts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.account_emails.account_emails_dto.AccountEmailsDto.AccountCommunicationsEmail;
import com.exsited.resources.account_emails.account_emails_dto.AccountEmailsDto.AccountCommunicationsEmailList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.Account;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountAddress;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountContact;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountImage;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountNoteFile;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteView;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteList;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceList;
import com.exsited.resources.orders.orders_dto.OrdersDto.AccountOrders;
import com.exsited.resources.payments.payments_dto.PaymentsDto.AccountPayments;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.AccountPurchaseInvoices;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.AccountPurchaseOrders;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.AccountPurchasePayments;
import com.exsited.resources.purchase_refunds.purchase_refunds_dto.PurchaseRefundsDto.AccountPurchaseRefunds;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.AccountRefunds;
import com.exsited.resources.settings.settings_dto.SettingsDto.CustomObjectList;
import java.util.Map;

/**
 * Forwards every call to {@link Accounts}.
 *
 * @deprecated {@link Accounts} is bound to a client itself and is what
 *     {@code client.accounts()} answers; use it instead.
 */
@Deprecated
public final class AccountsResource implements BoundResource {

    private final Accounts accounts;

    public AccountsResource(ExsitedClient client) {
        this.accounts = new Accounts(client);
    }

    @Override
    public ExsitedClient getClient() {
        return accounts.getClient();
    }

    public ApiResponse<AccountList> getAccounts(
            Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccounts(queryParams, options);
    }

    public ApiResponse<AccountList> getAccounts(Map<String, Object> queryParams) {
        return accounts.getAccounts(queryParams);
    }

    public ApiResponse<Account> getAccount(String accountId, RequestOptions options) {
        return accounts.getAccount(accountId, options);
    }

    public ApiResponse<Account> getAccount(String accountId) {
        return accounts.getAccount(accountId);
    }

    public ApiResponse<Account> getAccountInformation(String accountId, RequestOptions options) {
        return accounts.getAccountInformation(accountId, options);
    }

    public ApiResponse<Account> getAccountInformation(String accountId) {
        return accounts.getAccountInformation(accountId);
    }

    public ApiResponse<AccountImage> getAccountImage(String accountId, RequestOptions options) {
        return accounts.getAccountImage(accountId, options);
    }

    public ApiResponse<AccountImage> getAccountImage(String accountId) {
        return accounts.getAccountImage(accountId);
    }

    public ApiResponse<Account> getAccountContacts(String accountId, RequestOptions options) {
        return accounts.getAccountContacts(accountId, options);
    }

    public ApiResponse<Account> getAccountContacts(String accountId) {
        return accounts.getAccountContacts(accountId);
    }

    public ApiResponse<Account> getAccountAddresses(String accountId, RequestOptions options) {
        return accounts.getAccountAddresses(accountId, options);
    }

    public ApiResponse<Account> getAccountAddresses(String accountId) {
        return accounts.getAccountAddresses(accountId);
    }

    public ApiResponse<Account> getAccountBillingPreferences(
            String accountId, RequestOptions options) {
        return accounts.getAccountBillingPreferences(accountId, options);
    }

    public ApiResponse<Account> getAccountBillingPreferences(String accountId) {
        return accounts.getAccountBillingPreferences(accountId);
    }

    public ApiResponse<Account> getAccountPaymentMethods(String accountId, RequestOptions options) {
        return accounts.getAccountPaymentMethods(accountId, options);
    }

    public ApiResponse<Account> getAccountPaymentMethods(String accountId) {
        return accounts.getAccountPaymentMethods(accountId);
    }

    public ApiResponse<Account> getAccountInventories(String accountId, RequestOptions options) {
        return accounts.getAccountInventories(accountId, options);
    }

    public ApiResponse<Account> getAccountInventories(String accountId) {
        return accounts.getAccountInventories(accountId);
    }

    public ApiResponse<Account> getAccountQuotePartialInvoices(
            String accountId, RequestOptions options) {
        return accounts.getAccountQuotePartialInvoices(accountId, options);
    }

    public ApiResponse<Account> getAccountQuotePartialInvoices(String accountId) {
        return accounts.getAccountQuotePartialInvoices(accountId);
    }

    public ApiResponse<AccountContact> getAccountContact(
            String accountId, String contactType, RequestOptions options) {
        return accounts.getAccountContact(accountId, contactType, options);
    }

    public ApiResponse<AccountAddress> getAccountAddress(
            String accountId, String addressId, RequestOptions options) {
        return accounts.getAccountAddress(accountId, addressId, options);
    }

    public ApiResponse<Account> getAccountPaymentMethod(
            String accountId, String paymentMethodId, RequestOptions options) {
        return accounts.getAccountPaymentMethod(accountId, paymentMethodId, options);
    }

    public ApiResponse<NoteList> getAccountNotes(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountNotes(accountId, queryParams, options);
    }

    public ApiResponse<NoteView> getAccountNote(
            String accountId, String noteId, RequestOptions options) {
        return accounts.getAccountNote(accountId, noteId, options);
    }

    public ApiResponse<NoteFileList> getAccountNoteFiles(
            String accountId, String noteId, RequestOptions options) {
        return accounts.getAccountNoteFiles(accountId, noteId, options);
    }

    public ApiResponse<AccountNoteFile> getAccountNoteFile(
            String accountId, String noteId, String fileId, RequestOptions options) {
        return accounts.getAccountNoteFile(accountId, noteId, fileId, options);
    }

    public ApiResponse<AccountCommunicationsEmailList> getAccountEmails(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountEmails(accountId, queryParams, options);
    }

    public ApiResponse<AccountCommunicationsEmail> getAccountEmail(
            String accountId, String emailId, RequestOptions options) {
        return accounts.getAccountEmail(accountId, emailId, options);
    }

    public ApiResponse<AccountOrders> getAccountOrders(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountOrders(accountId, queryParams, options);
    }

    public ApiResponse<InvoiceList> getAccountInvoices(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountInvoices(accountId, queryParams, options);
    }

    public ApiResponse<AccountPayments> getAccountPayments(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountPayments(accountId, queryParams, options);
    }

    public ApiResponse<AccountRefunds> getAccountRefunds(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountRefunds(accountId, queryParams, options);
    }

    public ApiResponse<CreditNoteList> getAccountCreditNotes(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountCreditNotes(accountId, queryParams, options);
    }

    public ApiResponse<AccountPurchaseOrders> getAccountPurchaseOrders(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountPurchaseOrders(accountId, queryParams, options);
    }

    public ApiResponse<AccountPurchaseInvoices> getAccountPurchaseInvoices(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountPurchaseInvoices(accountId, queryParams, options);
    }

    public ApiResponse<AccountPurchasePayments> getAccountPurchasePayments(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountPurchasePayments(accountId, queryParams, options);
    }

    public ApiResponse<AccountPurchaseRefunds> getAccountPurchaseRefunds(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountPurchaseRefunds(accountId, queryParams, options);
    }

    public ApiResponse<CustomObjectList> getAccountCustomObjects(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return accounts.getAccountCustomObjects(accountId, queryParams, options);
    }

    public ApiResponse<Account> cancelAccount(
            String accountId, Object payload, RequestOptions options) {
        return accounts.cancelAccount(accountId, payload, options);
    }

    public ApiResponse<Account> setAccountPassword(
            String accountId, Object payload, RequestOptions options) {
        return accounts.setAccountPassword(accountId, payload, options);
    }

    public ApiResponse<Account> updateAccountInformation(
            String accountId, Object payload, RequestOptions options) {
        return accounts.updateAccountInformation(accountId, payload, options);
    }

    public ApiResponse<Account> createAccountAddresses(
            String accountId, Object payload, RequestOptions options) {
        return accounts.createAccountAddresses(accountId, payload, options);
    }

    public ApiResponse<Account> updateAccountAddress(
            String accountId, String addressId, Object payload, RequestOptions options) {
        return accounts.updateAccountAddress(accountId, addressId, payload, options);
    }

    public ApiResponse<Account> updateAccountContact(
            String accountId, String contactType, Object payload, RequestOptions options) {
        return accounts.updateAccountContact(accountId, contactType, payload, options);
    }

    public ApiResponse<Account> replaceAccountContact(
            String accountId, String contactType, Object payload, RequestOptions options) {
        return accounts.replaceAccountContact(accountId, contactType, payload, options);
    }

    public ApiResponse<Account> replaceAccountBillingPreferences(
            String accountId, Object payload, RequestOptions options) {
        return accounts.replaceAccountBillingPreferences(accountId, payload, options);
    }

    public ApiResponse<Account> updateAccountPaymentMethod(
            String accountId, String paymentMethodId, Object payload, RequestOptions options) {
        return accounts.updateAccountPaymentMethod(accountId, paymentMethodId, payload, options);
    }

    public ApiResponse<NoteView> createAccountNote(
            String accountId, String note, RequestOptions options) {
        return accounts.createAccountNote(accountId, note, options);
    }

    public ApiResponse<NoteView> updateAccountNote(
            String accountId, String noteId, String note, RequestOptions options) {
        return accounts.updateAccountNote(accountId, noteId, note, options);
    }

    public ApiResponse<Account> uploadAccountImage(
            String accountId, Object payload, RequestOptions options) {
        return accounts.uploadAccountImage(accountId, payload, options);
    }

    public ApiResponse<Dto> deleteAccount(String accountId, RequestOptions options) {
        return accounts.deleteAccount(accountId, options);
    }

    public ApiResponse<Dto> deleteAccountContact(
            String accountId, String contactType, RequestOptions options) {
        return accounts.deleteAccountContact(accountId, contactType, options);
    }

    public ApiResponse<Dto> deleteAccountAddress(
            String accountId, String addressId, RequestOptions options) {
        return accounts.deleteAccountAddress(accountId, addressId, options);
    }

    public ApiResponse<Dto> deleteAccountPaymentMethod(
            String accountId, String paymentMethodId, RequestOptions options) {
        return accounts.deleteAccountPaymentMethod(accountId, paymentMethodId, options);
    }

    public ApiResponse<Dto> deleteAccountNote(
            String accountId, String noteId, RequestOptions options) {
        return accounts.deleteAccountNote(accountId, noteId, options);
    }

    public ApiResponse<Dto> deleteAccountImage(String accountId, RequestOptions options) {
        return accounts.deleteAccountImage(accountId, options);
    }
}
