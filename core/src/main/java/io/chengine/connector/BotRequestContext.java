package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.commons.RequestTypeConverter;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

public interface BotRequestContext {

    boolean contains(Class<?> clazz);

    Object get(Class<?> clazz);

    BotApiIdentifier getApiBotIdentifier();

    Class<? extends Annotation> shouldBeHandledByAnnotation();

    /**
     * Returns a command for handle, nonnull if {@link BotRequestContext#shouldBeHandledByAnnotation()}
     * returns {@link io.chengine.command.HandleCommand}
     *
     * @return - command
     */
    @Nullable
    Command getCommand();

    boolean hasConverterToType(Class<?> clazz);

    RequestTypeConverter<Object, Object> getConverterToType(Class<?> clazz);

}
