package io.chengine.method;

import io.chengine.command.Command;

import java.lang.reflect.Method;

public interface HandlerMethodRegistry {

    Command getByReflectMethod(Method method);

    HandlerMethod getHandlerMethodByCommand(Command command);

    HandlerMethod getSingleHandlerMethodByAnnotationClass(Class<?> clazz);

}
