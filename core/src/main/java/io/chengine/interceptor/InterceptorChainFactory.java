package io.chengine.interceptor;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChainFactory {

    private final List<RequestInterceptor> requestInterceptors = new ArrayList<>();

    public InterceptorChainFactory(List<RequestInterceptor> requestInterceptors) {
        this.requestInterceptors.addAll(requestInterceptors);
    }

    public RequestInterceptorChain getChain() {
        return new DefaultRequestInterceptorChain(requestInterceptors);
    }

}
