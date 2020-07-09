package io.chengine.annotation;

import io.chengine.connector.BotApiIdentifier;

import java.lang.annotation.*;

/**
 * Mark handler methods by this annotation for process a bot command
 *
 * <pre>{@code
 * @Handler
 * public class SomeHandler {
 * 	@HandleCommand("/sayhello")
 *  	public void sayHello() {
 *  		System.out.println("Hello World!");
 *  	}
 * }
 * }
 * </pre>
 *
 * @see Handler
 * @see CommandParameter
 * @see CommandDescription
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleCommand {

	String value() default "";

	Class<? extends BotApiIdentifier>[] onlyFor() default BotApiIdentifier.class;

	Class<? extends BotApiIdentifier>[] forAllExcept() default BotApiIdentifier.class;

}
