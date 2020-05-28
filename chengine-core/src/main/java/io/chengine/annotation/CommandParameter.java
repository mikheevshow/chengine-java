package io.chengine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation for passing {@link Command} parameters into
 * invoked method.
 *
 * @see Command
 *
 * If chengine could not cast string representation of a command parameter
 * into method argument's type, then {@link ClassCastException} will be thrown.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameter {

	String value();

}
