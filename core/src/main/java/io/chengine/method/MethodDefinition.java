package io.chengine.method;

import io.chengine.connector.BotApiIdentifier;

import java.util.HashSet;
import java.util.Set;

public class MethodDefinition {

    private final Set<BotApiIdentifier> availableForApi;
    private final boolean forEditContextualMessage;

    public MethodDefinition(
            final Set<BotApiIdentifier> availableForApi,
            final boolean forEditContextualMessage
    ) {
        this.availableForApi = availableForApi != null ? availableForApi : new HashSet<>();
        this.forEditContextualMessage = forEditContextualMessage;
    }

    public boolean containApi(BotApiIdentifier apiIdentifier) {
        return availableForApi.contains(apiIdentifier);
    }

    public boolean isForEditContextualMessage() {
        return forEditContextualMessage;
    }
}
