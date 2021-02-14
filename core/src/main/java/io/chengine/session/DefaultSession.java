package io.chengine.session;

import javax.annotation.Nullable;

public class DefaultSession implements Session {

    private final SessionKey sessionKey;
    private final PipelineSessionInfo pipelineSessionInfo;

    public DefaultSession(SessionKey sessionKey, PipelineSessionInfo pipelineSessionInfo) {
        this.sessionKey = sessionKey;
        this.pipelineSessionInfo = pipelineSessionInfo;
    }

    @Override
    public SessionKey getSessionKey() {
        return sessionKey;
    }

    @Override
    public boolean inPipeline() {
        return pipelineSessionInfo != null;
    }

    @Nullable
    @Override
    public PipelineSessionInfo pipelineSessionInfo() {
        return pipelineSessionInfo;
    }

    @Override
    public String toString() {
        return "DefaultSession{" +
                "sessionKey=" + sessionKey +
                ", pipelineSessionInfo=" + pipelineSessionInfo +
                '}';
    }
}
