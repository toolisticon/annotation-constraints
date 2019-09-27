package io.toolisticon.annotationconstraints.api;

import io.toolisticon.spiap.api.Spi;
import io.toolisticon.spiap.api.SpiServiceLocator;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

/**
 * A spi interface to process annotation constraints.
 */
@Spi
public interface AnnotationConstraintSpi {

    /**
     * Get the annotations provided by this spi implementation.
     *
     * @return an array containing all provided annotations
     */
    Class<? extends Annotation> getSupportedAnnotation();


    /**
     * Checks constraint.
     *
     * @param annotatedElement The annotated element
     * @param annotationMirrorToCheck the annotation which has to be validated
     * @param constraintAnnotationMirror the constraint annotation used for checking
     * @param constraintAttributeName the name of the annotation attribute with constraint or null if it is placed on annotation type
     */
    void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String constraintAttributeName);

}
