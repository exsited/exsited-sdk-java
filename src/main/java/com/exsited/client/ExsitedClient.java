package com.exsited.client;

import com.exsited.Dto;
import com.exsited.Redaction;
import com.exsited.auth.FileTokenStore;
import com.exsited.auth.MemoryTokenStore;
import com.exsited.auth.TokenProvider;
import com.exsited.auth.TokenStore;
import com.exsited.config.SdkConfig;
import com.exsited.errors.InsecureTransportWarning;
import com.exsited.errors.SdkError;
import com.exsited.errors.SdkUsageError;
import com.exsited.http.MediaType;
import com.exsited.http.QueryParams;
import com.exsited.http.RetryPolicy;
import com.exsited.http.Transport;
import com.exsited.http.UrlBuilder;
import com.exsited.json.Json;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/** Authenticated access to one Exsited instance: config in, {@link ApiResponse} out. */
public final class ExsitedClient implements AutoCloseable {

    /** The root logger name every client's logger hangs off. */
    public static final String LOGGER_NAME = "exsited";

    /** The header v4 uses to collapse a repeated create into one. */
    public static final String IDEMPOTENCY_HEADER = "X-Idempotency-Key";

    /** The methods a key is generated for: POST alone is not idempotent, and a retry resends it. */
    public static final Set<String> IDEMPOTENCY_KEY_METHODS = Set.of("POST");

    private static final String API_VERSION_PARAM = "api_version";

    private static final Map<String, Integer> LEVEL_RANKS = Map.of(
            "DEBUG", 10, "INFO", 20, "WARNING", 30, "ERROR", 40, "CRITICAL", 50);

    private final SdkConfig config;
    private final Transport transport;
    private final boolean ownsTransport;
    private final TokenProvider tokenProvider;
    private final System.Logger logger;
    private final FileLogSink logSink;
    private final int logLevelRank;

    /** A client on the default token store, transport and token provider. */
    public ExsitedClient(SdkConfig config) {
        this(config, null, null, null, null);
    }

    /** A client with any collaborator supplied, {@code null} taking the default. */
    public ExsitedClient(SdkConfig config, TokenStore tokenStore, Transport transport,
            RetryPolicy retryPolicy, TokenProvider tokenProvider) {
        if (transport != null && retryPolicy != null) {
            throw new SdkUsageError(
                    "pass transport or retryPolicy, not both - an injected transport "
                            + "already carries its own policy, so this one would be discarded "
                            + "unused; build it into the transport instead, as "
                            + "new Transport(config, retryPolicy, onProgress)");
        }
        this.config = config;
        this.ownsTransport = transport == null;
        // The dots are percent-encoded so one client's logger cannot nest under another's.
        String loggerName = LOGGER_NAME + "."
                + URLEncoder.encode(config.getClientName(), StandardCharsets.UTF_8)
                        .replace(".", "%2E");
        // The JDK's own logging facade: it routes to whatever the application logs with.
        this.logger = System.getLogger(loggerName);
        // Every emission site is gated on this rank (see shouldLog), whatever the backend allows.
        this.logLevelRank = LEVEL_RANKS.getOrDefault(config.getLogLevel(), 30);
        this.logSink = FileLogSink.attach(loggerName, config.getLogFile(), config.getLogLevel());
        // Guarded: a throw below leaves no client to close(), so the log file would leak.
        try {
            warnAboutInsecureTransport();
            this.transport = transport != null ? transport
                    : new Transport(config, retryPolicy, this::reportProgress);
            TokenStore store = tokenStore != null ? tokenStore : buildTokenStore(config);
            this.tokenProvider = tokenProvider != null ? tokenProvider
                    : new TokenProvider(config, store, this.transport);
        } catch (RuntimeException | Error failure) {
            detachLogSink();
            throw failure;
        }
    }

    private static TokenStore buildTokenStore(SdkConfig config) {
        String path = config.getTokenCachePath();
        return path == null ? new MemoryTokenStore() : new FileTokenStore(path);
    }

    /** The configuration this client was built from, every default already resolved. */
    public SdkConfig getConfig() {
        return config;
    }

    /** This client's name, which also names its logger. */
    public String getName() {
        return config.getClientName();
    }

    /** The sorted names {@link #resource(String)} answers to, caller-registered ones included. */
    public List<String> listResourceNames() {
        return ResourceBinding.registeredResourceNames();
    }

    /** The resource registered under a name, matched exactly, bound to this client and cached. */
    public BoundResource resource(String resourceName) {
        Function<ExsitedClient, ? extends BoundResource> factory =
                ResourceBinding.resolveResourceModule(resourceName);
        return boundResources.computeIfAbsent(resourceName, name -> factory.apply(this));
    }

    /** {@link #resource(String)} checked against the type you expect. */
    public <T extends BoundResource> T resource(String resourceName, Class<T> type) {
        if (type == null) {
            throw new SdkUsageError("resource(name, type) needs the type to check "
                    + "against, as in resource(\"accounts\", Accounts.class)");
        }
        BoundResource bound = resource(resourceName);
        if (!type.isInstance(bound)) {
            throw new SdkUsageError("resource '" + resourceName + "' is a "
                    + bound.getClass().getSimpleName() + ", not a " + type.getSimpleName());
        }
        return type.cast(bound);
    }

    private final Map<String, BoundResource> boundResources = new ConcurrentHashMap<>();

    /** The aba files endpoints, bound to this client. */
    public com.exsited.resources.aba_files.AbaFiles abaFiles() {
        return (com.exsited.resources.aba_files.AbaFiles) resource("abaFiles");
    }

    /** The account emails endpoints, bound to this client. */
    public com.exsited.resources.account_emails.AccountEmails accountEmails() {
        return (com.exsited.resources.account_emails.AccountEmails) resource("accountEmails");
    }

    /** The account groups endpoints, bound to this client. */
    public com.exsited.resources.account_groups.AccountGroups accountGroups() {
        return (com.exsited.resources.account_groups.AccountGroups) resource("accountGroups");
    }

    /** The accounting codes endpoints, bound to this client. */
    public com.exsited.resources.accounting_codes.AccountingCodes accountingCodes() {
        return (com.exsited.resources.accounting_codes.AccountingCodes) resource("accountingCodes");
    }

    /** The accounts endpoints, bound to this client. */
    public com.exsited.resources.accounts.Accounts accounts() {
        return (com.exsited.resources.accounts.Accounts) resource("accounts");
    }

    /** The action scripts endpoints, bound to this client. */
    public com.exsited.resources.action_scripts.ActionScripts actionScripts() {
        return (com.exsited.resources.action_scripts.ActionScripts) resource("actionScripts");
    }

    /** The additional codes endpoints, bound to this client. */
    public com.exsited.resources.additional_codes.AdditionalCodes additionalCodes() {
        return (com.exsited.resources.additional_codes.AdditionalCodes) resource("additionalCodes");
    }

    /** The alert responsibilities endpoints, bound to this client. */
    public com.exsited.resources.alert_responsibilities.AlertResponsibilities alertResponsibilities() {
        return (com.exsited.resources.alert_responsibilities.AlertResponsibilities) resource("alertResponsibilities");
    }

    /** The alert responsibility endpoints, bound to this client. */
    public com.exsited.resources.alert_responsibility.AlertResponsibility alertResponsibility() {
        return (com.exsited.resources.alert_responsibility.AlertResponsibility) resource("alertResponsibility");
    }

    /** The alerts endpoints, bound to this client. */
    public com.exsited.resources.alerts.Alerts alerts() {
        return (com.exsited.resources.alerts.Alerts) resource("alerts");
    }

    /** The applications endpoints, bound to this client. */
    public com.exsited.resources.applications.Applications applications() {
        return (com.exsited.resources.applications.Applications) resource("applications");
    }

