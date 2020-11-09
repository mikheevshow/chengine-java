package io.chengine.annotation;

import io.chengine.annotation.dto.AbstractTrigger;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trigger {

    Class<? extends AbstractTrigger> value();
}
