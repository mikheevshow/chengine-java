package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.Method;

public interface MethodResolver {

	Method resolve(BotRequest request);

}