    /** The approval profiles endpoints, bound to this client. */
    public com.exsited.resources.approval_profiles.ApprovalProfiles approvalProfiles() {
        return (com.exsited.resources.approval_profiles.ApprovalProfiles) resource("approvalProfiles");
    }

    /** The approvals endpoints, bound to this client. */
    public com.exsited.resources.approvals.Approvals approvals() {
        return (com.exsited.resources.approvals.Approvals) resource("approvals");
    }

    /** The approval user groups endpoints, bound to this client. */
    public com.exsited.resources.approval_user_groups.ApprovalUserGroups approvalUserGroups() {
        return (com.exsited.resources.approval_user_groups.ApprovalUserGroups) resource("approvalUserGroups");
    }

    /** The attribute attachments endpoints, bound to this client. */
    public com.exsited.resources.attribute_attachments.AttributeAttachments attributeAttachments() {
        return (com.exsited.resources.attribute_attachments.AttributeAttachments) resource("attributeAttachments");
    }

    /** The billing periods endpoints, bound to this client. */
    public com.exsited.resources.billing_periods.BillingPeriods billingPeriods() {
        return (com.exsited.resources.billing_periods.BillingPeriods) resource("billingPeriods");
    }

    /** The bookings endpoints, bound to this client. */
    public com.exsited.resources.bookings.Bookings bookings() {
        return (com.exsited.resources.bookings.Bookings) resource("bookings");
    }

    /** The charging periods endpoints, bound to this client. */
    public com.exsited.resources.charging_periods.ChargingPeriods chargingPeriods() {
        return (com.exsited.resources.charging_periods.ChargingPeriods) resource("chargingPeriods");
    }

    /** The communication profiles endpoints, bound to this client. */
    public com.exsited.resources.communication_profiles.CommunicationProfiles communicationProfiles() {
        return (com.exsited.resources.communication_profiles.CommunicationProfiles) resource("communicationProfiles");
    }

    /** The communications endpoints, bound to this client. */
    public com.exsited.resources.communications.Communications communications() {
        return (com.exsited.resources.communications.Communications) resource("communications");
    }

    /** The countries endpoints, bound to this client. */
    public com.exsited.resources.countries.Countries countries() {
        return (com.exsited.resources.countries.Countries) resource("countries");
    }

    /** The credit note applications endpoints, bound to this client. */
    public com.exsited.resources.credit_note_applications.CreditNoteApplications creditNoteApplications() {
        return (com.exsited.resources.credit_note_applications.CreditNoteApplications) resource("creditNoteApplications");
    }

    /** The credit notes endpoints, bound to this client. */
    public com.exsited.resources.credit_notes.CreditNotes creditNotes() {
        return (com.exsited.resources.credit_notes.CreditNotes) resource("creditNotes");
    }

    /** The custom attributes endpoints, bound to this client. */
    public com.exsited.resources.custom_attributes.CustomAttributes customAttributes() {
        return (com.exsited.resources.custom_attributes.CustomAttributes) resource("customAttributes");
    }

    /** The custom components endpoints, bound to this client. */
    public com.exsited.resources.custom_components.CustomComponents customComponents() {
        return (com.exsited.resources.custom_components.CustomComponents) resource("customComponents");
    }

    /** The custom events endpoints, bound to this client. */
    public com.exsited.resources.custom_events.CustomEvents customEvents() {
        return (com.exsited.resources.custom_events.CustomEvents) resource("customEvents");
    }

    /** The custom features endpoints, bound to this client. */
    public com.exsited.resources.custom_features.CustomFeatures customFeatures() {
        return (com.exsited.resources.custom_features.CustomFeatures) resource("customFeatures");
    }

    /** The custom fields endpoints, bound to this client. */
    public com.exsited.resources.custom_fields.CustomFields customFields() {
        return (com.exsited.resources.custom_fields.CustomFields) resource("customFields");
    }

    /** The custom forms endpoints, bound to this client. */
    public com.exsited.resources.custom_forms.CustomForms customForms() {
        return (com.exsited.resources.custom_forms.CustomForms) resource("customForms");
    }

    /** The day of month endpoints, bound to this client. */
    public com.exsited.resources.day_of_month.DayOfMonth dayOfMonth() {
        return (com.exsited.resources.day_of_month.DayOfMonth) resource("dayOfMonth");
    }

    /** The departments endpoints, bound to this client. */
    public com.exsited.resources.departments.Departments departments() {
        return (com.exsited.resources.departments.Departments) resource("departments");
    }

    /** The designations endpoints, bound to this client. */
    public com.exsited.resources.designations.Designations designations() {
        return (com.exsited.resources.designations.Designations) resource("designations");
    }

    /** The document templates endpoints, bound to this client. */
    public com.exsited.resources.document_templates.DocumentTemplates documentTemplates() {
        return (com.exsited.resources.document_templates.DocumentTemplates) resource("documentTemplates");
    }

    /** The email templates endpoints, bound to this client. */
    public com.exsited.resources.email_templates.EmailTemplates emailTemplates() {
        return (com.exsited.resources.email_templates.EmailTemplates) resource("emailTemplates");
    }

    /** The employees endpoints, bound to this client. */
    public com.exsited.resources.employees.Employees employees() {
        return (com.exsited.resources.employees.Employees) resource("employees");
    }

    /** The events endpoints, bound to this client. */
    public com.exsited.resources.events.Events events() {
        return (com.exsited.resources.events.Events) resource("events");
    }

    /** The express endpoints, bound to this client. */
    public com.exsited.resources.express.Express express() {
        return (com.exsited.resources.express.Express) resource("express");
    }

    /** The external databases endpoints, bound to this client. */
    public com.exsited.resources.external_databases.ExternalDatabases externalDatabases() {
        return (com.exsited.resources.external_databases.ExternalDatabases) resource("externalDatabases");
    }

    /** The fulfillment profiles endpoints, bound to this client. */
    public com.exsited.resources.fulfillment_profiles.FulfillmentProfiles fulfillmentProfiles() {
        return (com.exsited.resources.fulfillment_profiles.FulfillmentProfiles) resource("fulfillmentProfiles");
    }

    /** The fulfillment terms endpoints, bound to this client. */
    public com.exsited.resources.fulfillment_terms.FulfillmentTerms fulfillmentTerms() {
        return (com.exsited.resources.fulfillment_terms.FulfillmentTerms) resource("fulfillmentTerms");
    }

    /** The gift certificates endpoints, bound to this client. */
    public com.exsited.resources.gift_certificates.GiftCertificates giftCertificates() {
        return (com.exsited.resources.gift_certificates.GiftCertificates) resource("giftCertificates");
    }

    /** The integration connections endpoints, bound to this client. */
    public com.exsited.resources.integration_connections.IntegrationConnections integrationConnections() {
        return (com.exsited.resources.integration_connections.IntegrationConnections) resource("integrationConnections");
    }

    /** The inventory adjustments endpoints, bound to this client. */
    public com.exsited.resources.inventory_adjustments.InventoryAdjustments inventoryAdjustments() {
        return (com.exsited.resources.inventory_adjustments.InventoryAdjustments) resource("inventoryAdjustments");
    }

    /** The invoices endpoints, bound to this client. */
    public com.exsited.resources.invoices.Invoices invoices() {
        return (com.exsited.resources.invoices.Invoices) resource("invoices");
    }

    /** The invoice terms endpoints, bound to this client. */
    public com.exsited.resources.invoice_terms.InvoiceTerms invoiceTerms() {
        return (com.exsited.resources.invoice_terms.InvoiceTerms) resource("invoiceTerms");
    }

    /** The item fulfillments endpoints, bound to this client. */
    public com.exsited.resources.item_fulfillments.ItemFulfillments itemFulfillments() {
        return (com.exsited.resources.item_fulfillments.ItemFulfillments) resource("itemFulfillments");
    }

