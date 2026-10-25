package com.exsited.client;

import com.exsited.resources.aba_files.AbaFiles;
import com.exsited.resources.account_emails.AccountEmails;
import com.exsited.resources.account_groups.AccountGroups;
import com.exsited.resources.accounting_codes.AccountingCodes;
import com.exsited.resources.accounts.Accounts;
import com.exsited.resources.action_scripts.ActionScripts;
import com.exsited.resources.additional_codes.AdditionalCodes;
import com.exsited.resources.alert_responsibilities.AlertResponsibilities;
import com.exsited.resources.alert_responsibility.AlertResponsibility;
import com.exsited.resources.alerts.Alerts;
import com.exsited.resources.applications.Applications;
import com.exsited.resources.approval_profiles.ApprovalProfiles;
import com.exsited.resources.approval_user_groups.ApprovalUserGroups;
import com.exsited.resources.approvals.Approvals;
import com.exsited.resources.attribute_attachments.AttributeAttachments;
import com.exsited.resources.billing_periods.BillingPeriods;
import com.exsited.resources.bookings.Bookings;
import com.exsited.resources.charging_periods.ChargingPeriods;
import com.exsited.resources.communication_profiles.CommunicationProfiles;
import com.exsited.resources.communications.Communications;
import com.exsited.resources.countries.Countries;
import com.exsited.resources.credit_note_applications.CreditNoteApplications;
import com.exsited.resources.credit_notes.CreditNotes;
import com.exsited.resources.custom_attributes.CustomAttributes;
import com.exsited.resources.custom_components.CustomComponents;
import com.exsited.resources.custom_events.CustomEvents;
import com.exsited.resources.custom_features.CustomFeatures;
import com.exsited.resources.custom_fields.CustomFields;
import com.exsited.resources.custom_forms.CustomForms;
import com.exsited.resources.day_of_month.DayOfMonth;
import com.exsited.resources.departments.Departments;
import com.exsited.resources.designations.Designations;
import com.exsited.resources.document_templates.DocumentTemplates;
import com.exsited.resources.email_templates.EmailTemplates;
import com.exsited.resources.employees.Employees;
import com.exsited.resources.events.Events;
import com.exsited.resources.express.Express;
import com.exsited.resources.external_databases.ExternalDatabases;
import com.exsited.resources.fulfillment_profiles.FulfillmentProfiles;
import com.exsited.resources.fulfillment_terms.FulfillmentTerms;
import com.exsited.resources.gift_certificates.GiftCertificates;
import com.exsited.resources.integration_connections.IntegrationConnections;
import com.exsited.resources.inventory_adjustments.InventoryAdjustments;
import com.exsited.resources.invoice_terms.InvoiceTerms;
import com.exsited.resources.invoices.Invoices;
import com.exsited.resources.item_fulfillments.ItemFulfillments;
import com.exsited.resources.item_receipts.ItemReceipts;
import com.exsited.resources.items.Items;
import com.exsited.resources.labours.Labours;
import com.exsited.resources.milestones.Milestones;
import com.exsited.resources.note_attachments.NoteAttachments;
import com.exsited.resources.note_groups.NoteGroups;
import com.exsited.resources.orders.Orders;
import com.exsited.resources.organisation.Organisation;
import com.exsited.resources.organisations.Organisations;
import com.exsited.resources.payments.Payments;
import com.exsited.resources.portals.Portals;
import com.exsited.resources.pricing_periods.PricingPeriods;
import com.exsited.resources.proforma_invoices.ProformaInvoices;
import com.exsited.resources.purchase_credit_note_applications.PurchaseCreditNoteApplications;
import com.exsited.resources.purchase_credit_notes.PurchaseCreditNotes;
import com.exsited.resources.purchase_express.PurchaseExpress;
import com.exsited.resources.purchase_invoices.PurchaseInvoices;
import com.exsited.resources.purchase_orders.PurchaseOrders;
import com.exsited.resources.purchase_payments.PurchasePayments;
import com.exsited.resources.purchase_refunds.PurchaseRefunds;
import com.exsited.resources.quote_orders.QuoteOrders;
import com.exsited.resources.receive_terms.ReceiveTerms;
import com.exsited.resources.reconciliations.Reconciliations;
import com.exsited.resources.refunds.Refunds;
import com.exsited.resources.rental_assets.RentalAssets;
import com.exsited.resources.reports.Reports;
import com.exsited.resources.return_merchandise_authorisations.ReturnMerchandiseAuthorisations;
import com.exsited.resources.return_vendor_authorizations.ReturnVendorAuthorizations;
import com.exsited.resources.roles.Roles;
import com.exsited.resources.sales_reps.SalesReps;
import com.exsited.resources.salutations.Salutations;
import com.exsited.resources.settings.Settings;
import com.exsited.resources.settings_workflows.SettingsWorkflows;
import com.exsited.resources.shipping_packages.ShippingPackages;
import com.exsited.resources.shipping_rules.ShippingRules;
import com.exsited.resources.system_events.SystemEvents;
import com.exsited.resources.tasks.Tasks;
import com.exsited.resources.teams.Teams;
import com.exsited.resources.time_periods.TimePeriods;
import com.exsited.resources.timesheets.Timesheets;
import com.exsited.resources.uom.Uom;
import com.exsited.resources.usages.Usages;
import com.exsited.resources.webhooks.Webhooks;
import com.exsited.resources.workflows.Workflows;
import com.exsited.resources.zones.Zones;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Which family class answers each {@code client.resource(name)} lookup.
 *
 * All 91 v4 families, one line each. The name is the family's package name in camelCase,
 * the SDK's accessor rule, and the name of the client's typed getter for it. It is
 * not the first segment of its paths, because many families do not own their path root:
 * RMAs are received under {@code /invoices}, accounting codes and most of the settings
 * families live under {@code /settings}, and custom features and custom components share
 * {@code /component}.
 */
