package io.chengine.method;

import io.chengine.BotRequest;
import io.chengine.BotResponse;
import io.chengine.command.Command;

public enum InjectableType {

	BOOLEAN(Boolean.TYPE),
	LONG(Long.TYPE),
	INTEGER(Integer.TYPE),
	ENUM(Enum.class),
	STRING(String.class),
	COMMAND(Command.class),
	BOT_REQUEST(BotRequest.class),
	BOT_RESPONSE(BotResponse.class);

	private final Class<?> injectableClass;
	// HotSpot optimization
	private final InjectableType[] injectableTypes = InjectableType.values();

	InjectableType(Class<?> injectableClass) {
		this.injectableClass = injectableClass;
	}

	public Class<?> getInjectableClass() {
		return injectableClass;
	}
}
