//package io.chengine.pipeline.action;
//
//import io.chengine.message.ActionResponse;
//import io.chengine.pipeline.action.exception.StageActionExecutionException;
//import io.chengine.pipeline.action.exception.StageActionNotSupportedException;
//import io.chengine.session.pipeline.PipelineSessionManager;
//
//import javax.annotation.concurrent.ThreadSafe;
//import java.util.function.Supplier;
//
//@ThreadSafe
//public class FireAndForgetExecutor<T> extends AbstractStageExecutor<T> {
//
//    public FireAndForgetExecutor(PipelineSessionManager pipelineSessionManager) {
//        super(pipelineSessionManager);
//    }
//
//    @Override
//    protected ActionResponse processStage(Executable<T> executable) {
//
//        if (!(executable.getClass().isAssignableFrom(FireStageAction.class))) {
//            throw new StageActionExecutionException("Can't execute actions of class: " + executable.getClass());
//        }
//
//        FireStageAction<T> stageAction = (FireStageAction<T>) executable;
//        Supplier<ActionResponse> response = stageAction.response();
//
//        if (response == null) {
//            throw new NullPointerException("doAction method has null argument");
//        }
//
//        completeStage();
//        return response.get();
//    }
//}
