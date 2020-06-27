package io.chengine.processor;

import io.chengine.connector.BotRequest;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;
import java.util.Optional;

public class DefaultMessageResolver implements MethodResolver {

	@Override
	public Pair<Method, Object> resolve(BotRequest request) {
		return null;

	}
}
