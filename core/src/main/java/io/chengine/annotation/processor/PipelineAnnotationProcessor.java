package io.chengine.annotation.processor;

import io.chengine.annotation.Handler;
import io.chengine.annotation.HandlerType;
import io.chengine.annotation.Stage;
import io.chengine.annotation.Trigger;
import io.chengine.pipeline.Pipeline;
import io.chengine.method.Method;
import io.chengine.method.MethodDefinition;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PipelineAnnotationProcessor implements AnnotationProcessor<PipelineAnnotationProcessor.Input, Object> {

    private final Predicate<Object> isPipeline = (obj) ->
        obj.getClass().getAnnotation(Handler.class).type().equals(HandlerType.PIPELINE);
    private final Predicate<Object> isStage = (obj) ->
        obj.getClass().isAnnotationPresent(Stage.class);

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(Handler.class);
    }

    @Override
    public void process(Input input, Consumer<Object> processingCallback) throws Exception {
        var pipelines = input.handlers.stream()
            .filter(isPipeline)
            .collect(Collectors.toList());

        var pipelineSet = pipelines.stream()
            .map(pipe -> {
                var methods = pipe.getClass().getMethods();
                var stages = Arrays.stream(methods)
                    .filter(isStage)
                    .map(method -> objectToStage(pipe, method))
                    .collect(Collectors.toList());

                return new Pipeline(
                    pipe.getClass().getAnnotation(Handler.class).value(),
                    pipe.getClass(),
                    stages,
                    stages.size(),
                    pipe.getClass().getAnnotation(Trigger.class).value()
                );
            })
            .collect(Collectors.toSet());

        input.pipelineSet.addAll(pipelineSet);

    }

    private io.chengine.pipeline.Stage objectToStage(Object obj, java.lang.reflect.Method method) {
        return new io.chengine.pipeline.Stage(
            obj.getClass().getAnnotation(Stage.class).name(),
            Method.of(method, obj, new MethodDefinition(null, false)),
            obj.getClass().getAnnotation(Stage.class).step(),
            obj.getClass().getAnnotation(Stage.class).mode()
        );
    }

    public static final class Input {

        private final Collection<?> handlers;
        private final Set<Pipeline> pipelineSet;

        public Input(
            Collection<?> handlers, Set<Pipeline> pipelineSet) {
            this.handlers = handlers;
            this.pipelineSet = pipelineSet;
        }

    }
}
