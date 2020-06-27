package io.chengine.processor;

import io.chengine.HandlerRegistry;
import io.chengine.connector.BotRequest;
import io.chengine.handler.HandlerNotFoundException;
import io.chengine.method.MethodArgumentInspector;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;

public class CommandMethodResolver implements MethodResolver {

	private final HandlerRegistry handlerRegistry;
	private final MethodArgumentInspector methodArgumentInspector;

	public CommandMethodResolver(
		final HandlerRegistry handlerRegistry,
		final MethodArgumentInspector methodArgumentInspector
	) {

		this.handlerRegistry = handlerRegistry;
		this.methodArgumentInspector = methodArgumentInspector;
	}



	@Override
	public Pair<Method, Object> resolve(BotRequest request) {

		var commandPath = request
			.message()
			.command()
			.path();

		var handlerMethod = handlerRegistry.getHandlerMethod(commandPath);
		if (handlerMethod == null) {
			throw new HandlerNotFoundException("No method found matching command '" + commandPath + "'");
		}

		return handlerMethod;
	}
}
