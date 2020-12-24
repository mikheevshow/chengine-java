package io.chengine.handler;

import io.chengine.command.HandleCommand;
import io.chengine.connector.BotApiIdentifier;
import io.chengine.message.Edit;
import io.chengine.method.MethodDefinition;

import java.lang.reflect.Method;
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

        return new HashSet<>();
    }

    private static boolean isForContextualMessageEditing(Method method) {
        return method.getReturnType().equals(Edit.class);
    }

}
