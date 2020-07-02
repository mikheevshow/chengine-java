package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;

	public ChengineMessageProcessor(MethodResolver methodResolver, MethodArgumentInspector methodArgumentInspector, ResponseResolver responseResolver) {
		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		var method = methodResolver.resolve(request);
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		try {
			var object = method.invoke(methodArguments);
			responseResolver.resolve(request, response, object);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}
}
