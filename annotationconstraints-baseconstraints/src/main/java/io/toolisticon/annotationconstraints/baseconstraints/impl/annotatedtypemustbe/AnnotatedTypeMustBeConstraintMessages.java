package io.toolisticon.annotationconstraints.baseconstraints.impl.annotatedtypemustbe;


import io.toolisticon.annotationprocessortoolkit.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum AnnotatedTypeMustBeConstraintMessages implements ValidationMessage {

    ERROR_CHECK_FAILS_FOR_ANNOTATED_ELEMENT("AnnotatedTypeMustBe_Constraint_ERROR_001", "Element annotated with '${0}' must be ${1} to type '${2}'"),
    ERROR_CHECK_FAILS_FOR_ENCLOSING_ELEMENT("AnnotatedTypeMustBe_Constraint_ERROR_002", "'${0}' of element annotated with '${1}' must be ${2} to type '${3}'"),
    ERROR_ANNOTATION_MUST_BE_PLACED_CORRECTLY("AnnotatedTypeMustBe_Constraint_ERROR_003", "Annotation ${0} must be place on either field, method, Annotation attribute, method parameter or constructor parameter");


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
        AnnotatedTypeMustBeConstraintMessages(String code, String message) {
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
