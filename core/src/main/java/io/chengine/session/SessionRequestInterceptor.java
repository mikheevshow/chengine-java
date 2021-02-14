package io.chengine.session;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequestContext;
import io.chengine.interceptor.RequestInterceptor;
import io.chengine.interceptor.RequestInterceptorChain;

import java.util.HashMap;
import java.util.Map;

public class SessionRequestInterceptor implements RequestInterceptor {

    private SessionCache sessionCache;
    private final Map<String, SessionKeyExtractor> apiIdentifierSessionKeyExtractorMap = new HashMap<>();

    @Override
    public void intercept(BotRequestContext requestContext, RequestInterceptorChain requestInterceptorChain) {
        if (sessionCache == null) {
            throw new NullPointerException("Session cache is null");
        }
        final String apiIdentifier = requestContext.getApiBotIdentifier().identifier();
        final SessionKeyExtractor keyExtractor = apiIdentifierSessionKeyExtractorMap.get(apiIdentifier);
        if (keyExtractor == null) {
            throw new NullPointerException("Not found session key extractor for api: \"" + apiIdentifier + "\"");
        }
        final SessionKey sessionKey = keyExtractor.extractFrom(requestContext);
        final Session session = sessionCache.getSessionBySessionKey(sessionKey);
        UserSessionContextHolder.setSession(session);

        requestInterceptorChain.doIntercept(requestContext);
    }

    public void setSessionManager(SessionCache sessionCache) {
        if (sessionCache == null) {
            throw new NullPointerException("Session cache can't be null");
        }
        this.sessionCache = sessionCache;
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
