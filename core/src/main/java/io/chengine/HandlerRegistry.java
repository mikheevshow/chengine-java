package io.chengine;


import io.chengine.command.Command;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Pair<Method, Object> getHandlerMethod(String command);

	default Pair<Method, Object> getHandlerMethod(Command command) {
		return getHandlerMethod(command.path());
	}

	Set<?> getAllHandlers();

}
