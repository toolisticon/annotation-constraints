package io.toolisticon.annotationconstraints.api;

import io.toolisticon.spiap.api.Spi;

@Spi
public interface ExternalConstraintMappingSpi {

    String getTargetAnnotation();

    String getExternalMappingAnnotation();

}
