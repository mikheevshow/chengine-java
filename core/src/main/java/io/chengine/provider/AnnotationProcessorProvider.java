package io.chengine.provider;

import io.chengine.annotation.ChengineAnnotationProcessor;

import java.util.List;

public interface AnnotationProcessorProvider {

    List<ChengineAnnotationProcessor> provideAll();

}
