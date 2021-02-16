package io.chengine.session;

import io.chengine.connector.BotApiIdentifier;
import io.chengine.connector.BotRequestContext;
import io.chengine.connector.DefaultBotRequestContext;
import io.chengine.interceptor.RequestInterceptor;
import io.chengine.interceptor.RequestInterceptorChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SessionRequestInterceptor implements RequestInterceptor, SessionCacheAware {

    private static final Logger log = LogManager.getLogger(SessionRequestInterceptor.class);

    private SessionCache sessionCache;
    private final Map<String, SessionKeyExtractor> apiIdentifierSessionKeyExtractorMap = new HashMap<>();

    @Override
    public void intercept(BotRequestContext requestContext, RequestInterceptorChain requestInterceptorChain) {
        UserSessionContextHolder.setSession(null); // Reset session
        final String apiIdentifier = requestContext.getApiBotIdentifier().identifier();
        final SessionKeyExtractor keyExtractor = apiIdentifierSessionKeyExtractorMap.get(apiIdentifier);
        if (keyExtractor == null) {
            throw new NullPointerException("Not found session key extractor for api: \"" + apiIdentifier + "\"");
        }
        final SessionKey sessionKey = keyExtractor.extractFrom(requestContext);
        ((DefaultBotRequestContext) requestContext).setSessionKey(sessionKey);

        if (sessionCache == null) {
            log.warn("Session cache is null, skip session searching");
        } else {
            final Session session = sessionCache.getSessionBySessionKey(sessionKey);
            if (session != null) {
                log.info("Session found: {}", session);
            }
            UserSessionContextHolder.setSession(session);
        }

        requestInterceptorChain.doIntercept(requestContext);
    }

    @Override
    public void setSessionCache(SessionCache sessionCache) {
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
