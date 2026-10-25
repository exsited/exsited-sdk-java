package com.exsited.resources.accounts;

import java.util.List;
import java.util.Set;

/**
 * Path templates, body keys and query names for the account endpoints. {@code {account_id}} is
 * the record's {@code id} ({@code "W13ZGY"}), never its {@code uuid}; {@code set-password} is
 * the one hyphenated path.
 */
public final class AccountsUrls {

    private AccountsUrls() {}

    public static final String ACCOUNTS = "/api/{api_version}/accounts";
    public static final String ACCOUNT = "/api/{api_version}/accounts/{account_id}";
    public static final String ACCOUNT_INFORMATION = "/api/{api_version}/accounts/{account_id}/information";
    public static final String ACCOUNT_CANCEL = "/api/{api_version}/accounts/{account_id}/cancel";
    public static final String ACCOUNT_SET_PASSWORD = "/api/{api_version}/accounts/{account_id}/set-password";

    public static final String ACCOUNT_CONTACTS = "/api/{api_version}/accounts/{account_id}/contacts";
    public static final String ACCOUNT_CONTACT = "/api/{api_version}/accounts/{account_id}/contacts/{contact_type}";

    public static final String ACCOUNT_ADDRESSES = "/api/{api_version}/accounts/{account_id}/addresses";
    public static final String ACCOUNT_ADDRESS = "/api/{api_version}/accounts/{account_id}/addresses/{address_id}";

    public static final String ACCOUNT_BILLING_PREFERENCES =
            "/api/{api_version}/accounts/{account_id}/billing_preferences";

    public static final String ACCOUNT_PAYMENT_METHODS = "/api/{api_version}/accounts/{account_id}/payment_methods";
    public static final String ACCOUNT_PAYMENT_METHOD =
            "/api/{api_version}/accounts/{account_id}/payment_methods/{payment_method_id}";

    public static final String ACCOUNT_NOTES = "/api/{api_version}/accounts/{account_id}/notes";
    public static final String ACCOUNT_NOTE = "/api/{api_version}/accounts/{account_id}/notes/{note_id}";
    public static final String ACCOUNT_NOTE_FILES = "/api/{api_version}/accounts/{account_id}/notes/{note_id}/files";
    public static final String ACCOUNT_NOTE_FILE =
            "/api/{api_version}/accounts/{account_id}/notes/{note_id}/files/{file_id}";

    public static final String ACCOUNT_IMAGE = "/api/{api_version}/accounts/{account_id}/image";

    public static final String ACCOUNT_EMAILS = "/api/{api_version}/accounts/{account_id}/communications/emails";
    public static final String ACCOUNT_EMAIL =
            "/api/{api_version}/accounts/{account_id}/communications/emails/{email_id}";

    public static final String ACCOUNT_ORDERS = "/api/{api_version}/accounts/{account_id}/orders";
    public static final String ACCOUNT_INVOICES = "/api/{api_version}/accounts/{account_id}/invoices";
    public static final String ACCOUNT_PAYMENTS = "/api/{api_version}/accounts/{account_id}/payments";
    public static final String ACCOUNT_REFUNDS = "/api/{api_version}/accounts/{account_id}/refunds";
    public static final String ACCOUNT_CREDIT_NOTES = "/api/{api_version}/accounts/{account_id}/credit_notes";
    public static final String ACCOUNT_PURCHASE_ORDERS = "/api/{api_version}/accounts/{account_id}/purchase_orders";
    public static final String ACCOUNT_PURCHASE_INVOICES = "/api/{api_version}/accounts/{account_id}/purchase_invoices";
    public static final String ACCOUNT_PURCHASE_PAYMENTS = "/api/{api_version}/accounts/{account_id}/purchase_payments";
    public static final String ACCOUNT_PURCHASE_REFUNDS = "/api/{api_version}/accounts/{account_id}/purchase_refunds";
    public static final String ACCOUNT_CUSTOM_OBJECTS = "/api/{api_version}/accounts/{account_id}/custom_objects";
    public static final String ACCOUNT_INVENTORIES = "/api/{api_version}/accounts/{account_id}/inventories";
    public static final String ACCOUNT_QUOTE_PARTIAL_INVOICES =
            "/api/{api_version}/accounts/{account_id}/quote_partial_invoices";
    public static final String ACCOUNT_REACTIVATE = "/api/{api_version}/accounts/{account_id}/reactivate";
    public static final String ACCOUNT_PURGE = "/api/{api_version}/accounts/{account_id}/purge";
    /**
     * A read names the contact by its slot, {@code CONTACT_1} to {@code CONTACT_5}; a write names
     * it by its {@code con_} id, and the server answers a slot there with a 404.
     */
    public static final String ACCOUNT_CONTACT_BY_TYPE = "/api/{api_version}/accounts/{account_id}/contacts/{contact_type}";
    /**
     * A create names the custom object the instance belongs to, a "co_" id; an update names the
     * instance, a "coi_" id. The server also serves the create under custom_objects_instances.
     */
    public static final String ACCOUNT_CUSTOM_OBJECT = "/api/{api_version}/accounts/{account_id}/custom_objects/{custom_object_id}";
    public static final String ACCOUNT_CUSTOM_OBJECT_INSTANCE = "/api/{api_version}/accounts/{account_id}/custom_objects_instances/{custom_object_instance_id}";
    /**
     * A read names both, the schema's "co_" id and then the instance's "coi_" id. The server also
     * serves the reads under custom_objects_instances, the same handler.
     */
    public static final String ACCOUNT_CUSTOM_OBJECT_INSTANCE_OF = "/api/{api_version}/accounts/{account_id}/custom_objects/{custom_object_id}/instances/{custom_object_instance_id}";
    /**
     * A component's objects under the account and its child accounts: the "cmp_" id, then the
     * object's "cco_" id. The path segment is the singular "component".
     */
    public static final String ACCOUNT_COMPONENT_OBJECTS = "/api/{api_version}/accounts/{account_id}/component/{component_id}";
    public static final String ACCOUNT_COMPONENT_OBJECT = "/api/{api_version}/accounts/{account_id}/component/{component_id}/{component_object_id}";
    public static final String ACCOUNT_KPIS = "/api/{api_version}/accounts/{account_id}/kpis";
    public static final String ACCOUNT_FINANCIAL_SUMMARY = "/api/{api_version}/accounts/{account_id}/financial_summary";

