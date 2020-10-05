package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe.AnnotatedTypeMustBeTestAnnotation;


public class InvalidUsage {

    @AnnotatedTypeMustBeTestAnnotation
    private Long field;

}