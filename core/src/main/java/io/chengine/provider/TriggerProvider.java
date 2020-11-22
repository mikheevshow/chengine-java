package io.chengine.provider;

import io.chengine.pipeline.EventTrigger;
import io.chengine.pipeline.Trigger;

import java.util.List;

public interface TriggerProvider {

    /**
     * For handler registration it's should be annotated by {@link Trigger}
     *
     * @return triggers :)
     */
    List<? extends EventTrigger> provideAll();
}
