package io.toolisticon.annotationconstraints.baseconstraints.impl.minlength;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class MinLengthImplTest {

    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void valid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/minLength/ValidUsage.java"))
                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void invalid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/minLength/InvalidUsage.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(MinLengthConstraintMessages.ERROR_STRING_IS_TOO_SHORT.getCode())
                .executeTest();

    }

}
