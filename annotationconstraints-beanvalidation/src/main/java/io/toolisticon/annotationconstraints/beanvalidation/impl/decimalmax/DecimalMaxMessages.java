package io.toolisticon.annotationconstraints.beanvalidation.impl.decimalmax;

import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

public enum DecimalMaxMessages implements ValidationMessage {

    ERROR_MUST_BE_SMALLER_OR_EQUAL("DecimalMax_ERROR_001","Value must be ${0} than ${1}."),
    ERROR_MUST_BE_USED_ON_ANNOTATION_ATTRIBUE("DecimalMax_ERROR_002","DecimalMax annotation must be placed on annotation attribute."),
    ERROR_MUST_BE_USED_ON_CORRECT_TYPE("DecimalMax_ERROR_003","DecimalMax annotation must be used on String, Integer or Long.")
    ;

    public static final String TOKEN_LESS = "less";
    public static final String TOKEN_LESS_OR_EQUAL = "less or equal";

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
    DecimalMaxMessages(String code, String message) {
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
