package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation constraint to check if names are matching.
 */

@Constraint
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@On(On.Location.ANNOTATION)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameMust {

    public enum NameKind {
        /**
         * Compares the simple name.
         */
        SIMPLE,
        /**
         * Compares the fully qualified name. f.e the full qualified package or class name
         */
        FULL_QUALIFIED;
    }

    /**
     * The comparison method.
     * Defaults to EQUAL
     *
     * @return The comparison method
     */
    ComparisonMethod comparisonMethod() default ComparisonMethod.EQUAL;

    /**
     * Allows checks for simple and qualified names.
     * Defaults to qualified names.
     * Nevertheless qualified names are only available for Elements implementing {@see QualifiedNameable}.
     *
     * @return whether to check simple or qualified names
     */
    NameKind nameKind() default NameKind.FULL_QUALIFIED;

    /**
     * Comparison value.
     * Either the exact name or regular expression depending on comparison method.
     *
     * @return the value
     */
    String value();

    /**
     * Defines the target of the constraint check.
     * Defaults to the annotated Element
     *
     * @return the constraint target
     */
    ConstraintsTarget constraintTarget() default ConstraintsTarget.ANNOTATED_ELEMENT;

}
