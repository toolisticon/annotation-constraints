package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.baseconstraints.testhelper.on.AnnotationAttributeConstrainedAnnotation;

public @interface ValidUsageOnAnnotationAttribute {
    @AnnotationAttributeConstrainedAnnotation
    String value();
}
