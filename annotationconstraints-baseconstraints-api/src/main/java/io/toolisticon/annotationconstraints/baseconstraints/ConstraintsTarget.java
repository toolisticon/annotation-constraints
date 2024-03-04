package io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.MessagerUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.QualifiedNameable;

/**
 * Defines the target on which the annotation constraint should be applied to.
 */
public enum ConstraintsTarget {

    /**
     * The annotated element itself. This should be default for most constraint annotations.
     */
    ANNOTATED_ELEMENT(),

    /**
     * The enclosing package of the annotated element;
     */
    ENCLOSING_PACKAGE(ElementKind.PACKAGE),

    /**
     * The enclosing type of the annotated element - might be an interface, a class or an enum.
     */
    ENCLOSING_TYPE(ElementKind.CLASS, ElementKind.INTERFACE, ElementKind.ENUM),

    /**
     * The enclosing class.
     */
    ENCLOSING_CLASS(ElementKind.CLASS),

    /**
     * The enclosing interface.
     */
    ENCLOSING_INTERFACE(ElementKind.INTERFACE),

    /**
     * The enclosing enum.
     */
    ENCLOSING_ENUM(),

    /**
     * Enclosing Method
     */
    ENCLOSING_METHOD(ElementKind.METHOD),

    /**
     * Enclosing Method
     */
    ENCLOSING_CONSTRUCTOR(ElementKind.CONSTRUCTOR),

    /**
     * The enclosing method or constructor of the annotated element.
     */
    ENCLOSING_METHOD_OR_CONSTRUCTOR(ElementKind.METHOD, ElementKind.CONSTRUCTOR),


    /**
     * The enclosing annotation of the annotated element.
     */
    ENCLOSING_ANNOTATION;

    /**
     * Holds a string representation off passed elementKinds
     */
    private final String errorMessageText;

    /**
     * The Element Kinds to check for.
     */
    private final ElementKind[] elementKinds;

    ConstraintsTarget(ElementKind... elementKinds) {
        this.errorMessageText = createErrorString(elementKinds);
        this.elementKinds = elementKinds;
    }

    /**
     * Returns the simple name for most ElementKinds.
     * Returns full qualified name for PackageElements.
     *
     * @param annotationMirror The annotation mirror which is used for writing the error message
     * @param element          the annotated element
     * @return the name of the enclosing element, or null if enclosing element couldn't be found.
     */
    public String getNameOfResolvedElement(AnnotationMirror annotationMirror, Element element, NameMust.NameKind nameKind) {
        Element resolvedElement = resolve(annotationMirror, element);


        if (resolvedElement != null) {
            if (nameKind == NameMust.NameKind.SIMPLE) {
                return resolvedElement.getSimpleName().toString();
            } else {

                if (resolvedElement instanceof QualifiedNameable) {
                    return ((QualifiedNameable) resolvedElement).getQualifiedName().toString();
                } else {
                    return resolvedElement.getSimpleName().toString();
                }

            }
        }

        return null;
    }

    /**
     * Resolves enclosing element of a specific kind.
     *
     * @param annotationMirror The annotation mirror which is used for writing the error message
     * @param element          the annotated element
     * @return the enclosing element, or null if element couldn't be found.
     */
    public Element resolve(AnnotationMirror annotationMirror, Element element) {

        // handle ANNOTATED_ELEMENT as special case
        Element result = (ANNOTATED_ELEMENT == this) ? element : ElementUtils.AccessEnclosingElements.getFirstEnclosingElementOfKind(element, this.elementKinds);

        if (result == null) {
            triggerErrorMessage(annotationMirror, element, this);
        }

        return result;
    }

    /**
     * Creates a string representation of passed ElementKinds
     *
     * @param elementKinds the ElementKinds
     * @return the string representation of passed
     */
    private static String createErrorString(ElementKind[] elementKinds) {

        StringBuilder stringBuilder = new StringBuilder("[");

        boolean first = true;
        for (ElementKind elementKind : elementKinds) {
            if (first) {
                first = false;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(elementKind.name());
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /**
     * Method for triggering an error message.
     *
     * @param annotationMirror  the annotation mirror
     * @param element           the element
     * @param constraintsTarget the constraints target kind
     */
    private static void triggerErrorMessage(AnnotationMirror annotationMirror, Element element, ConstraintsTarget constraintsTarget) {
        MessagerUtils.error(element, annotationMirror, "Annotated element must be enclosed by ${0}", constraintsTarget.errorMessageText);
    }

}
