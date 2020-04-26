package io.chengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Todo: допилить

public class CommandParser {

	/**
	 * Parse a string representation of a command into {@link Command} object.
	 * It's important to call validate method before parse the command {@link io.chengine.validation.CommandValidator}
	 *
	 * @see Command
	 *
	 * @param command string representation of a command
	 * @return a command wrap
	 */
	public Command parseCommand(String command) throws CommandParsingException {

		StringBuilder commandStringBuilder =  new StringBuilder();
		StringBuilder queryParameterCandidate = null;
		StringBuilder queryParameterCandidateValue = null;

		boolean appendCommand = true;
		boolean appendToQueryParameter = true;
		boolean appendToQueryParameterValue = false;

		Map<String, String> mapParameterValue = new HashMap<>();

		for (char c : command.toCharArray()) {

			if (c == '/') {
				appendCommand = true;
				appendToQueryParameter = true;
				appendToQueryParameterValue = false;

				if (queryParameterCandidate!= null && queryParameterCandidateValue != null) {
					mapParameterValue.put(queryParameterCandidate.toString(), queryParameterCandidateValue.toString());
				}

				queryParameterCandidate = new StringBuilder();
				queryParameterCandidateValue = null;
			}

			if (c == '#') {
				appendCommand = false;
				appendToQueryParameter = false;
				appendToQueryParameterValue = true;

				commandStringBuilder.append(c);

				queryParameterCandidateValue = new StringBuilder();
			}

			if (appendCommand) {
				commandStringBuilder.append(c);
			}

			if (queryParameterCandidate != null && appendToQueryParameter && c != '/') {
				queryParameterCandidate.append(c);
			}

			if (appendToQueryParameterValue && c != '#') {
				Objects
					.requireNonNull(queryParameterCandidateValue, "QueryParameterCandidateValue can't be null")
					.append(c);
			}

		}

		return new Command(commandStringBuilder.toString(), mapParameterValue);
	}

}
