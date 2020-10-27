package io.chengine.method;

import io.chengine.connector.BotApiIdentifier;

import java.util.Set;

public class MethodDefinition {

    private final Set<BotApiIdentifier> availableForApi;

    public MethodDefinition(Set<BotApiIdentifier> availableForApi) {
        this.availableForApi = availableForApi;
    }

    public boolean containApi(BotApiIdentifier apiIdentifier) {
        return availableForApi.contains(apiIdentifier);
    }
}
