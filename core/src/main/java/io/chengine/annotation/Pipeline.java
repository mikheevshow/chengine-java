package io.chengine.annotation;

import java.lang.annotation.*;

/**
 * Mark pipeline methods by this annotation for process a bot pipeline of commands
 *
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pipeline {

    String name() default "";

}
