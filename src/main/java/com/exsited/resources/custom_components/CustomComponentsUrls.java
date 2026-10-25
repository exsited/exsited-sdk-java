package com.exsited.resources.custom_components;

/** Path templates and body keys for the custom component endpoints. */
public final class CustomComponentsUrls {

    private CustomComponentsUrls() {}

    public static final String COMPONENT = "/api/{api_version}/component";
    public static final String COMPONENT_BY_UUID = "/api/{api_version}/component/{component_uuid}";
    public static final String COMPONENT_BY_ID = "/api/{api_version}/component/{component_uuid}/{component_object_id}";

    public static final String COMPONENT_OBJECTS = "/api/{api_version}/components/{component_uuid}/objects";
    public static final String COMPONENT_OBJECT = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}";
    public static final String COMPONENT_OBJECT_ACTIVITY = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/activity";
    public static final String COMPONENT_OBJECT_CUSTOM_OBJECTS = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/custom_objects";
    public static final String COMPONENT_OBJECT_PDF = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/pdf";
    public static final String COMPONENT_OBJECT_ACTIVITY_ENTRY = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/activity/{activity_id}";
    public static final String COMPONENT_OBJECT_MILESTONES = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/milestones";
    public static final String COMPONENT_OBJECT_MILESTONE = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/milestones/{milestone_id}";
    /** {@code history} is singular on the wire, for the list and for one entry alike. */
    public static final String COMPONENT_OBJECT_HISTORY = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/history";
    public static final String COMPONENT_OBJECT_HISTORY_ENTRY = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/history/{history_id}";
    public static final String COMPONENT_OBJECT_NOTES = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes";
    public static final String COMPONENT_OBJECT_NOTE = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}";
    public static final String COMPONENT_OBJECT_NOTE_FILES = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}/files";
    public static final String COMPONENT_OBJECT_NOTE_FILE = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/notes/{note_id}/files/{file_id}";
    /**
     * A custom object instance is created on the schema's own path, with no {@code instances}
     * segment; it is read, updated and deleted under {@code instances/{instance_id}}.
     */
    public static final String COMPONENT_OBJECT_CUSTOM_OBJECT = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/custom_objects/{custom_object_id}";
    public static final String COMPONENT_OBJECT_CUSTOM_OBJECT_INSTANCE = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/custom_objects/{custom_object_id}/instances/{instance_id}";
    public static final String COMPONENT_OBJECT_LINK_DOMAINS = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/link_domains";
    public static final String COMPONENT_OBJECT_UNLINK_DOMAINS = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/unlink_domains";
    public static final String COMPONENT_OBJECT_CHANGE_STATUS = "/api/{api_version}/components/{component_uuid}/objects/{component_object_id}/change_status";

    /** The key a listing's records sit under. */
    public static final String COMPONENT_LISTING_KEY = "data";
    public static final String COMPONENT_OBJECTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ACC_CUS_COMP_KEY = "Acc Cus Comp";
    public static final String COMPONENT_KEY = "component";
    public static final String COMPONENT_OBJECT_NOTES_KEY = "data";
    public static final String COMPONENT_OBJECT_NOTE_FILES_KEY = "data";
    public static final String CUSTOM_COMPONENT_OBJECT_KEY = "custom_component_object";
    public static final String CUSTOM_COMPONENT_OBJECT_ACTIVITY_KEY = "custom_component_object_activity";
    public static final String NOTE_KEY = "note";
    public static final String FILE_KEY = "file";
    public static final String CUSTOM_OBJECT_INSTANCES_KEY = "custom_object_instances";
    public static final String CUSTOM_OBJECT_INSTANCE_KEY = "custom_object_instance";
}
