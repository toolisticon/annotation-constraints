package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.baseconstraints.testhelper.on.MethodConstrainedAnnotation;

public @interface InvalidUsageOnNonMethod {

    @MethodConstrainedAnnotation
    String value();

}
