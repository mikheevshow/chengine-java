package io.chengine.context;

import io.chengine.connector.BotApiIdentifier;

public interface SecurityContext {

    boolean methodAvailableForApi(BotApiIdentifier identifier);

}
