package com.exsited.resources.teams.teams_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the team bodies. */
public final class TeamsDto {

    private TeamsDto() {}

    /** The settings team body, under {@code team}. */
    public static final class SettingsTeam extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/teams",
                "GET /api/{api_version}/settings/teams/{team_id}",
                "POST /api/{api_version}/settings/teams",
                "PATCH /api/{api_version}/settings/teams/{team_id}");

        @Override
        protected String envelopeKey() {
            return "team";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object managed_by;
        public String name;
        public String object;
        public String status;
        /** Null in every record measured, so untyped. */
        public Object version;
    }

    /** One page of {@code SettingsTeam} records, under {@code data}. */
    public static final class SettingsTeamList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/teams");

        public List<SettingsTeam> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
