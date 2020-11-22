package io.chengine.security;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequest;
import io.chengine.method.HandlerMethod;

public class DefaultSecurityGuard implements SecurityGuard {

    @Override
    public boolean methodAvailableForApi(HandlerMethod handlerMethod, BotApiIdentifier identifier) {
       final var definition = handlerMethod.definition();
        return definition.containApi(identifier);
    }

    @Override
    public boolean callMethodToEditMessage(HandlerMethod handlerMethod, BotRequest request) {
        return request.isCallback() && handlerMethod.definition().isForEditContextualMessage();
    }
}
