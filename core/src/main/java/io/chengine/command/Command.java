package io.chengine.command;

import javax.annotation.Nullable;
import java.util.*;

public class Command {

    private final String command;
    private final String path;
    private final Map<String, String> params;

    private Command(String command, String path, Map<String, String> params) {
        this.command = command;
        this.path = path;
        this.params = params;
    }

    public static Command fromLinkedMap(LinkedHashMap<String, String> map) {
        if (map.isEmpty())
            return Command.empty();

        final CommandBuilder builder = Command.builder();
        map.forEach(builder::put);
        return builder.build();
    }

    public static class CommandBuilder {

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
            var path = new StringBuilder();
            var paramMap = new HashMap<String, String>();
            if (parameterMap.isEmpty()) {
                throw new RuntimeException("Can't be");
            }
            parameterMap.forEach((key, value) -> {
                command.append("/").append(key);
                path.append("/").append(key);
                if (value != null) {
                    command.append("#").append(value);
                    path.append("#");
                    paramMap.put(key, value);
                }
            });

            return new Command(command.toString(), path.toString(), paramMap);
        }
    }

    public static CommandBuilder builder() {
        return new CommandBuilder();
    }

    public String command() {
        return command;
    }

    public String path() {
        return path;
    }

    public static Command empty() {
        return new Command("/", "/", Collections.emptyMap());
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

    public Iterator<String> iterator() {
        return CommandIterator.getInstance(this.command);
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
                ", path='" + path + '\'' +
                '}';
    }
}
