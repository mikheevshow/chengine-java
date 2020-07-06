package io.chengine.command.i18n;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class CommandMetaInfo {

    private final Map<Locale, String> localizationsMap;

    public CommandMetaInfo(Map<Locale, String> localizationsMap) {
        this.localizationsMap = localizationsMap;
    }

    public void put(Locale locale, String localization) {
        localizationsMap.put(locale,localization);
    }

    @Nullable
    public String getLocalization(final Locale locale) {
        return localizationsMap == null ? null : localizationsMap.get(locale);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandMetaInfo that = (CommandMetaInfo) o;
        return Objects.equals(localizationsMap, that.localizationsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localizationsMap);
    }

    @Override
    public String toString() {
        return "CommandMetaInfo{" +
                "localizationsMap=" + localizationsMap +
                '}';
    }
}
