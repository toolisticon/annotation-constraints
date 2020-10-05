package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Target annotation is kind of fuzzy in some cases about where annotations might be used.
 * <p>
 * E.g. this is the case for TYPE, which allows you to use annotations on Classes, Interfaces, Enums and Annotations.
 * Another example is METHOD, which allows you to use annotations on methods or annotation attribute declarations.
 * <p>
 * This constraint helps you to add a constraint additional to the target annotation which.
 */
@Documented
@Constraint
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface On {

    // TODO must decided to handle just complex cases Types:Interface,Class,Enum,Annotation;Method:Method,AnnotationAttribute;Parameter:ConstructorParameter, MethodParameter
    public enum Location {
        PACKAGE,
        ANNOTATION_ATTRIBUTE,
        ANNOTATION,
        CLASS,
        INTERFACE,
        ENUM,
        METHOD,
        CONSTRUCTOR,
        PARAMETER,
        METHOD_PARAMETER,
        CONSTRUCTOR_PARAMETER,
        FIELD
        // TODO shall we add something like METHOD_PARAMETER or CONSTRUCTOR_PARAMETER?
    }

    Location[] value();

}
