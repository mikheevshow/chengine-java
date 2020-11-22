package io.chengine.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandBuilderTest {

	@Test
	public void commandBuilderTest() {
		var command = Command.builder()
			.put("news")
			.put("all")
			.put("subscribe", "meduza")
			.build();

		assertAll(
			() -> assertEquals("/news/all/subscribe#", command.path()),
			() -> assertEquals(1, command.getParamSet().size()),
			() -> assertEquals("meduza", command.getParam("subscribe"))
		);
	}

}