    /** The item receipts endpoints, bound to this client. */
    public com.exsited.resources.item_receipts.ItemReceipts itemReceipts() {
        return (com.exsited.resources.item_receipts.ItemReceipts) resource("itemReceipts");
    }

    /** The items endpoints, bound to this client. */
    public com.exsited.resources.items.Items items() {
        return (com.exsited.resources.items.Items) resource("items");
    }

    /** The labours endpoints, bound to this client. */
    public com.exsited.resources.labours.Labours labours() {
        return (com.exsited.resources.labours.Labours) resource("labours");
    }

    /** The milestones endpoints, bound to this client. */
    public com.exsited.resources.milestones.Milestones milestones() {
        return (com.exsited.resources.milestones.Milestones) resource("milestones");
    }

    /** The note attachments endpoints, bound to this client. */
    public com.exsited.resources.note_attachments.NoteAttachments noteAttachments() {
        return (com.exsited.resources.note_attachments.NoteAttachments) resource("noteAttachments");
    }

    /** The note groups endpoints, bound to this client. */
    public com.exsited.resources.note_groups.NoteGroups noteGroups() {
        return (com.exsited.resources.note_groups.NoteGroups) resource("noteGroups");
    }

    /** The orders endpoints, bound to this client. */
    public com.exsited.resources.orders.Orders orders() {
        return (com.exsited.resources.orders.Orders) resource("orders");
    }

    /** The organisation endpoints, bound to this client. */
    public com.exsited.resources.organisation.Organisation organisation() {
        return (com.exsited.resources.organisation.Organisation) resource("organisation");
    }

    /** The organisations endpoints, bound to this client. */
    public com.exsited.resources.organisations.Organisations organisations() {
        return (com.exsited.resources.organisations.Organisations) resource("organisations");
    }

    /** The payments endpoints, bound to this client. */
    public com.exsited.resources.payments.Payments payments() {
        return (com.exsited.resources.payments.Payments) resource("payments");
    }

    /** The portals endpoints, bound to this client. */
    public com.exsited.resources.portals.Portals portals() {
        return (com.exsited.resources.portals.Portals) resource("portals");
    }

    /** The pricing periods endpoints, bound to this client. */
    public com.exsited.resources.pricing_periods.PricingPeriods pricingPeriods() {
        return (com.exsited.resources.pricing_periods.PricingPeriods) resource("pricingPeriods");
    }

    /** The proforma invoices endpoints, bound to this client. */
    public com.exsited.resources.proforma_invoices.ProformaInvoices proformaInvoices() {
        return (com.exsited.resources.proforma_invoices.ProformaInvoices) resource("proformaInvoices");
    }

    /** The purchase credit note applications endpoints, bound to this client. */
    public com.exsited.resources.purchase_credit_note_applications.PurchaseCreditNoteApplications purchaseCreditNoteApplications() {
        return (com.exsited.resources.purchase_credit_note_applications.PurchaseCreditNoteApplications) resource("purchaseCreditNoteApplications");
    }

    /** The purchase credit notes endpoints, bound to this client. */
    public com.exsited.resources.purchase_credit_notes.PurchaseCreditNotes purchaseCreditNotes() {
        return (com.exsited.resources.purchase_credit_notes.PurchaseCreditNotes) resource("purchaseCreditNotes");
    }

    /** The purchase express endpoints, bound to this client. */
    public com.exsited.resources.purchase_express.PurchaseExpress purchaseExpress() {
        return (com.exsited.resources.purchase_express.PurchaseExpress) resource("purchaseExpress");
    }

    /** The purchase invoices endpoints, bound to this client. */
    public com.exsited.resources.purchase_invoices.PurchaseInvoices purchaseInvoices() {
        return (com.exsited.resources.purchase_invoices.PurchaseInvoices) resource("purchaseInvoices");
    }

    /** The purchase orders endpoints, bound to this client. */
    public com.exsited.resources.purchase_orders.PurchaseOrders purchaseOrders() {
        return (com.exsited.resources.purchase_orders.PurchaseOrders) resource("purchaseOrders");
    }

    /** The purchase payments endpoints, bound to this client. */
    public com.exsited.resources.purchase_payments.PurchasePayments purchasePayments() {
        return (com.exsited.resources.purchase_payments.PurchasePayments) resource("purchasePayments");
    }

    /** The purchase refunds endpoints, bound to this client. */
    public com.exsited.resources.purchase_refunds.PurchaseRefunds purchaseRefunds() {
        return (com.exsited.resources.purchase_refunds.PurchaseRefunds) resource("purchaseRefunds");
    }

    /** The quote orders endpoints, bound to this client. */
    public com.exsited.resources.quote_orders.QuoteOrders quoteOrders() {
        return (com.exsited.resources.quote_orders.QuoteOrders) resource("quoteOrders");
    }

    /** The receive terms endpoints, bound to this client. */
    public com.exsited.resources.receive_terms.ReceiveTerms receiveTerms() {
        return (com.exsited.resources.receive_terms.ReceiveTerms) resource("receiveTerms");
    }

    /** The reconciliations endpoints, bound to this client. */
    public com.exsited.resources.reconciliations.Reconciliations reconciliations() {
        return (com.exsited.resources.reconciliations.Reconciliations) resource("reconciliations");
    }

    /** The refunds endpoints, bound to this client. */
    public com.exsited.resources.refunds.Refunds refunds() {
        return (com.exsited.resources.refunds.Refunds) resource("refunds");
    }

    /** The rental assets endpoints, bound to this client. */
    public com.exsited.resources.rental_assets.RentalAssets rentalAssets() {
        return (com.exsited.resources.rental_assets.RentalAssets) resource("rentalAssets");
    }

    /** The reports endpoints, bound to this client. */
    public com.exsited.resources.reports.Reports reports() {
        return (com.exsited.resources.reports.Reports) resource("reports");
    }

    /** The return merchandise authorisations endpoints, bound to this client. */
    public com.exsited.resources.return_merchandise_authorisations.ReturnMerchandiseAuthorisations returnMerchandiseAuthorisations() {
        return (com.exsited.resources.return_merchandise_authorisations.ReturnMerchandiseAuthorisations) resource("returnMerchandiseAuthorisations");
    }

    /** The return vendor authorizations endpoints, bound to this client. */
    public com.exsited.resources.return_vendor_authorizations.ReturnVendorAuthorizations returnVendorAuthorizations() {
        return (com.exsited.resources.return_vendor_authorizations.ReturnVendorAuthorizations) resource("returnVendorAuthorizations");
    }

    /** The roles endpoints, bound to this client. */
    public com.exsited.resources.roles.Roles roles() {
        return (com.exsited.resources.roles.Roles) resource("roles");
    }

    /** The sales reps endpoints, bound to this client. */
    public com.exsited.resources.sales_reps.SalesReps salesReps() {
        return (com.exsited.resources.sales_reps.SalesReps) resource("salesReps");
    }

    /** The salutations endpoints, bound to this client. */
    public com.exsited.resources.salutations.Salutations salutations() {
        return (com.exsited.resources.salutations.Salutations) resource("salutations");
    }

    /** The settings endpoints, bound to this client. */
    public com.exsited.resources.settings.Settings settings() {
        return (com.exsited.resources.settings.Settings) resource("settings");
    }

    /** The settings workflows endpoints, bound to this client. */
    public com.exsited.resources.settings_workflows.SettingsWorkflows settingsWorkflows() {
        return (com.exsited.resources.settings_workflows.SettingsWorkflows) resource("settingsWorkflows");
    }

    /** The shipping packages endpoints, bound to this client. */
    public com.exsited.resources.shipping_packages.ShippingPackages shippingPackages() {
        return (com.exsited.resources.shipping_packages.ShippingPackages) resource("shippingPackages");
    }

    /** The shipping rules endpoints, bound to this client. */
    public com.exsited.resources.shipping_rules.ShippingRules shippingRules() {
        return (com.exsited.resources.shipping_rules.ShippingRules) resource("shippingRules");
    }

