package io.chengine.message;

import java.util.function.Supplier;

public class TelegramSendLocation implements Send {

    private Double latitude;
    private Double longitude;
    private Boolean disableNotification;

    public TelegramSendLocation() {}

    public TelegramSendLocation withLatitude(Supplier<Double> latitude) {
        this.latitude = latitude.get();
        return this;
    }

    public TelegramSendLocation withLongitude(Supplier<Double> longitude) {
        this.longitude = longitude.get();
        return this;
    }

    public TelegramSendLocation disableNotification(Supplier<Boolean> disableNotification) {
        this.disableNotification = disableNotification.get();
        return this;
    }

}
