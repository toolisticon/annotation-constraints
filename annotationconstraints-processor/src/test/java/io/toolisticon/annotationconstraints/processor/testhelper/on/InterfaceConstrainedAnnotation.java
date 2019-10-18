package io.toolisticon.annotationconstraints.processor.testhelper.on;

import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@On(On.Location.INTERFACE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceConstrainedAnnotation {
}
