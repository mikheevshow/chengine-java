package io.chengine.handler;

import io.chengine.command.HandleCommand;
import io.chengine.connector.BotApiIdentifier;
import io.chengine.method.HandlerMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;

/**
 * Process {@link HandleCommand} annotation.
 */
public class HandleCommandAnnotationProcessor implements AnnotationProcessor<HandleCommandAnnotationProcessor.Input, Object> {

    private static final Logger log = LogManager.getLogger(HandleCommandAnnotationProcessor.class);

    //****************************************************************************************************************

    public static final class Input {

        private final Collection<?> handlers;
        private final Map<String, HandlerMethod> commandMethodMap;
        private final Map<java.lang.reflect.Method, String> methodPathMap;

        public Input(
                Collection<?> handlers,
                Map<String, HandlerMethod> commandMethodMap,
                Map<java.lang.reflect.Method, String> methodPathMap) {

            this.handlers = handlers;
            this.commandMethodMap = commandMethodMap;
            this.methodPathMap = methodPathMap;
        }

    }

    //****************************************************************************************************************

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(HandleCommand.class);
    }

    @Override
    public void process(Input input, Consumer<Object> processingCallback) throws Exception {
        log.info("Start processing \"@HandleCommand\" annotation...");
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

            String finalHandlerAnnotationCommandPath = handlerAnnotationCommandPath;

            for (var method : handler.getClass().getMethods()) {
                var annotation = method.getAnnotation(HandleCommand.class);
                if (annotation != null) {
                    var valueMethod = annotation.annotationType().getMethod("value");
                    var annotationCommandPathTemplate = valueMethod.invoke(annotation).toString();
                    var fullMethodCommandPathTemplate = finalHandlerAnnotationCommandPath + annotationCommandPathTemplate;


                    if ("".equals(fullMethodCommandPathTemplate)) {
                        throw new RuntimeException("Command annotation value can't be empty if Handler annotation value is empty in class " + handler.getClass().getCanonicalName());
                    }

                    if (input.commandMethodMap.containsKey(fullMethodCommandPathTemplate)) {
                        throw new RuntimeException("Duplicate of methods with parameter " + fullMethodCommandPathTemplate);
                    }

                    var methodDefinition = MethodDefinitionCreator.create(method);
                    var chengineMethod = HandlerMethod.of(method, handler, methodDefinition);
                    input.commandMethodMap.put(fullMethodCommandPathTemplate, chengineMethod);
                    input.methodPathMap.put(chengineMethod.get(), fullMethodCommandPathTemplate);
                }
            }

            processingCallback.accept(null);
        }
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
        var includeOnly = handleCommand.onlyFor();
        var includeAllExcept = handleCommand.forAllExcept();
        return doesntProcess(includeOnly) && doesntProcess(includeAllExcept);
    }

    private boolean doesntProcess(Class<? extends BotApiIdentifier>[] classes ) {
        return classes.length == 1 && classes[0].isInterface();
    }
}