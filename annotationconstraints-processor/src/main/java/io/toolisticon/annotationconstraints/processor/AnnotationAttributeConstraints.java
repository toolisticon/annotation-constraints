package io.toolisticon.annotationconstraints.processor;

import javax.lang.model.element.AnnotationMirror;
import java.util.ArrayList;
import java.util.List;

class AnnotationAttributeConstraints {

    private final String attributeName;
    private final List<AnnotationMirror> constraints;

    AnnotationAttributeConstraints(String attributeNumber,List<AnnotationMirror> constraints) {
        this.attributeName = attributeNumber;
        this.constraints = constraints;
    }

    String getAttributeName() {
        return attributeName;
    }

    List<AnnotationMirror> getConstraints() {
        return constraints;
    }
}
