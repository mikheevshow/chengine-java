package io.chengine.interceptor;

import io.chengine.connector.BotRequestContext;

public interface RequestInterceptorChain {
    void doIntercept(BotRequestContext requestContext);
}
