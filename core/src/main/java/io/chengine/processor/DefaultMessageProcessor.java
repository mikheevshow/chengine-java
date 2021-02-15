package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.connector.BotResponseContext;
import io.chengine.interceptor.InterceptorChainFactory;
import io.chengine.interceptor.RequestInterceptorChain;
import io.chengine.method.HandlerMethod;
import io.chengine.method.MethodArgumentInspector;
import io.chengine.session.UserSessionContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultMessageProcessor implements MessageProcessor<BotRequestContext, BotResponseContext> {

	private static final Logger log = LogManager.getLogger(DefaultMessageProcessor.class);

	private final MethodResolver methodResolver;
	private final MethodArgumentInspector methodArgumentInspector;
	private final ResponseResolver responseResolver;
	private InterceptorChainFactory requestInterceptorChainFactory;

	public DefaultMessageProcessor(
			final MethodResolver methodResolver,
			final MethodArgumentInspector methodArgumentInspector,
			final ResponseResolver responseResolver) {

		this.methodResolver = methodResolver;
		this.methodArgumentInspector = methodArgumentInspector;
		this.responseResolver = responseResolver;
	}

	@Override
	public void process(BotRequestContext request, BotResponseContext response) {
		if (requestInterceptorChainFactory == null) {
			throw new NullPointerException("Request interceptor chain factory is null");
		}
		requestInterceptorChainFactory.getChain().doIntercept(request);

		final HandlerMethod method = methodResolver.resolve(request);
		final Object[] methodArguments = methodArgumentInspector.inspectAndGetArguments(request, method.get());
		responseResolver.resolve(request, response, method, method.invoke(methodArguments));
	}

	public void setRequestInterceptorChain(InterceptorChainFactory requestInterceptorChain) {
		this.requestInterceptorChainFactory = requestInterceptorChain;
	}
}
