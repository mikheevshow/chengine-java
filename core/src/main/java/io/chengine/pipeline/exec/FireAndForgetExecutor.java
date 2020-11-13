package io.chengine.pipeline.exec;

import io.chengine.pipeline.action.FireStageAction;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class FireAndForgetExecutor implements Executor {

    @Override
    public void execute(Executable executable) {
        if (!(executable instanceof FireStageAction)) {
            throw new RuntimeException("Can't execute actions of class: " + executable.getClass());
        }

        var stage = (FireStageAction<?>) executable;
    }
}
