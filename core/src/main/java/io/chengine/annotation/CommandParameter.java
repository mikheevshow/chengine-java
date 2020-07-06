package io.chengine.annotation;

import java.lang.annotation.*;

/**
 * Use this annotation for passing {@link HandleCommand} ({@link io.chengine.command.Command}) parameters into
 * invoked method.
 *
 * @see HandleCommand
 *
 * If chengine could not cast string representation of a command parameter
 * into method argument's type, then {@link ClassCastException} will be thrown.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameter {

	String value();

}
