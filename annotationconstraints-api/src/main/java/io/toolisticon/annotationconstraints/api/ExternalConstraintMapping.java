package io.toolisticon.annotationconstraints.api;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be used to externally map a constraint to an existing annotation without the need to change the annotation.
 * This allows writing of constraints for 3rd party libraries.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
@Documented
public @interface ExternalConstraintMapping {



    /**
     * target annotation
     */
    Class<? extends Annotation> value();
}
