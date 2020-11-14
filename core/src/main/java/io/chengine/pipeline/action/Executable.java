package io.chengine.pipeline.action;

public interface Executable<T> {

    void executeOn(Executor<T> executor);

}
