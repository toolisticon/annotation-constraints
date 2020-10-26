package io.toolisticon.annotationconstraints.baseconstraints.impl.name;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.ComparisonMethod;
import io.toolisticon.annotationconstraints.baseconstraints.ConstraintsTarget;
import io.toolisticon.annotationconstraints.baseconstraints.NameMust;
import io.toolisticon.annotationprocessortoolkit.tools.AnnotationUtils;
import io.toolisticon.annotationprocessortoolkit.tools.AnnotationValueUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.spiap.api.Service;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

@Service(value = AnnotationConstraintSpi.class)
public class NameMustConstraintsImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return NameMust.class;
    }


    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {


        // test for regular expression
        ComparisonMethod comparisonMethod = getEnumValue(constraintAnnotationMirror, "comparisonMethod", ComparisonMethod.class);


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

        // get constraint annotation values
        NameMust.NameKind nameKind = getEnumValue(constraintAnnotationMirror, "nameKind", NameMust.NameKind.class);
        ConstraintsTarget target = getEnumValue(constraintAnnotationMirror, "constraintTarget", ConstraintsTarget.class);
        ComparisonMethod comparisonMethod = getEnumValue(constraintAnnotationMirror, "comparisonMethod", ComparisonMethod.class);
        String nameString = AnnotationValueUtils.getStringValue(AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(constraintAnnotationMirror));

        // get the target elements name
        String targetElementsName = target.getNameOfResolvedElement(annotationMirrorToCheck, annotatedElement, nameKind);

        // Now do comparison
        switch (comparisonMethod) {

            case EQUAL: {
                if (!nameString.equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL, target, nameString);
                }
                break;
            }
            case NOT_EQUAL: {
                if (nameString.equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_NOT_BE_EQUAL, target, nameString);
                }
                break;
            }

            case MATCH: {
                if (!Pattern.matches(nameString, targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH, target, targetElementsName, nameString);
                }
                break;
            }
            case NOT_MATCH: {
                if (Pattern.matches(nameString, targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH, target, targetElementsName, nameString);
                }
                break;
            }


        }


    }

    private <T extends Enum<T>> T getEnumValue(AnnotationMirror annotationMirror, String attributeName, Class<T> enumClass) {
        return AnnotationValueUtils.getEnumValue(enumClass, AnnotationUtils.getAnnotationValueOfAttributeWithDefaults(annotationMirror, attributeName));
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
