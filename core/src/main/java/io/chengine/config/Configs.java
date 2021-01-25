package io.chengine.config;

import io.chengine.command.CommandDescription;

public class Configs {

    /**
     * Enables plain text mapping. Use annotation {@link io.chengine.naturelang.PlainTextMap}
     * to map command on text command. Command parameter {@link io.chengine.command.CommandParameter}
     * should pass using space. To enter complex parameters use single comma over parameter
     *
     *
     */
    public static final String ENABLE_PLAIN_TEXT_MAPPING = "enablePlainTextMapping";

    /**
     * Enables automatic command description generation. If option is enabled
     * then '/help' command mapping will be created by the way. When user enters
     * '/help' command, the description about ones will be returned based on either
     * description in {@link CommandDescription#description()} or i18n property name
     * {@link CommandDescription#property()}
     *
     * @see io.chengine.command.CommandDescription
     */
    public static final String ENABLE_COMMAND_DESCRIPTION = "enableCommandDescription";


    public static final String HANDLER_PROVIDERS = "handlerProviders";
    public static final String TRIGGER_PROVIDERS = "triggerProviders";
    public static final String REQUEST_HANDLERS_AWARE = "requestHandlersAware";
    public static final String REQUEST_TYPE_CONVERTER_AWARE = "requestTypeConverterAware";
    public static final String RESPONSE_TYPE_HANDLER_AWARE = "responseTypeHandlerAware";
    public static final String ANNOTATION_PROCESSORS_AWARE = "annotationProcessorsAware";

}
