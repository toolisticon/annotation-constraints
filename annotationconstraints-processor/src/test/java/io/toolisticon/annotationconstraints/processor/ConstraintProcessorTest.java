package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationconstraints.processor.testconstraint.TestConstraintMessages;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.JavaFileObjectUtils;
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
                .testCompilation();
    }

    @Test
    public void test_valid_usage_onAnnotationAttribute() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/success/SuccessfulConstraintOnAnnotationAttribute.java"))
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void test_failure_wrongLocation() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/WrongLocation.java"))
                .compilationShouldFail()
                .expectedErrorMessages(TestConstraintMessages.ERROR_LOCATION.getCode())
                .testCompilation();
    }

    @Test
    public void test_failure_onAnnotationType() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/FailingConstraintOnAnnotationType.java"))
                .compilationShouldFail()
                .expectedErrorMessages(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .testCompilation();
    }

    @Test
    public void test_failure_onAnnotationAttribute() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/failure/FailingConstraintOnAnnotationType.java"))
                .compilationShouldFail()
                .expectedErrorMessages(TestConstraintMessages.ERROR_IN_PROCESSING_PHASE.getCode())
                .testCompilation();
    }

}