package com.example.emotions;

/*
  one row in the summary list.
  it represents an emotion + how many times it happened + percent of total.
*/
public class SummaryRow {

    private final EmotionType emotionType;
    private final int count;
    private final double fraction; // 0.0 to 1.0

    public SummaryRow(EmotionType emotionType, int count, double fraction) {
        this.emotionType = emotionType;
        this.count = count;
        this.fraction = fraction;
    }
    public EmotionType getEmotionType() {
        return emotionType;
    }
    public int getCount() {
        return count;
    }
    public double getFraction() {
        return fraction;
    }
}
