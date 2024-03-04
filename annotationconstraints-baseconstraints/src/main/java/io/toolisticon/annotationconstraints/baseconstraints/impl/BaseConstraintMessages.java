package io.toolisticon.annotationconstraints.baseconstraints.impl;


import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum BaseConstraintMessages implements ValidationMessage {

    ERROR_MUST_BE_PLACE_ON_ANNOTATION_TYPE("BASE_Constraint_ERROR_001", "Constraint must be placed on annotation type"),
    ERROR_MUST_BE_PLACE_ON_ANNOTATION_ATTRIBUTE("BASE_Constraint_ERROR_002", "Constraint must be placed on annotation attribute");


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
        BaseConstraintMessages(String code, String message) {
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
