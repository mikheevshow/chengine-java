package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.security.SecurityGuard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private static final Logger log = LogManager.getLogger(ChengineMessageProcessor.class);

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;
	private final SecurityGuard securityGuard;

	public ChengineMessageProcessor(

			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver,
			final SecurityGuard securityGuard

	) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
		this.securityGuard = securityGuard;

	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		var method = methodResolver.resolve(request);
		if (securityGuard.methodAvailableForApi(method, request.identifier())) {
			var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
			responseResolver.resolve(request, response, method.invoke(methodArguments));
		} else {

		}
	}
}
