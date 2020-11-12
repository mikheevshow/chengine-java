package io.chengine.pipeline;

import io.chengine.message.ActionResponse;

import java.util.function.*;

public class ActionStage {

    private ActionStage() {}

    public static ActionStageCreator create() {
        return new ActionStageCreator();
    }

    public static class ActionStageCreator {

        public ActionStageCreator doAction(Supplier<ActionResponse> actionResponseSupplier) {
            return this;
        }

        public ActionStageCreator checkStage(BooleanSupplier check) {
            return this;
        }

        public <T> ActionStageCreator checkStage(Supplier<CheckResult<T>> check) {
            return this;
        }

        public <T> ActionStageCreator doOnSuccessCheck(Function<CheckResult<T>, ActionResponse> success) {
            return this;
        }

        public ActionStageCreator doOnSuccessCheck(Supplier<ActionResponse> success) {
            return this;
        }

        public <T> ActionStageCreator doOnFailCheck(BiFunction<CheckResult<T>, Canceler, ActionResponse> fail) {
            return this;
        }

        public ActionStageCreator doOnFailCheck(Supplier<ActionResponse> fail) {
            return this;
        }

        public ActionStageCreator doOnFailCheck(Function<Canceler, ActionResponse> fail) {
            return this;
        }

        public ActionStageCreator doOnError(Function<Throwable, ActionResponse> error) {
            return this;
        }

        public ActionStageCreator doOnError(Consumer<Throwable> error) {
            return this;
        }

        public ActionStageCreator cancelPipeline() {
            return this;
        }

        public ActionStage done() {
            return new ActionStage();
        }

    }

}
