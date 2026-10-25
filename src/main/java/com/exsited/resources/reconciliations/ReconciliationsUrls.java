package com.exsited.resources.reconciliations;

/** Path templates and body keys for the reconciliation endpoints. */
public final class ReconciliationsUrls {

    private ReconciliationsUrls() {}

    /** v4 serves no reconciliation itself, only the two lists hung off one. */
    public static final String RECONCILIATION_SALE_PAYMENTS = "/api/{api_version}/reconciliations/{reconciliation_id}/sale_payments";
    public static final String RECONCILIATION_SALE_REFUNDS = "/api/{api_version}/reconciliations/{reconciliation_id}/sale_refunds";

    /** The key a listing's records sit under. */
    public static final String RECONCILIATION_SALE_PAYMENTS_KEY = "data";
    public static final String RECONCILIATION_SALE_REFUNDS_KEY = "data";
}
