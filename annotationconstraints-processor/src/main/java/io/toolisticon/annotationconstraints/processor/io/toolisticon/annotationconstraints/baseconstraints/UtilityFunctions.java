package io.toolisticon.annotationconstraints.processor.io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationprocessortoolkit.tools.AnnotationUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import java.lang.annotation.Annotation;

public final class UtilityFunctions {

    /**
     * Hidden.
     */
    private UtilityFunctions () {

    }

    public static AnnotationValue getAnnotationValueOfAttribute(AnnotationMirror annotationMirrorToCheck, String constraintAttributeName) {
        return AnnotationUtils.getAnnotationValueOfAttribute(annotationMirrorToCheck, constraintAttributeName);
    }

    public static <T extends Annotation> T getConstraintAnnotationOfAnnotationAttribute (AnnotationMirror annotationMirrorToCheck, String constraintAttributeName, Class<T> constraintAnnotationType) {
        T constraintAnnotation = null;
        for (ExecutableElement executableElement : annotationMirrorToCheck.getElementValues().keySet()) {

            if (executableElement.getSimpleName().toString().equals(constraintAttributeName)) {
                constraintAnnotation = executableElement.getAnnotation(constraintAnnotationType);
                break;
            }

        }
        return constraintAnnotation;
    }

}
