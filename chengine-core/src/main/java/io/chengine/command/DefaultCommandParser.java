package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.CommandValidator;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultCommandParser implements CommandParser {

	private CommandValidator commandValidator = new DefaultCommandValidator();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command parse(String command) throws CommandValidationException, EmptyCommandException, CommandParsingException  {

		commandValidator.validate(command);
		var matcher = commandValidator.commandMatcherFor(command);

		System.out.println(matcher.group(1));

		return null;
	}
}
