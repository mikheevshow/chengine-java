package io.chengine.command.validation;

import io.chengine.commons.NotImplementedException;

import javax.annotation.Nonnull;
import java.util.regex.Matcher;

public interface CommandValidator {

	boolean isCommand(String s);

	void validate(String s) throws CommandValidationException, EmptyCommandException;

	default Matcher commandMatcherFor(@Nonnull String command) {
		throw new NotImplementedException();
	}

}
