package io.chengine.annotation.processor;

import io.chengine.annotation.HandleCommand;
import io.chengine.connector.BotApiIdentifier;
import io.chengine.message.Edit;
import io.chengine.method.MethodDefinition;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MethodDefinitionCreator {

    public static MethodDefinition create(Method method) {
        return new MethodDefinition(
                availableIdentifiers(method),
                isForContextualMessageEditing(method)
        );
    }

    private static Set<BotApiIdentifier> availableIdentifiers(Method method) {

        var anno = method.getDeclaredAnnotation(HandleCommand.class);
        var includeApi = anno.onlyFor();
        var excludeApi = anno.forAllExcept();

        var includeApiSet = new HashSet<>(Arrays.asList(includeApi));
        var excludeApiSet = new HashSet<>(Arrays.asList(excludeApi));

        for (var api : includeApiSet) {
            if (excludeApiSet.contains(api)) {
                throw new RuntimeException("Апи конфликт, аннотация содержит одинаковые элементы включающих/исключающих апи");
            }
        }

        return new HashSet<>();
    }

    private static boolean isForContextualMessageEditing(Method method) {
        return method.getReturnType().equals(Edit.class);
    }

}
