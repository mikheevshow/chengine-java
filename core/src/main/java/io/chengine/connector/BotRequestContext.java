package io.chengine.connector;

import io.chengine.command.Command;
import io.chengine.commons.RequestTypeConverter;
import io.chengine.session.SessionKey;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

public interface BotRequestContext {

    boolean contains(Class<?> clazz);

    /**
     * Gets specified object from bot request context
     *
     * @param clazz - class which need to extract from the request context
     * @param <T>   - class type
     * @return - object of the required type or null
     */
    @Nullable
    <T> T get(Class<T> clazz);

    /**
     * Returns information about api that made the request
     *
     * @return - api identifier
     */
    BotApiIdentifier getApiBotIdentifier();

    /**
     * Returns session key extracted in {@link io.chengine.session.SessionRequestInterceptor}
     *
     * @return - session key
     * @see SessionKey
     * @see io.chengine.session.SessionKeyExtractor
     * @see io.chengine.session.SessionRequestInterceptor
     */
    SessionKey getSessionKey();

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
