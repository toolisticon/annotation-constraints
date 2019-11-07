package io.toolisticon.annotationconstraints.processor.testconstraint;

import io.toolisticon.annotationconstraints.api.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Constraint
public @interface TestConstraintAnnotation {
    boolean shouldCompilationSucceed();
}
