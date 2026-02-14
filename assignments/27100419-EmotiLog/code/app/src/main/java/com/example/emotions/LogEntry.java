package com.example.emotions;

import java.time.Instant;

/*
  represents one logged event -> stores emotion + timestamp.
*/
public class LogEntry {
    private final EmotionType emotionType;
    private final Instant timestamp;
    public LogEntry(EmotionType emotionType, Instant timestamp) {
        this.emotionType = emotionType;
        this.timestamp = timestamp;
    }
    public EmotionType getEmotionType() {
        return emotionType;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
}
