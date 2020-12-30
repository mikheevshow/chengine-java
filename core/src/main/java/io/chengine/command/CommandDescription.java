package io.chengine.command;

import java.lang.annotation.*;

/**
 * Annotation is used to add a description to {@link HandleCommand}
 *
 * @author Andrey Borisov
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescription {

    String description() default "";

    /**
     * Internalization property name
     */
    String property() default "";
}
