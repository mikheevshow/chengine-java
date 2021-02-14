package io.chengine.annotation;

import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public abstract class AbstractSingleHandlerAnnotationProcessor extends AbstractAnnotationProcessorHandlerRegistryAware {

    private static final Logger log = LogManager.getLogger(AbstractSingleHandlerAnnotationProcessor.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(Object handler) {
        if (handlerRegistry == null) {
            throw new NullPointerException("Handler registry not defined");
        }
        if (handler == null) {
            throw new NullPointerException("Handler not defined");
        }
        supports().forEach(annotation -> Arrays.stream(handler.getClass().getMethods())
                .forEach(method -> {
                    if (method.isAnnotationPresent(annotation)) {
                        HandlerMethod handlerMethod = HandlerMethod.of(method, handler);
                        handlerRegistry.putSingleHandler(annotation, handlerMethod);
                        log.info("Handler \"" + annotation.getName() + "\" registered");
                    }
                })
        );
    }

}
