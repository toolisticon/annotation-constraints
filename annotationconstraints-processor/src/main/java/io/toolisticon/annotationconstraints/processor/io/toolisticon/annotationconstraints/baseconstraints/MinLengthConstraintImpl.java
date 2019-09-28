package io.toolisticon.annotationconstraints.processor.io.toolisticon.annotationconstraints.baseconstraints;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.baseconstraints.MinLength;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.TypeUtils;
import io.toolisticon.spiap.api.Service;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import java.lang.annotation.Annotation;

@Service(value = AnnotationConstraintSpi.class)
public class MinLengthConstraintImpl implements AnnotationConstraintSpi {

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return MinLength.class;
    }

    
    
    @Override
	public void checkCorrectUsage(Element annotatedElement, AnnotationMirror constraintAnnotationMirror) {
		
    	if(!UtilityFunctions.isPlacedOnAnnotationAttribute(annotatedElement)) {
    		MessagerUtils.error(annotatedElement, constraintAnnotationMirror, BaseConstraintMessages.ERROR_MUST_BE_PLACE_ON_ANNOTATION_ATTRIBUTE);
    	}
    	
    	ExecutableElement annotationAttributeElement = ElementUtils.CastElement.castMethod(annotatedElement);
    	if(TypeUtils.CheckTypeKind.isPrimitive(annotationAttributeElement.getReturnType()) 
    			|| !TypeUtils.TypeRetrieval.getTypeElement(annotationAttributeElement.getReturnType()).getQualifiedName().toString().contentEquals(String.class.getCanonicalName())){
    		MessagerUtils.error(annotatedElement, MinLengthConstraintMessages.ERROR_ATTRIBUTE_IS_NOT_OF_TYPE_STRING);
    	}
    	
		
	}



	@Override
    public void checkConstraints(Element annotatedElement, AnnotationMirror annotationMirrorToCheck, AnnotationMirror constraintAnnotationMirror, String constraintAttributeName) {

        AnnotationValue annotationValue = UtilityFunctions.getAnnotationValueOfAttribute(annotationMirrorToCheck, constraintAttributeName);

        MinLength minLengthAnnotation = UtilityFunctions.getConstraintAnnotationOfAnnotationAttribute(annotationMirrorToCheck, constraintAttributeName, MinLength.class);

        try {
            String value = (String) annotationValue.getValue();

            if (value.length() < minLengthAnnotation.value()) {
                MessagerUtils.error(annotatedElement, annotationMirrorToCheck, MinLengthConstraintMessages.ERROR_STRING_IS_TOO_SHORT, minLengthAnnotation.value());
            }
        } catch (ClassCastException e) {
            MessagerUtils.error(annotatedElement, annotationMirrorToCheck, MinLengthConstraintMessages.ERROR_ATTRIBUTE_IS_NOT_OF_TYPE_STRING);
        }

    }
}
