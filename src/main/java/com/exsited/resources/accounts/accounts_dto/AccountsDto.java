package com.exsited.resources.accounts.accounts_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectsDataCustomForm;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectsDataDetail;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectsDataParent;
import com.exsited.resources.settings.settings_dto.SettingsDto.CustomObject;

import java.util.List;

/**
 * Typed views over the v4 account bodies.
 *
 * <p>A DTO is a view of the body. {@code response.getMap()} is the body as it arrived and is
 * where to look when a field here reads null and you expected otherwise: the view reads null
 * both for a key the API sent as null and for one it did not send at all, and only
 * {@code getMap()} can tell those apart.
 *
 * <p>Every type comes from recorded responses. A field the API sent as null in every record is
 * typed {@code Object}, so the view never refuses a body the API legitimately sends.
 *
 * <p>Fields are boxed and named for the wire, so they are snake_case rather than camelCase.
 *
 * <p>Three read/write asymmetries are recorded here and are not mistakes:
 * <ul>
 *   <li>An address <b>reads</b> {@code postal_code} and is <b>written</b> {@code post_code}.
 *   <li>A contact's name <b>reads</b> nested under {@code name} and is <b>written</b> flat.
 *   <li>The billing alignment setting is <b>written</b>
 *       {@code charging_and_billing_alignment} as a boolean, and <b>reads back</b>
 *       under that same name as a <i>string</i> (14 of 49 blocks) or as
 *       {@code charges_aligned_to_billing}, a boolean (5 of 49).
 * </ul>
 */
public final class AccountsDto {

    private AccountsDto() {}

    /** v4's standard embedded pointer to another record. Seen 2,963 times. */
    public static final class Reference extends Dto {
        public String object;
        public String id;
        public String name;
        public String code;
        public String href;
        public Double rate;
    }

    /** Whoever created or last updated a record. Seen 3,461 times. */
    public static final class User extends Dto {
        public String object;
        public String id;
        public String name;
        public String email;
        public String href;
    }

    public static final class Currency extends Dto {
        public String object;
        public String id;
        public String name;
        public String code;
        public String href;
    }

    public static final class TimeZone extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    public static final class CustomForm extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    /** Present on all 1,683 accounts, never null; each value is typically null. */
    public static final class SocialLinks extends Dto {
        public String website;
        public String linkedin;
        public String twitter;
        public String facebook;
    }

    /**
     * An address <b>as it reads back</b>.
     *
     * <p>Measured over 12 records, all with every field non-null - a thin sample,
     * enough to say what type each field holds but not which can be null.
     */
    public static final class Address extends Dto {
        public String object;
        public String id;
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String state;
        public String country;
        /** The read spelling. Written as {@code post_code}; see {@link AddressWrite}. */
        public String postal_code;
        public Boolean default_billing;
        public Boolean default_shipping;
        public String href;
    }

    /** A contact's name <b>as it reads back</b> - nested, and split four ways. */
    public static final class ContactName extends Dto {
        public String salutation;
        public String first;
        public String middle;
        public String last;
        public String designation;
    }

    public static final class ContactEmail extends Dto {
        public String address;
        public Boolean do_not_email;
        public Boolean email_opted_out;
    }

    /** A contact's phone, mobile or fax. All three share this shape. */
    public static final class ContactPhone extends Dto {
        public String country_code;
        public String area_code;
        public String number;
        public Boolean do_not_call;
        public Boolean call_opted_out;
        public String dialing_code;
        public String formatted;
    }

    /** A contact <b>as it reads back</b>. Measured over 10 records. */
    public static final class Contact extends Dto {
        public String object;
        public String id;
        /** {@code "billing"}, {@code "shipping"} or {@code "primary"}. What a v4 path takes. */
        public String type;
        public Boolean billing_contact;
        public Boolean shipping_contact;
        public Boolean receives_billing_information;
        public ContactName name;
        public ContactEmail email;
        /** null in 2 of 10. */
        public ContactPhone phone;
        /** null in 4 of 10. */
        public ContactPhone mobile;
        /** null in 4 of 10. */
        public ContactPhone fax;
        public ContactAddress address;
        public List<ContactCustomAttribute> custom_attributes;
        public String created_on;
        /** null in 8 of 10 - set only on a contact that has been edited. */
        public String last_updated_on;
        public User created_by;
        public User last_updated_by;
        public Long version;
    }

