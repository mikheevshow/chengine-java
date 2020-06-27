package io.chengine.processor;

import io.chengine.HandlerRegistry;
import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;

import java.util.Optional;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private HandlerRegistry handlerRegistry;
	private MethodResolver methodResolver;
	private MethodArgumentInspector methodArgumentInspector;

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
