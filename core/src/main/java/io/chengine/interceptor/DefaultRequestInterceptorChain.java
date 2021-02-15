package io.chengine.interceptor;

import io.chengine.connector.BotRequestContext;

import java.util.Iterator;
import java.util.List;

public class DefaultRequestInterceptorChain implements RequestInterceptorChain {

    private final Iterator<RequestInterceptor> requestInterceptors;

    public DefaultRequestInterceptorChain(List<RequestInterceptor> requestInterceptors) {
        this.requestInterceptors = requestInterceptors.iterator();
    }

    @Override
    public void doIntercept(BotRequestContext requestContext) {
        if (requestInterceptors.hasNext()) {
            RequestInterceptor requestInterceptor = requestInterceptors.next();
            requestInterceptor.intercept(requestContext, this);
        }
    }
}
