package io.chengine.processor;

import io.chengine.command.Command;
import io.chengine.command.HandleCommand;
import io.chengine.connector.BotRequestContext;
import io.chengine.handler.HandlerNotFoundException;
import io.chengine.handler.HandlerRegistry;
import io.chengine.method.HandlerMethod;
import io.chengine.method.NoSuchMethodException;

import java.util.Objects;

public class DefaultMethodResolver implements MethodResolver {

    private final HandlerRegistry handlerRegistry;

    public DefaultMethodResolver(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public HandlerMethod resolve(BotRequestContext request) {
        if (HandleCommand.class.equals(request.shouldBeHandledByAnnotation())) {
            final Command command = Objects.requireNonNull(request.getCommand());
            final HandlerMethod handlerMethod = handlerRegistry.get(command);
            if (handlerMethod == null) {
                throw new HandlerNotFoundException("No method found matching command '" + command.path() + "'");
            }

            return handlerMethod;
        }

        final HandlerMethod handlerMethod = handlerRegistry.getSingleByAnnotationClass(request.shouldBeHandledByAnnotation());

        if (handlerMethod == null) {
            throw new NoSuchMethodException("Can't find processing method for handle request");
        }

        return handlerMethod;
    }
}
