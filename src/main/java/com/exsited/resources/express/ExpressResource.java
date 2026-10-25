package com.exsited.resources.express;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.express.express_dto.ExpressDto;

/**
 * Forwards every call to {@link Express}.
 *
 * @deprecated {@link Express} is bound to a client itself and is what
 *     {@code client.express()} answers; use it instead.
 */
@Deprecated
public final class ExpressResource implements BoundResource {

    private final Express express;

    public ExpressResource(ExsitedClient client) {
        this.express = new Express(client);
    }

    @Override
    public ExsitedClient getClient() {
        return express.getClient();
    }

    public ApiResponse<ExpressDto.Express> createExpress(Object payload, RequestOptions options) {
        return express.createExpress(payload, options);
    }

    public ApiResponse<ExpressDto.Express> createExpress(Object payload) {
        return express.createExpress(payload);
    }
}
