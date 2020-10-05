package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.processor.testconstraint.TestConstraintMessages;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests of {@link io.toolisticon.annotationconstraints.api.Constraint}.
 * <p>
 */

public class ConstraintProcessorTest {


    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

    }


    @Test
    public void test_valid_usage_onAnnotationType() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/success/SuccessfulConstraintOnAnnotationType.java"))
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void test_valid_usage_onAnnotationAttribute() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/success/SuccessfulConstraintOnAnnotationAttribute.java"))
                .compilationShouldSucceed()
                .executeTest();
    }

    @Test
    public void test_failure_wrongLocation() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/WrongLocation.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(TestConstraintMessages.ERROR_LOCATION.getCode())
                .executeTest();
    }

    @Test
    public void test_failure_onAnnotationType() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/FailingConstraintOnAnnotationType.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .executeTest();
    }

    @Test
    public void test_failure_onAnnotationAttribute() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/FailingConstraintOnAnnotationType.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .executeTest();
    }

}