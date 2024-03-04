package io.toolisticon.annotationconstraints.beanvalidation.impl.decimalmax;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.AnnotationValueUtils;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.validation.constraints.DecimalMax;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;

/*
CharSequence
byte, short, int, long, and their respective wrappers
 */
public class DecimalMaxImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return DecimalMax.class;
    }

    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {

        if(!ElementUtils.CheckKindOfElement.isAnnotationAttribute(annotatedElement)) {
            MessagerUtils.error(annotatedElement,DecimalMaxMessages.ERROR_MUST_BE_USED_ON_CORRECT_TYPE);
        }

        ExecutableElement  annotationAttributeElement = ElementUtils.CastElement.castToExecutableElement(annotatedElement);
        TypeMirror returnType = annotationAttributeElement.getReturnType();
        //if (TypeUtils.TypeComparison.isAssignableTo(TypeUtils.TypeRetrieval.getTypeElement(Integer.class))) {

        //}

    }


    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {

        // get constraint value
        String maxValue = AnnotationValueUtils.getStringValue(AnnotationUtils.getAnnotationValueOfAttribute(constraintAnnotationMirror));
        boolean isInclusive = AnnotationValueUtils.getBooleanValue(AnnotationUtils.getAnnotationValueOfAttribute(constraintAnnotationMirror, "inclusive"));

        BigDecimal constraint = new BigDecimal(maxValue);

        // get annotation attribute value to check
        AnnotationValue annotationValue = AnnotationUtils.getAnnotationValueOfAttribute(annotationMirrorToCheck, attributeNameToBeCheckedByConstraint);

        if(annotationValue != null) {

            BigDecimal value = null;

            // String
            if (AnnotationValueUtils.isString(annotationValue)) {
                value = new BigDecimal(AnnotationValueUtils.getStringValue(annotationValue));
            }

            // long
            if(AnnotationValueUtils.isLong(annotationValue)) {
                value =BigDecimal.valueOf(AnnotationValueUtils.getLongValue(annotationValue));
            }

            // integer
            if(AnnotationValueUtils.isInteger(annotationValue)) {
                value =BigDecimal.valueOf(AnnotationValueUtils.getIntegerValue(annotationValue));
            }


            // Do check
            int result = constraint.compareTo(value);

            if (result <= (!isInclusive ? 0 : -1)) {
                MessagerUtils.error(annotatedElement,DecimalMaxMessages.ERROR_MUST_BE_SMALLER_OR_EQUAL, isInclusive ? DecimalMaxMessages.TOKEN_LESS_OR_EQUAL : DecimalMaxMessages.TOKEN_LESS, maxValue);
            }

        }

    }
}