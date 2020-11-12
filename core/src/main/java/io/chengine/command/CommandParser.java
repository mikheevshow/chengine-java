package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;

public interface CommandParser {

	/**
	 * Parse a string representation of a command into {@link Command} object.
	 *
	 * @param command a string representation of a command
	 * @return a command wrap
	 * @see Command
	 * @see io.chengine.command.validation.CommandValidator
	 * @see CommandIterator
	 */
	Command parse(String command);

}