    /** The system events endpoints, bound to this client. */
    public com.exsited.resources.system_events.SystemEvents systemEvents() {
        return (com.exsited.resources.system_events.SystemEvents) resource("systemEvents");
    }

    /** The tasks endpoints, bound to this client. */
    public com.exsited.resources.tasks.Tasks tasks() {
        return (com.exsited.resources.tasks.Tasks) resource("tasks");
    }

    /** The teams endpoints, bound to this client. */
    public com.exsited.resources.teams.Teams teams() {
        return (com.exsited.resources.teams.Teams) resource("teams");
    }

    /** The time periods endpoints, bound to this client. */
    public com.exsited.resources.time_periods.TimePeriods timePeriods() {
        return (com.exsited.resources.time_periods.TimePeriods) resource("timePeriods");
    }

    /** The timesheets endpoints, bound to this client. */
    public com.exsited.resources.timesheets.Timesheets timesheets() {
        return (com.exsited.resources.timesheets.Timesheets) resource("timesheets");
    }

    /** The uom endpoints, bound to this client. */
    public com.exsited.resources.uom.Uom uom() {
        return (com.exsited.resources.uom.Uom) resource("uom");
    }

    /** The usages endpoints, bound to this client. */
    public com.exsited.resources.usages.Usages usages() {
        return (com.exsited.resources.usages.Usages) resource("usages");
    }

    /** The webhooks endpoints, bound to this client. */
    public com.exsited.resources.webhooks.Webhooks webhooks() {
        return (com.exsited.resources.webhooks.Webhooks) resource("webhooks");
    }

    /** The workflows endpoints, bound to this client. */
    public com.exsited.resources.workflows.Workflows workflows() {
        return (com.exsited.resources.workflows.Workflows) resource("workflows");
    }

    /** The zones endpoints, bound to this client. */
    public com.exsited.resources.zones.Zones zones() {
        return (com.exsited.resources.zones.Zones) resource("zones");
    }

    /** Send one request; everything beyond the verb and path rides on {@code options}. */
    public ApiResponse<Dto> sendApiRequest(String httpMethod, String path, RequestOptions options) {
        return sendApiRequest(httpMethod, path, options, null);
    }

    /** As above, reading the body as {@code dtoType}, which wins over the options' factory. */
    public <T extends Dto> ApiResponse<T> sendApiRequest(
            String httpMethod, String path, RequestOptions options, Class<T> dtoType) {
        // Counted on acceptance: close() waits for accepted sends to finish before it releases
        // the log file and the connection pool.
        enterSend();
        try {
            return sendAcceptedRequest(httpMethod, path, options, dtoType);
        } finally {
            leaveSend();
        }
    }

    private <T extends Dto> ApiResponse<T> sendAcceptedRequest(
            String httpMethod, String path, RequestOptions options, Class<T> dtoType) {
        RequestOptions resolvedOptions = options == null ? RequestOptions.none() : options;
        String method = normaliseMethod(httpMethod);
        validatePath(path);
        // Judged before any I/O, like every other caller mistake.
        String responseFormat = resolveResponseFormat(resolvedOptions.getResponseFormat());
        // Resolved once: a DTO whose toMap() answers differently later would be validated in one
        // form and sent in another.
        Object jsonBody = resolveJsonBody(resolvedOptions.getJsonBody());
        // Read once: getFiles() hands back a fresh copy per call.
        Map<String, Object> callerFiles = resolvedOptions.getFiles();
        validatePayload(resolvedOptions, jsonBody, callerFiles);
        // The one rendering of the JSON body; the bytes judged are the bytes sent.
        byte[] jsonBodyBytes = encodeJsonBodyOnce(jsonBody);
        // After validatePayload, so "jsonBody and files together" is reported as that rather
        // than as a Content-Type conflict.
        validateContentType(resolvedOptions, callerFiles);

        String endpointTemplate = Redaction.reduceToEndpoint(path);
        String resolvedPath = UrlBuilder.substitutePathParams(
                path, seedApiVersion(path, resolvedOptions.getPathParams()));
        String url = UrlBuilder.buildUrl(config.getBaseUrl(), resolvedPath);
        Map<String, Object> params = QueryParams.buildQueryParams(resolvedOptions.getQueryParams());
        rejectDuplicatedQueryParams(url, params);
        // Last check before the token grant, so a bad header does not cost an OAuth2 request.
        validateCallerHeaders(resolvedOptions.getHeaders());
        // Generated once for the whole call: a retry and the 401 replay must carry the same key.
        Map<String, String> callerHeaders = withIdempotencyKey(method, resolvedOptions.getHeaders());

        // One private snapshot, taken before the token grant: every attempt, retries and the 401
        // replay included, sends the bytes validation judged.
        Map<String, Object> files = Transport.materialiseFileParts(callerFiles, config);
        // Reduced to wire text once, so the wire, the curl line and the 401 replay cannot disagree.
        Map<String, Object> formData =
                Transport.materialiseFormFields(resolvedOptions.getFormData());
        Object reportedBody = jsonBody != null ? jsonBody : formData;
        // The HTTP layer sets Content-Type from the body kind, so the recorded headers cannot say.
        boolean formEncoded = jsonBody == null && formData != null;
        Transport.TransportRequest wireRequest = Transport.TransportRequest.builder(method, url)
                // Reduced to its shape so a transport refusal names the call, not the record.
                .pathTemplate(endpointTemplate)
                .queryParams(params)
                .jsonBody(jsonBodyBytes)
                .formData(formData)
                .files(files)
                .requestInfoBody(Redaction.redactValue(
                        Console.summarisePayload(reportedBody, files)))
                .build();

        String accessToken = resolvedOptions.isAuthenticated()
                ? tokenProvider.accessToken() : null;

        ApiResponse<Dto> response = sendOnce(wireRequest, callerHeaders, accessToken,
                reportedBody, files, formEncoded, endpointTemplate);

        if (resolvedOptions.isAuthenticated() && response.getStatusCode() == 401
                && meansExpiredToken(response.getMap(), method)) {
            // Reported before the replay, so a 401 -> refresh -> 200 shows the 401.
            reportExchange(response, reportedBody, files, formEncoded, true, endpointTemplate);
            // The refused token must not survive this block by any exit, the failed refresh
            // included: the store outlives the call, and a file store outlives the process.
            String refused = accessToken;
            try {
                accessToken = tokenProvider.refresh(refused);
            } catch (RuntimeException failure) {
                // Compare-and-clear: another worker may already have stored a good token.
                tokenProvider.invalidateAccessToken(refused);
                throw failure;
            }
            // The replay differs from the first attempt in its bearer token and nothing else.
            response = sendOnce(wireRequest, callerHeaders, accessToken,
                    reportedBody, files, formEncoded, endpointTemplate);
        }
        reportExchange(response, reportedBody, files, formEncoded, false, endpointTemplate);

        // The view last: what is printed and logged is the body as it arrived.
        ApiResponse<T> viewed = response.withResponseView(
                responseFormat, resolvedOptions.getDtoFactory(), dtoType);

        boolean shouldRaise = resolvedOptions.getRaiseOnError() != null
                ? resolvedOptions.getRaiseOnError() : config.isRaiseOnError();
        return shouldRaise ? viewed.raiseForStatus() : viewed;
    }

    private String resolveResponseFormat(String responseFormat) {
        if (responseFormat == null) {
            return config.getResponseFormat();
        }
        // The normalised value travels on, or "DTO" would be accepted and answered with a map.
        return SdkConfig.normaliseResponseFormat(responseFormat);
    }

