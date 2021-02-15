package io.chengine.pipeline.annotation;

import io.chengine.pipeline.EventTrigger;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trigger {

    String command() default "";

    Class<? extends EventTrigger> event() default EventTrigger.class;

}
