package io.toolisticon.annotationconstraints.example.usage;

import io.toolisticon.annotationconstraints.baseconstraints.impl.minlength.MinLengthConstraintMessages;
import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import org.junit.Before;
import org.junit.Test;

public class ExternalMappingTest {
    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    @Test
    public void externalMappingsTest() {
        compileTestBuilder.andSourceFiles("/Invalid.java")
                .whenCompiled()
                .thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(MinLengthConstraintMessages.ERROR_STRING_IS_TOO_SHORT.getCode())
                .executeTest();
    }
}
