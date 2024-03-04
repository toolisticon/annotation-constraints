package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.api.ExternalConstraintMapping;
import io.toolisticon.aptk.tools.AbstractAnnotationProcessor;
import io.toolisticon.aptk.tools.AnnotationUtils;
import io.toolisticon.aptk.tools.ElementUtils;
import io.toolisticon.aptk.tools.FilerUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.TypeUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.corematcher.CoreMatchers;
import io.toolisticon.aptk.tools.fluentfilter.FluentElementFilter;
import io.toolisticon.aptk.tools.fluentvalidator.FluentElementValidator;
import io.toolisticon.aptk.tools.generators.SimpleJavaWriter;
import io.toolisticon.spiap.api.SpiService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpiService(Processor.class)
public class ExternalConstraintMappingProcessor extends AbstractAnnotationProcessor {


    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(ExternalConstraintMapping.class);

    private Set<String> externalMappings = new HashSet<>();

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // TODO NEED TO GET RID OF SPIAP DEPENDENCY, THEREFORE WE MUST GENERATE THE SPI SERVICE CONFIG ON OUR OWN WHEN PROCESSING OVER FLAG IS SET
        if (!roundEnvironment.processingOver()) {

            for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(ExternalConstraintMapping.class)) {

                // check if it is placed on annotation
                FluentElementValidator.createFluentElementValidator(annotatedElement).is(AptkCoreMatchers.IS_ANNOTATION_TYPE).validateAndIssueMessages();

                TypeElement annotatedTypeElement = ElementUtils.CastElement.castToTypeElement(annotatedElement);

                // Need to get target annotation TypeElement
                String targetAnnotationName = AnnotationUtils.getClassAttributeFromAnnotationAsFqn(annotatedElement, ExternalConstraintMapping.class);
                TypeElement targetAnnotationTypeElement = TypeUtils.TypeRetrieval.getTypeElement(targetAnnotationName);

                // Now check all annotation attributes  for correctness
                List<ExecutableElement> annotationAttributes = ElementUtils.AccessEnclosedElements.<ExecutableElement>getEnclosedElementsOfKind(annotatedElement, ElementKind.METHOD);
                for (ExecutableElement annotationAttribute : annotationAttributes) {

                    if (!FluentElementFilter.createFluentElementFilter(targetAnnotationTypeElement.getEnclosedElements()).applyFilter(AptkCoreMatchers.IS_METHOD).applyFilter(AptkCoreMatchers.BY_NAME).filterByOneOf(annotationAttribute.getSimpleName().toString()).hasSingleElement()) {
                        MessagerUtils.error(annotatedElement, ExternalConstraintMappingProcessorMessages.ERROR_ANNOTATION_ATTRIBUTE_NOT_FOUND_IN_TARGET_ANNOTATION, annotationAttribute.getSimpleName().toString(), targetAnnotationName);
                    }

                }

                // Now write service
                writeConfigurationFiles(annotatedTypeElement, targetAnnotationTypeElement.getQualifiedName().toString(), annotatedTypeElement.getQualifiedName().toString());

            }


        } else {

        }
        return false;
    }

    private void writeConfigurationFiles(TypeElement annotatedTypeElement, String targetAnnotation, String externalMappingAnnotation) {

        // Now create builder class with attributes
        String packageName = ((PackageElement) ElementUtils.AccessEnclosingElements.getFirstEnclosingElementOfKind(annotatedTypeElement, ElementKind.PACKAGE)).getQualifiedName().toString();
        String className = annotatedTypeElement.getSimpleName().toString() + "ExternalConstraintMapperService";

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("package", packageName);
        model.put("className", className);
        model.put("targetAnnotation", targetAnnotation);
        model.put("externalMappingAnnotation", externalMappingAnnotation);

        // create the Builder class
        String filePath = packageName + "." + className;
        try {
            SimpleJavaWriter javaWriter = FilerUtils.createSourceFile(filePath, annotatedTypeElement);
            javaWriter.writeTemplate("/ExternalMapping.tpl", model);
            javaWriter.close();

            // Add implementation
            externalMappings.add(className);
        } catch (IOException e) {
            MessagerUtils.error(annotatedTypeElement, ExternalConstraintMappingProcessorMessages.ERROR_COULD_NOT_CREATE_SERVICE.getMessage(), filePath);
        }

    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }
}
