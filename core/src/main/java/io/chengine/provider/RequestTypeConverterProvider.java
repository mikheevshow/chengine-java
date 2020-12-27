package io.chengine.provider;

import io.chengine.commons.RequestTypeConverter;

import java.util.List;

public interface RequestTypeConverterProvider {

    List<RequestTypeConverter> provideAll();

}
