package io.chengine.annotation.processor;

import io.chengine.annotation.TelegramHandleMedia;
import io.chengine.handler.HandlerMethod;
import io.chengine.handler.HandlerVisitor;

import java.lang.reflect.Method;

public class TelegramHandleMediaAnnotationProcessor implements HandlerVisitor {
    @Override
    public void visitHandler(HandlerMethod handler) {
        final Method method = handler.getMethod();
        if (method.isAnnotationPresent(TelegramHandleMedia.class)) {

        }
    }
}
