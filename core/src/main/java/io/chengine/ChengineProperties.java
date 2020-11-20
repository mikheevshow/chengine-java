package io.chengine;

import java.util.Properties;

/**
 * The {@code ChengineProperties} class represents a persistent set of
 * properties.
 */
public class ChengineProperties extends Properties {

	public static final String ENABLE_PLAIN_TEXT_MAPPING = "enablePlainTextMapping";
	public static final String HANDLER_PROVIDERS = "handlerProviders";
	public static final String TRIGGER_PROVIDERS = "triggerProviders";
	public static final String REQUEST_HANDLERS_AWARE = "requestHandlersAware";

	public ChengineProperties() {
		super();
	}

	public ChengineProperties(int initialCapacity) {
		super(initialCapacity);
	}

	public ChengineProperties(Properties defaults) {
		super(defaults);
	}

}
