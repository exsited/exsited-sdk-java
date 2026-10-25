package com.exsited.resources.purchase_refunds;

import java.util.List;

/** Path templates and body keys for the purchase refund endpoints. */
public final class PurchaseRefundsUrls {

    private PurchaseRefundsUrls() {}

    public static final String PURCHASE_REFUNDS = "/api/{api_version}/purchase_refunds";
    public static final String PURCHASE_REFUND = "/api/{api_version}/purchase_refunds/{purchase_refund_id}";
    /**
     * A purchase refund is raised against a purchase credit note, never at
     * {@code /purchase_refunds}.
     */
    public static final String PURCHASE_CREDIT_NOTE_PURCHASE_REFUNDS = "/api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}/purchase_refunds";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_REFUNDS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String PURCHASE_REFUND_KEY = "purchase_refund";

    /**
     * The keys {@code updatePurchaseRefund} takes under {@code purchase_refund}; any other is a
     * {@code 400}.
     */
    public static final List<String> PURCHASE_REFUND_UPDATE_KEYS = List.of(
            "alternate_date", "reference", "note", "custom_attributes");
}
