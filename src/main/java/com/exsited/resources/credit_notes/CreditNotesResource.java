package com.exsited.resources.credit_notes;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteList;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteRead;
import java.util.Map;

/**
 * Forwards every call to {@link CreditNotes}.
 *
 * @deprecated {@link CreditNotes} is bound to a client itself and is what
 *     {@code client.creditNotes()} answers; use it instead.
 */
@Deprecated
public final class CreditNotesResource implements BoundResource {

    private final CreditNotes creditNotes;

    public CreditNotesResource(ExsitedClient client) {
        this.creditNotes = new CreditNotes(client);
    }

    @Override
    public ExsitedClient getClient() {
        return creditNotes.getClient();
    }

    public ApiResponse<CreditNoteList> getCreditNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return creditNotes.getCreditNotes(queryParams, options);
    }

    public ApiResponse<CreditNoteList> getCreditNotes(Map<String, Object> queryParams) {
        return creditNotes.getCreditNotes(queryParams);
    }

    public ApiResponse<CreditNoteRead> getCreditNote(
            String creditNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return creditNotes.getCreditNote(creditNoteId, queryParams, options);
    }

    public ApiResponse<CreditNoteRead> getCreditNote(
            String creditNoteId, Map<String, Object> queryParams) {
        return creditNotes.getCreditNote(creditNoteId, queryParams);
    }

    public ApiResponse<CreditNoteRead> updateCreditNote(
            String creditNoteId, Object payload, RequestOptions options) {
        return creditNotes.updateCreditNote(creditNoteId, payload, options);
    }

    public ApiResponse<CreditNoteRead> updateCreditNote(String creditNoteId, Object payload) {
        return creditNotes.updateCreditNote(creditNoteId, payload);
    }
}
