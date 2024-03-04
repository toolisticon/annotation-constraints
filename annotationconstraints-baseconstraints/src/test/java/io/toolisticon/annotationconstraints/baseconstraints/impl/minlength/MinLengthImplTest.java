package io.toolisticon.annotationconstraints.baseconstraints.impl.minlength;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class MinLengthImplTest {

    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void valid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/minLength/ValidUsage.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }


    @Test
    public void invalid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/minLength/InvalidUsage.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(MinLengthConstraintMessages.ERROR_STRING_IS_TOO_SHORT.getCode())
                .executeTest();

    }

}
