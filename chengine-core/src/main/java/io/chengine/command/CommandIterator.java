package io.chengine.command;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * An object which iterates through a command and claw parts of itself into
 * array. You can use {@link Iterator} methods for command passing.
 */
@NotThreadSafe
class CommandIterator implements Iterator<String> {

	private final List<String> parts = new ArrayList<>();
	private Iterator<String> iterator;

	private CommandIterator() {
	}

	public static CommandIterator getInstance(String command) {
		var commandIterator = new CommandIterator();
		var parts = commandIterator.parts;
		command = command.substring(1);
		var continueParse = true;
		while (continueParse) {
			var charArray = command.toCharArray();
			var sb = new StringBuilder();
			for (int i = 0; i < charArray.length; i++) {

				var c = charArray[i];

				if (c != '/') {
					sb.append(c);
				}

				if (c == '/' || i + 1 == charArray.length) {
					parts.add(sb.toString());
					sb = new StringBuilder();
				}

				if (i + 1 == charArray.length) {
					continueParse = false;
				}

				if (c == '/') {
					command = command.substring(i + 1);
					break;
				}
			}
		}
		commandIterator.iterator = commandIterator.parts.iterator();
		return commandIterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public String next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	@Override
	public void forEachRemaining(Consumer<? super String> action) {
		iterator.forEachRemaining(action);
	}
}
