package io.chengine.commons;

public class Pair<T, G> {

    private T first;
    private G second;

    private Pair() {}

    private Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }

    public static <T, G> Pair<T, G> of(T first, G second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public G getSecond() {
        return second;
    }
}
