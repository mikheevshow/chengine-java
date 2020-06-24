package io.chengine;

import java.util.Objects;

public class ConfigurationProcessor {

	public ConfigurationProcessor(ChengineConfiguration chengineConfiguration) {
		Objects.requireNonNull(chengineConfiguration, "Chengine configuration can't be null");
		System.setProperty("chengine.scan.package", chengineConfiguration.getPackageToScan());
	}

}