    private static Object resolveJsonBody(Object jsonBody) {
        if (!(jsonBody instanceof Dto payload)) {
            return jsonBody;
        }
        Map<String, Object> body = payload.toMap();
        if (body == null) {
            throw new SdkUsageError("jsonBody: " + payload.getClass().getSimpleName()
                    + ".toMap() returned null, so there is no body to send - a payload "
                    + "object must describe the body it stands for");
        }
        return Redaction.detachValue(body);
    }

    /**
     * A caller's own per-call key wins, whatever its casing; the generated one is a random UUID.
     * A key in {@code defaultHeaders} is not the caller's: it is replaced on every POST, since one
     * key for every create a client sends would collapse distinct records into the first.
     */
    private static Map<String, String> withIdempotencyKey(String method, Map<String, String> headers) {
        Map<String, String> merged = headers == null ? new LinkedHashMap<>()
                : new LinkedHashMap<>(headers);
        if (!IDEMPOTENCY_KEY_METHODS.contains(method)) {
            return merged;
        }
        for (String existing : merged.keySet()) {
            if (existing.equalsIgnoreCase(IDEMPOTENCY_HEADER)) {
                return merged;
            }
        }
        merged.put(IDEMPOTENCY_HEADER, UUID.randomUUID().toString());
        return merged;
    }

    /** GET with no options. */
    public ApiResponse<Dto> sendGetRequest(String path) {
        return sendApiRequest("GET", path, null);
    }

    /** GET. */
    public ApiResponse<Dto> sendGetRequest(String path, RequestOptions options) {
        return sendApiRequest("GET", path, options);
    }

    /** GET, reading the body as {@code dtoType}. */
    public <T extends Dto> ApiResponse<T> sendGetRequest(
            String path, RequestOptions options, Class<T> dtoType) {
        return sendApiRequest("GET", path, options, dtoType);
    }

    /** POST with no body. */
    public ApiResponse<Dto> sendPostRequest(String path) {
        return sendApiRequest("POST", path, null);
    }

    /** POST. */
    public ApiResponse<Dto> sendPostRequest(String path, RequestOptions options) {
        return sendApiRequest("POST", path, options);
    }

    /** POST, reading the body as {@code dtoType}. */
    public <T extends Dto> ApiResponse<T> sendPostRequest(
            String path, RequestOptions options, Class<T> dtoType) {
        return sendApiRequest("POST", path, options, dtoType);
    }

    /** PUT with no body. */
    public ApiResponse<Dto> sendPutRequest(String path) {
        return sendApiRequest("PUT", path, null);
    }

    /** PUT. */
    public ApiResponse<Dto> sendPutRequest(String path, RequestOptions options) {
        return sendApiRequest("PUT", path, options);
    }

    /** PUT, reading the body as {@code dtoType}. */
    public <T extends Dto> ApiResponse<T> sendPutRequest(
            String path, RequestOptions options, Class<T> dtoType) {
        return sendApiRequest("PUT", path, options, dtoType);
    }

    /** PATCH with no body. */
    public ApiResponse<Dto> sendPatchRequest(String path) {
        return sendApiRequest("PATCH", path, null);
    }

    /** PATCH. */
    public ApiResponse<Dto> sendPatchRequest(String path, RequestOptions options) {
        return sendApiRequest("PATCH", path, options);
    }

    /** PATCH, reading the body as {@code dtoType}. */
    public <T extends Dto> ApiResponse<T> sendPatchRequest(
            String path, RequestOptions options, Class<T> dtoType) {
        return sendApiRequest("PATCH", path, options, dtoType);
    }

    /** DELETE with no body. */
    public ApiResponse<Dto> sendDeleteRequest(String path) {
        return sendApiRequest("DELETE", path, null);
    }

    /** DELETE. */
    public ApiResponse<Dto> sendDeleteRequest(String path, RequestOptions options) {
        return sendApiRequest("DELETE", path, options);
    }

    /** DELETE, reading the body as {@code dtoType}. */
    public <T extends Dto> ApiResponse<T> sendDeleteRequest(
            String path, RequestOptions options, Class<T> dtoType) {
        return sendApiRequest("DELETE", path, options, dtoType);
    }

    /** Release what this client made: its log file, and the transport only if it built it. */
    public void closeClient() {
        lifecycle.lock();
        try {
            if (closed) {
                return;
            }
            // Set first, so a send racing close() is refused before it is accepted.
            closed = true;
            // Then drained, uninterruptibly: an accepted send keeps the resources it was accepted
            // under. The interrupt is restored for the caller.
            boolean interrupted = false;
            while (inFlight > 0) {
                try {
                    drained.await();
                } catch (InterruptedException stopped) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        } finally {
            lifecycle.unlock();
        }
        try {
            if (ownsTransport) {
                transport.close();
            }
        } finally {
            detachLogSink();
        }
    }

    private void enterSend() {
        lifecycle.lock();
        try {
            if (closed) {
                throw new SdkUsageError("client '" + config.getClientName() + "' is closed: "
                        + "close() released its log file, and any connections it owned, so "
                        + "it cannot send again - build a new client rather than reusing a "
                        + "closed one");
            }
            inFlight++;
        } finally {
            lifecycle.unlock();
        }
    }

    private void leaveSend() {
        lifecycle.lock();
        try {
            if (--inFlight == 0) {
                drained.signalAll();
            }
        } finally {
            lifecycle.unlock();
        }
    }

    /** Equivalent to {@link #closeClient()}. */
    @Override
    public void close() {
        closeClient();
    }

    /** Acceptance and closure are one decision: {@link #closed} and {@link #inFlight} share it. */
    private final ReentrantLock lifecycle = new ReentrantLock();

    private final Condition drained = lifecycle.newCondition();

    private boolean closed;

    private int inFlight;

    private void detachLogSink() {
        if (logSink != null) {
            logSink.detach();
        }
    }

    private void warnAboutInsecureTransport() {
        if (!config.isVerifySsl()) {
            warn(new InsecureTransportWarning(
                    "TLS verification is disabled (verifySsl=false); "
                            + "responses cannot be trusted"));
        }
        warnAboutPlaintextTransport();
    }

    private void warnAboutPlaintextTransport() {
        String host = plaintextHostOf(config.getBaseUrl());
        if (host == null) {
            return;
        }
        warn(new InsecureTransportWarning(
                "baseUrl uses http://, so every request to " + host + " crosses "
                        + "the network in cleartext - the bearer token, and the credentials in "
                        + "the token request itself. Use https:// unless this is a local "
                        + "development instance"));
    }

    /** Messages already written to stderr, so each is emitted once per JVM. */
    private static final Set<String> WARNED = ConcurrentHashMap.newKeySet();

    private static boolean stderrWarningsOff() {
        return "off".equalsIgnoreCase(
                System.getProperty("exsited.sdk.insecureTransportWarning", "on"));
    }

    private void warn(InsecureTransportWarning warning) {
        // Two channels on purpose: an application may route its logger nowhere, and this notice
        // must still be seen. Deduplicated so a client built per request does not flood stderr,
        // and skipped when the logger prints to the console already, so it is said once.
        if (!stderrWarningsOff() && !loggerPrintsToConsole() && WARNED.add(warning.toString())) {
            System.err.println(warning);
        }
        if (shouldLog("WARNING")) {
            logger.log(System.Logger.Level.WARNING, warning.toString());
        }
        logToFile("WARNING", warning.toString());
    }

    /** Whether a WARNING on this client's logger already reaches a java.util.logging console. */
    private boolean loggerPrintsToConsole() {
        if (!shouldLog("WARNING")
                || !logger.getClass().getName().startsWith(JUL_BACKED_LOGGER)) {
            return false;
        }
        java.util.logging.Logger jul = java.util.logging.Logger.getLogger(logger.getName());
        if (!jul.isLoggable(java.util.logging.Level.WARNING)) {
            return false;
        }
        for (java.util.logging.Logger at = jul; at != null; at = at.getParent()) {
            for (java.util.logging.Handler handler : at.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler
                        && handler.getLevel().intValue()
                                <= java.util.logging.Level.WARNING.intValue()) {
                    return true;
                }
            }
            if (!at.getUseParentHandlers()) {
                return false;
            }
        }
        return false;
    }

