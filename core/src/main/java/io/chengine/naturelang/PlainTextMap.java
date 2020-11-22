package io.chengine.naturelang;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PlainTextMap {

  String prefix() default "";

  String value() default "";

  String[] alternateValues() default {};

  boolean ignoreCase() default true;

}
