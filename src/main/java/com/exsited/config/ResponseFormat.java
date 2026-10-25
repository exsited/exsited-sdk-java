package com.exsited.config;

import com.exsited.errors.SdkUsageError;

import java.util.Locale;

/** Which form {@code response.getData()} takes. */
public enum ResponseFormat {
    /** The decoded body, and the default. */
    MAP("map"),
    /** The API's own body text. */
    JSON("json"),
    /** The typed view the endpoint names. */
    DTO("dto");

    private final String value;

    ResponseFormat(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Parse a name, case-insensitively and ignoring surrounding space.
     *
     * @throws SdkUsageError for a value that names no form, rather than quietly becoming
     *     {@link #MAP}
     */
    public static ResponseFormat parse(String value) {
        if (value != null) {
            String normalised = value.strip().toLowerCase(Locale.ROOT);
            for (ResponseFormat format : values()) {
                if (format.value.equals(normalised)) {
                    return format;
                }
            }
        }
        throw new SdkUsageError(
                "responseFormat: expected one of map, json, dto, got \"" + value + "\"");
    }
}
