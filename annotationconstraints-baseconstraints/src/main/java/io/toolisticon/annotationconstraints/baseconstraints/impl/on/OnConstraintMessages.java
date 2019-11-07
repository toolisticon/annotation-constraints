package io.toolisticon.annotationconstraints.baseconstraints.impl.on;

import io.toolisticon.annotationprocessortoolkit.tools.corematcher.ValidationMessage;

public enum OnConstraintMessages implements ValidationMessage {

    ERROR_MATCHING_TARGET_NOT_FOUND("On_Constraint_ERROR_001","Couldn't find matching target value for On Location ${0}"),

    ERROR_TARGET_ANNOTATION_NOT_FOUND("On_Constraint_ERROR_002","No target annotation has been found. Please either remove On Constraint annotation or add matching Target annotation."),
    ERROR_WRONG_USAGE("On_Constraint_ERROR_003","'${0}' Constraint violated: Annotation ${1} must be placed on either ${2}");


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
    OnConstraintMessages(String code, String message) {
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
