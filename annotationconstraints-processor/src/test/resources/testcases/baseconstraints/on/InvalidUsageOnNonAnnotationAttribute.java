package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.processor.testhelper.on.AnnotationAttributeConstrainedAnnotation;


public class InvalidUsageOnNonAnnotationAttribute {

    @AnnotationAttributeConstrainedAnnotation
    public void method() {

    }

}
