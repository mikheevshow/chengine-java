package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;

	public ChengineMessageProcessor(

			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver

	) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;

	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		var method = methodResolver.resolve(request);
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		responseResolver.resolve(request, response, method, method.invoke(methodArguments));
	}
}
