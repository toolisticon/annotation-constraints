package io.toolisticon.annotationconstraints.baseconstraints.impl.namemust;

import io.toolisticon.annotationconstraints.baseconstraints.impl.minlength.MinLengthConstraintMessages;
import io.toolisticon.annotationconstraints.baseconstraints.impl.name.NameConstraintMessages;
import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class NameMustImplTest {

    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void valid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/namemust/ValidUsage.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }


    @Test
    public void invalid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/namemust/InvalidUsage.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(NameConstraintMessages.ERROR_REGULAR_EXPRESSION_VALUE_MUST_BE_EQUAL.getCode())
                .executeTest();

    }

}
