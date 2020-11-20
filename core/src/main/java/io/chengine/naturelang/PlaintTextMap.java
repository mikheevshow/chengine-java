package io.chengine.naturelang;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PlaintTextMap {

  String prefix() default "";

  String map() default "";

  String[] alternateValues() default {};

  boolean ignoreCase() default true;

}
