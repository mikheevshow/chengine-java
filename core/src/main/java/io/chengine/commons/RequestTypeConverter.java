package io.chengine.commons;

/**
 * Use this converter to generify business logic
 *
 * @param <T> - object type from bot api
 * @param <G> - handler method type
 */
public interface RequestTypeConverter<T, G> extends Converter<T, G> {}
