package io.chengine.handler;


import io.chengine.command.Command;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.PipelineDefinition;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Set<? extends HandlerMethod> getAllHandlers();

	PipelineDefinition getPipelineDefinitionByName(String string);

	PipelineDefinition getPipelineDefinitionByClass(Class<?> clazz);

	HandlerMethod getHandlerByCommand(String command);

	HandlerMethod getSingleHandler(Class<? extends Annotation> annotation);

	HandlerMethod getHandlerByCommand(Command command);

}
