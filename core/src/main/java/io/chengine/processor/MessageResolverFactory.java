package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.Method;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Optional;

public class MessageResolverFactory implements MethodResolver {

	private final CommandMethodResolver commandMethodResolver;

	public MessageResolverFactory(CommandMethodResolver commandMethodResolver) {
		this.commandMethodResolver = commandMethodResolver;
	}

	@Override
	@Nullable
	public Method resolve(BotRequest request) {
		if(request.message().isCommand()) {
			return commandMethodResolver.resolve(request);
		}

		return null;
	}
}
