package io.toolisticon.annotationconstraints.baseconstraints.impl.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.AnnotatedTypeMustBe;
import io.toolisticon.annotationconstraints.baseconstraints.ConstraintsTarget;
import io.toolisticon.annotationconstraints.baseconstraints.impl.BaseConstraintMessages;
import io.toolisticon.annotationconstraints.baseconstraints.impl.UtilityFunctions;
import io.toolisticon.aptk.tools.AnnotationValueUtils;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.TypeMirrorWrapper;
import io.toolisticon.aptk.tools.wrapper.ElementWrapper;
import io.toolisticon.spiap.api.SpiService;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;

@SpiService(value = AnnotationConstraintSpi.class)
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

        TypeMirrorWrapper targetClassTypeMirror = TypeMirrorWrapper.wrap(AnnotationValueUtils.getClassValueAsFQN(annotationValue));
        AnnotatedTypeMustBe.ComparisionKind comparisonKind = AnnotationValueUtils.getEnumValue(AnnotatedTypeMustBe.ComparisionKind.class, comparisonKindAnnotationValue);
        ConstraintsTarget constraintsTarget = AnnotationValueUtils.getEnumValue(ConstraintsTarget.class, constraintTarget);

        ElementWrapper<?> targetElement = ElementWrapper.wrap(constraintsTarget.resolve(annotationMirrorToCheck, annotatedElement));

        if (
                !(
                        (targetElement.isAnnotationAttribute()
                                || targetElement.isMethodParameter()
                                || targetElement.isConstructorParameter()
                                || targetElement.isField()
                                || targetElement.isClass()
                                || targetElement.isInterface()
                        ) && compare(comparisonKind, targetElement.unwrap().asType(), targetClassTypeMirror.unwrap())
                ) && !(
                        (targetElement.isMethod()
                                && compare(comparisonKind, ElementUtils.CastElement.castMethod(targetElement.unwrap()).getReturnType(), targetClassTypeMirror.unwrap())
                        )
                )
        ) {
            if (constraintsTarget != ConstraintsTarget.ANNOTATED_ELEMENT) {
                MessagerUtils.error(annotatedElement, annotationMirrorToCheck, AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ENCLOSING_ELEMENT, constraintsTarget.name(), annotationMirrorToCheck.getAnnotationType().toString(), comparisonKind.name(), targetClassTypeMirror.toString());
            } else {
                MessagerUtils.error(annotatedElement, annotationMirrorToCheck, AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ANNOTATED_ELEMENT, annotationMirrorToCheck.getAnnotationType().toString(), comparisonKind.name(), targetClassTypeMirror.toString());
            }
        }

    }

    boolean compare(AnnotatedTypeMustBe.ComparisionKind kind, TypeMirror annotatedTypeMirror, TypeMirror targetTypeMirror) {
        return kind.evaluate(annotatedTypeMirror, targetTypeMirror);
    }
}
