package io.chengine.annotation;

public enum StageMode {
    SYNC, // Sync pipeline stages (blocks user actions)
    ASYNC, // Async pipeline stages, immediately proceeds to the next step (no blocks user actions)
    FUTURE // Async current stage, but returns are made in the future (block user actions in this pipeline only)
}