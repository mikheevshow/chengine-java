package io.chengine;

import io.chengine.command.DefaultCommandParser;
import io.chengine.connector.BotMessagingConnector;
import io.chengine.command.validation.DefaultCommandValidator;

import java.util.Collection;

public class MessageProcessor {

	private final DefaultCommandValidator defaultCommandValidator = new DefaultCommandValidator();
	private final DefaultCommandParser defaultCommandParser = new DefaultCommandParser();

	public MessageProcessor(ChengineHandlerContext chengineHandlerContext, Collection<BotMessagingConnector> botMessagingConnectors) {

	}

}
