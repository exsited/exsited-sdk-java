package com.exsited.resources.express;

/** Path templates for the express endpoints. */
public final class ExpressUrls {

    private ExpressUrls() {}

    public static final String EXPRESS = "/api/{api_version}/express";

    /** The envelope one record arrives under. */
    public static final String EXPRESS_KEY = "express";
}
