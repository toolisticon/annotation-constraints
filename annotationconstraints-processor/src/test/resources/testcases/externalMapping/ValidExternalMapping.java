package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.api.ExternalConstraintMapping;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
@Documented
@ExternalConstraintMapping(ExternalConstraintMapping.class)
public @interface ValidExternalMapping {

    Class<? extends Annotation> value();

}