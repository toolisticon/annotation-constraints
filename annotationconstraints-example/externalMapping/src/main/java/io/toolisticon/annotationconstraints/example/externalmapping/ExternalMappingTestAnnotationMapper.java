package io.toolisticon.annotationconstraints.example.externalmapping;

import io.toolisticon.annotationconstraints.api.ExternalConstraintMapping;
import io.toolisticon.annotationconstraints.baseconstraints.MinLength;
import io.toolisticon.annotationconstraints.example.api.ExternalMappingTestAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExternalConstraintMapping(ExternalMappingTestAnnotation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExternalMappingTestAnnotationMapper {
    @MinLength(3)
    String value();
}
