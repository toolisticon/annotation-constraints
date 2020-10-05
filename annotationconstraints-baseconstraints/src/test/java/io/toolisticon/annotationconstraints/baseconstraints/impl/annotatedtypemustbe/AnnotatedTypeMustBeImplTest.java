package io.toolisticon.annotationconstraints.baseconstraints.impl.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class AnnotatedTypeMustBeImplTest {

    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }


    @Test
    public void valid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/annotatedtypemustbe/ValidUsage.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/annotatedtypemustbe/InvalidUsage.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ANNOTATED_ELEMENT.getCode())
                .executeTest();

    }

}
