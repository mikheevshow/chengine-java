package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.DefaultCommandValidator;
import io.chengine.command.validation.EmptyCommandException;

public interface CommandParser {

	/**
	 * Parse a string representation of a command into {@link Command} object.
	 * It's important to call validate method before parse the command {@link DefaultCommandValidator}
	 *
	 * @param command a string representation of a command
	 * @return a command wrap
	 * @see Command
	 */
	Command parse(String command) throws EmptyCommandException, CommandParsingException, CommandValidationException;

}
