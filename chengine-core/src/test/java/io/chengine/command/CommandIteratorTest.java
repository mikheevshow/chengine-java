package io.chengine.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandIteratorTest {

	@Test
	public void testCommandIterator() {
		var commandIterator = CommandIterator.get("/news/pews/woonsh#poonsh");
		assertAll(
			() -> assertEquals("news", commandIterator.next()),
			() -> assertEquals("pews", commandIterator.next()),
			() -> assertEquals("woonsh#poonsh", commandIterator.next())
		);
	}
}
