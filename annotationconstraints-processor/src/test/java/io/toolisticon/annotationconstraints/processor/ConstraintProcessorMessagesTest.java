package io.toolisticon.annotationconstraints.processor;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for {@link ConstraintProcessorMessages}.
 *
 * TODO: replace the example testcases with your own testcases
 *
 */
public class ConstraintProcessorMessagesTest {

    @Test
    @Ignore
    public void test_enum() {

        MatcherAssert.assertThat(ConstraintProcessorMessages.ERROR_COULD_NOT_CREATE_CLASS.getCode(), Matchers.startsWith("Constraint"));
        MatcherAssert.assertThat(ConstraintProcessorMessages.ERROR_COULD_NOT_CREATE_CLASS.getMessage(), Matchers.containsString("create class"));

    }


}
