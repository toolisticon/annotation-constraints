package io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.baseconstraints.AnnotatedTypeMustBe;
import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@On({On.Location.METHOD, On.Location.PARAMETER, On.Location.ANNOTATION_ATTRIBUTE, On.Location.FIELD})
@AnnotatedTypeMustBe(String.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotatedTypeMustBeTestAnnotation {
}
