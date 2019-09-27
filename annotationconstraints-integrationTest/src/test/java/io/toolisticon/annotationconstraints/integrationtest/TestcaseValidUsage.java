package io.toolisticon.annotationconstraints.integrationtest;

import io.toolisticon.annotationconstraints.api.Constraint;

@Constraint("GeneratedClass")
public class TestcaseValidUsage {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}