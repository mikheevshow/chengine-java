package io.chengine.message;

public class ActionResponseContainer implements ActionResponse {

    private final Object action;

    public ActionResponseContainer(Object action) {
        this.action = action;
    }

    public Object getAction() {
        return action;
    }

}
