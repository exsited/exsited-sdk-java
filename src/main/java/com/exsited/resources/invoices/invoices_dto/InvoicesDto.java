package com.exsited.resources.invoices.invoices_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/**
 * Typed views over the v4 invoices bodies.
 *
 * <p>A DTO is a view of the body. {@code ApiResponse.getMap()} is the body as it arrived and
 * is where to look when a field here reads null and you expected otherwise: the view reads null
 * both for a key the API sent as null and for one it did not send at all, and only the map can
 * tell those apart.
 *
 * <p>A field the API sent as null in every record is typed {@code Object}, and so is one whose
 * types disagree. Every field is boxed, so a JSON null never reads as 0 or false.
 *
 * <p>{@link Invoice} serves the listing entry, the detail read and several sub-resources at
 * once; its fields are those the recorded responses carried, and a live answer may carry a
 * different set.
 */
public final class InvoicesDto {

    /**
     * One invoice.
     *
     * Reads a {@code {"invoice": {...}}} body.
     */
    public static final class Invoice extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public InvoiceAccount account;
        public String account_id;
        public String account_name;
        public List<InvoiceActivityLog> activity_logs;
        public String alternate_billing_end_date;
        public String alternate_billing_start_date;
        public String alternate_due_date;
        public String alternate_issue_date;
        public String billing_end_date;
        public String billing_start_date;
        /** 4 measured, null on 2. */
        public Long code;
        public InvoiceCreatedBy created_by;
        public String created_on;
        public String credit_applied;
        public String credit_issued;
        public InvoiceCurrency currency;
        public List<CustomAttribute> custom_attributes;
        public InvoiceCustomForm custom_form;
        public List<Object> custom_objects;
        public String customer_purchase_order_id;
        public Double discount_amount;
        public String due;
        public String due_date;
        public String id;
        public String invoice_note;
        public List<Object> invoices;
        public String issue_date;
        public Map<String, Object> item_fulfillment;
        public List<ItemFulfillment> item_fulfillments;
        public InvoiceKpis kpis;
        /** 68 measured, null on 202. */
        public LastPayment last_payment;
        public InvoiceLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<InvoiceLine> lines;
        public String message;
        public Map<String, Object> note;
        public List<InvoiceNote> notes;
        public String object;
        public InvoiceOrder order;
        public String order_id;
        public String origin;
        public String outstanding;
        public Map<String, Object> pagination;
        public String paid;
        public String payment_applied;
        public String payment_status;
        public Boolean price_tax_inclusive;
        public String recurring_rate_count;
        public Map<String, Object> return_merchandise_authorisation;
        public List<Object> return_merchandise_authorisations;
        public String shipping_cost;
        public String status;
        public String subtotal;
        public String tax;
        public String tax_total;
        public String total;
        public String type;
        public String usage_rate_count;
        public String uuid;
        public Long version;
    }

    /**
     * A page of invoices: {@code {object, url, data, pagination}}, the records under
     * {@code data}, paged by cursor (measured).
     */
    public static final class InvoiceList extends Dto {
        public List<Invoice> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /**
     * A page of an invoice's notes:
     * {@code GET /api/{api_version}/invoices/{invoice_id}/notes} answers the listing's
     * {@code {object, url, data, pagination}} envelope with a cursor paging block (measured).
     */
    public static final class InvoiceNoteList extends Dto {
        public List<InvoiceNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** An invoice's currency. Never null. */
    public static final class InvoiceCurrency extends Dto {
        public String object;
        public String id;
        public String name;
        public String code;
        public String href;
    }

    /** The custom form the invoice was created under. */
    public static final class InvoiceCustomForm extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    /** The accounting code on a line. Present on all lines measured. */
    public static final class AccountingCode extends Dto {
        public String sales_revenue;
    }

    /** The tax block on a line. Present on all lines measured. */
    public static final class LineTax extends Dto {
        public String object;
        public String id;
        public String name;
        public Double rate;
        public String amount;
        public String href;
    }

    /** One line of an invoice. */
    public static final class InvoiceLine extends Dto {
        public String object;
        public String id;
        public Long version;
        public InvoiceLineItem item;
        public String item_id;
        public String item_uuid;
        public String item_name;
        public String item_uom;
        public String item_warehouse;
        public String item_serial_or_batch_number;
        public Long item_order_quantity;
        public String item_charge_type;
        public List<Object> item_custom_attributes;
        public Double pricing_snapshot_price;
        public String pricing_snapshot_uuid;
        public String charging_start_date;
        public String charging_end_date;
        public String alternate_charging_start_date;
        public String alternate_charging_end_date;
        public String subtotal;
        public String total;
        public LineTax tax;
        public AccountingCode accounting_code;
    }

    /** One custom attribute. */
    public static final class CustomAttribute extends Dto {
        public String name;
        public Object value;
    }

    /** The KPI block. Measured 268 times, never null. */
    public static class InvoiceKpis extends Dto {
        public String credit_applied;
        public String credit_issued;
        public String payment_applied;
        public String last_payment_date;
        public String last_amended_on;
        public String last_cancelled_on;
        public String last_reactivated_on;
        public String deleted_on;
        public String voided_on;
        public Object outstanding;
        public Object overdue;
    }

    /** The most recent payment against the invoice. */
    public static final class LastPayment extends Dto {
        public String uuid;
        public String id;
        public String date;
        public String amount;
        public String created_on;
    }

    /** One note on an invoice. */
    public static final class InvoiceNote extends Dto {
        @Override
        protected String envelopeKey() {
            return "note";
        }

        public String object;
        public String id;
        public String href;
        public String content;
        public Long version;
        public InvoiceNoteDetail invoice;
        public Object group;
        public Object user;
        public List<InvoiceNoteAttachment> attachments;
        public List<InvoiceNoteWatcher> watchers;
        public String created_on;
        public InvoiceNoteCreatedBy created_by;
        public Object last_updated_on;
        public Object last_updated_by;
    }

    /** One fulfilment against an invoice. Measured 4 times. */
    public static final class ItemFulfillment extends Dto {
        public String uuid;
        public String id;
        public String version;
        public String invoice_id;
        public String order_id;
        public String date;
        public String status;
        public String note;
        public String tracking_number;
        public String created_on;
        public String created_by;
        public String last_updated_on;
        public String last_updated_by;
        public List<Object> fulfillments;
    }

    /** The body {@code create_invoice} takes, under an {@code invoice} envelope. */
    public static final class InvoiceCreate extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public String account_id;
        public String issue_date;
        public String due_date;
        public String currency;
        public List<Object> lines;
        public String name;
        public String display_name;
        public String description;
        public String invoice_note;
        public String origin;
        public Boolean price_tax_inclusive;
    }

    /** The body {@code update_invoice_information} takes. */
    public static final class InvoiceInformationUpdate extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public String invoice_note;
        public String customer_purchase_order_id;
    }

    /** The body {@code cancel_invoice} takes, if any. */
    public static final class InvoiceCancel extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public String effective_date;
    }

    /** What {@code get_invoice_pdf} answers, under {@code invoice}. */
    public static final class InvoicePdf extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public InvoicePdfDetail pdf;
    }

    /** What {@code get_invoice_note_file} answers, under {@code file}. */
    public static final class InvoiceNoteFile extends Dto {
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

    /** A {@link Invoice}'s {@code account}. */
    public static final class InvoiceAccount extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Invoice}'s {@code order}. */
    public static final class InvoiceOrder extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link Invoice}'s {@code created_by}. */
    public static final class InvoiceCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Invoice}'s {@code last_updated_by}. */
    public static final class InvoiceLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link Invoice}'s {@code activity_logs}. */
    public static final class InvoiceActivityLog extends Dto {
        public String activity;
    }

    /** A {@link InvoicePdf}'s {@code pdf}. */
    public static final class InvoicePdfDetail extends Dto {
        public String download_url;
        public Long file_size;
        public String id;
        public String object;
        public String preview_url;
    }

    /** A {@link InvoiceNote}'s {@code invoice}. */
    public static final class InvoiceNoteDetail extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** One entry of a {@link InvoiceNote}'s {@code attachments}. */
    public static final class InvoiceNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link InvoiceNote}'s {@code watchers}. */
    public static final class InvoiceNoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link InvoiceNote}'s {@code created_by}. */
    public static final class InvoiceNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link InvoiceLine}'s {@code item}. */
    public static final class InvoiceLineItem extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    private InvoicesDto() {
        // Views only. Never instantiated.
    }

    /**
     * What {@code getInvoiceKpis} answers, under {@code invoice_kpis}: the block an invoice carries
     * as {@code kpis}, with an {@code object} beside it. Measured on 2 invoices.
     */
    public static final class InvoiceKpisRead extends InvoiceKpis {
        @Override
        protected String envelopeKey() {
            return "invoice_kpis";
        }

        public String object;
    }

    /**
     * What {@code getInvoiceLines} answers, under {@code invoice}: its {@code object} and
     * {@code lines}, and nothing else of the invoice.
     */
    public static final class InvoiceLines extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public String object;
        public List<InvoiceLine> lines;
    }

    /**
     * What {@code getInvoiceLine} answers, under {@code invoice}: its {@code object} and one
     * {@code line}.
     */
    public static final class InvoiceLineRead extends Dto {
        @Override
        protected String envelopeKey() {
            return "invoice";
        }

        public String object;
        public InvoiceLine line;
    }
}
