package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.processor.testhelper.on.AnnotationAttributeConstrainedAnnotation;

public @interface ValidUsageOnAnnotationAttribute {
    @AnnotationAttributeConstrainedAnnotation
    String value();
}
