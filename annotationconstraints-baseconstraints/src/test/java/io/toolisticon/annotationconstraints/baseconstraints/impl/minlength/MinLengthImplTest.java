package io.toolisticon.annotationconstraints.baseconstraints.impl.minlength;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.JavaFileObjectUtils;
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
                .testCompilation();

    }


    @Test
    public void invalid() {
        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/minLength/InvalidUsage.java"))
                .compilationShouldFail()
                .testCompilation();

    }

}
