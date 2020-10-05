package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe.AnnotatedTypeMustBeTestAnnotation;
import io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe.AnnotatedTypeMustBeTestAnnotationOnClass;
import io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe.AnnotatedTypeMustBeTestAnnotationOnClassWithTarget;

import java.io.Serializable;

@AnnotatedTypeMustBeTestAnnotationOnClass
public class ValidUsage implements Serializable {

    ValidUsage(@AnnotatedTypeMustBeTestAnnotation String param) {

    }

    @AnnotatedTypeMustBeTestAnnotation
    private String field;

    @AnnotatedTypeMustBeTestAnnotationOnClassWithTarget
    private void testMethod(@AnnotatedTypeMustBeTestAnnotation String param, String secondParam) {

    }

    @AnnotatedTypeMustBeTestAnnotation
    private String testMethodWithCorrectReturnType() {
        return "";
    }

}