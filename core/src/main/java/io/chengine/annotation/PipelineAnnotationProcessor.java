package io.chengine.annotation;

import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.handler.HandlerRegistryAware;
import io.chengine.method.HandlerMethod;
import io.chengine.pipeline.PipelineDefinition;
import io.chengine.pipeline.StageDefinition;
import io.chengine.pipeline.StageMode;
import io.chengine.pipeline.annotation.Pipeline;
import io.chengine.pipeline.annotation.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class PipelineAnnotationProcessor implements AnnotationProcessor, HandlerRegistryAware {

    private static final Logger log = LogManager.getLogger(PipelineAnnotationProcessor.class);

    private DefaultHandlerRegistry handlerRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<? extends Annotation>> supports() {
        Set<Class<? extends Annotation>> classes = new HashSet<>();
        classes.add(Stage.class);
        return classes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(Object handler) {
        if (handlerRegistry == null) {
            throw new NullPointerException("Handler registry is null");
        }
        final Class<?> handlerClass = handler.getClass();
        if (handlerClass.isAnnotationPresent(Pipeline.class)) {
            final Pipeline pipelineAnnotation = handlerClass.getAnnotation(Pipeline.class);
            String pipelineName = pipelineAnnotation.value();
            if ("".equals(pipelineName)) {
                pipelineName = handler.getClass().getName();
            }
            log.info("Start register pipeline: \"{}\"", pipelineName);
            final int inactiveTimeout = pipelineAnnotation.inactiveTimeout();
            final TimeUnit timeoutUnits = pipelineAnnotation.timeoutUnits();
            final List<StageDefinition> stageDefinitions = stageDefinitions(handler);
            final PipelineDefinition pipelineDefinition = new PipelineDefinition(
                    pipelineName,
                    handlerClass,
                    inactiveTimeout,
                    timeoutUnits,
                    stageDefinitions,
                    null
            );

            handlerRegistry.putPipeline(pipelineName, pipelineDefinition);
            log.info("Pipeline registered: \"{}\"", pipelineName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandlerRegistry(DefaultHandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            throw new NullPointerException("Handler registry can't be null");
        }
        this.handlerRegistry = handlerRegistry;
    }

    private List<StageDefinition> stageDefinitions(Object handler) {
        final List<StageDefinition> stageDefinitions = new ArrayList<>();
        for (final Class<? extends Annotation> annotation : supports()) {
            final Method[] methods = handler.getClass().getMethods();
            for (final Method method : methods) {
                if (method.isAnnotationPresent(annotation)) {
                    final Stage stage = (Stage) method.getAnnotation(annotation);
                    String stageName = stage.name();
                    if ("".equals(stageName)) {
                        stageName = method.getName();
                    }
                    final int step = stage.step();
                    final StageMode stageMode = stage.mode();
                    final HandlerMethod handlerMethod = HandlerMethod.of(method, handler);
                    final StageDefinition stageDefinition = new StageDefinition(
                            stageName,
                            handlerMethod,
                            step,
                            stageMode
                    );

                    stageDefinitions.add(stageDefinition);
                    log.info("Stage added name: \"{}\", step: \"{}\"", stageName, step);
                }
            }
        }

        return stageDefinitions;
    }
}
