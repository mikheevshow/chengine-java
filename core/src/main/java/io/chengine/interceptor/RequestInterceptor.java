package io.chengine.interceptor;

import io.chengine.connector.BotRequestContext;

public interface RequestInterceptor {

    void intercept(BotRequestContext requestContext, RequestInterceptorChain chain);

}
