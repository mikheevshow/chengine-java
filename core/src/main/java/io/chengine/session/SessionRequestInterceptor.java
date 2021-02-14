package io.chengine.session;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequestContext;
import io.chengine.interceptor.RequestInterceptor;

import java.util.HashMap;
import java.util.Map;

public class SessionRequestInterceptor implements RequestInterceptor {

    private SessionManager sessionManager;
    private final Map<String, SessionKeyExtractor> apiIdentifierSessionKeyExtractorMap = new HashMap<>();

    @Override
    public void intercept(BotRequestContext requestContext) {
        if (sessionManager == null) {
            throw new NullPointerException("Session manager is null");
        }
        final SessionKeyExtractor keyExtractor = apiIdentifierSessionKeyExtractorMap.get(requestContext.getApiBotIdentifier().identifier());
        final SessionKey sessionKey = keyExtractor.extractFrom(requestContext);
        final Session session = sessionManager.getSession(sessionKey);
        UserSessionContextHolder.setSession(session);
    }

    public void setSessionManager(SessionManager sessionManager) {
        if (sessionManager == null) {
            throw new NullPointerException("Session manager can't be null");
        }
        this.sessionManager = sessionManager;
    }

    public void putSessionKeyExtractor(BotApiIdentifier botApiIdentifier, SessionKeyExtractor sessionKeyExtractor) {
        if (botApiIdentifier == null) {
            throw new NullPointerException("Bot api identifier can't be null");
        }
        if (sessionKeyExtractor == null) {
            throw new NullPointerException("Session key identifier can't be null");
        }
        apiIdentifierSessionKeyExtractorMap.put(botApiIdentifier.identifier(), sessionKeyExtractor);
    }
}
