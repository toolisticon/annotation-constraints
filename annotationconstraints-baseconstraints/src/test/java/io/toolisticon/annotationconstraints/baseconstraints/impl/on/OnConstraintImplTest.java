package io.toolisticon.annotationconstraints.baseconstraints.impl.on;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.cute.CompileTestBuilder;
import io.toolisticon.cute.JavaFileObjectUtils;
import org.junit.Before;
import org.junit.Test;

public class OnConstraintImplTest {

    private CompileTestBuilder.CompilationTestBuilder compileTestBuilder = CompileTestBuilder
            .compilationTest()
            .addProcessors(ConstraintProcessor.class);

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

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnInterface.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_InterfaceConstraintOnNonInterface() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonInterface.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Interface() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetInterface.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Class
     * ###########################################################
     */

    @Test
    public void validUsage_ClassConstraintOnClass() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnClass.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_ClassConstraintOnClass() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonClass.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Class() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetClass.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Enum
     * ###########################################################
     */

    @Test
    public void validUsage_EnumConstraintOnEnum() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnEnum.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_EnumConstraintOnNonEnum() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonEnum.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Enum() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetEnum.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Annotation
     * ###########################################################
     */

    @Test
    public void validUsage_AnnotationConstraintOnAnnotation() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnAnnotation.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void validUsage_AnnotationConstraintOnAnnotationType() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnAnnotationType.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_AnnotationConstraintOnNonAnnotation() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotation.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Annotation() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetAnnotation.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Method
     * ###########################################################
     */

    @Test
    public void validUsage_MethodConstraintOnMethod() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnMethod.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_MethodConstraintOnAnnotationAttribute() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonMethod.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Method() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetMethod.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Annotation Attribute
     * ###########################################################
     */

    @Test
    public void validUsage_AnnotationAttributeConstraintOnAnnotationAttribute() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnAnnotationAttribute.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void invalidUsage_AnnotationAttributeConstraintOnMethod() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotationAttribute.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .executeTest();

    }

    @Test
    public void mismatchingTarget_AnnotationAttribute() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetAnnotationAttribute.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Constructor
     * ###########################################################
     */

    @Test
    public void validUsage_ConstructorConstraintOnConstructor() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnConstructor.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Constructor() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetConstructor.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Field
     * ###########################################################
     */

    @Test
    public void validUsage_FieldConstraintOnField() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnField.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Field() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetField.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_ParameterConstraintOnParameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnParameter.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_Parameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetConstructorParameter.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Method Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_MethodParameterConstraintOnParameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnMethodParameter.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_MethodParameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetMethodParameter.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Constructor Parameter
     * ###########################################################
     */

    @Test
    public void validUsage_ConstructorParameterConstraintOnParameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnConstructorParameter.java"))
                .compilationShouldSucceed()
                .executeTest();

    }

    @Test
    public void mismatchingTarget_ConstructorParameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetConstructorParameter.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }

    /*
     * ###########################################################
     * ## Package
     * ###########################################################
     */


    @Test
    public void mismatchingTarget_Package() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetPackage.java"))
                .compilationShouldFail()
                .expectErrorMessageThatContains(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .executeTest();

    }
}
