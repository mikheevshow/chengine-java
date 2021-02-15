package io.chengine.pipeline.annotation;

import io.chengine.handler.Handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Handler
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pipeline {

    String value() default "";

    int inactiveTimeout() default 0;

    TimeUnit timeoutUnits() default TimeUnit.SECONDS;

}