    /** The class the JDK's System.Logger answers when java.util.logging is the backend. */
    private static final String JUL_BACKED_LOGGER =
            "sun.util.logging.internal.LoggingProviderImpl";

    private boolean shouldLog(String level) {
        return LEVEL_RANKS.getOrDefault(level, 30) >= logLevelRank;
    }

    private void logToFile(String level, String message) {
        if (logSink != null) {
            logSink.write(level, message);
        }
    }

    /** The host an {@code http://} base URL would reach off this machine, or {@code null}. */
    public static String plaintextHostOf(String baseUrl) {
        if (!"http".equals(schemeOf(baseUrl))) {
            return null;
        }
        String host = hostOf(baseUrl);
        return host.isEmpty() || isLoopbackHost(host) ? null : host;
    }

    private static String schemeOf(String url) {
        if (url == null) {
            return "";
        }
        int marker = url.indexOf("://");
        return marker < 0 ? "" : url.substring(0, marker).toLowerCase(Locale.ROOT);
    }

    private static String hostOf(String url) {
        int marker = url.indexOf("://");
        String rest = url.substring(marker + 3);
        for (int i = 0; i < rest.length(); i++) {
            char character = rest.charAt(i);
            if (character == '/' || character == '?' || character == '#') {
                rest = rest.substring(0, i);
                break;
            }
        }
        // A port, but not the colons inside a bracketed IPv6 literal.
        if (rest.startsWith("[")) {
            int close = rest.indexOf(']');
            rest = close < 0 ? rest : rest.substring(0, close + 1);
        } else {
            int portColon = rest.indexOf(':');
            rest = portColon < 0 ? rest : rest.substring(0, portColon);
        }
        return rest.toLowerCase(Locale.ROOT);
    }

    private static boolean isLoopbackHost(String host) {
        String bare = host.replace("[", "").replace("]", "");
        String lower = bare.toLowerCase(Locale.ROOT);
        if (lower.equals("localhost") || lower.equals("localhost.localdomain")) {
            return true;
        }
        if (bare.indexOf(':') >= 0) {
            // No DNS name holds a colon, so this is an IPv6 literal and parses without a lookup.
            try {
                return java.net.InetAddress.getByName(bare).isLoopbackAddress();
            } catch (java.net.UnknownHostException notALiteral) {
                return false;
            }
        }
        // IPv4 loopback is all of 127/8, parsed by hand so a malformed quad never reaches a lookup.
        String[] octets = bare.split("\\.", -1);
        if (octets.length != 4) {
            return false;
        }
        for (String octet : octets) {
            if (octet.isEmpty() || octet.length() > 3
                    || !octet.chars().allMatch(Character::isDigit)
                    || Integer.parseInt(octet) > 255) {
                return false;
            }
        }
        return Integer.parseInt(octets[0]) == 127;
    }

    private ApiResponse<Dto> sendOnce(Transport.TransportRequest wireRequest,
            Map<String, String> callerHeaders, String accessToken, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, String pathTemplate) {
        Map<String, String> headers = buildRequestHeaders(callerHeaders, accessToken);
        Transport.TransportRequest attempt =
                wireRequest.withHeaders(headers, Redaction.redactMapping(headers));
        try {
            return transport.sendHttpRequest(attempt);
        } catch (SdkError error) {
            // No response is still an exchange the caller asked to see, so a timeout prints too.
            RequestInfo info = error.getRequestInfo();
            if (info != null) {
                reportFailedExchange(info, reportedBody, files, formEncoded, pathTemplate);
            }
            throw error;
        }
    }

    /** Merged case-insensitively: a caller's {@code authorization} never replaces the SDK's. */
    Map<String, String> buildRequestHeaders(Map<String, String> callerHeaders, String accessToken) {
        Map<String, String> canonical = new LinkedHashMap<>();
        Map<String, String> byLowerName = new LinkedHashMap<>();

        putHeader(canonical, byLowerName, "Accept", "application/json");
        putHeader(canonical, byLowerName, "User-Agent", config.getUserAgent());
        config.getDefaultHeaders().forEach((name, value) ->
                putHeader(canonical, byLowerName, name, value));
        for (Map.Entry<String, String> entry : callerHeaders.entrySet()) {
            putHeader(canonical, byLowerName, entry.getKey(), entry.getValue());
        }
        if (accessToken != null) {
            // Applied last under the canonical spelling, whatever casing a caller used.
            byLowerName.remove("authorization");
            putHeader(canonical, byLowerName, "Authorization", "Bearer " + accessToken);
        }
        Map<String, String> merged = new LinkedHashMap<>();
        byLowerName.forEach((lower, value) -> merged.put(canonical.get(lower), value));
        return merged;
    }

    private static void validateCallerHeaders(Map<String, String> callerHeaders) {
        if (callerHeaders == null) {
            return;
        }
        for (Map.Entry<String, String> entry : callerHeaders.entrySet()) {
            validateCallerHeader(entry.getKey(), entry.getValue());
        }
    }

    private static void validateCallerHeader(String name, String value) {
        if (name == null) {
            // The value is not named: an API key may travel in it, and this message is logged.
            throw new SdkUsageError("headers: a header name must be text, got null. The "
                    + "value beside it is not shown: it may be a credential");
        }
        if (value == null) {
            throw new SdkUsageError("headers: the value for '" + name
                    + "' must be text, got null - a null value would silently remove "
                    + "the header rather than send it empty");
        }
        validateHeaderName(name);
        validateHeaderValue(name, value);
    }

    private static void putHeader(Map<String, String> canonical, Map<String, String> byLowerName,
            String name, String value) {
        validateHeaderName(name);
        validateHeaderValue(name, value);
        String lower = name.toLowerCase(Locale.ROOT);
        // The SDK's spellings win; a caller's first spelling stands for any other name.
        canonical.putIfAbsent(lower, name);
        if (Set.of("authorization", "accept", "user-agent").contains(lower)) {
            canonical.put(lower, lower.equals("authorization") ? "Authorization"
                    : lower.equals("accept") ? "Accept" : "User-Agent");
        }
        byLowerName.put(lower, value);
    }

    private static void validateHeaderName(String name) {
        if (name.indexOf('\r') >= 0 || name.indexOf('\n') >= 0) {
            // Shown via bounded(), which escapes the break; echoed raw it would forge a log line.
            throw new SdkUsageError("headers: a header name may not contain a line break, "
                    + "got " + bounded(name));
        }
        if (!isSendableName(name)) {
            throw new SdkUsageError("headers: the name " + bounded(name)
                    + " cannot be sent - a header name is an RFC 9110 token, so it carries "
                    + "letters, digits and !#$%&'*+-.^_`|~ only. A ':' would close the name "
                    + "early, and a separator such as '(', ',', '/' or '=' is a name an "
                    + "intermediary that enforces the grammar answers 400 to");
        }
    }

    /** Also refuses an edge space or tab, which the HTTP client would trim silently. */
    private static void validateHeaderValue(String name, String value) {
        if (value.indexOf('\r') >= 0 || value.indexOf('\n') >= 0) {
            throw new SdkUsageError(
                    "headers: the value for " + bounded(name) + " may not contain a line break");
        }
        if (hasEdgeWhitespace(value)) {
            throw new SdkUsageError("headers: the value for " + bounded(name)
                    + " may not begin or end with a space or a tab - the HTTP client trims both "
                    + "ends, so such a value would be sent altered rather than refused");
        }
        if (!isSendableValue(value)) {
            // Refused before the HTTP client, whose own exception quotes the value.
            throw new SdkUsageError("headers: the value for " + bounded(name) + " cannot be "
                    + "sent - a header value may carry only visible ASCII, space or tab. The "
                    + "value is not shown: it may be a credential");
        }
    }

