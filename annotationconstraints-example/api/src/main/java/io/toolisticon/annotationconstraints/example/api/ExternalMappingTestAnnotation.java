package io.toolisticon.annotationconstraints.example.api;

import io.toolisticon.annotationconstraints.baseconstraints.MinLength;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExternalMappingTestAnnotation {

    @MinLength(3)
    String value();


}