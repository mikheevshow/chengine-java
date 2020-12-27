package io.chengine.method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * A wrap around {@link Method} class. Contains "invoke" methods
 * which are throwing {@link MethodInvocationException}.
 */
public class HandlerMethod {

    private final Logger log = LogManager.getLogger(HandlerMethod.class);

    private final Method method;
    private  MethodParameter[] parameters;
    private final Object object;
    private final Class<?> objectClass;

    public static HandlerMethod of(
            @Nonnull java.lang.reflect.Method method,
            @Nonnull Object object) {

        Objects.requireNonNull(method, "Method can't be null");
        Objects.requireNonNull(object, "Object can't be null");

        var containsMethod = Arrays
                .asList(object.getClass().getDeclaredMethods())
                .contains(method);

        if (!containsMethod) {
            throw new MethodInstantinationException("Error creating method wrap. Method " + method.getName() + " doesn't belong to object with class" + object.getClass().getName());
        }

        return new HandlerMethod(method, object);
    }

    private HandlerMethod(java.lang.reflect.Method method, Object object) {
        this.method = method;
        this.object = object;
        this.objectClass = object.getClass();

        fillMethodParameters();
    }

    public <T> T invokeChecked(Class<T> clazz, Object ... args) throws InvocationTargetException, IllegalAccessException {
        var result = this.method.invoke(object, args);
        return clazz.cast(result);
    }

    public <T> T invoke(Class<T> clazz, Object ... args) {
        try {
            return invokeChecked(clazz, args);
        } catch (InvocationTargetException | IllegalAccessException ex) {
            throw new MethodInvocationException(ex);
        }
    }

    public Object invoke(Object ... args) {
        return invoke(Object.class, args);
    }

    public Object invokeChecked(Object ... args) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(object, args);
    }

    public void invokeVoid(Object ... args) {
        invoke(Void.class, args);
    }

    public java.lang.reflect.Method get() {
        return this.method;
    }

    public Object onObject() {
        return this.object;
    }

    public boolean belongsTo(Object object) {
        return objectClass.isInstance(object) || belongsTo(object.getClass());
    }

    public boolean belongsTo(Class<?> clazz) {
        return clazz != null && objectClass.isAssignableFrom(clazz);
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    private void fillMethodParameters() {
        parameters = new MethodParameter[method.getParameterCount()];
        for (int i = 0; i < method.getParameterCount(); i++) {
            final Parameter parameter = method.getParameters()[i];
            parameters[i] = new MethodParameter(i, parameter.getType(), parameter.getAnnotations(), parameter.getName(), parameter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerMethod handlerMethod1 = (HandlerMethod) o;
        return method.equals(handlerMethod1.method) &&
                object.equals(handlerMethod1.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, object);
    }

    @Override
    public String toString() {
        return "Method{" +
                "method=" + method +
                ", object=" + object +
                ", objectClass=" + objectClass +
                '}';
    }
}
