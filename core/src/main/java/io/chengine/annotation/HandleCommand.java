package io.chengine.annotation;

import io.chengine.connector.BotApiIdentifier;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleCommand {

	String value() default "";

	Class<? extends BotApiIdentifier>[] canBeHandledBy() default BotApiIdentifier.class;

	Class<? extends BotApiIdentifier>[] canBeHandledByAllExcept() default BotApiIdentifier.class;

}
