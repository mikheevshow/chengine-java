//package io.chengine.session;
//
//import io.chengine.connector.BotApiIdentifier;
//import io.chengine.connector.Chat;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//public interface Session<T> {
//
//    UUID uuid();
//
//    BotApiIdentifier api();
//
//    User user();
//
//    Chat chat();
//
//    int getTtl();
//
//    TimeUnit getTtlTimeUnit();
//
//    boolean isTerminated();
//
//    T data();
//
//}
