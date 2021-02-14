package io.chengine.session;

import javax.annotation.Nullable;

public interface Session {

    SessionKey getSessionKey();

    boolean inPipeline();

    @Nullable
    PipelineSessionInfo pipelineSessionInfo();

}
