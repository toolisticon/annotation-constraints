package io.toolisticon.annotationconstraints.example.usage;

import io.toolisticon.annotationconstraints.baseconstraints.impl.minlength.MinLengthConstraintMessages;
import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class ExternalMappingTest {
    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void externalMappingsTest() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/Invalid.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(MinLengthConstraintMessages.ERROR_STRING_IS_TOO_SHORT.getCode())
                .executeTest();
    }
}
