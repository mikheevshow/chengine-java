package io.chengine.pipeline.action;

public interface Executor<T> {

    void execute(Executable<T> executable);

}
