package com.exsited.resources.custom_components.custom_components_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the custom components bodies. */
public final class CustomComponentsDto {

    /** The component body. */
    public static final class Component extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/component",
                "GET /api/{api_version}/component/{component_uuid}",
                "POST /api/{api_version}/component",
                "PATCH /api/{api_version}/component/{component_uuid}");

        @Override
        protected String envelopeKey() {
            return "component";
        }

        public List<Object> child_custom_components;
        /** never populated in 6 measured records. */
        public Object created_by;
        public String created_on;
        /** never populated in 6 measured records. */
        public Object custom_component;
        public List<ComponentCustomForm> custom_forms;
        public List<Object> custom_objects;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 6 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String status;
        public String use_in;
        public Long version;
        /** On the create answer only, and empty in every one measured. */
        public List<Object> activity_logs;
    }

    /** A page of Component records. */
    public static final class ComponentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/component");

        public List<Component> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /**
     * The component instance body. It arrives under the component's own name, the key the
     * payload is written under, which the create and update methods unwrap; read on its own,
     * this view takes a record that is already unwrapped.
     */
    public static final class ComponentByUuid extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/component/{component_uuid}",
                "PATCH /api/{api_version}/component/{component_uuid}/{component_object_id}");

        public List<Object> activity_logs;
        public List<Object> attribute_groups;
        public List<Object> attributes;
        public String created_by;
        public String created_on;
        public Map<String, Object> custom_form;
        public List<Object> custom_objects;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public List<Object> parents;
        public List<Object> restrictions;
        public String status;
        public String uuid;
        public String version;
    }

    /** The component object body. */
    public static final class ComponentObject extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}");

        @Override
        protected String envelopeKey() {
            return "Acc Cus Comp";
        }

        public List<Object> attribute_groups;
        public List<Object> attributes;
        public String created_by;
        public String created_on;
        public ComponentObjectCustomComponentObject custom_component_object;
        public Map<String, Object> custom_form;
        public List<Object> custom_objects;
        public String id;
        public String last_updated_by;
        public String last_updated_on;
        public List<Object> parents;
        public List<Object> restrictions;
        public String status;
        public String uuid;
        public String version;
    }

    /** The component objects body. */
    public static final class ComponentObjects extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects");

        public List<ComponentObjectsData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The component object activity body. */
    public static final class ComponentObjectActivity extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/activity");

        @Override
        protected String envelopeKey() {
            return "Acc Cus Comp";
        }

        public List<Object> activites;
        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The component object custom objects body. */
    public static final class ComponentObjectCustomObjects extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/custom_objects");

        public List<Object> custom_objects;
        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link Component}'s {@code custom_forms}. */
    public static final class ComponentCustomForm extends Dto {
        public Long column_count;
        public ComponentCustomFormData data;
        public String id;
        public String object;
        public String template_name;
    }

    /** One entry of a {@link ComponentObjects}'s {@code data}. */
    public static final class ComponentObjectsData extends Dto {
        public List<Object> accounts;
        public List<Object> attribute_groups;
        public List<Object> attributes;
        public String code;
        public ComponentObjectsDataDetail component;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public ComponentObjectsDataCustomForm custom_form;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public List<Object> items;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public List<Object> orders;
        public List<ComponentObjectsDataParent> parents;
        public List<Object> quotes;
        public List<Object> restrictions;
        public String status;
        public Long version;
        public List<Object> workflow_data;
        /** Null in every record measured, so untyped. */
        public Object workflow_stage;
        /** Null in every record measured, so untyped. */
        public Object workflow_status;
        /** Null in every record measured, so untyped. */
        public Object workflow_status_uuid;
    }

    /** A {@link ComponentObject}'s {@code custom_component_object}. */
    public static class ComponentObjectCustomComponentObject extends Dto {
        public List<Object> accounts;
        public List<Object> attribute_groups;
        public List<Object> attributes;
        public String code;
        public ComponentObjectCustomComponentObjectDetail component;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public ComponentObjectCustomComponentObjectCustomForm custom_form;
        public List<Object> custom_objects;
        public String href;
        public String id;
        public List<Object> items;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String object;
        public List<Object> orders;
        public List<ComponentObjectCustomComponentObjectParent> parents;
        public List<Object> quotes;
        public List<Object> restrictions;
        public String status;
        public Long version;
        public List<Object> workflow_data;
        /** Null in every record measured, so untyped. */
        public Object workflow_stage;
        /** Null in every record measured, so untyped. */
        public Object workflow_status;
        /** Null in every record measured, so untyped. */
        public Object workflow_status_uuid;
    }

    /** A {@link ComponentCustomForm}'s {@code data}. */
    public static final class ComponentCustomFormData extends Dto {
        @WireName("firstColumnAttribute")
        public List<ComponentCustomFormDataFirstColumnAttribute> firstColumnAttribute;
        @WireName("secondColumnAttribute")
        public List<ComponentCustomFormDataSecondColumnAttribute> secondColumnAttribute;
        @WireName("thirdColumnAttribute")
        public List<Object> thirdColumnAttribute;
    }

    /** A {@link ComponentObjectsData}'s {@code component}. */
    public static final class ComponentObjectsDataDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ComponentObjectsData}'s {@code custom_form}. */
    public static final class ComponentObjectsDataCustomForm extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link ComponentObjectsData}'s {@code parents}. */
    public static final class ComponentObjectsDataParent extends Dto {
        public String email;
        public String id;
        public String name;
        public String object;
        public String type;
    }

    /** A {@link ComponentObjectCustomComponentObject}'s {@code component}. */
    public static final class ComponentObjectCustomComponentObjectDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link ComponentObjectCustomComponentObject}'s {@code custom_form}. */
    public static final class ComponentObjectCustomComponentObjectCustomForm extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link ComponentObjectCustomComponentObject}'s {@code parents}. */
    public static final class ComponentObjectCustomComponentObjectParent extends Dto {
        public String email;
        public String id;
        public String name;
        public String object;
        public String type;
    }

    /** One entry of a {@link ComponentCustomFormData}'s {@code firstColumnAttribute}. */
    public static final class ComponentCustomFormDataFirstColumnAttribute extends Dto {
        @WireName("canEncrypt")
        public Boolean canEncrypt;
        public String description;
        @WireName("displayName")
        public String displayName;
        public Boolean enabled;
        @WireName("encryptData")
        public Boolean encryptData;
        /** Null in every record measured, so untyped. */
        public Object entity;
        @WireName("fieldRequired")
        public Boolean fieldRequired;
        @WireName("formMaxLengthInputed")
        public Boolean formMaxLengthInputed;
        @WireName("formMinLengthInputed")
        public Boolean formMinLengthInputed;
        public Long id;
        public Long ids;
        @WireName("isRequired")
        public Boolean isRequired;
        @WireName("isUnique")
        public Boolean isUnique;
        /** Null in every record measured, so untyped. */
        @WireName("maxLength")
        public Object maxLength;
        @WireName("metaData")
        public String metaData;
        /** Null in every record measured, so untyped. */
        @WireName("minLength")
        public Object minLength;
        public String name;
        public List<ComponentCustomFormDataFirstColumnAttributeOption> options;
        public String position;
        @WireName("showInListView")
        public Boolean showInListView;
        /** Null in every record measured, so untyped. */
        @WireName("timeFormat")
        public Object timeFormat;
        public String type;
        @WireName("typeValue")
        public String typeValue;
        @WireName("validationMessage")
        public String validationMessage;
        @WireName("validationMessageRequired")
        public Boolean validationMessageRequired;
        public String value;
    }

    /** One entry of a {@link ComponentCustomFormData}'s {@code secondColumnAttribute}. */
    public static final class ComponentCustomFormDataSecondColumnAttribute extends Dto {
        @WireName("displayName")
        public String displayName;
        @WireName("fieldEnabled")
        public Boolean fieldEnabled;
        @WireName("fieldRequired")
        public Boolean fieldRequired;
        public Long ids;
        public String name;
        @WireName("showInListView")
        public Boolean showInListView;
        public String type;
        @WireName("typeActual")
        public ComponentCustomFormDataSecondColumnAttributeTypeActual typeActual;
        @WireName("validationMessageRequired")
        public Boolean validationMessageRequired;
    }

    /** One entry of a {@link ComponentCustomFormDataFirstColumnAttribute}'s {@code options}. */
    public static final class ComponentCustomFormDataFirstColumnAttributeOption extends Dto {
        public String id;
        public String name;
    }

    /** A {@link ComponentCustomFormDataSecondColumnAttribute}'s {@code typeActual}. */
    public static final class ComponentCustomFormDataSecondColumnAttributeTypeActual extends Dto {
        public String key;
        public String value;
    }

    private CustomComponentsDto() {
    }

    /**
     * The component object body a v4 write answers, under {@code custom_component_object}: the
     * fields a read nests there, with {@code activity_logs} beside them.
     */
    public static final class ComponentObjectRecord extends ComponentObjectCustomComponentObject {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/components/{component_uuid}/objects",
                "PATCH /api/{api_version}/components/{component_uuid}/objects/{component_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_component_object";
        }

        public List<ComponentObjectRecordActivityLog> activity_logs;
        public String last_updated_on;
    }

    /** One entry of a {@link ComponentObjectRecord}'s {@code activity_logs}. */
    public static final class ComponentObjectRecordActivityLog extends Dto {
        public String activity;
    }

    /** One component object activity, under {@code custom_component_object_activity}. */
    public static final class ComponentObjectActivityEntry extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/activity",
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/activity/{activity_id}");

        @Override
        protected String envelopeKey() {
            return "custom_component_object_activity";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String href;
        public String id;
        public String message;
        public String object;
        public String status;
        public String type;
        public String user_name;
        public Long version;
    }

    /** A component object's note, under {@code note}. */
    public static final class ComponentObjectNote extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes",
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}",
                "POST /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes",
                "PATCH /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}");

        @Override
        protected String envelopeKey() {
            return "note";
        }

        public List<ComponentObjectNoteAttachment> attachments;
        public String content;
        public ComponentObjectNoteCreatedBy created_by;
        public String created_on;
        public ComponentObjectNoteCustomComponentObject custom_component_object;
        /** Null in every record measured, so untyped. */
        public Object group;
        public String href;
        public String id;
        public ComponentObjectNoteLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object user;
        public Long version;
        public List<Object> watchers;
    }

    /** One page of {@code ComponentObjectNote} records, under {@code data}. */
    public static final class ComponentObjectNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes");

        public List<ComponentObjectNote> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /**
     * One entry of a {@link ComponentObjectNote}'s {@code attachments}; {@code uuid} is the file
     * id.
     */
    public static class ComponentObjectNoteAttachment extends Dto {
        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** A {@link ComponentObjectNote}'s {@code created_by}. */
    public static final class ComponentObjectNoteCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link ComponentObjectNote}'s {@code custom_component_object}, the note's owner. */
    public static final class ComponentObjectNoteCustomComponentObject extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /**
     * A {@link ComponentObjectNote}'s {@code last_updated_by}, set once the note has been updated.
     */
    public static final class ComponentObjectNoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /**
     * A note's files, under {@code data}; its {@code pagination} carries {@code has_more} alone.
     */
    public static final class ComponentObjectNoteFiles extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}/files");

        public List<ComponentObjectNoteAttachment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One file of a note, under {@code file}. */
    public static final class ComponentObjectNoteFile extends ComponentObjectNoteAttachment {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}/files/{file_id}");

        @Override
        protected String envelopeKey() {
            return "file";
        }
    }
}
