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

        /**
        // test for regular expression
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
*/
    }


    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {

        /*-
        NameMust nameMustAnnotation = annotatedElement.getAnnotation(NameMust.class);

        // First get target elements name
        ConstraintsTarget target = nameMustAnnotation.constraintTarget();

        String targetElementsName = target.getNameOfResolvedElement(annotationMirrorToCheck, annotatedElement);

        // Now do comparison
        switch (nameMustAnnotation.comparisonMethod()) {

            case MATCH: {
                if (nameMustAnnotation.value().equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL, target, nameMustAnnotation.value());
                }
                break;
            }
            case NOT_MATCH: {
                if (nameMustAnnotation.value().equals(targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL, target, nameMustAnnotation.value());
                }
                break;
            }

            case EQUAL: {

                if (Pattern.matches(nameMustAnnotation.value(), targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH, target, targetElementsName, nameMustAnnotation.value());
                }
                break;
            }
            case NOT_EQUAL: {
                if (Pattern.matches(nameMustAnnotation.value(), targetElementsName)) {
                    MessagerUtils.error(annotatedElement, annotationMirrorToCheck, NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_MATCH, target, targetElementsName, nameMustAnnotation.value());
                }
                break;
            }


        }


         */

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
