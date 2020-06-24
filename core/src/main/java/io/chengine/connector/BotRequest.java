package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.user.User;

public interface BotRequest {

	boolean isCommand();

	Command getCommand();

	User getUser();

}
