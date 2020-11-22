package io.chengine.pipeline.action;

import io.chengine.message.ActionResponse;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @param <T> - a context object for passing data between chain methods
 */
public class CheckStageAction<T> extends AbstractStageAction<T> {

    private final Supplier<StageCheck<T>> check;

    // ==============================================================================================================
    //	 Success Functions
    // ==============================================================================================================

    private Function<StageCheck<T>, ActionResponse> successStageCheckActionResponse;
    private Supplier<ActionResponse> successActionResponse;

    // ==============================================================================================================
    //	 Fail Functions
    // ==============================================================================================================

    private Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseReturn;
    private Supplier<ActionResponse> failActionResponseReturn;
    private Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseResume;
    private Supplier<ActionResponse> failActionResponseResume;
    private Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseTerminate;


    protected CheckStageAction(Supplier<StageCheck<T>> check) {
        this.check = check;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#success()}.
     * So it give a possibility to send a message. Stage index for current user increments.
     *
     * @param success - a function which receive {@link StageCheck} object and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onSuccessCheckReturn(Function<StageCheck<T>, ActionResponse> success) {
        this.successStageCheckActionResponse = success;
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#success()}.
     * So it give a possibility to send a message. Stage index for current user increments.
     *
     * @param success - a supplier which returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onSuccessCheckReturn(Supplier<ActionResponse> success) {
        this.successActionResponse = success;
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message. Stage index for current user doesn't change.
     *
     * @param fail - a function which receive {@link StageCheck} object and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckReturn(Function<StageCheck<T>, ActionResponse> fail) {
        this.failStageCheckActionResponseReturn = fail;
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message. Stage index for current user doesn't change.
     *
     * @param response - a supplier which returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckReturn(Supplier<ActionResponse> response) {
        this.failActionResponseReturn = response;
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message. Stage index for current user increments and marks as successful.
     *
     * @param fail - a function which receive {@link StageCheck} object and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckResume(Function<StageCheck<T>, ActionResponse> fail) {
        this.failStageCheckActionResponseResume = fail;
        return this;
    }

    /**
     * This method works when {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message. Stage index for current user increments and marks as successful.
     *
     * @param fail - a function which receive {@link StageCheck} object and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckResume(Supplier<ActionResponse> fail) {
        this.failActionResponseResume = fail;
        return this;
    }

    /**
     * Terminates a pipeline if {@link CheckStageAction#check} returns a {@link StageCheck#fail()}.
     * So it give a possibility to send a message before termination
     *
     * @param fail - a function which receive {@link CheckStageAction#check} result and returns {@link ActionResponse}
     * @return itself
     * @see StageCheck
     * @see ActionResponse
     */
    public CheckStageAction<T> onFailCheckTerminate(Function<StageCheck<T>, ActionResponse> fail) {
        this.failStageCheckActionResponseTerminate = fail;
        return this;
    }

    @Override
    protected void onPartialAssembly() {

    }

    Supplier<StageCheck<T>> check() {
        return check;
    }


    Function<StageCheck<T>, ActionResponse> successStageCheckActionResponse() {
        return successStageCheckActionResponse;
    }

    Supplier<ActionResponse> successActionResponse() {
        return successActionResponse;
    }

    Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseReturn() {
        return failStageCheckActionResponseReturn;
    }

    Supplier<ActionResponse> failActionResponseReturn() {
        return failActionResponseReturn;
    }

    Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseResume() {
        return failStageCheckActionResponseResume;
    }

    Supplier<ActionResponse> failActionResponseResume() {
        return failActionResponseResume;
    }

    Function<StageCheck<T>, ActionResponse> failStageCheckActionResponseTerminate() {
        return failStageCheckActionResponseTerminate;
    }

}
