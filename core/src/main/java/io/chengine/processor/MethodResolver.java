package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.Method;
import org.apache.commons.lang3.tuple.Pair;

public interface MethodResolver {

	Method resolve(BotRequest request);

}
