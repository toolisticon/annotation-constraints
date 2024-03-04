package io.toolisticon.annotationconstraints.baseconstraints.impl.annotatedtypemustbe;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class AnnotatedTypeMustBeImplTest {

    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }


    @Test
    public void valid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/annotatedtypemustbe/ValidUsage.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalid() {
        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/annotatedtypemustbe/InvalidUsage.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(AnnotatedTypeMustBeConstraintMessages.ERROR_CHECK_FAILS_FOR_ANNOTATED_ELEMENT.getCode())
                .executeTest();

    }

}
