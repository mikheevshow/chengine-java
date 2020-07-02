package io.chengine;


import io.chengine.command.Command;
import io.chengine.method.Method;

import java.util.Set;

public interface HandlerRegistry {

	Set<String> getAllPaths();

	Set<?> getAllHandlers();

	Method get(String command);

	default Method get(Command command) {
		return get(command.path());
	}

}
