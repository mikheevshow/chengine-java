package io.chengine.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleCommand {

	String value() default "";

	String[] canBeHandledFromBotsWithQualifier() default "";

	Class<? extends BotApiIdentifier>[] canBeReceivedFromApi() default BotApiIdentifier.class;

	Class<? extends BotApiIdentifier>[] canBeHandledByAllExcept() default BotApiIdentifier.class;

}
