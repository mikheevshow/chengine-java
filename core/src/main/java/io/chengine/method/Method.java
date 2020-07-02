package io.chengine.method;

import java.util.Objects;

public class Method {

    private final java.lang.reflect.Method method;
    private final Object object;

    public Method(java.lang.reflect.Method method, Object object) {
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

    public void invokeVoid(Object ... args) {
        invoke(Void.class, args);
    }

    public java.lang.reflect.Method get() {
        return this.method;
    }

    public Object onObject() {
        return this.object;
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
