package io.chengine.security;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequest;
import io.chengine.method.Method;

public interface SecurityGuard {

    boolean methodAvailableForApi(Method method, BotApiIdentifier identifier);

    boolean methodCallingForEditMessage(Method method, BotRequest request);

}
