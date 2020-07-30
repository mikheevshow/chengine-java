package io.chengine.connector;

import io.chengine.command.Command;

public interface Message<Identifier> {

	Identifier id();

	boolean containsCommand();

	Command command();

}
