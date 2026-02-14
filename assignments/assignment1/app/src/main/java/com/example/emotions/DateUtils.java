package com.example.emotions;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/*
  date/time helper stuff.
  main uses:
  - convert instant -> local date (for daily filtering)
  - format time nicely (for logs list)
  - format date nicely (for screen header)
*/
public class DateUtils {

    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("MMM d, yyyy");

    public static String formatTime(Instant instant) {
        return instant.atZone(ZONE).toLocalTime().format(TIME_FMT);
    }

    public static LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZONE).toLocalDate();
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FMT);
    }
}
