package io.toolisticon.annotationconstraints.baseconstraints;


import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

public abstract class AbstractAnnotationTypeConstraintSpi<T extends Annotation> extends AbstractAnnotationConstraintSpi<T>{

    protected AbstractAnnotationTypeConstraintSpi (Class<T> annotationType) {
        super(annotationType);
    }

    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String constraintAttributeName) {

        if (constraintAttributeName != null) {
            //MessagerUtils.warning("")
        }

    }

    public abstract void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, T constraintAnnotation);

}
