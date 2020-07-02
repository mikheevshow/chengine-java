package io.chengine.command;

import javax.annotation.Nullable;
import java.util.*;

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

	static class CommandBuilder {

		private final Map<String, String> parameterMap = new LinkedHashMap<>();

		public CommandBuilder put(String param) {
			this.put(param, null);
			return this;
		}

		public CommandBuilder put(String param, String value) {
			parameterMap.put(param, value);
			return this;
		}

		public Command build() {
			var command = new StringBuilder();
			var paramMap = new HashMap<String, String>();
			parameterMap.forEach((key, value) -> {
				command.append("/").append(key);
				if (value != null) {
					command.append("#").append(value);
					paramMap.put(key, value);
				}
			});
			return new Command(command.toString(), paramMap);
		}
	}

	public static CommandBuilder builder() {
		return new CommandBuilder();
	}

	public String path() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Command command1 = (Command) o;
		return command.equals(command1.command) &&
				Objects.equals(params, command1.params);
	}

	@Override
	public int hashCode() {
		return Objects.hash(command, params);
	}

	@Override
	public String toString() {
		return "Command{" +
				"command='" + command + '\'' +
				", params=" + params +
				'}';
	}
}
