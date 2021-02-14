package io.chengine.handler;


import io.chengine.command.Command;
import io.chengine.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Set<? extends HandlerMethod> getAllHandlers();

	HandlerMethod getHandlerByCommand(String command);

	HandlerMethod getSingleHandler(Class<? extends Annotation> annotation);

	HandlerMethod getHandlerByCommand(Command command);

}
