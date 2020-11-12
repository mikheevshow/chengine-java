package io.chengine.command;

import io.chengine.command.validation.CommandValidationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultCommandParserTest {

	private final CommandParser defaultCommandParser = DefaultCommandParser.instance();

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
		assertThrows(CommandValidationException.class, () -> defaultCommandParser.parse(command));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"/hello##/some",
		"/hello#//some",
		"/hello/some##",
		"/#",
		"/####",
		"#/#",
		"///",
		"###",
		"command",
		"/thiscommandmuchmorethanthirtytwosymbolsandmuchmorethansixtyfoursymbols"
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
			() -> assertEquals("/news#/podcasts/id#", command.path()),
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
			() -> assertEquals("/news", command.path()),
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
			() -> assertEquals("/news/pews", command.path()),
			() -> assertEquals(0, command.getParamSet().size()),
			() -> assertNull(command.getParam("someParam")),
			() -> assertFalse(command.hasParams())
		);
	}

	@Test
	@SneakyThrows
	public void parseCorrectTest4() {
		var command = defaultCommandParser.parse("/news/news#pews");
		assertAll(
			() -> assertEquals("/news/news#", command.path()),
			() -> assertEquals(1, command.getParamSet().size()),
			() -> assertNull(command.getParam("someParam")),
			() -> assertTrue(command.hasParams())
		);
	}

	@Test
	@SneakyThrows
	public void parseCorrectTest5() {
		var command = defaultCommandParser.parse("/hello#");
		assertAll(
				() -> assertEquals("/hello#", command.path()),
				() -> assertEquals(1, command.getParamSet().size()),
				() -> assertEquals("", command.getParam("hello"))
		);
	}

	@Test
	@SneakyThrows
	public void parseCorrectTest6() {
		var command = defaultCommandParser.parse("/hello#/some");
		assertAll(
				() -> assertEquals("/hello#/some", command.path()),
				() -> assertEquals(1, command.getParamSet().size()),
				() -> assertEquals("", command.getParam("hello"))
		);
	}
}
