package com.exsited.resources.accounts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.account_emails.account_emails_dto.AccountEmailsDto.AccountCommunicationsEmail;
import com.exsited.resources.account_emails.account_emails_dto.AccountEmailsDto.AccountCommunicationsEmailList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.Account;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountAddress;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountComponentObject;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountContact;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountCustomObject;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountFinancialSummary;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountImage;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountKpis;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountNoteFile;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteList;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteView;
import com.exsited.resources.accounts.accounts_dto.AccountsDto;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteList;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjects;
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

/** Account endpoints; {@link #createAccount} creates one at {@code POST /accounts}, answered 201. */
public final class Accounts implements BoundResource {

    private final ExsitedClient client;

    public Accounts(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List accounts; {@code limit}, {@code cursor} and {@code sort} page it, other names filter.
     */
    public ApiResponse<AccountList> getAccounts(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNTS,
                reopen(options).queryParams(queryParams).build(), AccountList.class);
    }

    public ApiResponse<AccountList> getAccounts(Map<String, Object> queryParams) {
        return getAccounts(queryParams, null);
    }

    public ApiResponse<AccountList> getAccounts() {
        return getAccounts(null, null);
    }

    /** One account by its short {@code id}; the {@code uuid} answers 404, filter on it instead. */
    public ApiResponse<Account> getAccount(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccount(String accountId) {
        return getAccount(accountId, null);
    }

    /** An account's core information: {@link #getAccount}'s body, read before an update. */
    public ApiResponse<Account> getAccountInformation(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_INFORMATION,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountInformation(String accountId) {
        return getAccountInformation(accountId, null);
    }

    /** The account's profile image reference: a URI, not bytes, and null when it has none. */
    public ApiResponse<AccountImage> getAccountImage(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_IMAGE,
                reopen(options).pathParam("account_id", accountId).build(), AccountImage.class);
    }

    public ApiResponse<AccountImage> getAccountImage(String accountId) {
        return getAccountImage(accountId, null);
    }

    /** An account's contacts, on the {@code account} envelope, not a list envelope. */
    public ApiResponse<Account> getAccountContacts(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CONTACTS,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountContacts(String accountId) {
        return getAccountContacts(accountId, null);
    }

    /** An account's addresses; each {@code "addr_…"} id addresses its update and delete. */
    public ApiResponse<Account> getAccountAddresses(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_ADDRESSES,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountAddresses(String accountId) {
        return getAccountAddresses(accountId, null);
    }

    /** Billing preferences; {@link AccountsDto.BillingPreferences} has both alignment names. */
    public ApiResponse<Account> getAccountBillingPreferences(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_BILLING_PREFERENCES,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountBillingPreferences(String accountId) {
        return getAccountBillingPreferences(accountId, null);
    }

    /** An account's payment methods; each {@code "pm_…"} id addresses its update and delete. */
    public ApiResponse<Account> getAccountPaymentMethods(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PAYMENT_METHODS,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountPaymentMethods(String accountId) {
        return getAccountPaymentMethods(accountId, null);
    }

    /** An account's inventories: unpaged, under {@code account}, not the list envelope. */
    public ApiResponse<Account> getAccountInventories(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_INVENTORIES,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountInventories(String accountId) {
        return getAccountInventories(accountId, null);
    }

    /** Partial invoices from quotes: unpaged, under {@code partial_invoices}, not the path. */
    public ApiResponse<Account> getAccountQuotePartialInvoices(
            String accountId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_QUOTE_PARTIAL_INVOICES,
                reopen(options).pathParam("account_id", accountId).build(), Account.class);
    }

    public ApiResponse<Account> getAccountQuotePartialInvoices(String accountId) {
        return getAccountQuotePartialInvoices(accountId, null);
    }

    /** One contact by type ({@code billing}, {@code shipping}, {@code primary}); an id is a 404. */
    public ApiResponse<AccountContact> getAccountContact(
            String accountId, String contactType, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CONTACT,
                reopen(options).pathParam("account_id", accountId).pathParam("contact_type", contactType).build(),
                AccountContact.class);
    }

    public ApiResponse<AccountContact> getAccountContact(String accountId, String contactType) {
        return getAccountContact(accountId, contactType, null);
    }

    /** Retrieve one address by its {@code "addr_…"} id. */
    public ApiResponse<AccountAddress> getAccountAddress(
            String accountId, String addressId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_ADDRESS,
                reopen(options).pathParam("account_id", accountId).pathParam("address_id", addressId).build(),
                AccountAddress.class);
    }

    public ApiResponse<AccountAddress> getAccountAddress(String accountId, String addressId) {
        return getAccountAddress(accountId, addressId, null);
    }

    /** One payment method; one not on the account answers 200 with no {@code payment_methods}. */
    public ApiResponse<Account> getAccountPaymentMethod(
            String accountId, String paymentMethodId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PAYMENT_METHOD,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("payment_method_id", paymentMethodId).build(),
                Account.class);
    }

    public ApiResponse<Account> getAccountPaymentMethod(String accountId, String paymentMethodId) {
        return getAccountPaymentMethod(accountId, paymentMethodId, null);
    }

    /** An account's notes: unlike the other sub-resources, a paged list envelope. */
    public ApiResponse<NoteList> getAccountNotes(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_NOTES,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(),
                NoteList.class);
    }

    public ApiResponse<NoteList> getAccountNotes(String accountId, Map<String, Object> queryParams) {
        return getAccountNotes(accountId, queryParams, null);
    }

    public ApiResponse<NoteList> getAccountNotes(String accountId) {
        return getAccountNotes(accountId, null, null);
    }

    /** Retrieve one note by its {@code "note_…"} id. */
    public ApiResponse<NoteView> getAccountNote(
            String accountId, String noteId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_NOTE,
                reopen(options).pathParam("account_id", accountId).pathParam("note_id", noteId).build(),
                NoteView.class);
    }

