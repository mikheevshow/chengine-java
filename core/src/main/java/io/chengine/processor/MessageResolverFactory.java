package io.chengine.processor;

import io.chengine.connector.BotRequest;
import io.chengine.method.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static java.lang.Boolean.TRUE;

public class MessageResolverFactory implements MethodResolver {

	private static final Logger log = LogManager.getLogger(MessageResolverFactory.class);

	private final CommandMethodResolver commandMethodResolver;

	public MessageResolverFactory(CommandMethodResolver commandMethodResolver) {
		this.commandMethodResolver = commandMethodResolver;
	}

	@Override
	@Nullable
	public Method resolve(BotRequest request) {
		if(TRUE.equals(request.isCommand())) {
			return commandMethodResolver.resolve(request);
		} else if (TRUE.equals(request.isLocation())) {
			// TODO handle location here
		} else if (TRUE.equals(request.isCallback())) {
			if (request.message().containsCommand()) {
				return commandMethodResolver.resolve(request);
			} else {
				throw new MethodResolveException("Can't handle callback without command");
			}
		} else if (request.isAttachment()) {

		}
		return null;
	}
}
