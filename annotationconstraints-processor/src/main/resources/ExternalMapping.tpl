package ${package};

import io.toolisticon.annotationconstraints.api.ExternalConstraintMappingSpi;
import io.toolisticon.spiap.api.Service;

import java.lang.annotation.Annotation;

@Service(ExternalConstraintMappingSpi.class)
public class ${className} implements ExternalConstraintMappingSpi{

    public String getTargetAnnotation() {

        return "${targetAnnotation}";

    }

    public String getExternalMappingAnnotation() {
        return "${externalMappingAnnotation}";
    }




}
