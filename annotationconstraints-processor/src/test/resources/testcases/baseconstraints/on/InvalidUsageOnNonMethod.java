package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.processor.testhelper.on.MethodConstrainedAnnotation;

public @interface InvalidUsageOnNonMethod {

    @MethodConstrainedAnnotation
    String value();

}
