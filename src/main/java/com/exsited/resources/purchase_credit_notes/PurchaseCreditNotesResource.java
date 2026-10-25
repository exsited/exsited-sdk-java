package com.exsited.resources.purchase_credit_notes;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteList;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteRead;
import java.util.Map;

/**
 * Forwards every call to {@link PurchaseCreditNotes}.
 *
 * @deprecated {@link PurchaseCreditNotes} is bound to a client itself and is what
 *     {@code client.purchaseCreditNotes()} answers; use it instead.
 */
@Deprecated
public final class PurchaseCreditNotesResource implements BoundResource {

    private final PurchaseCreditNotes purchaseCreditNotes;

    public PurchaseCreditNotesResource(ExsitedClient client) {
        this.purchaseCreditNotes = new PurchaseCreditNotes(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchaseCreditNotes.getClient();
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchaseCreditNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseCreditNotes.getPurchaseCreditNotes(queryParams, options);
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchaseCreditNotes(
            Map<String, Object> queryParams) {
        return purchaseCreditNotes.getPurchaseCreditNotes(queryParams);
    }

    public ApiResponse<PurchaseCreditNoteRead> getPurchaseCreditNote(
            String purchaseCreditNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseCreditNotes.getPurchaseCreditNote(purchaseCreditNoteId, queryParams, options);
    }

    public ApiResponse<PurchaseCreditNoteRead> getPurchaseCreditNote(
            String purchaseCreditNoteId, Map<String, Object> queryParams) {
        return purchaseCreditNotes.getPurchaseCreditNote(purchaseCreditNoteId, queryParams);
    }

    public ApiResponse<PurchaseCreditNoteRead> updatePurchaseCreditNote(
            String purchaseCreditNoteId, Object payload, RequestOptions options) {
        return purchaseCreditNotes.updatePurchaseCreditNote(purchaseCreditNoteId, payload, options);
    }

    public ApiResponse<PurchaseCreditNoteRead> updatePurchaseCreditNote(String purchaseCreditNoteId, Object payload) {
        return purchaseCreditNotes.updatePurchaseCreditNote(purchaseCreditNoteId, payload);
    }
}
