package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link ExternalConstraintMappingProcessor}.
 */
public class ExternalConstraintMappingProcessorTest {

    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ExternalConstraintMappingProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

    }

    @Test
    public void testValidMapping() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/externalMapping/ValidExternalMapping.java"))
                .compilationShouldSucceed()
                .testCompilation();
    }

    @Test
    public void testInvalidMapping() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/externalMapping/InvalidExternalMapping.java"))
                .compilationShouldFail()
                .expectedErrorMessages(ExternalConstraintMappingProcessorMessages.ERROR_ANNOTATION_ATTRIBUTE_NOT_FOUND_IN_TARGET_ANNOTATION.getCode())
                .testCompilation();
    }

}
