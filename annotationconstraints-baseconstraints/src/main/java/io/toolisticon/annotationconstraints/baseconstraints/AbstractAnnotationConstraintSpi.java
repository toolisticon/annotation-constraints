package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;

import java.lang.annotation.Annotation;

public abstract class AbstractAnnotationConstraintSpi<T extends Annotation> implements AnnotationConstraintSpi {

    private final Class<T> annotationType;

    protected AbstractAnnotationConstraintSpi(Class<T> annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public Class<T> getSupportedAnnotation() {
        return this.annotationType;
    }


}
