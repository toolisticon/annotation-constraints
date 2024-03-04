package io.toolisticon.annotationconstraints.processor.testconstraint;

import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

/**
 * Messages for {@link TestConstraintServiceImpl}.
 */
public enum TestConstraintMessages implements ValidationMessage {

    ERROR_LOCATION("TestConstraint_ERROR_001","FAILED"),
    ERROR_IN_PROCESSING_PHASE("TestConstraint_ERROR_002","FAILED"),
    WTF ("WTF", "WTF");


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
    TestConstraintMessages(String code, String message) {
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
