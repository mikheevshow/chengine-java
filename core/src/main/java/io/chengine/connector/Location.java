package io.chengine.connector;

public class Location {

    private final Double longitude;
    private final Double latitude;

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double longitude() {
        return longitude;
    }

    public Double latitude() {
        return latitude;
    }
}
