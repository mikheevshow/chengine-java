package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.session.SessionManager;
import io.chengine.pipeline.processor.PipelineRequestHandler;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;

	private final SessionManager sessionManager;
	private final PipelineRequestHandler pipelineRequestHandler;

	public ChengineMessageProcessor(
			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver,
			final SessionManager sessionManager,
			final PipelineRequestHandler pipelineRequestHandler

	) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
		this.sessionManager = sessionManager;
		this.pipelineRequestHandler = pipelineRequestHandler;

	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		if (sessionManager.getCurrentSession() != null) {
			pipelineRequestHandler.handleRequest(request, response);
		} else {
			var method = methodResolver.resolve(request);
			var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
			responseResolver.resolve(request, response, method, method.invoke(methodArguments));
		}
	}
}
