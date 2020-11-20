package io.chengine.pipeline;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trigger {

    String command() default "";

    Class<? extends EventTrigger> event() default EventTrigger.class;

}
