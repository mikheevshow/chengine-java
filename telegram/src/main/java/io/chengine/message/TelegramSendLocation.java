package io.chengine.message;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class TelegramSendLocation extends TelegramAbstractSend {

    private Double latitude;
    private Double longitude;
    private Integer approachingNotificationDistance;
    private Integer heading;
    private Double horizontalAccuracy;
    private Integer livePeriod;

    protected TelegramSendLocation() {}

    public TelegramSendLocation withLatitude(@Nonnull Supplier<Double> latitude) {
        this.latitude = validateSupplier(latitude);
        return this;
    }

    public TelegramSendLocation withLongitude(@Nonnull Supplier<Double> longitude) {
        this.longitude = validateSupplier(longitude);
        return this;
    }

    public TelegramSendLocation disableNotification(@Nonnull Supplier<Boolean> disableNotification) {
        setDisableNotificationInternal(disableNotification);
        return this;
    }

    public TelegramSendLocation withApproachingNotificationDistance(@Nonnull Supplier<Integer> approachingNotificationDistance) {
        this.approachingNotificationDistance = validateSupplier(approachingNotificationDistance);
        return this;
    }

    public TelegramSendLocation withHeading(@Nonnull Supplier<Integer> heading) {
        this.heading = validateSupplier(heading);
        return this;
    }

    public TelegramSendLocation horizontalAccuracy(@Nonnull Supplier<Double> horizontalAccuracy) {
        this.horizontalAccuracy = validateSupplier(horizontalAccuracy);
        return this;
    }

    public TelegramSendLocation livePeriod(@Nonnull Supplier<Integer> livePeriod) {
        this.livePeriod = validateSupplier(livePeriod);
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getApproachingNotificationDistance() {
        return approachingNotificationDistance;
    }

    public Integer getHeading() {
        return heading;
    }

    public Double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    public Integer getLivePeriod() {
        return livePeriod;
    }
}
