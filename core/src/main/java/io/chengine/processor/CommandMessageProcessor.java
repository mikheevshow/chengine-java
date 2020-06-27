package io.chengine.processor;

import io.chengine.HandlerRegistry;
import io.chengine.connector.BotRequest;
import io.chengine.handler.HandlerNotFoundException;
import io.chengine.method.MethodArgumentInspector;

public class CommandMessageProcessor implements MessageProcessor<BotRequest> {

	private final HandlerRegistry handlerRegistry;
	private final MethodArgumentInspector methodArgumentInspector;

	public CommandMessageProcessor(
		final HandlerRegistry handlerRegistry,
		final MethodArgumentInspector methodArgumentInspector
	) {

		this.handlerRegistry = handlerRegistry;
		this.methodArgumentInspector = methodArgumentInspector;
	}

	@Override
	public void process(BotRequest request) {

		var commandPath = request
			.message()
			.command()
			.path();

		var handlerMethod = handlerRegistry.getHandlerMethod(commandPath);
		if (handlerMethod == null) {
			throw new HandlerNotFoundException("No method found matching command '" + commandPath + "'");
		}

		// Вот эта часть будет одинакова почти для всех процессоров сообщений
		// пока оставлю так, но нужно думать как это выносить
		var method = handlerMethod.getLeft();
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method);
		try {
			var obj = method.invoke(handlerMethod.getRight(), methodArguments);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
