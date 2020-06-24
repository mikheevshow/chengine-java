package io.chengine.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandIteratorTest {

	@Test
	public void testCommandIterator() {
		var startTime = System.nanoTime();
		var commandIterator = CommandIterator.getInstance("/news/pews/woonsh#poonsh");
		var elapsedTime = System.nanoTime() - startTime;
		System.out.println("Get iterator elapsed time nano " + elapsedTime);

		assertAll(
			() -> assertEquals("news", commandIterator.next()),
			() -> assertEquals("pews", commandIterator.next()),
			() -> assertEquals("woonsh#poonsh", commandIterator.next())
		);
	}
}
