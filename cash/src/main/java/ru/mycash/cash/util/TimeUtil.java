package ru.mycash.cash.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parseTmeStamp(String timeStamp)
    {
        return LocalDateTime.parse(timeStamp,DATE_TIME_FORMATTER_TIMESTAMP);
    }


    public static final LocalDate MIN_DATE = LocalDate.of(1000,01,01);
    public static final LocalDate MAX_DATE = LocalDate.of(3000,01,01);
    public static final LocalTime MIN_TIME = LocalTime.of(00,00,00);
    public static final LocalTime MAX_TIME = LocalTime.of(23,59,59);


    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    //good realization
    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    // bad (my) realization

public static boolean  isBeatweenDate  (LocalDate actual, LocalDate startDate, LocalDate endDate)
{
    return (actual.isAfter(startDate)|| actual.equals(startDate))&& (actual.isBefore(endDate) ||actual.equals(endDate));
}

    public static boolean  isBeatweenTime  (LocalTime actual, LocalTime startTime, LocalTime endTime)
    {
        return (actual.isAfter(startTime)|| actual.equals(startTime))&& (actual.isBefore(endTime) ||actual.equals(endTime));
    }

    public static boolean  isBeatweenDateTime  (LocalDateTime actual, LocalDateTime startTime, LocalDateTime endTime)
    {
        return (actual.isAfter(startTime)|| actual.equals(startTime)) && (actual.isBefore(endTime) ||actual.equals(endTime));
    }

    public static LocalDate parseLocalDate (String localDateString)
    {
        try {
            return LocalDate.parse(localDateString);
        }
        catch (DateTimeParseException e)
        {
            return null;
        }
    }

    public static LocalTime parseLocalTime (String localTimeString)
    {
        try {
            return LocalTime.parse(localTimeString);
        }
        catch (DateTimeParseException e)
        {
            return null;
        }
    }
}
