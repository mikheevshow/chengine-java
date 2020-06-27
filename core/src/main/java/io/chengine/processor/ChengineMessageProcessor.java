package io.chengine.processor;

import io.chengine.HandlerRegistry;
import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;

import java.util.Optional;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final HandlerRegistry handlerRegistry;
	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;

	public ChengineMessageProcessor(HandlerRegistry handlerRegistry, MethodResolver methodResolver, MethodArgumentInspector methodArgumentInspector) {
		this.handlerRegistry = handlerRegistry;
		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		var handlerMethod = methodResolver.resolve(request);
		var method = handlerMethod.getLeft();
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method);
		try {
			var args = method.invoke(handlerMethod.getRight(), methodArguments);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}
}
