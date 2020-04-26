package io.chengine;

import io.chengine.connector.BotMessagingConnector;
import io.chengine.validation.CommandValidator;

import java.util.Collection;

public class MessageProcessor {

	private final CommandValidator commandValidator = new CommandValidator();
	private final CommandParser commandParser = new CommandParser();

	public MessageProcessor(ChengineHandlerContext chengineHandlerContext, Collection<BotMessagingConnector> botMessagingConnectors) {

	}

}