    private Map<String, Object> seedApiVersion(String path, Map<String, Object> pathParams) {
        Map<String, Object> resolved = pathParams == null
                ? new LinkedHashMap<>() : new LinkedHashMap<>(pathParams);
        if (UrlBuilder.findPathParamNames(path).contains(API_VERSION_PARAM)
                && !resolved.containsKey(API_VERSION_PARAM)) {
            resolved.put(API_VERSION_PARAM, config.getApiVersion());
        }
        return resolved;
    }

    private static String normaliseMethod(String httpMethod) {
        if (httpMethod == null || httpMethod.strip().isEmpty()) {
            throw new SdkUsageError("httpMethod must name a verb, got '" + httpMethod + "'");
        }
        String method = httpMethod.strip().toUpperCase(Locale.ROOT);
        if (!Transport.isHttpToken(method)) {
            throw new SdkUsageError("httpMethod must be a single verb with no spaces or "
                    + "control characters, got " + bounded(method) + " - a method name is an "
                    + "RFC 9110 token. A verb HTTP allows but this API does not is the "
                    + "API's to refuse, so nothing here judges the verb against a list");
        }
        return method;
    }

    private static void validatePath(String path) {
        if (path == null || path.strip().isEmpty()) {
            throw new SdkUsageError("path must name an endpoint, got '" + path + "' - an "
                    + "empty path requests the instance root");
        }
    }

    /** Judges the already-resolved {@code jsonBody} and {@code files}, not {@code options}. */
    private static void validatePayload(RequestOptions options, Object jsonBody,
            Map<String, Object> files) {
        if (options.getJsonBody() != null && options.getFormData() != null) {
            throw new SdkUsageError("pass jsonBody or formData, not both - they are "
                    + "different content types and only one can be sent");
        }
        if (jsonBody != null && files != null) {
            throw new SdkUsageError("pass jsonBody or files, not both - a multipart "
                    + "body replaces the JSON one, so the JSON would be discarded unsent");
        }
        // After the two "not both" checks, so a jsonBody beside a bad file part names that pair.
        validateFiles(files);
        // The encoder's own check, run here so a bad filename does not cost a token grant.
        Transport.checkMultipartLabels(files, options.getFormData());
    }

    /** Render the JSON body once and judge those bytes, not a second rendering of the value. */
    private static byte[] encodeJsonBodyOnce(Object jsonBody) {
        if (jsonBody == null) {
            return null;
        }
        if (jsonBody instanceof CharSequence text) {
            String document = text.toString();
            validateJsonText(document);
            // The caller's own bytes: a body given as text keeps its spacing and order.
            return document.getBytes(StandardCharsets.UTF_8);
        }
        if (!(jsonBody instanceof Map) && !(jsonBody instanceof List)) {
            throw new SdkUsageError("jsonBody must be a JSON string, a mapping or a "
                    + "list, got " + jsonBody.getClass().getSimpleName());
        }
        // Transport's own renderer, so the bytes judged are the bytes that would be sent.
        return Transport.encodeJsonBody(jsonBody).getBytes(StandardCharsets.UTF_8);
    }

    private static void validateJsonText(String jsonBody) {
        if (jsonBody.isBlank()) {
            throw new SdkUsageError("jsonBody: a JSON string must hold an object or an "
                    + "array, got an empty string");
        }
        Object parsed;
        try {
            parsed = Json.parse(jsonBody);
        } catch (Json.JsonException error) {
            throw new SdkUsageError("jsonBody: not valid JSON: " + error.getMessage(), error);
        }
        if (!(parsed instanceof Map) && !(parsed instanceof List)) {
            throw new SdkUsageError("jsonBody: a JSON string must hold an object or an "
                    + "array, got " + jsonKind(parsed) + " - a bare value is valid JSON, but no "
                    + "endpoint here takes one as a body");
        }
    }

