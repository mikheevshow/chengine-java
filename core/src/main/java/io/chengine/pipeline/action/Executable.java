package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

public interface Executable<T> {

    void executeOn(Executor<T> executor);

    ActionResponse execute();

}
