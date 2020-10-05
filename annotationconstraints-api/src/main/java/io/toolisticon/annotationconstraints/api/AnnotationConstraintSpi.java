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
     * Get the annotation supported by this spi implementation.
     *
     * @return the supported annotation
     */
    Class<? extends Annotation> getSupportedAnnotation();


    /**
     * Check if annotation is used correctly. 
     * @param annotatedElement the annotated element
     * @param constraintAnnotationMirror the AnnotationMirror representing the constraint annotation
     */
    void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror);
    
    
    /**
     * Checks constraint.
     *
     * @param annotatedElement The annotated element
     * @param annotationMirrorToCheck the annotation which has to be validated
     * @param constraintAnnotationMirror the constraint annotation used for checking
     * @param attributeNameToBeCheckedByConstraint the name of the annotation attribute with constraint or null if it is placed on annotation type
     */
    void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint);

}
