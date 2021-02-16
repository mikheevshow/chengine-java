package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.command.HandleCommand;
import io.chengine.commons.RequestTypeConverter;
import io.chengine.session.SessionKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultBotRequestContext implements BotRequestContext {

    private final Map<Class<?>, Object> typeObjectMap = new HashMap<>();
    private final Map<Class<?>, RequestTypeConverter<Object, Object>> classConverterMap = new HashMap<>();
    private BotApiIdentifier botApiIdentifier;
    private SessionKey sessionKey;
    private Class<? extends Annotation> handleAnnotation;
    private Command command;

    @Override
    public boolean contains(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }

        return typeObjectMap.containsKey(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }

        return (T) typeObjectMap.get(clazz);
    }

    @Override
    public BotApiIdentifier getApiBotIdentifier() {
        return this.botApiIdentifier;
    }

    @Override
    public SessionKey getSessionKey() {
        return this.sessionKey;
    }

    @Nullable
    @Override
    public Command getCommand() {
        if (HandleCommand.class.equals(shouldBeHandledByAnnotation()) && command == null) {
            throw new RuntimeException("Request should be a command, but it doesn't.");
        }

        return command;
    }

    @Override
    public boolean hasConverterToType(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }

        return classConverterMap.containsKey(clazz);
    }

    @Override
    public RequestTypeConverter<Object, Object> getConverterToType(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }

        return classConverterMap.get(clazz);
    }

    @Override
    public Class<? extends Annotation> shouldBeHandledByAnnotation() {
        return handleAnnotation;
    }

    public void add(Class<?> clazz, Object object) {
        typeObjectMap.put(clazz, object);
    }

    public void add(Class<?> clazz, RequestTypeConverter<Object, Object> converter) {
        classConverterMap.put(clazz, converter);
    }

    public void setSessionKey(SessionKey sessionKey) {
        if (sessionKey == null) {
            throw new IllegalArgumentException("Session key can't be null");
        }

        this.sessionKey = sessionKey;
    }

    public void setBotApiIdentifier(@Nonnull BotApiIdentifier apiIdentifier) {
        Objects.requireNonNull(apiIdentifier, "Bot api identifier can't be null");
        this.botApiIdentifier = apiIdentifier;
    }

    public void setHandleAnnotation(Class<? extends Annotation> handleAnnotation) {
        this.handleAnnotation = handleAnnotation;
    }

    public void setCommand(@Nonnull Command command) {
        Objects.requireNonNull(command, "Command can't be null");
        this.command = command;
    }
}
