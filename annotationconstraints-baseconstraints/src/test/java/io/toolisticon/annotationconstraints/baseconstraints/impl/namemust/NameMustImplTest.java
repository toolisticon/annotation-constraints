package io.toolisticon.annotationconstraints.baseconstraints.impl.namemust;

import io.toolisticon.annotationconstraints.baseconstraints.impl.minlength.MinLengthConstraintMessages;
import io.toolisticon.annotationconstraints.baseconstraints.impl.name.NameConstraintMessages;
import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class NameMustImplTest {

    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void valid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/namemust/ValidUsage.java"))
                .compilationShouldSucceed()
                .executeTest();

    }


    @Test
    public void invalid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/namemust/InvalidUsage.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL.getCode())
                .executeTest();

    }

}
