package io.toolisticon.annotationconstraints.baseconstraints;

public enum ComparisonMethod {

    /**
     * Must match regular expression
     */
    MATCH,
    /**
     * Must not match regular expression
     */
    NOT_MATCH,
    /**
     * Must be equal
     */
    EQUAL,
    /**
     * Must not be equal
     */
    NOT_EQUAL;

}
