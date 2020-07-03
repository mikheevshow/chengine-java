package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final static Logger log = LogManager.getLogger(ChengineMessageProcessor.class);

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
		log.info("Request " + request.toString());
		var method = methodResolver.resolve(request);
		var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		responseResolver.resolve(request, response, method.invoke(methodArguments));
	}
}
