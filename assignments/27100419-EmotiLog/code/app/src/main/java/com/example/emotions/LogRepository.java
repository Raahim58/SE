package com.example.emotions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

/*
  this holds logs in memory while the app is running; singleton so all activities share the same list.
  returns only logs that happened on the given day.
*/
public class LogRepository {
    private static LogRepository instance;
    private final List<LogEntry> logs = new ArrayList<>();
    private LogRepository() {}
    public static LogRepository getInstance() {
        if (instance == null) {
            instance = new LogRepository();
        }
        return instance;
    }
    public void add(LogEntry entry) {
        logs.add(entry);
    }
    // DateUtils.toLocalDate(timestamp) used to decide which day the log belongs to.
    public List<LogEntry> getForDay(LocalDate day) {
        List<LogEntry> result = new ArrayList<>();
        for (LogEntry e : logs) {
            if (DateUtils.toLocalDate(e.getTimestamp()).equals(day)) {
                result.add(e);
            }
        }
        return result;
    }
}
