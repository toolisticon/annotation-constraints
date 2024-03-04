package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.processor.testconstraint.TestConstraintMessages;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests of {@link io.toolisticon.annotationconstraints.api.Constraint}.
 * <p>
 */

public class ConstraintProcessorTest {


    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute
            .blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

    }


    @Test
    public void test_valid_usage_onAnnotationType() {

        compileTestBuilder.andSourceFiles("testcases/success/SuccessfulConstraintOnAnnotationType.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();
    }

    @Test
    public void test_valid_usage_onAnnotationAttribute() {

        compileTestBuilder
                .andSourceFiles("testcases/success/SuccessfulConstraintOnAnnotationAttribute.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();
    }

    @Test
    public void test_failure_wrongLocation() {

        compileTestBuilder
                .andSourceFiles("testcases/failure/WrongLocation.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(TestConstraintMessages.ERROR_LOCATION.getCode())
                .executeTest();
    }

    @Test
    public void test_failure_onAnnotationType() {

        compileTestBuilder
                .andSourceFiles("testcases/failure/FailingConstraintOnAnnotationType.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .executeTest();
    }

    @Test
    public void test_failure_onAnnotationAttribute() {

        compileTestBuilder
                .andSourceFiles("testcases/failure/FailingConstraintOnAnnotationType.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .executeTest();
    }

}