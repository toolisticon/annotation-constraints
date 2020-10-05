package io.toolisticon.annotationconstraints.baseconstraints.testhelper.namemust;

import io.toolisticon.annotationconstraints.baseconstraints.ComparisonMethod;
import io.toolisticon.annotationconstraints.baseconstraints.ConstraintsTarget;
import io.toolisticon.annotationconstraints.baseconstraints.NameMust;
import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@On(On.Location.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@NameMust(comparisonMethod = ComparisonMethod.EQUAL, nameKind = NameMust.NameKind.FULL_QUALIFIED, constraintTarget = ConstraintsTarget.ENCLOSING_PACKAGE, value = "io.toolisticon.annotationconstraints.baseconstraints")

public @interface NameMustBeEqualAnnotation {


}
