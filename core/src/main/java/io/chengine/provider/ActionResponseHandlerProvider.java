package io.chengine.provider;

import io.chengine.processor.AbstractActionResponseHandler;

import java.util.List;


public interface ActionResponseHandlerProvider {

    List<AbstractActionResponseHandler> provideAll();

}