    public static final class PaymentProcessor extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    /** A payment method <b>as it reads back</b>. Measured over 7 records. */
    public static final class PaymentMethod extends Dto {
        public String object;
        public String id;
        public String processor_type;
        public String status;
        /** The wire name is {@code default}, which is a Java keyword and cannot be
         *  a field name - so the field is {@code isDefault} and {@link WireName}
         *  carries the real key. The only such field in this SDK. */
        @WireName("default")
        public Boolean isDefault;
        public Boolean restricted_to_orders;
        /** Untyped: null in <b>all 7</b> records measured. Named as though it holds
         *  a list, but nothing observed says so. */
        public Object specified_order_ids;
        public PaymentProcessor processor;
        public String reference;
        public Long payment_count;
        /** null in 4 of 7 - a method never charged. */
        public String last_used_at;
        public String created_on;
        public String last_updated_on;
        public User created_by;
        public User last_updated_by;
        public Long version;
    }

    /**
     * Billing preferences <b>as they read back</b>. 49 blocks measured.
     *
     * <p>Field presence varies a great deal between accounts, so most of these are
     * absent rather than null on a given one, and {@code map()} is the only way to
     * tell.
     */
    public static final class BillingPreferences extends Dto {
        public String communication_profile;
        public String invoice_mode;
        public String invoice_term;
        public String billing_period;
        public String billing_start_date;
        public Long billing_start_day_of_month;
        /** One of the two names the alignment setting reads back under. Boolean on
         *  all 5 blocks that carried it. */
        public Boolean charges_aligned_to_billing;
        /** The other. A <b>string</b> on all 14 blocks that carried it - the write
         *  side sends this same name as a boolean. */
        public String charging_and_billing_alignment;
        public String payment_processor;
        public String payment_mode;
        public String payment_term;
        public String payment_term_alignment;
        public Boolean consolidate_invoice;
        public String fulfillment_mode;
        public String fulfillment_term;
        /** Untyped: an object on 20 of 49, whose keys were never varied enough to
         *  establish a shape. */
        public Object proforma_properties;
        public String communication_group;
        public String billing_mode;
        public String charging_period;
        public String charging_start_date;
        public String event_profile;
    }

    /** Identified by {@code uuid} - a bare UUID, not prefixed the way a note's id is. */
    public static final class NoteAttachment extends Dto {
        public String uuid;
        public String filename;
        public String content_type;
        public Long size;
        public String href;
    }

    public static final class Note extends Dto {
        public String object;
        public String id;
        /** HTML, stored and returned as written. */
        public String content;
        public Reference account;
        public Object group;
        public Object user;
        public List<NoteAttachment> attachments;
        public List<NoteWatcher> watchers;
        public String created_on;
        public String last_updated_on;
        public User created_by;
        public User last_updated_by;
        public Long version;
        public String href;
    }

    /**
     * One account. Measured over <b>1,686 records</b>.
     *
     * <p>The envelope is {@code "account"}, so this reads both a
     * {@code {"account": {...}}} detail body and a single entry out of a listing's
     * {@code data}.
     */
    public static class Account extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String object;
        /** The short code - {@code "W13ZGY"} - and what every path takes. */
        public String id;
        public String name;
        public String status;
        public String type;
        public String display_name;
        /** null on 576 of 1,683. */
        public String email_address;
        /** null on 953 of 1,678. */
        public String description;
        public Currency currency;
        public TimeZone time_zone;
        /** null on 1,013 of 1,678 - an account with no tax profile. */
        public Reference tax;
        public CustomForm custom_form;
        /** null on 1,182 of 1,683. */
        public String parent_account_id;
        /** Untyped: null in <b>all 1,683</b> records. Use the image endpoint instead. */
        public Object image_url;
        /** null on 1,580 of 1,683. */
        public String workflow_status;
        public SocialLinks social_links;
        public List<AccountCommunicationPreference> communication_preferences;
        public Boolean invoice_parent_account;
        public Boolean portal_access;
        /** null on 1,015 of 1,678. */
        public String origin;
        public Reference group;
        public Reference manager;
        /** null on 1,663 of 1,678 - rarely set. */
        public Reference sales_rep;
        /** null on 1,673 of 1,678 - rarely set. */
        public Reference user_team;
        public AccountAccountingCode accounting_code;
        public Reference pricing_level;
        public String referral_tracking;
        public String created_on;
        /** null on 1,429 of 1,678 - set only once an account has been edited. */
        public String last_updated_on;
        public User created_by;
        public User last_updated_by;
        public Long version;
        public String href;

