package io.toolisticon.annotationconstraints.baseconstraints.impl.name;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.ComparisonMethod;
import io.toolisticon.annotationconstraints.baseconstraints.ConstraintsTarget;
import io.toolisticon.annotationconstraints.baseconstraints.NameMust;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.AnnotationValueUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.spiap.api.SpiService;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

@SpiService(value = AnnotationConstraintSpi.class)
public class NameConstraintImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return NameMust.class;
    }


    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {

        // test for regular expressions
        AnnotationValue comparisonMethodAnnotationValue = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "comparisonMethod");
        ComparisonMethod comparisonMethod = AnnotationValueUtils.getEnumValue(ComparisonMethod.class, comparisonMethodAnnotationValue);

        AnnotationValue valueAnnotationValue = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror);
        String value = AnnotationValueUtils.getStringValue(valueAnnotationValue);


        switch (comparisonMethod) {
            case EQUAL:
            case NOT_EQUAL: {

                if (value.isEmpty()) {
                    MessagerUtils.error(annotatedElement, constraintAnnotationMirror, valueAnnotationValue, NameConstraintMessages.ERROR_EQUALS_VALUE_MUST_NOT_BE_EMPTY);
                }
                break;

            }
            case MATCH:
            case NOT_MATCH: {

                createPattern(annotatedElement, constraintAnnotationMirror, valueAnnotationValue, value);
                break;

            }

        }


    }


    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {

        // get target
        AnnotationValue targetAnnotationValue = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "constraintTarget");
        ConstraintsTarget target = AnnotationValueUtils.getEnumValue(ConstraintsTarget.class, targetAnnotationValue);

        // First get target elements name
        AnnotationValue nameKindAnnotationValue = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "nameKind");
        NameMust.NameKind nameKind = AnnotationValueUtils.getEnumValue(NameMust.NameKind.class, nameKindAnnotationValue);

        String targetElementsName = target.getNameOfResolvedElement(annotationMirrorToCheck, annotatedElement, nameKind);

        // Now do comparison
        AnnotationValue comparisonMethodAnnotationValue = AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror, "comparisonMethod");
        ComparisonMethod comparisonMethod = AnnotationValueUtils.getEnumValue(ComparisonMethod.class, comparisonMethodAnnotationValue);


        // get value
        AnnotationValue valueAnnotationValue = AnnotationUtils.getAnnotationValueOfAttribute(constraintAnnotationMirror, "value");
        String value = AnnotationValueUtils.getStringValue(valueAnnotationValue);


        switch (comparisonMethod) {

            case EQUAL: {
                if (!value.equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL, target, targetElementsName, value);
                }
                break;
            }
            case NOT_EQUAL: {
                if (value.equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_NOT_BE_EQUAL, target, targetElementsName, value);
                }
                break;
            }

            case MATCH: {

                if (!Pattern.matches(value, targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH, target, targetElementsName, value);
                }
                break;
            }
            case NOT_MATCH: {
                if (Pattern.matches(value, targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_NOT_MATCH, target, targetElementsName, value);
                }
                break;
            }


        }


    }


    private Pattern createPattern(Element annotatedElement, AnnotationMirror constraintAnnotationMirror, AnnotationValue valueAnnotationValue, String patternString) {

        Pattern pattern = null;
        try {
            pattern = Pattern.compile(patternString);
        } catch (Exception e) {
            MessagerUtils.error(annotatedElement, constraintAnnotationMirror, valueAnnotationValue, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_VALID, patternString, e.getMessage());
        }
        return pattern;

    }

}
