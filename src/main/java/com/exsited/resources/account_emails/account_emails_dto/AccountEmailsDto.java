package com.exsited.resources.account_emails.account_emails_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the account emails bodies. */
public final class AccountEmailsDto {

    /** The account communications email body. */
    public static final class AccountCommunicationsEmail extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/communications/emails",
                "GET /api/{api_version}/accounts/{account_id}/communications/emails/{email_uuid}",
                "GET /api/{api_version}/accounts/{account_id}/communications/emails/{email_id}");

        @Override
        protected String envelopeKey() {
            return "email";
        }

        /** never populated in 4 measured records. */
        public Object additional_template;
        public List<AccountCommunicationsEmailAttachment> attachments;
        public List<Object> bcc;
        /** never populated in 4 measured records. */
        public Object body;
        public List<Object> cc;
        /** never populated in 4 measured records. */
        public Object created_by;
        public String created_on;
        /** never populated in 4 measured records. */
        public Object from;
        public String id;
        /** never populated in 4 measured records. */
        public Object last_updated_by;
        /** never populated in 4 measured records. */
        public Object last_updated_on;
        public String mail_type;
        public String message_type;
        public String object;
        public String status;
        /** never populated in 4 measured records. */
        public Object subject;
        public String to;
        public String uuid;
    }

    /** A page of AccountCommunicationsEmail records. */
    public static final class AccountCommunicationsEmailList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/accounts/{account_id}/communications/emails");

        public List<AccountCommunicationsEmail> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link AccountCommunicationsEmail}'s {@code attachments}. */
    public static final class AccountCommunicationsEmailAttachment extends Dto {
        public String file_path;
        public String name;
        public String path;
        public Long size;
        public String type;
    }

    private AccountEmailsDto() {
    }
}
