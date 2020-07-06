package io.chengine.method;

import io.chengine.connector.BotApiIdentifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A wrap around {@link java.lang.reflect.Method} class. Contains "invoke" methods
 * which are throwing {@link MethodInvocationException}.
 */
public class Method {

    private final java.lang.reflect.Method method;
    private final Object object;
    private final Class<?> objectClass;
    private final Map<String, Boolean> apiAccessMap;

    public static Method of(
            @Nonnull java.lang.reflect.Method method,
            @Nonnull Object object,
            @Nullable Map<String, Boolean> apiAccessMap
    ) {

        Objects.requireNonNull(method, "Method can't be null");
        Objects.requireNonNull(object, "Object can't be null");

        var containsMethod = Arrays
                .asList(object.getClass().getDeclaredMethods())
                .contains(method);

        if (!containsMethod) {
            throw new RuntimeException("Error creating method wrap. Method " + method.getName() + " doesn't belong to object with class" + object.getClass().getName());
        }

        return new Method(method, object, Objects.requireNonNullElse(apiAccessMap, new HashMap<>()));
    }

    private Method(java.lang.reflect.Method method, Object object, Map<String, Boolean> apiAccessMap) {
        this.method = method;
        this.object = object;
        this.objectClass = object.getClass();
        this.apiAccessMap = apiAccessMap;
    }

    public <T> T invokeChecked(Class<T> clazz, Object ... args) throws InvocationTargetException, IllegalAccessException {
        var result = this.method.invoke(object, args);
        return clazz.cast(result);
    }

    public <T> T invoke(Class<T> clazz, Object ... args) {
        try {
            return invokeChecked(clazz, args);
        } catch (Exception ex) {
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

    public boolean availableForBotApi(BotApiIdentifier botApiIdentifier) {
        return availableForBotApi(botApiIdentifier.identifier());
    }

    public boolean availableForBotApi(String botApiIdentifier) {
        return apiAccessMap.getOrDefault(botApiIdentifier, false);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method method1 = (Method) o;
        return method.equals(method1.method) &&
                object.equals(method1.object);
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
                '}';
    }
}
