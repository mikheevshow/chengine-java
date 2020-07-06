package io.chengine.annotation.processor;

import io.chengine.annotation.HandleCommand;
import io.chengine.annotation.Handler;
import io.chengine.connector.BotApiIdentifier;
import io.chengine.method.Method;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;

/**
 * Process {@link HandleCommand} annotation.
 */
public class HandleCommandAnnotationProcessor implements AnnotationProcessor<HandleCommandAnnotationProcessor.Input, HandleCommandAnnotationProcessor.Callback> {

    //****************************************************************************************************************

    public static final class Input {

        private final Collection<?> handlers;
        private final Map<String, Method> commandMethodMap;
        private final Map<java.lang.reflect.Method, String> methodPathMap;

        public Input(
                Collection<?> handlers,
                Map<String, Method> commandMethodMap,
                Map<java.lang.reflect.Method, String> methodPathMap) {

            this.handlers = handlers;
            this.commandMethodMap = commandMethodMap;
            this.methodPathMap = methodPathMap;
        }

    }

    public static final class Callback {}

    //****************************************************************************************************************

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(HandleCommand.class);
    }

    @Override
    public void process(Input input, Consumer<Callback> processingCallback) throws Exception {
        for (var handler : input.handlers) {
            var handlerAnnotationCommandPath = "";
            for (var annotation : handler.getClass().getAnnotations()) {
                if (isHandler(annotation)) {
                    var valueMethod = annotation.annotationType().getMethod("value");
                    handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
                    break;
                } else if (annotatedByHandler(annotation)) { // See contract about inheritance of Handler annotation
                    var valueMethod = annotation.annotationType().getMethod("command");
                    handlerAnnotationCommandPath = valueMethod.invoke(annotation).toString();
                    break;
                }
            }
        }
        processingCallback.accept(new Callback());
    }

    @Nullable
    private Annotation findHandlerAnnotationRecursively(final Annotation[] annotations) {
        if (annotations == null || annotations.length == 0)
            return null;

        for (final Annotation annotation : annotations) {
            if (isHandler(annotation))
                return annotation;
        }

        for (final Annotation annotation : annotations) {
            Annotation a = findHandlerAnnotationRecursively(annotation.annotationType().getAnnotations());
            if (a != null)
                return a;
        }

        return null;
    }

    private boolean annotatedByHandler(Annotation annotation) {
        Annotation[] annotations = annotation.annotationType().getDeclaredAnnotations();
        Annotation handlerAnnotation = findHandlerAnnotationRecursively(annotations);

        return handlerAnnotation != null;
    }

    private boolean isHandler(Annotation annotation) {
        return annotation.annotationType().equals(Handler.class);
    }

    //****************************************************************************************************************
    //
    //								Include/Exclude api processing
    //
    //****************************************************************************************************************

    private void processBotApiInfo(@Nonnull java.lang.reflect.Method method) {
        var handleCommand = method.getAnnotation(HandleCommand.class);
        if (skip(handleCommand)) {
            return;
        }

    }

    private boolean skip(@Nonnull HandleCommand handleCommand) {
        var includeOnly = handleCommand.canBeHandledBy();
        var includeAllExcept = handleCommand.canBeHandledByAllExcept();
        return doesntProcess(includeOnly) && doesntProcess(includeAllExcept);
    }

    private boolean doesntProcess(Class<? extends BotApiIdentifier>[] classes ) {
        return classes.length == 1 && classes[0].isInterface();
    }
}
