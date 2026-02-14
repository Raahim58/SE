package com.example.emotions;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/*
  takes a list of log entries and turns it into summary rows.
  this keeps math logic out of the activity.
*/
public class SummaryCalculator {
    public static List<SummaryRow> compute(List<LogEntry> logs) {
        int total = logs.size();
        Map<EmotionType, Integer> counts = new EnumMap<>(EmotionType.class); // emotion -> count
        // start everything at 0 so summary always shows all emotions
        for (EmotionType t : EmotionType.values()) {
            counts.put(t, 0);
        }
        // count each log entry
        for (LogEntry e : logs) {
            EmotionType t = e.getEmotionType();
            counts.put(t, counts.get(t) + 1);
        }
        // convert to rows
        List<SummaryRow> rows = new ArrayList<>();
        for (EmotionType t : EmotionType.values()) {
            int c = counts.get(t);
            double frac = (total == 0) ? 0.0 : ((double) c / (double) total);
            rows.add(new SummaryRow(t, c, frac));
        }
        return rows;
    }
}
