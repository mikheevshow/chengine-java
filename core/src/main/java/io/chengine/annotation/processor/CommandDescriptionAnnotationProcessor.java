package io.chengine.annotation.processor;

import io.chengine.annotation.CommandDescription;
import io.chengine.command.i18n.CommandMetaInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

/**
 *
 */
public class CommandDescriptionAnnotationProcessor implements AnnotationProcessor<CommandDescriptionAnnotationProcessor.Input, Object> {

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
    public void process(Input input, Consumer<Object> processingCallback) throws Exception {

        for (var handler : input.handlers) {
            for (var method : handler.getClass().getMethods()) {
                var descriptionAnnotation = method.getAnnotation(CommandDescription.class);
                if (descriptionAnnotation != null) {
                    var description = descriptionAnnotation.annotationType().getMethod("description");
                    var descriptions = (String[]) description.invoke(descriptionAnnotation);
                    var commandMetaInfo = new CommandMetaInfo();
                    for (var localization : descriptions) {
                        var delimiter = " : ";
                        var delimiterPosition = localization.indexOf(" : ");
                        var locale = localization.substring(0, delimiterPosition);
                        var localizedDescription = localization.substring(delimiter.length() + delimiterPosition);
                        commandMetaInfo.put(new Locale(locale), localizedDescription);
                    }
                    var commandPath = input.methodPathMap.get(method);
                    input.commandCommandMetaInfoMap.put(commandPath, commandMetaInfo);
                }
            }
        }

        processingCallback.accept(null);
    }
}
