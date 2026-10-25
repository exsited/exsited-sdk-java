package com.exsited.resources.gift_certificates;

import java.util.List;

/** Path templates and body keys for the gift certificate endpoints. */
public final class GiftCertificatesUrls {

    private GiftCertificatesUrls() {}

    public static final String GIFT_CERTIFICATES = "/api/{api_version}/gift_certificates";
    public static final String GIFT_CERTIFICATE = "/api/{api_version}/gift_certificates/{gc_id}";
    public static final String GIFT_CERTIFICATE_ALLOCATIONS = "/api/{api_version}/gift_certificates/{gc_id}/allocations";
    public static final String GIFT_CERTIFICATE_TRANSACTIONS = "/api/{api_version}/gift_certificates/{gc_id}/transactions";
    public static final String GIFT_CERTIFICATE_ALLOCATION = "/api/{api_version}/gift_certificates/{gc_id}/allocations/{allocation_id}";
    public static final String GIFT_CERTIFICATE_TRANSACTION = "/api/{api_version}/gift_certificates/{gc_id}/transactions/{transaction_id}";
    /**
     * The three balance transitions. Each answers {@code 200} with the gift certificate as
     * {@code getGiftCertificate} reads it.
     */
    public static final String GIFT_CERTIFICATE_AMEND = "/api/{api_version}/gift_certificates/{gc_id}/amend";
    public static final String GIFT_CERTIFICATE_DEALLOCATIONS = "/api/{api_version}/gift_certificates/{gc_id}/deallocations";

    /** The key a listing's records sit under. */
    public static final String GIFT_CERTIFICATES_KEY = "data";
    public static final String GIFT_CERTIFICATE_ALLOCATIONS_KEY = "data";
    public static final String GIFT_CERTIFICATE_TRANSACTIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String GIFT_CERTIFICATE_KEY = "gift_certificate";
    public static final String GIFT_CERTIFICATE_ALLOCATION_KEY = "gift_certificate_allocation";
    public static final String GIFT_CERTIFICATE_TRANSACTION_KEY = "gift_certificate_transaction";

    /** The one key {@code amendGiftCertificate} takes under {@code gift_certificate}. */
    public static final List<String> GIFT_CERTIFICATE_AMEND_KEYS = List.of("new_balance");

    /**
     * The one key the allocation writes take under {@code gift_certificate}: {@code account}, as
     * {@code {"code": <account id>}} or {@code {"id": "acc_<uuid>"}}.
     */
    public static final List<String> GIFT_CERTIFICATE_ALLOCATE_KEYS = List.of("account");
}
