package io.chengine.interceptor;

public interface RequestInterceptorChainFactoryAware {

    void setRequestInterceptorChainFactory(InterceptorChainFactory interceptorChainFactory);

}
