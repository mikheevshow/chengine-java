package io.chengine.processor;

import io.chengine.connector.BotRequest;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;

public interface MethodResolver {

	Pair<Method, Object> resolve(BotRequest request);

}
