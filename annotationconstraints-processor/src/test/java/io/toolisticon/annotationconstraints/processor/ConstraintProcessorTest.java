package io.toolisticon.annotationconstraints.processor;

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
    public void test_valid_usage() {

        compileTestBuilder
                .addSources(JavaFileObjectUtils.readFromResource("testcases/testcasevalidusage/TestClass.java"))
                .compilationShouldSucceed()
                .expectedWarningMessages(ConstraintProcessorMessages.WARNING_CONSTRAINT_SPI_IMPLEMENTATION_NOT_FOUND.getCode())
                .testCompilation();
    }


}