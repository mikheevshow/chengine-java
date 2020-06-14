package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import io.chengine.command.validation.EmptyCommandException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultCommandParserTest {

	private final DefaultCommandParser defaultCommandParser = new DefaultCommandParser();

	@ParameterizedTest
	@ValueSource(strings = {
		"",
		"/",
		"/   ",
		"  /   ",
		" ",
		"    "
	})
	public void failedParse1(String command) {
		assertThrows(EmptyCommandException.class, () -> defaultCommandParser.parse(command));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"/hello#",
		"/hello#/some",
		"/hello##/some",
		"/hello#//some",
		"/hello/some##",
		"/#",
		"/####",
		"#/#",
		"///",
		"###",
		"command",
		"/thiscommandmuchmorethanthirtytwosymbols"
	})
	public void failedParse2(String command) {
		assertThrows(CommandValidationException.class, () -> defaultCommandParser.parse(command));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"/hello#myfriend/hello#yourfriend"
	})
	public void failedParse3(String command) {
		assertThrows(CommandParsingException.class, () -> defaultCommandParser.parse(command));
	}


	@Test
	@SneakyThrows
	public void parseCorrectTest1() {
		var command = defaultCommandParser.parse("/news#meduza/podcasts/id#1");
		assertAll(
			() -> assertEquals("/news/podcasts/id", command.getCommand()),
			() -> assertEquals(2, command.getParamSet().size()),
			() -> assertEquals("meduza", command.getParam("news")),
			() -> assertEquals("1", command.getParam("id")),
			() -> assertNull(command.getParam("podcast")),
			() -> assertNull(command.getParam("somenotexistingkey")),
			() -> assertTrue(command.hasParams())
		);
	}

	@Test
	@SneakyThrows
	public void parseCorrectTest2() {
		var command = defaultCommandParser.parse("/news");
		assertAll(
			() -> assertEquals("/news", command.getCommand()),
			() -> assertEquals(0, command.getParamSet().size()),
			() -> assertNull(command.getParam("someParam")),
			() -> assertFalse(command.hasParams())
		);
	}

	@Test
	@SneakyThrows
	public void parseCorrectTest3() {
		var command = defaultCommandParser.parse("/news/pews");
		assertAll(
			() -> assertEquals("/news/pews", command.getCommand()),
			() -> assertEquals(0, command.getParamSet().size()),
			() -> assertNull(command.getParam("someParam")),
			() -> assertFalse(command.hasParams())
		);
	}

//	@Test
//	@SneakyThrows
//	public void parseCorrectTest3() {
//		var command = commandParser.parseCommand("/news/news#pews");
//		assertAll(
//			() -> assertEquals("/news/pews", command.getCommand()),
//			() -> assertEquals(0, command.getParamSet().size()),
//			() -> assertNull(command.getParam("someParam")),
//			() -> assertFalse(command.hasParams())
//		);
//	}



}
