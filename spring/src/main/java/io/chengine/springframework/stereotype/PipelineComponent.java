package io.chengine.springframework.stereotype;

import io.chengine.handler.Handler;
import io.chengine.pipeline.PipelineHandlerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Handler(type = PipelineHandlerType.TYPE)
public @interface PipelineComponent {

    int inactiveTimeout() default 0;

    TimeUnit timeoutUnits() default TimeUnit.SECONDS;

}
