package com.exsited.resources.webhooks;

/** Path templates and body keys for the webhook endpoints. */
public final class WebhooksUrls {

    private WebhooksUrls() {}

    /** v4 serves no webhook listing: {@code GET /webhooks} without an id is refused. */
    public static final String WEBHOOK = "/api/{api_version}/webhooks/{webhook_id}";

    /** The envelope one record arrives under. */
    public static final String WEBHOOK_KEY = "webhook";
}
