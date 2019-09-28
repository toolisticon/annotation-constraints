package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.api.AnnotationConstraintSpi;
import io.toolisticon.annotationconstraints.api.Constraint;
import io.toolisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.AnnotationUtils;
import io.toolisticon.annotationprocessortoolkit.tools.ElementUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.spiap.api.Service;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Annotation Processor for checking constraints on usage of annotations.
 * <p>
 * Implementations will be bound by a SPI.
 * <p>
 * Two kinds of constraints exist:
 * - On annotation type
 * - On annotation attributes
 */

@Service(Processor.class)
@SupportedAnnotationTypes("*")
public class ConstraintProcessor extends AbstractAnnotationProcessor {

    private Map<TypeElement, AnnotationConstraints> onAnnotationConstraintsLUT = new HashMap<TypeElement, AnnotationConstraints>();
    private Map<String, AnnotationConstraintSpi> annotationConstraintSpiMap = new HashMap<String, AnnotationConstraintSpi>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        // read spi implementations
        ServiceLoader<AnnotationConstraintSpi> services = ServiceLoader.load(AnnotationConstraintSpi.class, ConstraintProcessor.class.getClassLoader());

        for (AnnotationConstraintSpi annotationConstraintSpi : services) {
            annotationConstraintSpiMap.put(annotationConstraintSpi.getSupportedAnnotation().getCanonicalName(), annotationConstraintSpi);
        }

        System.out.println("!!!!!!!!!! FOUND " + annotationConstraintSpiMap.size() + " SERVICES ????????????");
    }

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    	// Check if processed annotations are constraints and used correctly
    	for (TypeElement annotationTypeElement : annotations) {
    		
    		// check if annotation is constraint
    		if (annotationTypeElement.getAnnotation(Constraint.class) != null) {
    			
    			AnnotationConstraintSpi service = annotationConstraintSpiMap.get(annotationTypeElement.getQualifiedName().toString());
    			
    			for (Element element : roundEnv.getElementsAnnotatedWith(annotationTypeElement)) {
    				service.checkCorrectUsage(element, AnnotationUtils.getAnnotationMirror(annotationTypeElement, annotationTypeElement.getQualifiedName().toString()));
    			}
    					
    					
    		}
    		
    	}
    	
    	
        // process Services annotation
        for (TypeElement annotationTypeElement : annotations) {


            // check if annotation has been processed once before
            AnnotationConstraints annotationConstraints = onAnnotationConstraintsLUT.get(annotationTypeElement);

            if (annotationConstraints == null) {

                // annotation hasn't been processed before so determine constraints
                annotationConstraints = determineConstraints(annotationTypeElement);
                onAnnotationConstraintsLUT.put(annotationTypeElement, annotationConstraints);

            }


        }

        // Now process elements in round
        for (TypeElement annotation : annotations) {
            AnnotationConstraints annotationConstraints = onAnnotationConstraintsLUT.get(annotation);
            if (annotationConstraints.hasConstraints()) {
                for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {

                    AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(element, annotation.getQualifiedName().toString());


                    // Now need to apply constraints
                    // on type
                    for (AnnotationMirror constraintAnnotation : annotationConstraints.getConstraintsOnAnnotationType()) {

                        AnnotationConstraintSpi annotationConstraintSpi = annotationConstraintSpiMap.get(constraintAnnotation.getAnnotationType().toString());

                        if (annotationConstraintSpi != null) {
                            annotationConstraintSpi.checkConstraints(element, annotationMirror, constraintAnnotation, null);
                        } else {
                            MessagerUtils.printMessage(element, annotationMirror, Diagnostic.Kind.WARNING, ConstraintProcessorMessages.WARNING_CONSTRAINT_SPI_IMPLEMENTATION_NOT_FOUND, constraintAnnotation.getAnnotationType().toString(), annotation.getQualifiedName().toString());
                        }
                    }

                    // on annotation attribute
                    for (AnnotationAttributeConstraints annotationAttributeConstraints : annotationConstraints.getConstraintsOnAnnotationAttributes()) {

                        // check all constraints on attribute
                        for (AnnotationMirror constraintAnnotation : annotationAttributeConstraints.getConstraints()) {

                            AnnotationConstraintSpi annotationConstraintSpi = annotationConstraintSpiMap.get(((TypeElement)constraintAnnotation.getAnnotationType().asElement()).getQualifiedName().toString());

                            if (annotationConstraintSpi != null) {
                                annotationConstraintSpi.checkConstraints(element, annotationMirror, constraintAnnotation, annotationAttributeConstraints.getAttributeName());
                            } else {
                                MessagerUtils.printMessage(element, annotationMirror, Diagnostic.Kind.WARNING, ConstraintProcessorMessages.WARNING_CONSTRAINT_SPI_IMPLEMENTATION_NOT_FOUND, constraintAnnotation.getAnnotationType().toString(), annotation.getQualifiedName().toString());
                            }


                        }


                    }
                }
            }
        }


        return false;

    }


    private AnnotationConstraints determineConstraints(TypeElement annotationTypeElement) {

        // This will be done in two phases
        // PHASE 1 : check for constraints on annotation  type
        AnnotationConstraints annotationConstraints = new AnnotationConstraints(annotationTypeElement);
        for (AnnotationMirror annotation : annotationTypeElement.getAnnotationMirrors()) {

            if (hasConstraintAnnotationOnTypeElement(annotation)) {
                annotationConstraints.addConstraintOnType(annotation);
            }

        }


        // PHASE 2 : check for constraints on Annotation attributes
        List<ExecutableElement> executableElements = ElementUtils.CastElement.castElementList(ElementUtils.AccessEnclosedElements.getEnclosedElementsOfKind(annotationTypeElement, ElementKind.METHOD), ExecutableElement.class);

        for (ExecutableElement executableElement : executableElements) {
            String name = executableElement.getSimpleName().toString();

            List<AnnotationMirror> detectedConstraints = new ArrayList<AnnotationMirror>();

            for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
                if (annotationMirror.getAnnotationType().asElement().getAnnotation(Constraint.class) != null) {
                    detectedConstraints.add(annotationMirror);

                }
            }


            if (detectedConstraints.size() > 0) {
                // detected constraint
                annotationConstraints.addConstraintOnType(new AnnotationAttributeConstraints(name, detectedConstraints));
            }

        }


        return annotationConstraints;
    }

    /**
     * Check if annotation is annotated with Constraint annotation.
     *
     * @param annotationMirror
     * @return
     */
    protected boolean hasConstraintAnnotationOnTypeElement(AnnotationMirror annotationMirror) {

        return AnnotationUtils.getAnnotationMirror(annotationMirror.getAnnotationType().asElement(), Constraint.class) != null;

    }


}
