package io.chengine.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class MethodParameter {

    private final int parameterIndex;
    private final Class<?> parameterType;
    private final Annotation[] parameterAnnotations;
    private final String parameterName;
    private final Parameter parameter;

    public MethodParameter(
            int parameterIndex,
            Class<?> parameterType,
            Annotation[] parameterAnnotations,
            String parameterName,
            Parameter parameter) {

        this.parameterIndex = parameterIndex;
        this.parameterType = parameterType;
        this.parameterAnnotations = parameterAnnotations;
        this.parameterName = parameterName;
        this.parameter = parameter;
    }

    public int getParameterIndex() {
        return parameterIndex;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public Annotation[] getParameterAnnotations() {
        return parameterAnnotations;
    }

    public String getParameterName() {
        return parameterName;
    }

    public Parameter getParameter() {
        return parameter;
    }
}
