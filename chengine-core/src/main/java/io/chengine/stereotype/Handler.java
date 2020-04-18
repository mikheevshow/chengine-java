package io.chengine.stereotype;

import io.chengine.annotations.HandlerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {

	String value() default "";

	HandlerType type() default HandlerType.MESSAGE;

}
