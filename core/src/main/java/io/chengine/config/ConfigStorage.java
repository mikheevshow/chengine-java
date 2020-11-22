package io.chengine.config;

import java.util.Properties;

public class ConfigStorage {

    private final Properties properties;

    public ConfigStorage(Properties properties) {
        this.properties = properties;
    }

    public boolean textCommandMappingEnabled() {
        return (Boolean) properties.getOrDefault(Configs.ENABLE_PLAIN_TEXT_MAPPING, false);
    }


}
