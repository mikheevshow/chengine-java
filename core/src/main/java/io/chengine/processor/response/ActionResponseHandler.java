package io.chengine.processor.response;

import io.chengine.message.ActionResponse;

public interface ActionResponseHandler {

    void handle(ActionResponse response);

}
