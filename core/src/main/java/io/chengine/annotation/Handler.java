package io.chengine.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {

	String value() default "";

	HandlerType type() default HandlerType.DEFAULT;
}
