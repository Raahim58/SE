package com.example.emotions;

/*
  this defines the preset emotions available in the app.
  using enum keeps things simple and safe.
*/
public enum EmotionType {
    HAPPY("happy", "😊"),
    SAD("sad", "😢"),
    GRATEFUL("grateful", "🙏"),
    ANGRY("angry", "😡"),
    EXCITED("excited", "🤩"),
    STRESSED("stressed", "😖");

    private final String label;
    private final String emoji;
    EmotionType(String label, String emoji) {
        this.label = label;
        this.emoji = emoji;
    }
    public String getLabel() {
        return label;
    }
    public String getEmoji() {
        return emoji;
    }
}

