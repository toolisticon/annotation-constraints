# Annotation Constraints Annotation Processor

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.annotationconstraints/annotationconstraints-processor/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.annotationconstraints/annotationconstraints-processor)
[![Build Status](https://travis-ci.org/toolisticon/annotation-constraints.svg?branch=master)](https://travis-ci.org/toolisticon/annotation-constraints)
[![codecov](https://codecov.io/gh/toolisticon/annotation-constraints/branch/master/graph/badge.svg)](https://codecov.io/gh/toolisticon/annotation-constraints)

This is a tech demo about providing and validating constraints on annotations.
There are two possible ways to achieve this:

- Placing constraint annotations on your annotation types or attributes
- Creating a digital twin annotation to define constraints on 3rd party annotations.

The project provides an annotation processor that is applied on all annotation types checking for existing constraints. Then it picks up the corresponding constraint implementation via an SPI and tests it.
It trigger compiler errors in case of broken constraints...

# License

This project is released under the revised [MIT License](LICENSE).

This project includes and repackages the [Annotation-Processor-Toolkit](https://github.com/holisticon/annotation-processor-toolkit) released under the  [MIT License](/3rdPartyLicenses/annotation-processor-toolkit/LICENSE.txt).
