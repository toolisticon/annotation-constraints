package io.toolisticon.annotationconstraints.processor;


import io.toolisticon.annotationprocessortoolkit.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum ConstraintProcessorMessages implements ValidationMessage {

    // TODO: Replace this by your own error messages
    ERROR_COULD_NOT_CREATE_CLASS("Constraint_ERROR_001", "Could not create class ${0} : ${1}"),
    WARNING_CONSTRAINT_SPI_IMPLEMENTATION_NOT_FOUND("Constraint_WARNING_001", "Couldn't find and apply annotation constraint implementation for constraint ${0} in annotation ${1}.");


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
        ConstraintProcessorMessages(String code, String message) {
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
