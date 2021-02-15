package io.chengine.pipeline.annotation;

import io.chengine.pipeline.StageMode;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stage {

    int step();

    String name();

    StageMode mode() default StageMode.SYNC;
}