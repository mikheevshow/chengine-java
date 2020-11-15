package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.HandlerMethod;

public interface MethodResolver {

	HandlerMethod resolve(BotRequest request);

}
