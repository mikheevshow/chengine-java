package io.chengine.command.i18n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class I18nFactory {

    private static final List<Locale> LIST_OF_LOCATIONS = new ArrayList<>();

    static {
        LIST_OF_LOCATIONS.addAll(Arrays.asList(Locale.getAvailableLocales()));
        LIST_OF_LOCATIONS.add(new Locale("RU"));
    }

    public static List<Locale> listOfLocations() {
        return LIST_OF_LOCATIONS;
    }
}
