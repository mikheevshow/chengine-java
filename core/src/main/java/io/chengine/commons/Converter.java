package io.chengine.commons;

public interface Converter<F, T> {

    T convert(F from);

}
