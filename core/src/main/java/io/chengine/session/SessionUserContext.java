package io.chengine.session;

public class SessionUserContext {
    private static final ThreadLocal<Session> threadLocalScope = new ThreadLocal<>();

    public static Session getSession() {
        return threadLocalScope.get();
    }

    public static void setSession(Session session) {
        threadLocalScope.set(session);
    }
}
