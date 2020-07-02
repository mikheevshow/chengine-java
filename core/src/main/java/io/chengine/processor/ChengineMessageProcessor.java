package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;

	public ChengineMessageProcessor(MethodResolver methodResolver, MethodArgumentInspector methodArgumentInspector) {
		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		var method = methodResolver.resolve(request);
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		try {
			var args = method.invoke(methodArguments);
			System.out.println(method.belongsTo(response));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}
}
