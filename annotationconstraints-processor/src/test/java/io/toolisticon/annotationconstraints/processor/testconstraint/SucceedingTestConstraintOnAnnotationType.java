package io.toolisticon.annotationconstraints.processor.testconstraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestConstraintAnnotation(shouldCompilationSucceed = true)
public @interface SucceedingTestConstraintOnAnnotationType {
}
