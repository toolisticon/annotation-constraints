package io.toolisticon.annotationconstraints.processor;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

class AnnotationConstraints {

    private final TypeElement annotationTypeElement;
    private final List<AnnotationMirror> constraintsOnAnnotationType = new ArrayList<AnnotationMirror>();
    private final List<AnnotationAttributeConstraints> constraintsOnAnnotationAttributes = new ArrayList<AnnotationAttributeConstraints>();

    AnnotationConstraints(TypeElement annotationTypeElement) {

        this.annotationTypeElement = annotationTypeElement;

    }

    void addConstraintOnType(AnnotationMirror annotationMirror) {
        if (annotationMirror != null) {
            constraintsOnAnnotationType.add(annotationMirror);
        }
    }

    void addConstraintOnType(AnnotationAttributeConstraints annotationAttributeConstraints) {
        if (annotationAttributeConstraints != null) {
            constraintsOnAnnotationAttributes.add(annotationAttributeConstraints);
        }
    }

    public TypeElement getAnnotationTypeElement() {
        return annotationTypeElement;
    }

    public List<AnnotationMirror> getConstraintsOnAnnotationType() {
        return constraintsOnAnnotationType;
    }

    public List<AnnotationAttributeConstraints> getConstraintsOnAnnotationAttributes() {
        return constraintsOnAnnotationAttributes;
    }

    public boolean hasConstraints () {
        return constraintsOnAnnotationType.size() > 0 || constraintsOnAnnotationAttributes.size() > 0;
    }
}
