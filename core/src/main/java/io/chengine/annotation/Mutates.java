package io.chengine.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Mutates {

    Class<?>[] by() default Object.class;

}
