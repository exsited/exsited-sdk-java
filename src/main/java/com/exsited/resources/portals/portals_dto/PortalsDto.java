package com.exsited.resources.portals.portals_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the portals bodies. */
public final class PortalsDto {

    /** The portal login body. */
    public static final class PortalLogin extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/portal/login");

        @Override
        protected String envelopeKey() {
            return "portal";
        }

        public PortalLoginUser user;
    }

    /** What {@code create_portal_change_password} answers, under {@code portal}. */
    public static final class PortalChangePassword extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/portal/change_password");

        @Override
        protected String envelopeKey() {
            return "portal";
        }

        public String message;
    }

    /** What {@code create_portal_reset_link} answers. */
    public static final class PortalResetLink extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/portal/reset_link");

        public String message;
        public String object;
    }

    /** A {@link PortalLogin}'s {@code user}. */
    public static final class PortalLoginUser extends Dto {
        public String email_address;
        public Boolean grant_access_portal;
        public String image_uri;
        public String name;
        public List<PortalLoginUserRow> portals;
        public String type;
    }

    /** One entry of a {@link PortalLoginUser}'s {@code portals}. */
    public static final class PortalLoginUserRow extends Dto {
        public String account;
        public String display_name;
        public String uuid;
    }

    private PortalsDto() {
    }
}
