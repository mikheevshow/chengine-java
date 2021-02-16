package io.chengine.pipeline.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stage {

    int step();

    String name();

    StageMode mode() default StageMode.SYNC;

}