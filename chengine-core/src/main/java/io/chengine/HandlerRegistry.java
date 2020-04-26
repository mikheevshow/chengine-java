package io.chengine;


import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;
import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Pair<Method, Object> getHandlerMethod(String command);

	Set<?> getAllHandlers();

}
