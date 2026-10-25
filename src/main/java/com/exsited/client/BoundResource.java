package com.exsited.client;

import com.exsited.errors.SdkUsageError;

/** A group of endpoints with one client already applied. */
public interface BoundResource {

    ExsitedClient getClient();

    /** The client a bound resource is built over, refused when null rather than at its first call. */
    static ExsitedClient requireClient(ExsitedClient client) {
        if (client == null) {
            throw new SdkUsageError(
                    "a bound resource needs a client: pass the one its calls should "
                            + "be sent through");
        }
        return client;
    }
}
