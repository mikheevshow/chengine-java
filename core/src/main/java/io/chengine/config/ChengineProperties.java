package io.chengine.config;

import java.util.Properties;

/**
 * The {@code ChengineProperties} class represents a persistent set of
 * properties.
 */
public class ChengineProperties extends Properties {



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