    /** The key a listing's records sit under. */
    public static final String ACCOUNTS_KEY = "data";
    public static final String ACCOUNT_EMAILS_KEY = "data";
    public static final String ACCOUNT_NOTES_KEY = "data";
    public static final String ACCOUNT_NOTE_FILES_KEY = "data";
    public static final String ACCOUNT_ORDERS_KEY = "data";
    public static final String ACCOUNT_PAYMENTS_KEY = "data";
    public static final String ACCOUNT_PURCHASE_INVOICES_KEY = "data";
    public static final String ACCOUNT_PURCHASE_ORDERS_KEY = "data";
    public static final String ACCOUNT_PURCHASE_PAYMENTS_KEY = "data";
    public static final String ACCOUNT_PURCHASE_REFUNDS_KEY = "data";
    public static final String ACCOUNT_REFUNDS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ACCOUNT_KEY = "account";
    public static final String NOTE_KEY = "note";
    public static final String ADDRESS_KEY = "address";
    public static final String BILLING_PREFERENCES_KEY = "billing_preferences";
    public static final String CONTACT_KEY = "contact";
    public static final String PAYMENT_METHOD_KEY = "payment_method";
    public static final String FILE_KEY = "file";
    public static final String ACCOUNT_COMPONENT_OBJECTS_KEY = "data";
    public static final String ACCOUNT_KPIS_KEY = "account_kpis";
    public static final String CUSTOM_OBJECT_KEY = "custom_object";
    public static final String CUSTOM_OBJECT_INSTANCE_KEY = "custom_object_instance";
    public static final String CUSTOM_COMPONENT_OBJECT_KEY = "custom_component_object";

    /**
     * The names that shape the listing; every other name is a {@code filter_<field>} or
     * {@code filter_<field>_<operator>}, and an unknown one is {@code 400} naming the field.
     */
    public static final List<String> ACCOUNTS_QUERY_PARAMS = List.of("limit", "cursor", "sort");

    /** The fields {@code filter_<field>} accepts, each with {@code _neq} too. */
    public static final Set<String> FILTERABLE_FIELDS = Set.of(
            "accounting_code", "communication_preference", "created_by", "created_on", "currency",
            "description", "display_name", "email_address", "facebook", "group", "id",
            "last_updated_by", "last_updated_on", "linkedin", "manager", "name", "origin", "status",
            "tax", "time_zone", "twitter", "uuid", "website");

    /** The subset that also takes {@code _contains} and {@code _not_contains}. */
    public static final Set<String> TEXT_FILTER_FIELDS = Set.of(
            "created_by", "description", "display_name", "email_address", "facebook", "id",
            "last_updated_by", "linkedin", "name", "origin", "twitter", "website");

    /**
     * The subset that also takes {@code _gt}, {@code _gte}, {@code _lt} and {@code _lte}, as
     * {@code YYYY-MM-DD}.
     */
    public static final Set<String> DATE_FILTER_FIELDS = Set.of("created_on", "last_updated_on");

    /**
     * The primary contact's fields, filtered as {@code filter_contact1_<field>}; a custom attribute
     * is {@code filter_attribute_<name>}, and the string, number, boolean and text kinds are not
     * implemented by the API.
     */
    public static final Set<String> CONTACT_FILTER_FIELDS = Set.of(
            "address_line1", "contact_salutation", "country", "designation", "email_address",
            "first_name", "last_name", "middle_name");
}
