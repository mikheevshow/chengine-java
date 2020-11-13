package io.chengine.pipeline.exec;

import io.chengine.pipeline.action.CheckStageAction;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CheckStageExecutor implements Executor {

    @Override
    public void execute(Executable executable) {
        if (!(executable instanceof CheckStageAction)) {
            throw new RuntimeException("Can't execute actions of class: " + executable.getClass());
        }
    }
}