    private static String jsonKind(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "string";
        }
        if (value instanceof Boolean) {
            return "boolean";
        }
        return value instanceof Number ? "number" : value.getClass().getSimpleName();
    }

    private static void validateFiles(Map<String, Object> files) {
        if (files == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : files.entrySet()) {
            if (!Transport.isSendableFilePart(entry.getValue())) {
                throw Transport.refuseFilePart(entry.getKey());
            }
        }
    }

    private void validateContentType(RequestOptions options, Map<String, Object> files) {
        String where = "headers";
        String value = findContentType(options.getHeaders());
        if (value == null) {
            where = "config.defaultHeaders";
            value = findContentType(config.getDefaultHeaders());
        }
        if (value == null) {
            return;
        }
        if (!isMediaType(value)) {
            throw new SdkUsageError(grammarRefusal(where, value));
        }
        String conflict = bodyConflict(value, where,
                options.getJsonBody() != null,
                options.getFormData() != null,
                // In step with the transport: only a non-empty map takes the multipart branch.
                files != null && !files.isEmpty());
        if (conflict != null) {
            throw new SdkUsageError(conflict);
        }
    }

    private static String findContentType(Map<String, String> headers) {
        if (headers == null) {
            return null;
        }
        String found = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (CONTENT_TYPE_HEADER.equalsIgnoreCase(entry.getKey())
                    && entry.getValue() != null) {
                found = entry.getValue();
            }
        }
        return found;
    }

    /** Refuse a query name the URL already carries; the API would choose between two values. */
    private static void rejectDuplicatedQueryParams(String url, Map<String, Object> params) {
        if (params.isEmpty() || !url.contains("?")) {
            return;
        }
        Set<String> existing = new java.util.HashSet<>();
        for (String chunk : url.substring(url.indexOf('?') + 1).split("&")) {
            int equals = chunk.indexOf('=');
            String name = equals < 0 ? chunk : chunk.substring(0, equals);
            // Decoded, since queryParams are compared as the caller spelled them.
            existing.add(decodeQueryName(name));
        }
        List<String> clash = params.keySet().stream().filter(existing::contains).sorted().toList();
        if (!clash.isEmpty()) {
            throw new SdkUsageError("queryParams " + String.join(", ", clash)
                    + " already appear in the path's own query string, so each would be "
                    + "sent twice with two values and the API chooses - pass them once, "
                    + "in one place");
        }
    }

    private static String decodeQueryName(String name) {
        try {
            return java.net.URLDecoder.decode(name, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException error) {
            return name;    // a malformed escape is compared as written
        }
    }

    /**
     * The words that mean "this token is no good", and only those: {@code unauthorized} is
     * what the API sends, the RFC 6750 spellings are a gateway's, and the last two are other
     * spellings an instance can send. The bare word {@code expired} is absent: "your trial has expired"
     * is a business refusal, not a token to replace.
     */
    private static final Set<String> TOKEN_FAILURE_MARKERS = Set.of(
            "unauthorized",
            "invalid_access_token",
            "invalid_token",
            "expired_token",
            "invalid_request");

    /**
     * Whether a 401 means a stale access token rather than a business rejection.
     *
     * <p>{@code code}, {@code error} and {@code message} are three separate questions, each
     * matched as a whole value or a whole word and only when it is text; a body that names none
     * is not a token failure. A body that is not a JSON object replays only a safe method.
     */
    private static boolean meansExpiredToken(Object body, String method) {
        if (!(body instanceof Map<?, ?> map)) {
            return RetryPolicy.SAFE_METHODS.contains(method);
        }
        return marksTokenFailure(map.get("code"))
                || marksTokenFailure(map.get("error"))
                || marksTokenFailure(map.get("message"));
    }

    private static boolean marksTokenFailure(Object value) {
        if (!(value instanceof String text)) {
            return false;
        }
        String normalised = text.strip().toLowerCase(Locale.ROOT);
        if (TOKEN_FAILURE_MARKERS.contains(normalised)) {
            return true;
        }
        for (String word : normalised.split("\\s+")) {
            if (TOKEN_FAILURE_MARKERS.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private void reportProgress(String message) {
        if (config.isPrintApiInfo() && !config.isPrintOnlyOnError()) {
            System.out.println(message);
        }
        if (shouldLog("INFO")) {
            logger.log(System.Logger.Level.INFO, message);
        }
        logToFile("INFO", message);
    }

    private static String exchangeRecord(String pathTemplate, RequestInfo info, Object status) {
        return String.format("%s %s -> %s in %ss (attempt %s/%s)",
                info.getHttpMethod(), escaped(String.valueOf(pathTemplate)), status,
                info.getElapsedSeconds(), info.getAttemptCount(), info.getMaxAttempts());
    }

    private boolean wantsPrint() {
        return config.isPrintApiInfo() || config.isPrintRequestPayload()
                || config.isPrintResponseBody() || config.isPrintHeaders() || config.isPrintCurl();
    }

    /** The ERROR-level twin of {@link #reportExchange}; {@code printOnlyOnError} never hides it. */
    private void reportFailedExchange(RequestInfo info, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, String pathTemplate) {
        if (wantsPrint()) {
            System.out.println(Console.renderFailedExchange(
                    info, reportedBody, files, formEncoded, config));
        }
        String line = exchangeRecord(pathTemplate, info, Console.NO_RESPONSE_STATUS);
        if (shouldLog("ERROR")) {
            logger.log(System.Logger.Level.ERROR, line);
        }
        logToFile("ERROR", line);
        logBodies(reportedBody, null);
    }

    private void reportExchange(ApiResponse<?> response, Object reportedBody,
            Map<String, Object> files, boolean formEncoded, boolean warning,
            String pathTemplate) {
        RequestInfo info = response.getRequestInfo();
        if (wantsPrint() && !(config.isPrintOnlyOnError() && response.succeeded())) {
            System.out.println(Console.renderExchange(
                    response, reportedBody, files, formEncoded, config));
        }
        String line = exchangeRecord(pathTemplate, info, response.getStatusCode());
        if (warning) {
            if (shouldLog("WARNING")) {
                logger.log(System.Logger.Level.WARNING, line);
            }
        } else if (shouldLog("INFO")) {
            logger.log(System.Logger.Level.INFO, line);
        }
        logToFile(warning ? "WARNING" : "INFO", line);
        logBodies(reportedBody, response.getMap());
    }

    /** Redacted and neutralised, so a server-controlled newline cannot forge a log line. */
    private void logBodies(Object reportedBody, Object responseData) {
        if (!config.isLogBodies()) {
            return;
        }
        String bodies = "payload="
                + Console.neutralise(
                        Console.renderJsonValue(Redaction.redactValue(reportedBody), true))
                + " response="
                + Console.neutralise(
                        Console.renderJsonValue(Redaction.redactValue(responseData), true));
        if (shouldLog("DEBUG")) {
            logger.log(System.Logger.Level.DEBUG, bodies);
        }
        logToFile("DEBUG", bodies);
    }

    private static boolean isSendableName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            boolean token = (character >= 'A' && character <= 'Z')
                    || (character >= 'a' && character <= 'z')
                    || (character >= '0' && character <= '9')
                    || "!#$%&'*+-.^_`|~".indexOf(character) >= 0;
            if (!token) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSendableValue(String value) {
        if (value == null) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (character != '\t' && (character < 0x20 || character > 0x7e)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasEdgeWhitespace(String value) {
        if (value.isEmpty()) {
            return false;
        }
        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        return first == ' ' || first == '\t' || last == ' ' || last == '\t';
    }

    // The Content-Type grammar; SdkConfig carries the subset defaultHeaders needs, keep in step.
    // Only a Content-Type value is ever echoed in a refusal, and only bounded.

    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private static final String FORM_MEDIA_TYPE = "application/x-www-form-urlencoded";

    /** {@code tchar} from RFC 9110 section 5.6.2, written out rather than built from {@code \w}. */
    private static final String TCHAR = "[!#$%&'*+\\-.^_`|~0-9A-Za-z]";

    private static final String TOKEN = TCHAR + "+";

    /** {@code quoted-string} from RFC 9110 section 5.6.4, capped at latin-1's U+00FF. */
    private static final String QUOTED_STRING =
            "\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]"
                    + "|\\\\[\\t \\x21-\\x7E\\x80-\\xFF])*\"";

    private static final String PARAMETER = TOKEN + "=(?:" + TOKEN + "|" + QUOTED_STRING + ")";

    /** Use {@link java.util.regex.Matcher#matches()}; {@code $} matches before a last newline. */
    private static final java.util.regex.Pattern MEDIA_TYPE = java.util.regex.Pattern.compile(
            TOKEN + "/" + TOKEN + "(?:[ \\t]*;[ \\t]*" + PARAMETER + ")*");

    private static final int MAX_SHOWN = 120;

    /** Longer values are refused unread: the regex recurses per parameter and can overflow. */
    private static final int MAX_JUDGEABLE = 1024;

    private static final String GRAMMAR_ADVICE =
            "type/subtype, optionally followed by '; parameter=value'";

    private static boolean isMediaType(String value) {
        if (value.length() > MAX_JUDGEABLE) {
            return false;
        }
        return MEDIA_TYPE.matcher(value).matches();
    }

    private static String grammarRefusal(String where, String value) {
        return where + ": Content-Type must be a media type like 'application/json', got "
                + bounded(value) + " - " + GRAMMAR_ADVICE;
    }

    private static String bodyConflict(String value, String where,
            boolean hasJson, boolean hasForm, boolean hasFiles) {
        String prefix = where + ": Content-Type " + bounded(value)
                + " cannot be sent with this request";
        String mediaType = MediaType.mediaTypeOf(value);

        if (hasFiles) {
            return prefix + " - it sends files, so the body is multipart and the encoder "
                    + "generates the boundary parameter that frames it. No Content-Type set "
                    + "here can carry that boundary, so the server would be unable to parse "
                    + "the body at all. Leave it unset and the SDK sets it, boundary included";
        }
        if (hasForm && !FORM_MEDIA_TYPE.equals(mediaType)) {
            return prefix + " - it sends formData, which goes on the wire as "
                    + FORM_MEDIA_TYPE + ", so the body would be labelled as something it is "
                    + "not. Send " + FORM_MEDIA_TYPE + ", or leave it unset and the SDK sets it";
        }
        if (hasJson && !MediaType.isJson(mediaType)) {
            return prefix + " - it sends jsonBody, which goes on the wire as "
                    + MediaType.JSON + ", so the body would be labelled as something it is "
                    + "not. Send " + MediaType.JSON + ", or a vendor JSON type such as "
                    + "application/vnd.api+json, or leave it unset and the SDK sets it";
        }
        return null;
    }

    /** Quoted, escaped and bounded to {@link #MAX_SHOWN}, so a value cannot inject a line break. */
    private static String bounded(String value) {
        return value == null ? "null" : "'" + escaped(value) + "'";
    }

    private static String escaped(String value) {
        StringBuilder escaped = new StringBuilder(value.length() + 2);
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            switch (character) {
                case '\\' -> escaped.append("\\\\");
                case '\'' -> escaped.append("\\'");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                default -> {
                    if (character < 0x20 || character == 0x7f) {
                        escaped.append(String.format("\\x%02x", (int) character));
                    } else {
                        escaped.append(character);
                    }
                }
            }
        }
        String shown = escaped.toString();
        if (shown.length() > MAX_SHOWN) {
            int head = (MAX_SHOWN - 3) / 2;
            int tail = MAX_SHOWN - 3 - head;
            shown = shown.substring(0, head) + "..." + shown.substring(shown.length() - tail);
        }
        return shown;
    }
}
