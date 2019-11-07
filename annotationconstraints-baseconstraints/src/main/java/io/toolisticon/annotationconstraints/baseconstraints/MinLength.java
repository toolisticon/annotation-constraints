package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint
@Target(ElementType.METHOD)
@On(On.Location.ANNOTATION_ATTRIBUTE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {

    int value() default 0;

}
