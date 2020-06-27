package io.chengine.processor;

import io.chengine.connector.BotRequest;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Optional;

public class MessageResolverFactory implements MethodResolver {

	private final CommandMethodResolver commandMethodResolver;

	public MessageResolverFactory(CommandMethodResolver commandMethodResolver) {
		this.commandMethodResolver = commandMethodResolver;
	}

	@Override
	@Nullable
	public Pair<Method, Object> resolve(BotRequest request) {
		if(request.message().isCommand()) {
			return commandMethodResolver.resolve(request);
		}

		return null;
	}
}