final class ResourceBindings {

    /**
     * Every family, in a map the registry seeds itself from.
     *
     * <p>A map rather than a method that registers them, so nothing here calls
     * back into {@link ResourceBinding} while that class is still initialising.
     * A method reference is a fresh object each time it is evaluated, so a second
     * pass over these lines would look like 91 rebindings against 91 different
     * factories and the registry would refuse every one of them.
     */
    static Map<String, Function<ExsitedClient, ? extends BoundResource>> bindings() {
        Map<String, Function<ExsitedClient, ? extends BoundResource>> out
                = new LinkedHashMap<>();
        out.put("abaFiles", AbaFiles::new);
        out.put("accountEmails", AccountEmails::new);
        out.put("accountGroups", AccountGroups::new);
        out.put("accountingCodes", AccountingCodes::new);
        out.put("accounts", Accounts::new);
        out.put("actionScripts", ActionScripts::new);
        out.put("additionalCodes", AdditionalCodes::new);
        out.put("alertResponsibilities", AlertResponsibilities::new);
        out.put("alertResponsibility", AlertResponsibility::new);
        out.put("alerts", Alerts::new);
        out.put("applications", Applications::new);
        out.put("approvalProfiles", ApprovalProfiles::new);
        out.put("approvals", Approvals::new);
        out.put("approvalUserGroups", ApprovalUserGroups::new);
        out.put("attributeAttachments", AttributeAttachments::new);
        out.put("billingPeriods", BillingPeriods::new);
        out.put("bookings", Bookings::new);
        out.put("chargingPeriods", ChargingPeriods::new);
        out.put("communicationProfiles", CommunicationProfiles::new);
        out.put("communications", Communications::new);
        out.put("countries", Countries::new);
        out.put("creditNoteApplications", CreditNoteApplications::new);
        out.put("creditNotes", CreditNotes::new);
        out.put("customAttributes", CustomAttributes::new);
        out.put("customComponents", CustomComponents::new);
        out.put("customEvents", CustomEvents::new);
        out.put("customFeatures", CustomFeatures::new);
        out.put("customFields", CustomFields::new);
        out.put("customForms", CustomForms::new);
        out.put("dayOfMonth", DayOfMonth::new);
        out.put("departments", Departments::new);
        out.put("designations", Designations::new);
        out.put("documentTemplates", DocumentTemplates::new);
        out.put("emailTemplates", EmailTemplates::new);
        out.put("employees", Employees::new);
        out.put("events", Events::new);
        out.put("express", Express::new);
        out.put("externalDatabases", ExternalDatabases::new);
        out.put("fulfillmentProfiles", FulfillmentProfiles::new);
        out.put("fulfillmentTerms", FulfillmentTerms::new);
        out.put("giftCertificates", GiftCertificates::new);
        out.put("integrationConnections", IntegrationConnections::new);
        out.put("inventoryAdjustments", InventoryAdjustments::new);
        out.put("invoices", Invoices::new);
        out.put("invoiceTerms", InvoiceTerms::new);
        out.put("itemFulfillments", ItemFulfillments::new);
        out.put("itemReceipts", ItemReceipts::new);
        out.put("items", Items::new);
        out.put("labours", Labours::new);
        out.put("milestones", Milestones::new);
        out.put("noteAttachments", NoteAttachments::new);
        out.put("noteGroups", NoteGroups::new);
        out.put("orders", Orders::new);
        out.put("organisation", Organisation::new);
        out.put("organisations", Organisations::new);
        out.put("payments", Payments::new);
        out.put("portals", Portals::new);
        out.put("pricingPeriods", PricingPeriods::new);
        out.put("proformaInvoices", ProformaInvoices::new);
        out.put("purchaseCreditNoteApplications", PurchaseCreditNoteApplications::new);
        out.put("purchaseCreditNotes", PurchaseCreditNotes::new);
        out.put("purchaseExpress", PurchaseExpress::new);
        out.put("purchaseInvoices", PurchaseInvoices::new);
        out.put("purchaseOrders", PurchaseOrders::new);
        out.put("purchasePayments", PurchasePayments::new);
        out.put("purchaseRefunds", PurchaseRefunds::new);
        out.put("quoteOrders", QuoteOrders::new);
        out.put("receiveTerms", ReceiveTerms::new);
        out.put("reconciliations", Reconciliations::new);
        out.put("refunds", Refunds::new);
        out.put("rentalAssets", RentalAssets::new);
        out.put("reports", Reports::new);
        out.put("returnMerchandiseAuthorisations", ReturnMerchandiseAuthorisations::new);
        out.put("returnVendorAuthorizations", ReturnVendorAuthorizations::new);
        out.put("roles", Roles::new);
        out.put("salesReps", SalesReps::new);
        out.put("salutations", Salutations::new);
        out.put("settings", Settings::new);
        out.put("settingsWorkflows", SettingsWorkflows::new);
        out.put("shippingPackages", ShippingPackages::new);
        out.put("shippingRules", ShippingRules::new);
        out.put("systemEvents", SystemEvents::new);
        out.put("tasks", Tasks::new);
        out.put("teams", Teams::new);
        out.put("timePeriods", TimePeriods::new);
        out.put("timesheets", Timesheets::new);
        out.put("uom", Uom::new);
        out.put("usages", Usages::new);
        out.put("webhooks", Webhooks::new);
        out.put("workflows", Workflows::new);
        out.put("zones", Zones::new);
        return out;
    }

    private ResourceBindings() {
        // Bindings only. Never instantiated.
    }
}
