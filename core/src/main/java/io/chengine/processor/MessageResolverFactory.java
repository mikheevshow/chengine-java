package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.Method;

import javax.annotation.Nullable;

public class MessageResolverFactory implements MethodResolver {

	private final CommandMethodResolver commandMethodResolver;

	public MessageResolverFactory(CommandMethodResolver commandMethodResolver) {
		this.commandMethodResolver = commandMethodResolver;
	}

	@Override
	@Nullable
	public Method resolve(BotRequest request) {
		if(request.message().containsCommand()) {
			return commandMethodResolver.resolve(request);
		}

		return null;
	}
}
