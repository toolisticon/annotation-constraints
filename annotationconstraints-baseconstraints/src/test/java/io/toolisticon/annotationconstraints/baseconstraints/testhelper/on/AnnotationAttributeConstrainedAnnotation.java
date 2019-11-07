package io.toolisticon.annotationconstraints.baseconstraints.testhelper.on;

import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@On(On.Location.ANNOTATION_ATTRIBUTE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationAttributeConstrainedAnnotation {
}
