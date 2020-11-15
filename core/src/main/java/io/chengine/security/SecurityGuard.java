package io.chengine.security;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequest;
import io.chengine.method.HandlerMethod;

public interface SecurityGuard {

    boolean methodAvailableForApi(HandlerMethod handlerMethod, BotApiIdentifier identifier);

    boolean callMethodToEditMessage(HandlerMethod handlerMethod, BotRequest request);

}
