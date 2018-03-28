package ru.mycash.cash.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

public static boolean  isBeatweenDate  (LocalDate actual, LocalDate startDate, LocalDate endDate)
{
    return (actual.isAfter(startDate)|| actual.isEqual(startDate))&& (actual.isBefore(endDate) ||actual.isEqual(startDate));
}

    public static boolean  isBeatweenTime  (LocalTime actual, LocalTime startTime, LocalTime endTime)
    {
        return (actual.isAfter(startTime)|| actual.equals(startTime))&& (actual.isBefore(endTime) ||actual.equals(startTime));
    }

    public static boolean  isBeatweenDateTime  (LocalDateTime actual, LocalDateTime startTime, LocalDateTime endTime)
    {
        return (actual.isAfter(startTime)|| actual.equals(startTime))&& (actual.isBefore(endTime) ||actual.equals(startTime));
    }
}