        // Sub-resources: each arrives only on the endpoint that asks for it, so all
        // are absent - not null - on a plain getAccount.
        public List<Address> addresses;
        public List<Contact> contacts;
        public List<PaymentMethod> payment_methods;
        public BillingPreferences billing_preferences;
        public List<AccountCustomAttribute> custom_attributes;
        public List<Reference> custom_objects;
        public List<Object> inventories;
        /** The key the quote-partial-invoices endpoint answers under - which does
         *  <b>not</b> match its path segment. */
        public List<Object> partial_invoices;
        public List<Object> activity_logs;
        public PaymentMethod payment_method;
        /** Null in every record measured, so untyped. */
        public Object tax_id;
        /** Null in every record measured, so untyped. */
        public Object tax_id_number;
    }

    /** A listing of accounts. The records sit under {@code data}. */
    public static final class AccountList extends Dto {
        public String object;
        public String url;
        public List<Account> data;
        public PaginationDto pagination;
    }

    /** A <b>URI, not image bytes</b>, and null when the account has no image. */
    public static final class AccountImage extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String image_uri;
        public String account_id;
    }

    public static final class NoteView extends Dto {
        @Override
        protected String envelopeKey() {
            return "note";
        }

        public String object;
        public String id;
        public String content;
        public Reference account;
        public List<NoteAttachment> attachments;
        public List<NoteViewWatcher> watchers;
        public String created_on;
        public String last_updated_on;
        public User created_by;
        public User last_updated_by;
        public Long version;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        /** Null in every record measured, so untyped. */
        public Object user;
    }

    public static final class NoteList extends Dto {
        public String object;
        public String url;
        public List<Note> data;
        public PaginationDto pagination;
    }

    /**
     * A note's attachments.
     *
     * <p>Separate from {@link NoteList} because {@code data} holds
     * {@link NoteAttachment}, not {@link Note}; one class covering both would have
     * to leave {@code data} untyped, which is the whole value of the view.
     */
    public static final class NoteFileList extends Dto {
        public String object;
        public String url;
        public List<NoteAttachment> data;
        public PaginationDto pagination;
    }

    public static final class AccountCancel extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        /** Required, and {@code YYYY-MM-DD}. Any other format is a 400 with code
         *  {@code invalid} - the endpoint does not parse a datetime. */
        public String effective_date;
    }

    /**
     * <b>Flatter than the read.</b> {@code currency} and {@code time_zone} go in as
     * plain strings while {@link Account} reads them back as nested objects, so
     * posting a read body back unchanged is a 400.
     *
     * <p>{@code addresses} and {@code communication_preference} are deliberately
     * absent: the endpoint answers 400 {@code not_understood} for both.
     */
    public static final class AccountInformationUpdate extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String name;
        public String display_name;
        public String description;
        public String email_address;
        public String origin;
        public String user_team;
        public String group;
        public String manager;
        public String referral_tracking;
        public String parent_account;
        /** The currency <b>code</b>, not the nested object the read returns. */
        public String currency;
        /** The time zone <b>name</b>, not the nested object the read returns. */
        public String time_zone;
        public Boolean grant_portal_access;
        public String grant_portal_password;
        /** {@code {uuid, code}}. */
        public Object tax;
    }

    /** No envelope: these two sit at the <b>top level</b>, unlike {@link AccountCancel}. */
    public static final class AccountSetPassword extends Dto {
        public String password;
        public String confirm_password;
    }

    /** Base64 in a JSON body, <b>not</b> a multipart upload. */
    public static final class AccountImageUpload extends Dto {
        public String image_base64;
    }

    /** One address <b>as it is written</b>. Note {@code post_code} against the read's
     *  {@code postal_code}. */
    public static final class AddressWrite extends Dto {
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        /** The name, not an id. */
        public String state;
        /** The name, not an id. */
        public String country;
        /** The write spelling. Reads back as {@code postal_code}. */
        public String post_code;
        public Boolean default_billing;
        public Boolean default_shipping;
    }

    /** A <b>list</b> under {@code addresses}, at the top level with no envelope. */
    public static final class AddressesCreate extends Dto {
        public List<AddressWrite> addresses;
    }

    /** One address under a <b>singular</b> key, not the plural the create takes. */
    public static final class AddressUpdate extends Dto {
        @Override
        protected String envelopeKey() {
            return "address";
        }

        public String address_line_1;
        public String address_line_2;
        public String city;
        public String state;
        public String country;
        public String post_code;
        public Boolean default_billing;
        public Boolean default_shipping;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
    }

    /** <b>Flat where the read is nested</b>: {@code first_name} against
     *  {@link ContactName}'s {@code first}. */
    public static final class ContactWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "contact";
        }

        public String salutation;
        public String designation;
        public String first_name;
        public String middle_name;
        public String last_name;
        public ContactEmail email;
        public ContactPhone phone;
        public ContactPhone mobile;
        public ContactPhone fax;
        public String address_line_1;
        public String address_line_2;
        public String city;
        public String state;
        public String country;
        public String post_code;
    }

    public static final class BillingPreferencesWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "billing_preferences";
        }

        public String invoice_mode;
        public String invoice_term;
        public String billing_period;
        /** An <b>enum</b>, not a date - {@code "DAY_OF_MONTH"} and the like. A
         *  calendar date is a 400 with code {@code matches_invalid}, which reads as
         *  a formatting complaint and is a type one. */
        public String billing_start_date;
        public String billing_start_day_of_month;
        /** Written as a <b>boolean</b>; reads back as a string under this same name.
         *  See {@link BillingPreferences}. */
        public Boolean charging_and_billing_alignment;
        public String payment_processor;
        public String payment_term;
    }

    public static final class PaymentMethodUpdate extends Dto {
        @Override
        protected String envelopeKey() {
            return "payment_method";
        }

        /** Same keyword problem as {@link PaymentMethod#isDefault}. */
        @WireName("default")
        public Boolean isDefault;
        public String reference;
        public String status;
    }

    /**
     * What {@code get_account_contact} answers: a {@link Account} whose {@code contacts} is one
     * record, not a list.
     */
    public static final class AccountContact extends Account {
        public Contact contacts;
    }

    /**
     * What {@code get_account_address} answers: a {@link Account} whose {@code addresses} is one
     * record, not a list.
     */
    public static final class AccountAddress extends Account {
        public Address addresses;
    }

    /** What {@code get_account_note_file} answers, under {@code file}. */
    public static final class AccountNoteFile extends Dto {
        @Override
        protected String envelopeKey() {
            return "file";
        }

        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link Account}'s {@code accounting_code}. */
    public static final class AccountAccountingCode extends Dto {
        public String account_receivable;
    }

    /** One entry of a {@link Account}'s {@code communication_preferences}. */
    public static final class AccountCommunicationPreference extends Dto {
        public String channel;
        public Boolean enabled;
    }

    /** One entry of a {@link Account}'s {@code custom_attributes}. */
    public static final class AccountCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public Object value;
    }

    /** One entry of a {@link NoteView}'s {@code watchers}. */
    public static final class NoteViewWatcher extends Dto {
        /** Null in every record measured, so untyped. */
        public Object email;
        public String name;
        public String type;
    }

    /** A {@link Contact}'s {@code address}. */
    public static final class ContactAddress extends Dto {
        public String city;
        public String country;
        /** Null in every record measured, so untyped. */
        public Object country_code;
        public String line_1;
        public String line_2;
        public String postal_code;
        public String state;
    }

    /** One entry of a {@link Contact}'s {@code custom_attributes}. */
    public static final class ContactCustomAttribute extends Dto {
        public String name;
        public Object value;
    }

    /** One entry of a {@link Note}'s {@code watchers}. */
    public static final class NoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /**
     * Body for {@code createAccount}. {@code name} and {@code type} ({@code CUSTOMER},
     * {@code SUPPLIER} or {@code CUSTOMER_AND_SUPPLIER}) are required; writes are flat, as in
     * {@link AccountInformationUpdate}.
     */
    public static final class AccountCreate extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String name;
        public String type;
        public String display_name;
        public String status;
        public String email_address;
        /** The currency code; the instance default when omitted. */
        public String currency;
        /** The time zone name. */
        public String time_zone;
        public String description;
        public String origin;
        public String user_team;
        public String website;
        public String linkedin;
        public String twitter;
        public String facebook;
        public String pricing_level;
        public String invoice_term;
        public String payment_term;
        public String communication_profile;
        public String payment_processor;
        public String parent_account;
        public String sales_rep;
        public String referral_tracking;
        public String group;
        public String manager;
        public String portal_password;
        public Boolean portal_access;
        public Boolean grant_portal_access;
        public String grant_portal_password;
        public Boolean tax_exempt;
        public Boolean invoice_parent_account;
        /** A decimal; the binder parses a number or a numeric string. */
        public Object credit_limit;
        public String custom_form;
        public List<Object> communication_preferences;
        /** {@code {"account_receivable": "<name>"}}. */
        public Object accounting_code;
        /** {@code {"uuid": ..., "code": ...}}. */
        public Object tax;
        public Object billing_preferences;
        public List<Object> custom_attributes;
        /** Each needs a {@code type}, as in {@link ContactCreate}. */
        public List<Object> contacts;
        /** Each as {@link AddressWrite}. */
        public List<Object> addresses;
        /** Each as {@link PaymentMethodCreate}. */
        public List<Object> payment_methods;
        public List<Object> discount_profiles;
    }

    /**
     * Body for {@code replaceAccount}. {@code name} and {@code type} are required on every call;
     * {@code status}, {@code addresses}, {@code payment_methods} and the two {@code grant_portal_*}
     * keys are refused.
     */
    public static final class AccountReplace extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String name;
        public String type;
        public String display_name;
        public String email_address;
        public String currency;
        public String time_zone;
        public String description;
        public String origin;
        public String user_team;
        public String website;
        public String linkedin;
        public String twitter;
        public String facebook;
        public String pricing_level;
        public String invoice_term;
        public String payment_term;
        public String communication_profile;
        public String payment_processor;
        public String parent_account;
        public String sales_rep;
        public String referral_tracking;
        public String group;
        public String manager;
        public String portal_password;
        public Boolean portal_access;
        public Boolean tax_exempt;
        public Boolean invoice_parent_account;
        public Object credit_limit;
        public String custom_form;
        public List<Object> communication_preferences;
        public Object accounting_code;
        public Object tax;
        public Object billing_preferences;
        public List<Object> custom_attributes;
        public List<Object> discount_profiles;
        /**
         * An entry with an {@code id} ({@code "con_..."}) updates that contact; one without is
         * created and needs a {@code type}.
         */
        public List<Object> contacts;
    }

    /**
     * Body for {@code reactivateAccount}, the same as {@link AccountCancel}. To reactivate now send
     * the mapping {@code {"account": {"effective_date": null}}}: an unset field is left out, and a
     * body without the key is a 400.
     */
    public static final class AccountReactivate extends Dto {
        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String effective_date;
    }

    /**
     * One contact as it is created. {@code type} is required and is the slot's enum name,
     * {@code CONTACT_1} to {@code CONTACT_5}; it reads back as something else, {@code CONTACT_1} as
     * {@code billing}.
     */
    public static final class ContactCreate extends Dto {
        public String type;
        public String salutation;
        public String designation;
        public String first_name;
        public String middle_name;
        public String last_name;
        public ContactEmail email;
        public ContactPhone phone;
        public ContactPhone mobile;
        public ContactPhone fax;
        public String address_line_1;
        public String address_line_2;
        public String address_line_3;
        public String address_line_4;
        public String address_line_5;
        public String city;
        public String state;
        public String country;
        public String post_code;
        public Boolean receive_billing_information;
        public List<Object> custom_attributes;
    }

    /**
     * Body for {@code createAccountContacts}: a list under {@code contacts}. The server also takes
     * one object under {@code contact}.
     */
    public static final class ContactsCreate extends Dto {
        public List<ContactCreate> contacts;
    }

    /**
     * Body for {@code createAccountPaymentMethod}: one method, under a singular key, the
     * {@code OTHER} key set. {@code processor_type}, {@code payment_processor} (a {@code pp_} id)
     * and {@code reference} are required; {@code name} is refused.
     */
    public static final class PaymentMethodCreate extends Dto {
        @Override
        protected String envelopeKey() {
            return "payment_method";
        }

        /**
         * {@code DIRECT_CREDIT}, {@code DIRECT_DEBIT} or {@code OTHER}; the card and bank keys the
         * first two accept are not modelled, so send a mapping for those.
         */
        public String processor_type;
        public String payment_processor;
        /** At most 70 characters. */
        public String reference;
        public String status;
        @WireName("default")
        public Boolean isDefault;
        public String note;
        public Boolean use_for_specified_orders;
        public List<Object> specified_orders;
    }

    /**
     * Body for {@code replaceAccountPaymentMethod}. {@code name} is required;
     * {@code processor_type} and {@code payment_processor} cannot be replaced and are refused.
     */
    public static final class PaymentMethodReplace extends Dto {
        @Override
        protected String envelopeKey() {
            return "payment_method";
        }

        public String name;
        public String reference;
        @WireName("default")
        public Boolean isDefault;
        public String note;
        public Boolean use_for_specified_orders;
        public List<Object> specified_orders;
        public List<Object> custom_attributes;
    }

    /**
     * Body for both custom object instance writes, under the plural {@code custom_object_instances}
     * for one instance; {@code attributes} must be a list of {@code {name, value}}.
     */
    public static final class CustomObjectInstanceWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "custom_object_instances";
        }

        public List<Object> attributes;
    }

    /**
     * Body for {@code sendAccountEmail}, with no envelope. {@code to} is required with at least one
     * address; {@code subject} is at most 255 characters.
     */
    public static final class AccountEmailSend extends Dto {
        public List<String> to;
        public String subject;
        public String body;
        public List<String> cc;
        public List<String> bcc;
    }

    /** What {@code getAccountKpis} answers, under {@code account_kpis}. */
    public static final class AccountKpis extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/kpis");

        @Override
        protected String envelopeKey() {
            return "account_kpis";
        }

        public String object;
        public String total_revenue;
        public String monthly_recurring_revenue;
        public String total_collected;
        public String total_refunded;
        public String total_outstanding;
        public String total_overdue;
        public String total_balance;
        public String total_credit_balance;
        public Long total_orders;
        public Long active_orders;
        /** Null in every record measured, so untyped. */
        public Object last_reactivated_at;
        /** Null in every record measured, so untyped. */
        public Object last_cancelled_at;
        /** Null in every record measured, so untyped. */
        public Object deleted_at;
        public String computed_at;
    }

    /**
     * What {@code getAccountFinancialSummary} answers, under {@code account}: its {@code name} and
     * the {@code financial_summary} itself.
     */
    public static final class AccountFinancialSummary extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/financial_summary");

        @Override
        protected String envelopeKey() {
            return "account";
        }

        public String name;
        public AccountFinancialSummaryDetail financial_summary;
    }

    /** A {@link AccountFinancialSummary}'s {@code financial_summary}. */
    public static final class AccountFinancialSummaryDetail extends Dto {
        public String total_due;
        public String total_grand;
        public String total_paid;
        public Long invoice_count;
        public Long total_due_invoice_count;
        public String total_overdue_amount;
        public Long total_overdue_invoice_count;
        public String collected_this_month;
    }

    /**
     * What {@code getAccountCustomObject} answers, under {@code custom_object}: the schema, with
     * the account's instances of it and their own {@code pagination}.
     */
    public static final class AccountCustomObject extends CustomObject {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/custom_objects/{custom_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_object";
        }

        /** Empty in every record measured, so its entries are untyped. */
        public List<Object> instances;
        public PaginationDto pagination;
    }

    /**
     * What {@code getAccountComponentObject} answers, under {@code custom_component_object}: the
     * fields of one entry of the listing {@code getAccountComponentObjects} answers.
     */
    public static final class AccountComponentObject extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/component/{component_id}/{component_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_component_object";
        }

        /**
         * Declared here rather than inherited from ComponentObjectsData: a view resolves its
         * annotations in the module that declares it, where the nested names of another module's
         * base do not exist, so an inherited nested field raises NameError on the first read.
         */
        public List<Object> accounts;
        public List<Object> attribute_groups;
        public List<Object> attributes;
        public String code;
        public ComponentObjectsDataDetail component;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public ComponentObjectsDataCustomForm custom_form;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public List<Object> items;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public List<Object> orders;
        public List<ComponentObjectsDataParent> parents;
        public List<Object> quotes;
        public List<Object> restrictions;
        public String status;
        public Long version;
        public List<Object> workflow_data;
        /** Null in every record measured, so untyped. */
        public Object workflow_stage;
        /** Null in every record measured, so untyped. */
        public Object workflow_status;
        /** Null in every record measured, so untyped. */
        public Object workflow_status_uuid;
    }
}
