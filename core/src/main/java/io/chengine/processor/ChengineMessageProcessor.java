package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.connector.BotResponse;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.pipeline.PipelineSessionManager;
import io.chengine.pipeline.processor.PipelineRequestHandler;

public class ChengineMessageProcessor implements MessageProcessor<BotRequest, BotResponse> {

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;

	private final PipelineSessionManager pipelineSessionManager;
	private final PipelineRequestHandler pipelineRequestHandler;

	public ChengineMessageProcessor(

			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver,
			final PipelineSessionManager pipelineSessionManager,
			final PipelineRequestHandler pipelineRequestHandler

	) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
		this.pipelineSessionManager = pipelineSessionManager;
		this.pipelineRequestHandler = pipelineRequestHandler;

	}

	@Override
	public void process(BotRequest request, BotResponse response) {
		if (pipelineSessionManager.getCurrentSession() != null) {
			pipelineRequestHandler.handleRequest(request, response);
		} else {
			var method = methodResolver.resolve(request);
			var methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
			responseResolver.resolve(request, response, method, method.invoke(methodArguments));
		}
	}
}
