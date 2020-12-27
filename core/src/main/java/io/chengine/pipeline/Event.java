package io.chengine.pipeline;

import io.chengine.connector.BotRequestContext;

public interface Event {

    BotRequestContext botRequest();

}
