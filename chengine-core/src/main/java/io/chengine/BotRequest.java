package io.chengine;

import io.chengine.command.Command;

public interface BotRequest {

	boolean isCommand();

	Command getCommand();

}
