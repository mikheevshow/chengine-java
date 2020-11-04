package io.chengine.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stage {

    int step() default 0;

    String name() default ""; // TODO связывать c Pipeline по name?

    PipelineStage mode() default PipelineStage.SYNC;
}
