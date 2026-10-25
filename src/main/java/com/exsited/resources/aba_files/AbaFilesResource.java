package com.exsited.resources.aba_files;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;

/**
 * Forwards every call to {@link AbaFiles}.
 *
 * @deprecated {@link AbaFiles} is bound to a client itself and is what
 *     {@code client.abaFiles()} answers; use it instead.
 */
@Deprecated
public final class AbaFilesResource implements BoundResource {

    private final AbaFiles abaFiles;

    public AbaFilesResource(ExsitedClient client) {
        this.abaFiles = new AbaFiles(client);
    }

    @Override
    public ExsitedClient getClient() {
        return abaFiles.getClient();
    }

    public ApiResponse<Dto> generateAbaFile(Object payload, RequestOptions options) {
        return abaFiles.generateAbaFile(payload, options);
    }

    public ApiResponse<Dto> generateAbaFile(Object payload) {
        return abaFiles.generateAbaFile(payload);
    }
}
