package io.chengine.session;

import javax.annotation.Nullable;

public interface Session {

    SessionKey key();

    boolean inPipeline();

    @Nullable
    PipelineSessionInfo pipelineSessionInfo();

}
