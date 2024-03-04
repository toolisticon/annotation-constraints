package io.toolisticon.annotationconstraints.baseconstraints.impl.on;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class OnConstraintImplTest {

    private CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder = Cute.blackBoxTest()
            .given().processors(ConstraintProcessor.class);

    @Before
    public void init() {
        MessagerUtils.setPrintMessageCodes(true);
    }

    /*
     * ###########################################################
     * ## Interface
     * ###########################################################
     */

    @Test
    public void validUsage_InterfaceConstraintOnInterface() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnInterface.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_InterfaceConstraintOnNonInterface() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonInterface.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Interface() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetInterface.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Class
     * ###########################################################
     */

    @Test
    public void validUsage_ClassConstraintOnClass() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnClass.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_ClassConstraintOnClass() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonClass.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Class() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetClass.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Enum
     * ###########################################################
     */

    @Test
    public void validUsage_EnumConstraintOnEnum() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnEnum.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_EnumConstraintOnNonEnum() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonEnum.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Enum() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetEnum.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Annotation
     * ###########################################################
     */

    @Test
    public void validUsage_AnnotationConstraintOnAnnotation() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnAnnotation.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void validUsage_AnnotationConstraintOnAnnotationType() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnAnnotationType.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_AnnotationConstraintOnNonAnnotation() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotation.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Annotation() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetAnnotation.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Method
     * ###########################################################
     */

    @Test
    public void validUsage_MethodConstraintOnMethod() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnMethod.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_MethodConstraintOnAnnotationAttribute() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonMethod.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Method() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetMethod.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Annotation Attribute
     * ###########################################################
     */

    @Test
    public void validUsage_AnnotationAttributeConstraintOnAnnotationAttribute() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnAnnotationAttribute.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void invalidUsage_AnnotationAttributeConstraintOnMethod() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotationAttribute.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_AnnotationAttribute() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetAnnotationAttribute.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Constructor
     * ###########################################################
     */

    @Test
    public void validUsage_ConstructorConstraintOnConstructor() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnConstructor.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Constructor() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetConstructor.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Field
     * ###########################################################
     */

    @Test
    public void validUsage_FieldConstraintOnField() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnField.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Field() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetField.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_ParameterConstraintOnParameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnParameter.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Parameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetConstructorParameter.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Method Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_MethodParameterConstraintOnParameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnMethodParameter.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_MethodParameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetMethodParameter.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Constructor Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_ConstructorParameterConstraintOnParameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/ValidUsageOnConstructorParameter.java")
                .whenCompiled().thenExpectThat().compilationSucceeds()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_ConstructorParameter() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetConstructorParameter.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Package
     * ###########################################################
     */


    @Test
    public void mismatchingTarget_Package() {

        compileTestBuilder.andSourceFiles("/testcases/baseconstraints/on/MismatchingTargetPackage.java")
                .whenCompiled().thenExpectThat().compilationFails()
                .andThat().compilerMessage().ofKindError().contains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }
}
