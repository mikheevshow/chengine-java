package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

public interface Executor<T> {

    ActionResponse execute(Executable<T> executable);

}
