package io.chengine.handler;


import io.chengine.command.Command;
import io.chengine.method.HandlerMethod;

import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Set<?> getAllHandlers();

	HandlerMethod get(String command);

	default HandlerMethod get(Command command) {
		return get(command.path());
	}

}
