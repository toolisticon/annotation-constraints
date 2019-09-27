package io.toolisticon.annotationconstraints.processor.testhelper;

import io.toolisticon.annotationconstraints.baseconstraints.MinLength;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    @MinLength
    String value();

}
