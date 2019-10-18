package io.toolisticon.annotationconstraints.processor.baseconstraints;

import io.toolisticon.annotationconstraints.api.Constraint;
import io.toolisticon.annotationprocessortoolkit.ToolingProvider;
import io.toolisticon.annotationprocessortoolkit.tools.AnnotationUtils;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.annotationprocessortoolkit.tools.TypeUtils;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.UnitTestProcessor;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Unit test class for {@link UtilityFunctions}.
 */
public class UtilityFunctionsTest {

    // -------------------------------------------------------------------
    // -- Testcase Classes
    // -------------------------------------------------------------------

    @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
    @Constraint
    @interface ConstraintAnnotation {
        String value();
    }

    @Target(ElementType.TYPE)
    @ConstraintAnnotation("T")
    @interface TestAnnotation {
        @ConstraintAnnotation("M")
        String test();

        String testWithDefault() default "XYZ";
    }

    @TestAnnotation(test = "ABC")
    static class TestAnnotationUseCase {

    }

    // -------------------------------------------------------------------
    // -- Unit tests
    // -------------------------------------------------------------------

    private CompileTestBuilder.UnitTestBuilder unitTestBuilder = CompileTestBuilder
            .unitTest();

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void getAnnotationValueOfAttribute_readMandatoryValue() {
        unitTestBuilder.useProcessor(
                new UnitTestProcessor() {
                    @Override
                    public void unitTest(ProcessingEnvironment processingEnvironment, TypeElement typeElement) {

                        // setup tooling provider
                        ToolingProvider.setTooling(processingEnvironment);

                        TypeElement annotatedElement = TypeUtils.TypeRetrieval.getTypeElement(TestAnnotationUseCase.class);
                        AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement, TestAnnotation.class.getCanonicalName());

                        // execute
                        AnnotationValue annotationValue = UtilityFunctions.getAnnotationValueOfAttribute(annotationMirror, "test");

                        // validate
                        MatcherAssert.assertThat(annotationValue.getValue().toString(), Matchers.is("ABC"));


                    }
                })
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void getAnnotationValueOfAttribute_readDefaultValue() {
        unitTestBuilder.useProcessor(
                new UnitTestProcessor() {
                    @Override
                    public void unitTest(ProcessingEnvironment processingEnvironment, TypeElement typeElement) {

                        // setup tooling provider
                        ToolingProvider.setTooling(processingEnvironment);

                        TypeElement annotatedElement = TypeUtils.TypeRetrieval.getTypeElement(TestAnnotationUseCase.class);
                        AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement, TestAnnotation.class.getCanonicalName());

                        // execute
                        AnnotationValue annotationValue = UtilityFunctions.getAnnotationValueOfAttribute(annotationMirror, "testWithDefault");

                        // validate
                        MatcherAssert.assertThat(annotationValue, Matchers.nullValue());


                    }
                })
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void getConstraintAnnotationOfAnnotation_validUsage (){
        unitTestBuilder.useProcessor(
                new UnitTestProcessor() {
                    @Override
                    public void unitTest(ProcessingEnvironment processingEnvironment, TypeElement typeElement) {

                        // setup tooling provider
                        ToolingProvider.setTooling(processingEnvironment);

                        TypeElement annotatedElement = TypeUtils.TypeRetrieval.getTypeElement(TestAnnotationUseCase.class);
                        AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement, TestAnnotation.class.getCanonicalName());

                        // execute
                        ConstraintAnnotation constraintAnnotation = UtilityFunctions.getConstraintAnnotationOfAnnotation(annotationMirror, ConstraintAnnotation.class);

                        // validate
                        MatcherAssert.assertThat(constraintAnnotation, Matchers.notNullValue());
                        MatcherAssert.assertThat(constraintAnnotation.value(), Matchers.is("T"));

                    }
                })
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void getConstraintAnnotationOfAnnotationAttribute_validUsage (){
        unitTestBuilder.useProcessor(
                new UnitTestProcessor() {
                    @Override
                    public void unitTest(ProcessingEnvironment processingEnvironment, TypeElement typeElement) {

                        // setup tooling provider
                        ToolingProvider.setTooling(processingEnvironment);

                        TypeElement annotatedElement = TypeUtils.TypeRetrieval.getTypeElement(TestAnnotationUseCase.class);
                        AnnotationMirror annotationMirror = AnnotationUtils.getAnnotationMirror(annotatedElement, TestAnnotation.class.getCanonicalName());

                        // execute
                        ConstraintAnnotation constraintAnnotation = UtilityFunctions.getConstraintAnnotationOfAnnotationAttribute(annotationMirror, "test", ConstraintAnnotation.class);

                        // validate
                        MatcherAssert.assertThat(constraintAnnotation, Matchers.notNullValue());
                        MatcherAssert.assertThat(constraintAnnotation.value(), Matchers.is("M"));


                    }
                })
                .compilationShouldSucceed()
                .testCompilation();
    }

}
