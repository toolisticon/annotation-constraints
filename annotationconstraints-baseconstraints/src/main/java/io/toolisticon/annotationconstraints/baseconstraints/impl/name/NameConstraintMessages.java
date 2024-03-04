package io.toolisticon.annotationconstraints.baseconstraints.impl.name;


import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum NameConstraintMessages implements ValidationMessage {

    ERROR_EQUALS_VALUE_MUST_NOT_BE_EMPTY("Name_Constraint_ERROR_001", "Passed value String must not be empty."),
    ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_VALID("Name_Constraint_ERROR_002", "Passed value String '${0}' must contain a valid regular expression"),
    ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL("Name_Constraint_ERROR_003", "${0} name '${1}' must be equal to'${2}'"),
    ERROR_REGULAR_EXPRESSION_VALUE_MUST_NOT_BE_EQUAL("Name_Constraint_ERROR_004", "${0} name ${1}' must not be equal to '${2}'"),
    ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH("Name_Constraint_ERROR_005", "${0} name '${1}' must match regular expression '${2}'"),
    ERROR_REGULAR_EXPRESSION_VALUE_MUST_NOT_MATCH("Name_Constraint_ERROR_00", "${0} name '${1}' must match regular expression '${2}'")
    ;



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
    NameConstraintMessages(String code, String message) {
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
