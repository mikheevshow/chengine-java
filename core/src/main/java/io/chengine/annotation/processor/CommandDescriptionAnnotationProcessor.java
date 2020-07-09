package io.chengine.annotation.processor;

import io.chengine.annotation.CommandDescription;
import io.chengine.command.i18n.CommandMetaInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 */
public class CommandDescriptionAnnotationProcessor implements AnnotationProcessor<CommandDescriptionAnnotationProcessor.Input, CommandDescriptionAnnotationProcessor.Callback> {

    //****************************************************************************************************************

    public static final class Input {

        private final Collection<?> handlers;
        private final Map<String, CommandMetaInfo> commandCommandMetaInfoMap;
        private final Map<Method, String> methodPathMap;

        public Input(
                Collection<?> handlers,
                Map<String, CommandMetaInfo> commandCommandMetaInfoMap,
                Map<Method, String> methodPathMap) {

            this.handlers = handlers;
            this.commandCommandMetaInfoMap = commandCommandMetaInfoMap;
            this.methodPathMap = methodPathMap;
        }

    }

    public static final class Callback { }

    //****************************************************************************************************************

    @Override
    public Collection<Class<? extends Annotation>> supports() {
        return Collections.singletonList(CommandDescription.class);
    }

    @Override
    public void process(Input input, Consumer<Callback> processingCallback) {
        // just do nothing for now

        //						Annotation descriptionAnnotation = method.getAnnotation(CommandDescription.class);
//						if(Objects.nonNull(descriptionAnnotation)) {
//							Method descriptionMethod = descriptionAnnotation.annotationType().getMethod("description");
//							String[] descriptions = (String[]) descriptionMethod.invoke(descriptionAnnotation);
//
//							Arrays
//									.stream(descriptions)
//									.forEach(str -> {
//										String delimiter = " : ";
//										int delimiterPos = str.indexOf(delimiter);
//
//										Map<String, String> localeDescription =
//												commandDescriptions.getOrDefault(fullMethodCommandPathTemplate, new HashMap<>());
//										localeDescription.put(str.substring(0, delimiterPos), str.substring(delimiter.length() + delimiterPos));
//
//										commandDescriptions.put(fullMethodCommandPathTemplate, localeDescription);
//									});
//						}

        processingCallback.accept(new Callback());
    }
}
