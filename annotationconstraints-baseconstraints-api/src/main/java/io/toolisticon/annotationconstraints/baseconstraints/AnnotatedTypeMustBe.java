package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.Constraint;
import io.toolisticon.annotationprocessortoolkit.tools.TypeUtils;

import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Constraint annotation to restrict annotation to be placed on field, method parameter or annotation Attribute with a specific type.
 * Or that on method with a specific return value type.
 */

@Documented
@Constraint
@Target(ElementType.ANNOTATION_TYPE)
@On(On.Location.ANNOTATION)
@Retention(RetentionPolicy.RUNTIME)

public @interface AnnotatedTypeMustBe {

    public enum ComparisionKind {
        EQUAL {
            public boolean evaluate(TypeMirror annotatedTypeMirror, TypeMirror targetTypeMirror) {
                return TypeUtils.TypeComparison.isTypeEqual(annotatedTypeMirror, targetTypeMirror);
            }
        },
        ASSIGNABLE {
            public boolean evaluate(TypeMirror annotatedTypeMirror, TypeMirror targetTypeMirror) {
                return TypeUtils.TypeComparison.isAssignableTo(annotatedTypeMirror, targetTypeMirror);
            }
        };


        public abstract boolean evaluate(TypeMirror annotatedTypeMirror, TypeMirror targetTypeMirror);

    }

    Class value();

    ComparisionKind comparisionKind() default ComparisionKind.EQUAL;

    ConstraintsTarget constraintTarget() default ConstraintsTarget.ANNOTATED_ELEMENT;

}
