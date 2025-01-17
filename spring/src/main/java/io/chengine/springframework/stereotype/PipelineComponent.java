package io.chengine.springframework.stereotype;

import io.chengine.pipeline.annotation.Pipeline;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Component
@Pipeline
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PipelineComponent {

    String value() default "";

    int inactiveTimeout() default 0;

    TimeUnit timeoutUnits() default TimeUnit.SECONDS;

}
