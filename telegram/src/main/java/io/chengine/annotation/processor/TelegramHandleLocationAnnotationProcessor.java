package io.chengine.annotation.processor;

import io.chengine.annotation.TelegramHandleLocation;
import io.chengine.handler.HandlerMethod;
import io.chengine.handler.HandlerVisitor;

import java.lang.reflect.Method;

public class TelegramHandleLocationAnnotationProcessor implements HandlerVisitor {
    @Override
    public void visitHandler(HandlerMethod handler) {
        final Method method = handler.getMethod();
        if (method.isAnnotationPresent(TelegramHandleLocation.class)) {

        }
    }
}
