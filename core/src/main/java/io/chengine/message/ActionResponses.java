package io.chengine.message;

public interface ActionResponses {

    static ActionResponse empty() {
        return new ActionResponseEmpty();
    }

}
