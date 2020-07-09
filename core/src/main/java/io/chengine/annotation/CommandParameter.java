package io.chengine.annotation;

import java.lang.annotation.*;

/**
 * Use this annotation for passing {@link HandleCommand} ({@link io.chengine.command.Command}) parameters into
 * invoked method.
 * <p>
 * If chengine could not cast string representation of a command parameter
 * into method argument's type, then runtime exception will be thrown.
 *
 * @see HandleCommand
 *
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameter {

	String value();

}
