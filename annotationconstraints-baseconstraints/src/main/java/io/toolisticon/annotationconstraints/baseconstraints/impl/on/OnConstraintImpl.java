package io.toolisticon.annotationconstraints.baseconstraints.impl.on;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.On;
import io.toolisticon.annotationconstraints.baseconstraints.impl.BaseConstraintMessages;
import io.toolisticon.annotationconstraints.baseconstraints.impl.UtilityFunctions;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.spiap.api.SpiService;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@SpiService(value = AnnotationConstraintSpi.class)
public class OnConstraintImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return On.class;
    }

    @Override
    public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {

        if (!UtilityFunctions.isPlacedOnAnnotationType(annotatedElement)) {
            MessagerUtils.error(annotatedElement, constraintAnnotationMirror, BaseConstraintMessages.ERROR_MUST_BE_PLACE_ON_ANNOTATION_TYPE);
        }

        // Now check if annotation value matches
        Target target = annotatedElement.getAnnotation(Target.class);
        On onAnnotation = annotatedElement.getAnnotation(On.class);

        if (target != null) {

            for (On.Location location : onAnnotation.value()) {

                boolean foundLocation = false;

                for (ElementType elementType : target.value()) {
                    switch (location) {
                        case CLASS:
                        case INTERFACE:
                        case ENUM: {
                            if (ElementType.TYPE.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case ANNOTATION: {
                            if (ElementType.TYPE.equals(elementType) || ElementType.ANNOTATION_TYPE.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case METHOD:
                        case ANNOTATION_ATTRIBUTE: {
                            if (ElementType.METHOD.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case CONSTRUCTOR: {
                            if (ElementType.CONSTRUCTOR.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case FIELD: {
                            if (ElementType.FIELD.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case PACKAGE: {
                            if (ElementType.PACKAGE.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }
                        case PARAMETER:
                        case CONSTRUCTOR_PARAMETER:
                        case METHOD_PARAMETER: {
                            if (ElementType.PARAMETER.equals(elementType)) {
                                foundLocation = true;
                            }
                            break;
                        }

                    }

                    // skip searching if match has been found
                    if (foundLocation) {
                        break;
                    }
                }

                if (!foundLocation) {
                    MessagerUtils.error(annotatedElement, constraintAnnotationMirror, OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND, location.toString());
                }

            }

        } else {
            MessagerUtils.error(annotatedElement, OnConstraintMessages.ERROR_TARGET_ANNOTATION_NOT_FOUND);
        }


    }


    @Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String attributeNameToBeCheckedByConstraint) {


        // Now check if annotation
        On onAnnotation = UtilityFunctions.getConstraintAnnotationOfAnnotation(annotationMirrorToCheck, On.class);

        boolean foundMatchingElementType = false;
        for (On.Location location : onAnnotation.value()) {

            switch (location) {
                case ANNOTATION_ATTRIBUTE: {
                    // must be placed on method of annotation type
                    if (ElementUtils.CheckKindOfElement.isMethod(annotatedElement) && ElementUtils.CheckKindOfElement.isAnnotation(annotatedElement.getEnclosingElement())) {
                        foundMatchingElementType = true;
                    }
                    break;
                }
                case PACKAGE: {
                    // must be placed on Package
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isPackage(annotatedElement);
                    break;
                }
                case ANNOTATION: {
                    // must be placed on Annotation Type
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isAnnotation(annotatedElement);
                    break;
                }
                case CLASS: {
                    // must be placed on Type(Class)
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isClass(annotatedElement);
                    break;
                }
                case INTERFACE: {
                    // must be placed on Interface
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isInterface(annotatedElement);
                    break;
                }
                case ENUM: {
                    // must be placed on Enum
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isEnum(annotatedElement);
                    break;
                }
                case CONSTRUCTOR: {
                    // must be placed on constructor
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isConstructor(annotatedElement);
                    break;
                }
                case METHOD: {
                    // must be placed on method, but not on annotation attribute
                    if (ElementUtils.CheckKindOfElement.isMethod(annotatedElement) && !ElementUtils.CheckKindOfElement.isAnnotation(annotatedElement.getEnclosingElement())) {
                        foundMatchingElementType = true;
                    }
                    break;
                }
                case FIELD: {
                    // must be placed on field
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isField(annotatedElement);
                    break;
                }
                case PARAMETER: {
                    // must be placed on Parameter
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isParameter(annotatedElement);
                    break;
                }
                case METHOD_PARAMETER: {
                    // must be placed on Parameter
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isParameter(annotatedElement) && ElementUtils.CheckKindOfElement.isMethod(annotatedElement.getEnclosingElement());
                    break;
                }
                case CONSTRUCTOR_PARAMETER: {
                    // must be placed on Parameter
                    foundMatchingElementType = ElementUtils.CheckKindOfElement.isParameter(annotatedElement) && ElementUtils.CheckKindOfElement.isConstructor(annotatedElement.getEnclosingElement());
                    break;
                }
            }
            // skip search if matching ElementType has been detected
            if (foundMatchingElementType) {
                break;
            }

        }

        // trigger error message if matching type hasn't been found
        if (!foundMatchingElementType) {
            MessagerUtils.error(annotatedElement, annotationMirrorToCheck, OnConstraintMessages.ERROR_WRONG_USAGE, UtilityFunctions.getSimpleName(constraintAnnotationMirror), UtilityFunctions.getSimpleName(annotationMirrorToCheck), onAnnotation.value());
        }


    }
}