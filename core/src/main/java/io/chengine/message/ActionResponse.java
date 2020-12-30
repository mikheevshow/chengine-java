package io.chengine.message;

import java.io.Serializable;

public interface ActionResponse extends Serializable {

    static ActionResponseContainer of(Object o) {
        return new ActionResponseContainer(o);
    }

}
