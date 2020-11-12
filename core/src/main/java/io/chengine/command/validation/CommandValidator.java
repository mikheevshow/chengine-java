package io.chengine.command.validation;

public interface CommandValidator {

	/**
	 * Check if string is a command
	 *
	 * @param s - string for checking
	 * @return true if string is a command, false otherwise
	 */
	boolean isCommand(String s);

	/**
	 * Validate a string representation of command
	 *
	 * @param s - string for validating
	 * @throws CommandValidationException
	 */
	void validate(String s);

}
