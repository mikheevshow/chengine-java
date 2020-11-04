package io.chengine.annotation.processor;

import io.chengine.annotation.Pipeline;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * Process {@link io.chengine.annotation.Pipeline} annotation.
 */
public class PipelineAnnotationProcessor implements AnnotationProcessor<PipelineAnnotationProcessor.Input, Object> {

    private static final Logger log = LogManager.getLogger(PipelineAnnotationProcessor.class);

    public static final class Input {

        private final Collection<?> pipelines;

        public Input(
            Collection<?> pipelines
        ) {
            this.pipelines = pipelines;
        }

    }

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(Pipeline.class);
    }

    @Override
    public void process(PipelineAnnotationProcessor.Input input, Consumer<Object> processingCallback) throws Exception {
        log.info("Start processing \"@Pipeline\" annotation...");

        for (var pipeline : input.pipelines) {

        }
    }

    private boolean isPipeline() {
        return false;
    }

}
