package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.method.HandlerMethod;
import io.chengine.method.MethodArgumentInspector;

public class ChengineMessageProcessor implements MessageProcessor<BotRequestContext, BotResponseContext> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;

	public ChengineMessageProcessor(
			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
	}

	@Override
	public void process(BotRequestContext request, BotResponseContext response) {
		final HandlerMethod method = methodResolver.resolve(request);
		final Object[] methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		responseResolver.resolve(request, response, method, method.invoke(methodArguments));
	}
}
