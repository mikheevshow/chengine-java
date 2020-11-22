package io.chengine.handler;

import java.lang.reflect.Method;

public class HandlerMethod {

    private final Object handler;
    private final Method method;

    public HandlerMethod(Object handler, Method method) {
        this.handler = handler;
        this.method = method;
    }

    public Object getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }
}
