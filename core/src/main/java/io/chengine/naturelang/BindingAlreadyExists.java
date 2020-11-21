package io.chengine.naturelang;

public class BindingAlreadyExists extends RuntimeException {
    public BindingAlreadyExists(String message) {
        super(message);
    }
}
