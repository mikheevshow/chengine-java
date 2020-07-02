package io.chengine.method;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

/**
 * A wrap around {@link java.lang.reflect.Method} class. Contains "invoke" methods
 * which are throwing {@link MethodInvocationException}.
 */
public class Method {

    private final java.lang.reflect.Method method;
    private final Object object;

    public static Method of(@Nonnull java.lang.reflect.Method method, @Nonnull Object object) {

        Objects.requireNonNull(method, "Method can't be null");
        Objects.requireNonNull(object, "Object can't be null");

        var containsMethod = Arrays
                .asList(object.getClass().getDeclaredMethods())
                .contains(method);

        if (!containsMethod) {
            throw new RuntimeException("Error creating method wrap. Method " + method.getName() + " doesn't belong to object with class" + object.getClass().getName());
        }

        return new Method(method, object);
    }

    private Method(java.lang.reflect.Method method, Object object) {
        this.method = method;
        this.object = object;
    }

    public <T> T invoke(Class<T> clazz, Object ... args) {
        try {
            var result = this.method.invoke(object, args);
            return clazz.cast(result);
        } catch (Exception ex) {
            throw new MethodInvocationException(ex);
        }
    }

    public Object invoke(Object ... args) {
        return invoke(Object.class, args);
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
        return belongsTo(object.getClass());
    }

    public boolean belongsTo(Class<?> clazz) {
        return this.object.getClass().equals(clazz);
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
