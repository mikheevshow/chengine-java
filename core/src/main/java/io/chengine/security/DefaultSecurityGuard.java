package io.chengine.security;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequest;
import io.chengine.method.Method;

public class DefaultSecurityGuard implements SecurityGuard {

    @Override
    public boolean methodAvailableForApi(Method method, BotApiIdentifier identifier) {
       final var definition = method.definition();
        return definition.containApi(identifier);
    }

    @Override
    public boolean callMethodToEditMessage(Method method, BotRequest request) {
        return request.isCallback() && method.definition().isForEditContextualMessage();
    }
}
