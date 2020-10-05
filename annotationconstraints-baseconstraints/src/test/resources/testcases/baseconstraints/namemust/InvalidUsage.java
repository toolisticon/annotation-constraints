package io.toolisticon.annotationconstraints.baseconstraints.wrongpackage;

import io.toolisticon.annotationconstraints.baseconstraints.testhelper.namemust.NameMustBeEqualAnnotation;

public class InvalidUsage {

    @NameMustBeEqualAnnotation
    public void method() {

    }

}