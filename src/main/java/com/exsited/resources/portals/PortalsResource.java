package com.exsited.resources.portals;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalChangePassword;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalLogin;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalResetLink;

/**
 * Forwards every call to {@link Portals}.
 *
 * @deprecated {@link Portals} is bound to a client itself and is what
 *     {@code client.portals()} answers; use it instead.
 */
@Deprecated
public final class PortalsResource implements BoundResource {

    private final Portals portals;

    public PortalsResource(ExsitedClient client) {
        this.portals = new Portals(client);
    }

    @Override
    public ExsitedClient getClient() {
        return portals.getClient();
    }

    public ApiResponse<Dto> activatePortal(Object payload, RequestOptions options) {
        return portals.activatePortal(payload, options);
    }

    public ApiResponse<Dto> activatePortal(Object payload) {
        return portals.activatePortal(payload);
    }

    public ApiResponse<PortalChangePassword> createPortalChangePassword(Object payload, RequestOptions options) {
        return portals.createPortalChangePassword(payload, options);
    }

    public ApiResponse<PortalChangePassword> createPortalChangePassword(Object payload) {
        return portals.createPortalChangePassword(payload);
    }

    public ApiResponse<PortalLogin> createPortalLogin(Object payload, RequestOptions options) {
        return portals.createPortalLogin(payload, options);
    }

    public ApiResponse<PortalLogin> createPortalLogin(Object payload) {
        return portals.createPortalLogin(payload);
    }

    public ApiResponse<Dto> createPortalReset(Object payload, RequestOptions options) {
        return portals.createPortalReset(payload, options);
    }

    public ApiResponse<Dto> createPortalReset(Object payload) {
        return portals.createPortalReset(payload);
    }

    public ApiResponse<PortalResetLink> createPortalResetLink(Object payload, RequestOptions options) {
        return portals.createPortalResetLink(payload, options);
    }

    public ApiResponse<PortalResetLink> createPortalResetLink(Object payload) {
        return portals.createPortalResetLink(payload);
    }

    public ApiResponse<Dto> createPortalValidate(Object payload, RequestOptions options) {
        return portals.createPortalValidate(payload, options);
    }

    public ApiResponse<Dto> createPortalValidate(Object payload) {
        return portals.createPortalValidate(payload);
    }
}
