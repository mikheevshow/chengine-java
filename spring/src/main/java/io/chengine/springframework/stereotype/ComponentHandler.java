package io.chengine.springframework.stereotype;

import io.chengine.annotation.Handler;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * You can use this annotation instead {@link Handler @Handler} for providing
 * abilities of using spring dependency injection mechanism.
 *
 * All handlers which will be annotated by this annotation also will be captured by
 * spring application context.
 *
 * @see Handler
 * @see Component
 *
 */
@Component
@Handler
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentHandler {

	String command() default "";

}

