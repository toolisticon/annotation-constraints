package io.toolisticon.annotationconstraints.baseconstraints.testhelper.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.baseconstraints.AnnotatedTypeMustBe;
import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@On({On.Location.CLASS})
@AnnotatedTypeMustBe(value = Serializable.class, comparisionKind = AnnotatedTypeMustBe.ComparisionKind.ASSIGNABLE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotatedTypeMustBeTestAnnotationOnClass {
}
