package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

public enum ExternalConstraintMappingProcessorMessages implements ValidationMessage {

    ERROR_ANNOTATION_ATTRIBUTE_NOT_FOUND_IN_TARGET_ANNOTATION("EXTERNAL_MAPPING_ERROR_001", "Annotation attribute '${0}' doesn't exist in target annotation '${1}'"),
    ERROR_COULD_NOT_CREATE_SERVICE("EXTERNAL_MAPPING_ERROR_001", "Couldnt create External mapper service ${0}");


    /**
     * the message code.
     */
    private final String code;
    /**
     * the message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param code    the message code
     * @param message the message text
     */
    ExternalConstraintMappingProcessorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code of the message.
     *
     * @return the message code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the message text.
     *
     * @return the message text
     */
    public String getMessage() {
        return message;
    }


}