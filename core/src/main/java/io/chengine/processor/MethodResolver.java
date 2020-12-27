package io.chengine.processor;

import io.chengine.connector.BotRequestContext;
import io.chengine.method.HandlerMethod;

public interface MethodResolver {

	HandlerMethod resolve(BotRequestContext request);

}
