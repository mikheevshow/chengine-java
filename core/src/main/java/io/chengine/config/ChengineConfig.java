package io.chengine.config;

import io.chengine.provider.HandlerProvider;
import io.chengine.provider.TriggerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChengineConfig {

    /**
     * Enables plain text mapping. Use annotation {@link io.chengine.naturelang.PlainTextMap}
     * to map command on text command. Command parameter {@link io.chengine.command.CommandParameter}
     * should pass using space. To enter complex parameters use single comma over parameter
     *
     */
    private boolean enablePlainTextMapping = false;

    /**
     * Enables automatic command description generation. If option is enabled
     * then '/help' command mapping will be created by the way. When user enters
     * '/help' command, the description about ones will be returned based on either
     * description in {@link io.chengine.command.CommandDescription#description()} or i18n property name
     * {@link io.chengine.command.CommandDescription#property()}
     *
     * @see io.chengine.command.CommandDescription
     */
    private boolean enableCommandDescriptionAutoconfiguration = false;

    private List<HandlerProvider> handlerProviders = new ArrayList<>();
    private List<TriggerProvider> triggerProviders = new ArrayList<>();

    public ChengineConfig() {}

    public ChengineConfig enablePlainTextMapping(boolean enable) {
        this.enablePlainTextMapping = enable;
        return this;
    }

    public ChengineConfig enableCommandDescriptionAutoconfiguration(boolean enable) {
        this.enableCommandDescriptionAutoconfiguration = enable;
        return this;
    }

    public ChengineConfig addHandlerProviders(List<HandlerProvider> handlerProviders) {
        Objects.requireNonNull(handlerProviders);
        this.handlerProviders.addAll(handlerProviders);
        return this;
    }

    public ChengineConfig addTriggerProviders(List<TriggerProvider> triggerProviders) {
        Objects.requireNonNull(triggerProviders);
        this.triggerProviders.addAll(triggerProviders);
        return this;
    }

    public boolean isEnablePlainTextMapping() {
        return enablePlainTextMapping;
    }

    public boolean isEnableCommandDescriptionAutoconfiguration() {
        return enableCommandDescriptionAutoconfiguration;
    }

    public List<HandlerProvider> getHandlerProviders() {
        return handlerProviders;
    }

    public List<TriggerProvider> getTriggerProviders() {
        return triggerProviders;
    }
}
