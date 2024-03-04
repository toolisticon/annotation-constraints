package io.toolisticon.annotationconstraints.processor.testconstraint;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.CoreMatchers;
import io.toolisticon.aptk.tools.fluentfilter.FluentElementFilter;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.List;

public class TestConstraintServiceImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return TestConstraintAnnotation.class;
    }

    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {
        if (!(ElementUtils.CheckKindOfElement.isAnnotation(annotatedElement) || ElementUtils.CheckKindOfElement.isAnnotationAttribute(annotatedElement))) {
            MessagerUtils.error(annotatedElement, TestConstraintMessages.ERROR_LOCATION);
        }
    }

    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {

        if (attributeNameToBeCheckedByConstraint != null) {

            List<? extends Element> elements = FluentElementFilter.createFluentElementFilter(annotationMirrorToCheck.getAnnotationType().asElement().getEnclosedElements())
                    .applyFilter(CoreMatchers.BY_NAME).filterByOneOf(attributeNameToBeCheckedByConstraint)
                    .applyFilter(CoreMatchers.BY_ELEMENT_KIND).filterByOneOf(ElementKind.METHOD)
                    .getResult();

            if (elements != null && elements.size() == 1) {

                TestConstraintAnnotation testConstraintAnnotation = elements.get(0).getAnnotation(TestConstraintAnnotation.class);
                if (testConstraintAnnotation != null && !testConstraintAnnotation.shouldCompilationSucceed()) {
                    MessagerUtils.error(annotatedElement, TestConstraintMessages.ERROR_IN_PROCESSING_PHASE);
                    return;
                }

            } else {
                MessagerUtils.error(annotatedElement, TestConstraintMessages.WTF);

            }


        } else {

            TestConstraintAnnotation testConstraintAnnotation = annotationMirrorToCheck.getAnnotationType().asElement().getAnnotation(TestConstraintAnnotation.class);

            if (testConstraintAnnotation == null) {
                MessagerUtils.error(annotatedElement, TestConstraintMessages.WTF);
            } else if (!testConstraintAnnotation.shouldCompilationSucceed()) {
                MessagerUtils.error(annotatedElement, TestConstraintMessages.ERROR_IN_PROCESSING_PHASE);
            }

        }
    }
}
