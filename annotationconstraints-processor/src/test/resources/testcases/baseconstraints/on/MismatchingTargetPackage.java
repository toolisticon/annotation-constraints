package io.toolisticon.annotationconstraints.processor.test;

import io.toolisticon.annotationconstraints.baseconstraints.On;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@On(On.Location.PACKAGE)
public @interface MismatchingTargetPackage {


}
