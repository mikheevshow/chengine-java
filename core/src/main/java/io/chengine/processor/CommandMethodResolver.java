package io.chengine.processor;

import io.chengine.HandlerRegistry;
import io.chengine.connector.BotRequest;
import io.chengine.handler.HandlerNotFoundException;
import io.chengine.method.Method;
import org.apache.commons.lang3.tuple.Pair;

public class CommandMethodResolver implements MethodResolver {

	private final HandlerRegistry handlerRegistry;

	public CommandMethodResolver(final HandlerRegistry handlerRegistry) {

		this.handlerRegistry = handlerRegistry;
	}



	@Override
	public Method resolve(BotRequest request) {

		var command = request.message().command();

		var handlerMethod = handlerRegistry.get(command);
		if (handlerMethod == null) {
			throw new HandlerNotFoundException("No method found matching command '" + command.path() + "'");
		}

		return handlerMethod;
	}
}
