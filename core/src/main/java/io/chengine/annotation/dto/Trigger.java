package io.chengine.annotation.dto;

public abstract class Trigger {
    public abstract boolean runWith(Event event);
}
