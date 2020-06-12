package io.chengine.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandParserTest {

	private CommandParser commandParser = new CommandParser();

	@Test
	public void parseCorrectTest() {
		try {
			var command = commandParser.parseCommand("/news#meduza/podcasts/id#1");
			Assertions.assertEquals("/news/podcasts/id", command.getCommand());
			Assertions.assertEquals(2, command.getParamSet().size());
			Assertions.assertEquals("meduza", command.getParam("news"));
			Assertions.assertEquals("1", command.getParam("id"));
			Assertions.assertNull(command.getParam("podcast"));
			Assertions.assertNull(command.getParam("somenotexistingkey"));
		} catch (CommandParsingException ex) {
			Assertions.fail(ex);
		}
 	}

}
