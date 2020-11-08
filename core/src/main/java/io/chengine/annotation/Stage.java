package io.chengine.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stage {

    int step() default 0; // 0 - entrypoint

    String name() default "";

    StageMode mode() default StageMode.SYNC;
}