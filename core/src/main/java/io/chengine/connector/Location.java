package io.chengine.connector;

public class Location {

    private final float longitude;
    private final float latitude;

    public Location(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float longitude() {
        return longitude;
    }

    public float latitude() {
        return latitude;
    }
}