    public ApiResponse<NoteView> getAccountNote(String accountId, String noteId) {
        return getAccountNote(accountId, noteId, null);
    }

    /** List the files attached to a note. Each entry is identified by {@code uuid}. */
    public ApiResponse<NoteFileList> getAccountNoteFiles(
            String accountId, String noteId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_NOTE_FILES,
                reopen(options).pathParam("account_id", accountId).pathParam("note_id", noteId).build(),
                NoteFileList.class);
    }

    public ApiResponse<NoteFileList> getAccountNoteFiles(String accountId, String noteId) {
        return getAccountNoteFiles(accountId, noteId, null);
    }

    /** One file on a note; a binary one is bytes in {@code getMap()}, {@code getDto()} null. */
    public ApiResponse<AccountNoteFile> getAccountNoteFile(
            String accountId, String noteId, String fileId,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_NOTE_FILE,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("note_id", noteId).pathParam("file_id", fileId).build(),
                AccountNoteFile.class);
    }

    public ApiResponse<AccountNoteFile> getAccountNoteFile(String accountId, String noteId, String fileId) {
        return getAccountNoteFile(accountId, noteId, fileId, null);
    }

    /** List the emails sent to or received from an account. */
    public ApiResponse<AccountCommunicationsEmailList> getAccountEmails(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_EMAILS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountCommunicationsEmailList.class);
    }

    public ApiResponse<AccountCommunicationsEmailList> getAccountEmails(String accountId, Map<String, Object> queryParams) {
        return getAccountEmails(accountId, queryParams, null);
    }

    public ApiResponse<AccountCommunicationsEmailList> getAccountEmails(String accountId) {
        return getAccountEmails(accountId, null, null);
    }

    /** Retrieve one email by its {@code uuid}. */
    public ApiResponse<AccountCommunicationsEmail> getAccountEmail(
            String accountId, String emailId, RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_EMAIL,
                reopen(options).pathParam("account_id", accountId).pathParam("email_id", emailId).build(), AccountCommunicationsEmail.class);
    }

    public ApiResponse<AccountCommunicationsEmail> getAccountEmail(String accountId, String emailId) {
        return getAccountEmail(accountId, emailId, null);
    }

    // Related listings: each narrows another family's listing to one account and names its view.

    /** List an account's orders. */
    public ApiResponse<AccountOrders> getAccountOrders(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_ORDERS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountOrders.class);
    }

    public ApiResponse<AccountOrders> getAccountOrders(String accountId, Map<String, Object> queryParams) {
        return getAccountOrders(accountId, queryParams, null);
    }

    public ApiResponse<AccountOrders> getAccountOrders(String accountId) {
        return getAccountOrders(accountId, null, null);
    }

    /** List an account's invoices. */
    public ApiResponse<InvoiceList> getAccountInvoices(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_INVOICES,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), InvoiceList.class);
    }

    public ApiResponse<InvoiceList> getAccountInvoices(String accountId, Map<String, Object> queryParams) {
        return getAccountInvoices(accountId, queryParams, null);
    }

    public ApiResponse<InvoiceList> getAccountInvoices(String accountId) {
        return getAccountInvoices(accountId, null, null);
    }

    /** List an account's payments. */
    public ApiResponse<AccountPayments> getAccountPayments(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PAYMENTS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountPayments.class);
    }

    public ApiResponse<AccountPayments> getAccountPayments(String accountId, Map<String, Object> queryParams) {
        return getAccountPayments(accountId, queryParams, null);
    }

    public ApiResponse<AccountPayments> getAccountPayments(String accountId) {
        return getAccountPayments(accountId, null, null);
    }

    /** List an account's refunds. */
    public ApiResponse<AccountRefunds> getAccountRefunds(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_REFUNDS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountRefunds.class);
    }

    public ApiResponse<AccountRefunds> getAccountRefunds(String accountId, Map<String, Object> queryParams) {
        return getAccountRefunds(accountId, queryParams, null);
    }

    public ApiResponse<AccountRefunds> getAccountRefunds(String accountId) {
        return getAccountRefunds(accountId, null, null);
    }

    /** List an account's credit notes. */
    public ApiResponse<CreditNoteList> getAccountCreditNotes(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CREDIT_NOTES,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), CreditNoteList.class);
    }

    public ApiResponse<CreditNoteList> getAccountCreditNotes(String accountId, Map<String, Object> queryParams) {
        return getAccountCreditNotes(accountId, queryParams, null);
    }

    public ApiResponse<CreditNoteList> getAccountCreditNotes(String accountId) {
        return getAccountCreditNotes(accountId, null, null);
    }

    /** List an account's purchase orders. */
    public ApiResponse<AccountPurchaseOrders> getAccountPurchaseOrders(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PURCHASE_ORDERS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountPurchaseOrders.class);
    }

    public ApiResponse<AccountPurchaseOrders> getAccountPurchaseOrders(String accountId, Map<String, Object> queryParams) {
        return getAccountPurchaseOrders(accountId, queryParams, null);
    }

    public ApiResponse<AccountPurchaseOrders> getAccountPurchaseOrders(String accountId) {
        return getAccountPurchaseOrders(accountId, null, null);
    }

    /** List an account's purchase invoices. */
    public ApiResponse<AccountPurchaseInvoices> getAccountPurchaseInvoices(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PURCHASE_INVOICES,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountPurchaseInvoices.class);
    }

    public ApiResponse<AccountPurchaseInvoices> getAccountPurchaseInvoices(String accountId, Map<String, Object> queryParams) {
        return getAccountPurchaseInvoices(accountId, queryParams, null);
    }

    public ApiResponse<AccountPurchaseInvoices> getAccountPurchaseInvoices(String accountId) {
        return getAccountPurchaseInvoices(accountId, null, null);
    }

    /** List an account's purchase payments. */
    public ApiResponse<AccountPurchasePayments> getAccountPurchasePayments(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PURCHASE_PAYMENTS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountPurchasePayments.class);
    }

    public ApiResponse<AccountPurchasePayments> getAccountPurchasePayments(String accountId, Map<String, Object> queryParams) {
        return getAccountPurchasePayments(accountId, queryParams, null);
    }

    public ApiResponse<AccountPurchasePayments> getAccountPurchasePayments(String accountId) {
        return getAccountPurchasePayments(accountId, null, null);
    }

    /** List an account's purchase refunds. */
    public ApiResponse<AccountPurchaseRefunds> getAccountPurchaseRefunds(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_PURCHASE_REFUNDS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AccountPurchaseRefunds.class);
    }

    public ApiResponse<AccountPurchaseRefunds> getAccountPurchaseRefunds(String accountId, Map<String, Object> queryParams) {
        return getAccountPurchaseRefunds(accountId, queryParams, null);
    }

    public ApiResponse<AccountPurchaseRefunds> getAccountPurchaseRefunds(String accountId) {
        return getAccountPurchaseRefunds(accountId, null, null);
    }

    /** List an account's custom objects. */
    public ApiResponse<CustomObjectList> getAccountCustomObjects(
            String accountId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CUSTOM_OBJECTS,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), CustomObjectList.class);
    }

    public ApiResponse<CustomObjectList> getAccountCustomObjects(String accountId, Map<String, Object> queryParams) {
        return getAccountCustomObjects(accountId, queryParams, null);
    }

    public ApiResponse<CustomObjectList> getAccountCustomObjects(String accountId) {
        return getAccountCustomObjects(accountId, null, null);
    }

    /** Cancel an account; {@code effective_date} is {@code YYYY-MM-DD}, and a datetime is a 400. */
    public ApiResponse<Account> cancelAccount(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_CANCEL,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> cancelAccount(String accountId, Object payload) {
        return cancelAccount(accountId, payload, null);
    }

    /** Set the portal password, {@code password} and {@code confirm_password} with no envelope. */
    public ApiResponse<Account> setAccountPassword(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_SET_PASSWORD,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> setAccountPassword(String accountId, Object payload) {
        return setAccountPassword(accountId, payload, null);
    }

    /**
     * Partly update an account; {@code currency} and {@code time_zone} go in as plain strings, and
     * {@code addresses} and {@code communication_preference} are refused as {@code not_understood}.
     */
    public ApiResponse<Account> updateAccountInformation(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_INFORMATION,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> updateAccountInformation(String accountId, Object payload) {
        return updateAccountInformation(accountId, payload, null);
    }

    /**
     * Add a top-level {@code addresses} list; {@code state} and {@code country} are names, and the
     * write's {@code post_code} is the read's {@code postal_code}.
     */
    public ApiResponse<Account> createAccountAddresses(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_ADDRESSES,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> createAccountAddresses(String accountId, Object payload) {
        return createAccountAddresses(accountId, payload, null);
    }

    /** Update one address, under a singular {@code address} key, not {@code addresses}. */
    public ApiResponse<Account> updateAccountAddress(
            String accountId, String addressId, Object payload,
            RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_ADDRESS,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("address_id", addressId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> updateAccountAddress(String accountId, String addressId, Object payload) {
        return updateAccountAddress(accountId, addressId, payload, null);
    }

    /** Partly update a contact; names go in flat, though the read nests them under {@code name}. */
    public ApiResponse<Account> updateAccountContact(
            String accountId, String contactType, Object payload,
            RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_CONTACT,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("contact_type", contactType).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> updateAccountContact(String accountId, String contactType, Object payload) {
        return updateAccountContact(accountId, contactType, payload, null);
    }

    /** Replace one contact: a PUT, so a field left out is cleared. */
    public ApiResponse<Account> replaceAccountContact(
            String accountId, String contactType, Object payload,
            RequestOptions options) {
        requirePayload(payload);
        return client.sendPutRequest(AccountsUrls.ACCOUNT_CONTACT,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("contact_type", contactType).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> replaceAccountContact(String accountId, String contactType, Object payload) {
        return replaceAccountContact(accountId, contactType, payload, null);
    }

    /** Replace billing preferences; {@code billing_start_date} is an enum, a date is a 400. */
    public ApiResponse<Account> replaceAccountBillingPreferences(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPutRequest(AccountsUrls.ACCOUNT_BILLING_PREFERENCES,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> replaceAccountBillingPreferences(String accountId, Object payload) {
        return replaceAccountBillingPreferences(accountId, payload, null);
    }

    /** Update one payment method. */
    public ApiResponse<Account> updateAccountPaymentMethod(
            String accountId, String paymentMethodId, Object payload,
            RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_PAYMENT_METHOD,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("payment_method_id", paymentMethodId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> updateAccountPaymentMethod(String accountId, String paymentMethodId, Object payload) {
        return updateAccountPaymentMethod(accountId, paymentMethodId, payload, null);
    }

    /**
     * Attach a note, form-encoded as the {@code note} field; HTML comes back as written.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<NoteView> createAccountNote(
            String accountId, String note, Map<String, Object> files, RequestOptions options) {
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("account_id", accountId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(AccountsUrls.ACCOUNT_NOTES,
                request.build(), NoteView.class);
    }

    public ApiResponse<NoteView> createAccountNote(
            String accountId, String note, RequestOptions options) {
        return createAccountNote(accountId, note, null, options);
    }

    public ApiResponse<NoteView> createAccountNote(String accountId, String note) {
        return createAccountNote(accountId, note, null);
    }

    /**
     * Update a note's content. Form-encoded, like {@link #createAccountNote}.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<NoteView> updateAccountNote(
            String accountId, String noteId, String note, Map<String, Object> files, RequestOptions options) {
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("account_id", accountId).pathParam("note_id", noteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_NOTE,
                request.build(), NoteView.class);
    }

    public ApiResponse<NoteView> updateAccountNote(
            String accountId, String noteId, String note, RequestOptions options) {
        return updateAccountNote(accountId, noteId, note, null, options);
    }

    public ApiResponse<NoteView> updateAccountNote(String accountId, String noteId, String note) {
        return updateAccountNote(accountId, noteId, note, null);
    }

    /** Set the profile image as base64 in a JSON body, not a multipart upload. */
    public ApiResponse<Account> uploadAccountImage(
            String accountId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_IMAGE,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> uploadAccountImage(String accountId, Object payload) {
        return uploadAccountImage(accountId, payload, null);
    }

    /** Delete an account; a 409 means orders, invoices or payments lock it: not retryable. */
    public ApiResponse<Dto> deleteAccount(
            String accountId, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT,
                reopen(options).pathParam("account_id", accountId).build(), null);
    }

    public ApiResponse<Dto> deleteAccount(String accountId) {
        return deleteAccount(accountId, null);
    }

    /** Delete one contact, addressed by its type. */
    public ApiResponse<Dto> deleteAccountContact(
            String accountId, String contactType, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_CONTACT,
                reopen(options).pathParam("account_id", accountId).pathParam("contact_type", contactType).build(),
                null);
    }

    public ApiResponse<Dto> deleteAccountContact(String accountId, String contactType) {
        return deleteAccountContact(accountId, contactType, null);
    }

    /** Delete one address. */
    public ApiResponse<Dto> deleteAccountAddress(
            String accountId, String addressId, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_ADDRESS,
                reopen(options).pathParam("account_id", accountId).pathParam("address_id", addressId).build(),
                null);
    }

    public ApiResponse<Dto> deleteAccountAddress(String accountId, String addressId) {
        return deleteAccountAddress(accountId, addressId, null);
    }

    /** Delete one payment method. */
    public ApiResponse<Dto> deleteAccountPaymentMethod(
            String accountId, String paymentMethodId, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_PAYMENT_METHOD,
                reopen(options).pathParam("account_id", accountId)
                        .pathParam("payment_method_id", paymentMethodId).build(), null);
    }

    public ApiResponse<Dto> deleteAccountPaymentMethod(String accountId, String paymentMethodId) {
        return deleteAccountPaymentMethod(accountId, paymentMethodId, null);
    }

    /** Delete one note. */
    public ApiResponse<Dto> deleteAccountNote(
            String accountId, String noteId, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_NOTE,
                reopen(options).pathParam("account_id", accountId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteAccountNote(String accountId, String noteId) {
        return deleteAccountNote(accountId, noteId, null);
    }

    /** Remove the account's profile image. Answers 204, so {@code getMap()} is null. */
    public ApiResponse<Dto> deleteAccountImage(
            String accountId, RequestOptions options) {
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_IMAGE,
                reopen(options).pathParam("account_id", accountId).build(), null);
    }

    public ApiResponse<Dto> deleteAccountImage(String accountId) {
        return deleteAccountImage(accountId, null);
    }

    /**
     * Create an account: {@code POST /accounts}; answered {@code 201}, under {@code account}.
     * {@code name} and {@code type} are required, and {@code POST /accounts/{account_id}} is a
     * {@code 400}.
     */
    public ApiResponse<Account> createAccount(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNTS,
                reopen(options).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> createAccount(Object payload) {
        return createAccount(payload, null);
    }

    /**
     * Replace an account: {@code PUT /accounts/{account_id}}, wrapped in {@code account}.
     * {@code name} and {@code type} are required on every call, and a field the body omits is kept,
     * not cleared.
     */
    public ApiResponse<Account> replaceAccount(
            String accountId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requirePayload(payload);
        return client.sendPutRequest(AccountsUrls.ACCOUNT,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> replaceAccount(String accountId, Object payload) {
        return replaceAccount(accountId, payload, null);
    }

    /**
     * Reactivate a cancelled account: {@code POST /accounts/{account_id}/reactivate}, the body
     * {@code cancelAccount} takes; a null {@code effective_date} means now.
     */
    public ApiResponse<Account> reactivateAccount(
            String accountId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_REACTIVATE,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> reactivateAccount(String accountId, Object payload) {
        return reactivateAccount(accountId, payload, null);
    }

    /**
     * Add contacts to an account: {@code POST /accounts/{account_id}/contacts}; answered
     * {@code 201}. A list under {@code contacts}, each with its slot as {@code type}
     * ({@code CONTACT_1}) in the body.
     */
    public ApiResponse<Account> createAccountContacts(
            String accountId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_CONTACTS,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> createAccountContacts(String accountId, Object payload) {
        return createAccountContacts(accountId, payload, null);
    }

    /**
     * Replace one address: {@code PUT /accounts/{account_id}/addresses/{address_id}}, under a
     * singular {@code address}; {@code address_line_1} and {@code country} are required.
     */
    public ApiResponse<Account> replaceAccountAddress(
            String accountId, String addressId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(addressId, "address_id");
        requirePayload(payload);
        return client.sendPutRequest(AccountsUrls.ACCOUNT_ADDRESS,
                reopen(options).pathParam("account_id", accountId).pathParam("address_id", addressId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> replaceAccountAddress(String accountId, String addressId, Object payload) {
        return replaceAccountAddress(accountId, addressId, payload, null);
    }

    /**
     * Add one payment method: {@code POST /accounts/{account_id}/payment_methods}; answered
     * {@code 201}. One object under {@code payment_method}, not a list; the first method becomes
     * the default.
     */
    public ApiResponse<Account> createAccountPaymentMethod(
            String accountId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_PAYMENT_METHODS,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> createAccountPaymentMethod(String accountId, Object payload) {
        return createAccountPaymentMethod(accountId, payload, null);
    }

    /**
     * Replace one payment method:
     * {@code PUT /accounts/{account_id}/payment_methods/{payment_method_id}}; {@code name} is
     * required, and {@code processor_type} and {@code payment_processor} are refused.
     */
    public ApiResponse<Account> replaceAccountPaymentMethod(
            String accountId, String paymentMethodId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(paymentMethodId, "payment_method_id");
        requirePayload(payload);
        return client.sendPutRequest(AccountsUrls.ACCOUNT_PAYMENT_METHOD,
                reopen(options).pathParam("account_id", accountId).pathParam("payment_method_id", paymentMethodId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> replaceAccountPaymentMethod(
            String accountId, String paymentMethodId, Object payload) {
        return replaceAccountPaymentMethod(accountId, paymentMethodId, payload, null);
    }

    /**
     * Create a custom object instance on an account:
     * {@code POST /accounts/{account_id}/custom_objects/{custom_object_id}}, the schema's
     * {@code co_} id. Answers {@code custom_object_instance}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createAccountCustomObjectInstance(
            String accountId, String customObjectId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(customObjectId, "custom_object_id");
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_CUSTOM_OBJECT,
                reopen(options).pathParam("account_id", accountId).pathParam("custom_object_id", customObjectId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createAccountCustomObjectInstance(
            String accountId, String customObjectId, Object payload) {
        return createAccountCustomObjectInstance(accountId, customObjectId, payload, null);
    }

    /**
     * Update a custom object instance:
     * {@code PATCH /accounts/{account_id}/custom_objects_instances/{custom_object_instance_id}},
     * the instance's {@code coi_} id; answered with the account, per the handler.
     */
    public ApiResponse<Account> updateAccountCustomObjectInstance(
            String accountId, String customObjectInstanceId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(customObjectInstanceId, "custom_object_instance_id");
        requirePayload(payload);
        return client.sendPatchRequest(AccountsUrls.ACCOUNT_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("account_id", accountId).pathParam("custom_object_instance_id", customObjectInstanceId).jsonBody(payload).build(), Account.class);
    }

    public ApiResponse<Account> updateAccountCustomObjectInstance(
            String accountId, String customObjectInstanceId, Object payload) {
        return updateAccountCustomObjectInstance(accountId, customObjectInstanceId, payload, null);
    }

    /**
     * Send an email and log it on the account:
     * {@code POST /accounts/{account_id}/communications/emails}; {@code to}, {@code subject},
     * {@code body}, {@code cc} and {@code bcc} with no envelope. It sends real mail. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> sendAccountEmail(
            String accountId, Object payload, RequestOptions options) {
        requireValue(accountId, "account_id");
        requirePayload(payload);
        return client.sendPostRequest(AccountsUrls.ACCOUNT_EMAILS,
                reopen(options).pathParam("account_id", accountId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> sendAccountEmail(String accountId, Object payload) {
        return sendAccountEmail(accountId, payload, null);
    }

    /**
     * Purge an account and its records permanently: {@code POST /accounts/{account_id}/purge};
     * {@code 204}, no body sent. There is no undo. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> purgeAccount(
            String accountId, RequestOptions options) {
        requireValue(accountId, "account_id");
        return client.sendPostRequest(AccountsUrls.ACCOUNT_PURGE,
                reopen(options).pathParam("account_id", accountId).build(), null);
    }

    public ApiResponse<Dto> purgeAccount(String accountId) {
        return purgeAccount(accountId, null);
    }

    /**
     * The account's revenue and order counters: {@code GET /accounts/{account_id}/kpis}, under
     * {@code account_kpis}; the money fields are strings, {@code "0.00"}.
     */
    public ApiResponse<AccountKpis> getAccountKpis(
            String accountId, RequestOptions options) {
        requireValue(accountId, "account_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_KPIS,
                reopen(options).pathParam("account_id", accountId).build(), AccountKpis.class);
    }

    public ApiResponse<AccountKpis> getAccountKpis(String accountId) {
        return getAccountKpis(accountId, null);
    }

    /**
     * The account's invoice totals: {@code GET /accounts/{account_id}/financial_summary}, under
     * {@code account}, beside its {@code name}; the money fields are strings.
     */
    public ApiResponse<AccountFinancialSummary> getAccountFinancialSummary(
            String accountId, RequestOptions options) {
        requireValue(accountId, "account_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_FINANCIAL_SUMMARY,
                reopen(options).pathParam("account_id", accountId).build(), AccountFinancialSummary.class);
    }

    public ApiResponse<AccountFinancialSummary> getAccountFinancialSummary(String accountId) {
        return getAccountFinancialSummary(accountId, null);
    }

    /**
     * One custom object with this account's instances of it:
     * {@code GET /accounts/{account_id}/custom_objects/{custom_object_id}}, the {@code co_} id,
     * under {@code custom_object}; the instances are paged inside it.
     */
    public ApiResponse<AccountCustomObject> getAccountCustomObject(
            String accountId, String customObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CUSTOM_OBJECT,
                reopen(options).pathParam("account_id", accountId).pathParam("custom_object_id", customObjectId).queryParams(queryParams).build(), AccountCustomObject.class);
    }

    public ApiResponse<AccountCustomObject> getAccountCustomObject(
            String accountId, String customObjectId, Map<String, Object> queryParams) {
        return getAccountCustomObject(accountId, customObjectId, queryParams, null);
    }

    public ApiResponse<AccountCustomObject> getAccountCustomObject(String accountId, String customObjectId) {
        return getAccountCustomObject(accountId, customObjectId, null, null);
    }

    /**
     * One custom object instance:
     * {@code GET /accounts/{account_id}/custom_objects/{custom_object_id}/instances/{custom_object_instance_id}},
     * the {@code coi_} id, under {@code custom_object_instance}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getAccountCustomObjectInstance(
            String accountId, String customObjectId, String customObjectInstanceId, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(customObjectInstanceId, "custom_object_instance_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_CUSTOM_OBJECT_INSTANCE_OF,
                reopen(options).pathParam("account_id", accountId).pathParam("custom_object_id", customObjectId).pathParam("custom_object_instance_id", customObjectInstanceId).build(), null);
    }

    public ApiResponse<Dto> getAccountCustomObjectInstance(
            String accountId, String customObjectId, String customObjectInstanceId) {
        return getAccountCustomObjectInstance(accountId, customObjectId, customObjectInstanceId, null);
    }

    /**
     * List a component's objects under an account and its child accounts:
     * {@code GET /accounts/{account_id}/component/{component_id}}, the {@code cmp_} id; cursor
     * paged.
     */
    public ApiResponse<ComponentObjects> getAccountComponentObjects(
            String accountId, String componentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(componentId, "component_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_COMPONENT_OBJECTS,
                reopen(options).pathParam("account_id", accountId).pathParam("component_id", componentId).queryParams(queryParams).build(), ComponentObjects.class);
    }

    public ApiResponse<ComponentObjects> getAccountComponentObjects(
            String accountId, String componentId, Map<String, Object> queryParams) {
        return getAccountComponentObjects(accountId, componentId, queryParams, null);
    }

    public ApiResponse<ComponentObjects> getAccountComponentObjects(String accountId, String componentId) {
        return getAccountComponentObjects(accountId, componentId, null, null);
    }

    /**
     * One component object under an account:
     * {@code GET /accounts/{account_id}/component/{component_id}/{component_object_id}}, the
     * {@code cco_} id, under {@code custom_component_object}.
     */
    public ApiResponse<AccountComponentObject> getAccountComponentObject(
            String accountId, String componentId, String componentObjectId, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(componentId, "component_id");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(AccountsUrls.ACCOUNT_COMPONENT_OBJECT,
                reopen(options).pathParam("account_id", accountId).pathParam("component_id", componentId).pathParam("component_object_id", componentObjectId).build(), AccountComponentObject.class);
    }

    public ApiResponse<AccountComponentObject> getAccountComponentObject(
            String accountId, String componentId, String componentObjectId) {
        return getAccountComponentObject(accountId, componentId, componentObjectId, null);
    }

    /**
     * Delete one note attachment:
     * {@code DELETE /accounts/{account_id}/notes/{note_id}/files/{file_id}}, the attachment's
     * {@code uuid}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteAccountNoteFile(
            String accountId, String noteId, String fileId, RequestOptions options) {
        requireValue(accountId, "account_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(AccountsUrls.ACCOUNT_NOTE_FILE,
                reopen(options).pathParam("account_id", accountId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteAccountNoteFile(String accountId, String noteId, String fileId) {
        return deleteAccountNoteFile(accountId, noteId, fileId, null);
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

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }

    private static void requireNote(String note) {
        if (note == null) {
            throw new SdkUsageError(
                    "note is required: the note text is the body of this request, and "
                            + "omitting it would store the string \"null\" as the note");
        }
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }
}
