package io.chengine.annotation;

import io.chengine.command.HandleCommand;
import io.chengine.handler.DefaultHandlerRegistry;
import io.chengine.handler.Handler;
import io.chengine.handler.HandlerRegistryAware;
import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class HandleCommandAnnotationProcessor implements HandlerRegistryAware, AnnotationProcessor {

    private static final Logger log = LogManager.getLogger(HandleCommandAnnotationProcessor.class);
    private static final Set<Class<? extends Annotation>> supportedHandlerAnnotations = new HashSet<>();
    private DefaultHandlerRegistry handlerRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandlerRegistry(DefaultHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<? extends Annotation>> supports() {
        Set<Class<? extends Annotation>> classes = new HashSet<>();
        classes.add(HandleCommand.class);
        return classes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(Object handler) {
        if (handlerRegistry == null) {
            throw new NullPointerException("Handler registry can't be null");
        }
        if (handler == null) {
            throw new NullPointerException("Handler not defined");
        }
        log.info("Start registering command handlers...");
        for (final Class<? extends Annotation> handlerAnnotation : handlerAnnotations()) {
            if (handler.getClass().isAnnotationPresent(handlerAnnotation)) {
                for (Class<? extends Annotation> handleCommandAnnotation : supports()) {
                    final Method[] methods = handler.getClass().getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(handleCommandAnnotation)) {
                            try {
                                String commandPath;
                                //final Method handlerAnnotationValue = handlerAnnotation.getMethod("value");
                                //commandPath = handlerAnnotationValue.invoke(handlerAnnotation).toString();
                                HandleCommand handleMethodAnnotation = (HandleCommand) method.getAnnotation(handleCommandAnnotation);
                                final Method handleMethodAnnotationValue = handleMethodAnnotation
                                        .annotationType()
                                        .getMethod("value");
                                //commandPath = commandPath + handleMethodAnnotationValue.invoke(handleCommandAnnotation).toString();
                                commandPath = handleMethodAnnotation.value();
                                if ("".equals(commandPath)) {
                                    throw new RuntimeException("Command annotation value can't be empty if Handler annotation value is empty in class " + handler.getClass().getCanonicalName());
                                }
                                final HandlerMethod handlerMethod = HandlerMethod.of(method, handler);
                                handlerRegistry.putCommand(commandPath, handlerMethod);
                                log.info("Command registered: " + commandPath);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }
        }
        log.info("Finish registering command handlers...");
    }

    protected Set<Class<? extends Annotation>> handlerAnnotations() {
        supportedHandlerAnnotations.add(Handler.class);
        return new HashSet<>(supportedHandlerAnnotations);
    }

    public boolean addHandlerAnnotation(Class<? extends Annotation> annotation) {
        return supportedHandlerAnnotations.add(annotation);
    }

    public boolean removeAnnotation(Class<? extends Annotation> annotation) {
        return supportedHandlerAnnotations.remove(annotation);
    }
}
