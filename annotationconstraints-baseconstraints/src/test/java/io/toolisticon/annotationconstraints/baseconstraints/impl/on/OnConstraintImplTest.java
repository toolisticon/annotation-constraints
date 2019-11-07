package io.toolisticon.annotationconstraints.baseconstraints.impl.on;

import io.toolisticon.annotationconstraints.processor.ConstraintProcessor;
import io.toolisticon.annotationprocessortoolkit.tools.MessagerUtils;
import io.toolisticon.compiletesting.CompileTestBuilder;
import io.toolisticon.compiletesting.JavaFileObjectUtils;
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
                .testCompilation();

    }

    @Test
    public void invalidUsage_InterfaceConstraintOnNonInterface() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonInterface.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Interface() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetInterface.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void invalidUsage_ClassConstraintOnClass() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonClass.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Class() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetClass.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void invalidUsage_EnumConstraintOnNonEnum() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonEnum.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Enum() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetEnum.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void validUsage_AnnotationConstraintOnAnnotationType() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/ValidUsageOnAnnotationType.java"))
                .compilationShouldSucceed()
                .testCompilation();

    }

    @Test
    public void invalidUsage_AnnotationConstraintOnNonAnnotation() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotation.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Annotation() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetAnnotation.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void invalidUsage_MethodConstraintOnAnnotationAttribute() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonMethod.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Method() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetMethod.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void invalidUsage_AnnotationAttributeConstraintOnMethod() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/InvalidUsageOnNonAnnotationAttribute.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_WRONG_USAGE.getCode())
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_AnnotationAttribute() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetAnnotationAttribute.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Constructor() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetConstructor.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Field() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetField.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .testCompilation();

    }

    @Test
    public void mismatchingTarget_Parameter() {

        compileTestBuilder.addSources(JavaFileObjectUtils.readFromResource("/testcases/baseconstraints/on/MismatchingTargetParameter.java"))
                .compilationShouldFail()
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

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
                .expectedErrorMessages(OnConstraintMessages.ERROR_MATCHING_TARGET_NOT_FOUND.getCode())
                .testCompilation();

    }
}
