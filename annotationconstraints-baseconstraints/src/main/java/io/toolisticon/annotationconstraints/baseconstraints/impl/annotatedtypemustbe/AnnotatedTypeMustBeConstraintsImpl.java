package io.toolisticon.annotationconstraints.baseconstraints.impl.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.AnnotatedTypeMustBe;
import io.toolisticon.annotationconstraints.baseconstraints.ConstraintsTarget;
import io.toolisticon.annotationconstraints.baseconstraints.impl.BaseConstraintMessages;
import io.toolisticon.annotationconstraints.baseconstraints.impl.UtilityFunctions;
import io.toolisticon.annotationprocessortoolkit.tools.AnnotationValueUtils;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.spiap.api.Service;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;

@Service(value = AnnotationConstraintSpi.class)
public class AnnotatedTypeMustBeConstraintsImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return AnnotatedTypeMustBe.class;
    }


    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {

        if (!UtilityFunctions.isPlacedOnAnnotationType(annotatedElement)) {
            MessagerUtils.error(annotatedElement, constraintAnnotationMirror, BaseConstraintMessages.ERROR_MUST_BE_PLACE_ON_ANNOTATION_TYPE);
        }

    }


    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {


        AnnotationValue annotationValue = UtilityFunctions.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "value");
        AnnotationValue comparisonKindAnnotationValue = UtilityFunctions.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "comparisionKind");
        AnnotationValue constraintTarget = UtilityFunctions.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "constraintTarget");

        TypeMirror targetClassTypeMirror = AnnotationValueUtils.getClassValue(annotationValue);
        AnnotatedTypeMustBe.ComparisionKind comparisionKind = AnnotationValueUtils.getEnumValue(AnnotatedTypeMustBe.ComparisionKind.class, comparisonKindAnnotationValue);
        ConstraintsTarget constraintsTarget = AnnotationValueUtils.getEnumValue(ConstraintsTarget.class, constraintTarget);

        Element targetElement = constraintsTarget.resolve(annotationMirrorToCheck, annotatedElement);

        if (
                !(
                        (ElementUtils.CheckKindOfElement.isAnnotationAttribute(targetElement)
                                || ElementUtils.CheckKindOfElement.isMethodParameter(targetElement)
                                || ElementUtils.CheckKindOfElement.isConstructorParameter(targetElement)
                                || ElementUtils.CheckKindOfElement.isField(targetElement)
                                || ElementUtils.CheckKindOfElement.isClass(targetElement)
                                || ElementUtils.CheckKindOfElement.isInterface(targetElement)
                        ) && compare(comparisionKind, targetElement.asType(), targetClassTypeMirror)
                ) && !(
                        (ElementUtils.CheckKindOfElement.isMethod(targetElement)
                                && compare(comparisionKind, ElementUtils.CastElement.castMethod(targetElement).getReturnType(), targetClassTypeMirror)
                        )
                )
        ) {
            if (constraintsTarget != ConstraintsTarget.ANNOTATED_ELEMENT) {
                MessagerUtils.error(annotatedElement, annotationMirrorToCheck, AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ENCLOSING_ELEMENT, constraintsTarget.name(), annotationMirrorToCheck.getAnnotationType().toString(), comparisionKind.name(), targetClassTypeMirror.toString());
            } else {
                MessagerUtils.error(annotatedElement, annotationMirrorToCheck, AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ANNOTATED_ELEMENT, annotationMirrorToCheck.getAnnotationType().toString(), comparisionKind.name(), targetClassTypeMirror.toString());
            }
        }

    }

    boolean compare(AnnotatedTypeMustBe.ComparisionKind kind, TypeMirror annotatedTypeMirror, TypeMirror targetTypeMirror) {
        return kind.evaluate(annotatedTypeMirror, targetTypeMirror);
    }
}
