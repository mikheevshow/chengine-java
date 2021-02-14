package io.chengine.interceptor;

import io.chengine.connector.BotRequestContext;

import java.util.Arrays;
import java.util.Iterator;

public class DefaultRequestInterceptorChain implements RequestInterceptorChain {

    private final Iterator<RequestInterceptor> requestInterceptors;

    public DefaultRequestInterceptorChain(RequestInterceptor... requestInterceptors) {
        this.requestInterceptors = Arrays.asList(requestInterceptors).iterator();
    }

    @Override
    public void doIntercept(BotRequestContext requestContext) {
        if (requestInterceptors.hasNext()) {
            RequestInterceptor requestInterceptor = requestInterceptors.next();
            requestInterceptor.intercept(requestContext, this);
        }
    }
}
