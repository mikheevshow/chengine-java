package io.chengine.command;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

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

	public boolean hasParams() {
		return params != null && !params.isEmpty();
	}

	public Set<String> getParamSet() {
		if (params == null) {
			return Collections.emptySet();
		} else {
			return Set.copyOf(params.keySet());
		}
	}

	@Nullable
	public String getParam(String key) {
		return params == null ? null : params.get(key);
	}

}
