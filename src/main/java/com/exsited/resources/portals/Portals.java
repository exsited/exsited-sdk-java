package com.exsited.resources.portals;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalChangePassword;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalLogin;
import com.exsited.resources.portals.portals_dto.PortalsDto.PortalResetLink;

/** Portal endpoints, under {@code /api/{api_version}/portal}. */
public final class Portals implements BoundResource {

    private final ExsitedClient client;

    public Portals(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Activate portal account: {@code POST /portal/activate}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> activatePortal(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_ACTIVATE,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> activatePortal(
            Object payload) {
        return activatePortal(payload, null);
    }

    /**
     * Change password: {@code POST /portal/change_password}.
     */
    public ApiResponse<PortalChangePassword> createPortalChangePassword(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_CHANGE_PASSWORD,
                reopen(options).jsonBody(payload).build(), PortalChangePassword.class);
    }

    public ApiResponse<PortalChangePassword> createPortalChangePassword(
            Object payload) {
        return createPortalChangePassword(payload, null);
    }

    /** Portal login: {@code POST /portal/login}. */
    public ApiResponse<PortalLogin> createPortalLogin(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_LOGIN,
                reopen(options).jsonBody(payload).build(), PortalLogin.class);
    }

    public ApiResponse<PortalLogin> createPortalLogin(
            Object payload) {
        return createPortalLogin(payload, null);
    }

    /** Reset password: {@code POST /portal/reset}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> createPortalReset(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_RESET,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createPortalReset(
            Object payload) {
        return createPortalReset(payload, null);
    }

    /**
     * Request reset link: {@code POST /portal/reset_link}.
     */
    public ApiResponse<PortalResetLink> createPortalResetLink(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_RESET_LINK,
                reopen(options).jsonBody(payload).build(), PortalResetLink.class);
    }

    public ApiResponse<PortalResetLink> createPortalResetLink(
            Object payload) {
        return createPortalResetLink(payload, null);
    }

    /** Validate account: {@code POST /portal/validate}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> createPortalValidate(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_VALIDATE,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createPortalValidate(
            Object payload) {
        return createPortalValidate(payload, null);
    }

    /**
     * Set a portal password by email: {@code POST /portal/change},
     * {@code {"portal": {"email", "password"}}}; no current password is asked, and it answers
     * {@code 202}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> setPortalPassword(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PortalsUrls.PORTAL_CHANGE,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> setPortalPassword(Object payload) {
        return setPortalPassword(payload, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
