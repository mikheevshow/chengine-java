package io.chengine;

import javax.annotation.Nullable;
import java.util.Map;

public class Command {

	private final String command;
	private final Map<String, String> params;

	public Command(String command, Map<String, String> params) {
		this.command = command;
		if (!params.isEmpty()) {
			this.params = params;
		} else {
			this.params = null;
		}
	}

	public String getCommand() {
		return command;
	}

	@Nullable
	public Map<String, String> getParams() {
		return params;
	}
}
