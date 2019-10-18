package io.toolisticon.annotationconstraints.processor.baseconstraints.minlength;


import io.toolisticon.annotationprocessortoolkit.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum MinLengthConstraintMessages implements ValidationMessage {

    ERROR_STRING_IS_TOO_SHORT("MinLength_Constraint_ERROR_001", "String must have a minimal length of ${0}"),
    ERROR_ATTRIBUTE_IS_NOT_OF_TYPE_STRING("MinLength_Constraint_ERROR_002", "The annotated attribute must be of type String.");


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
        MinLengthConstraintMessages(String code, String message) {
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
