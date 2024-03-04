package io.toolisticon.annotationconstraints.processor;

import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link ExternalConstraintMappingProcessor}.
 */
public class ExternalConstraintMappingProcessorTest {

    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ExternalConstraintMappingProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);

    }

    @Test
    public void testValidMapping() {
        compileTestBuilder.andSourceFiles("/testcases/externalMapping/ValidExternalMapping.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();
    }

    @Test
    public void testInvalidMapping() {
        compileTestBuilder.andSourceFiles("/testcases/externalMapping/InvalidExternalMapping.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(ExternalConstraintMappingProcessorMessages.ERROR_ANNOTATION_ATTRIBUTE_NOT_FOUND_IN_TARGET_ANNOTATION.getCode())
                .executeTest();
    }

}
