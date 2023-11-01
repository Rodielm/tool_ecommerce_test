package dev.rodiel.toolcomerce.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String fixMissingHours(String date, String format_pattern) {
        if (date.length() < format_pattern.length()) {
            return date = date + "00";
        }
        return date;
    }

    public static LocalDateTime dateFormatter(String date, String format_pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        return LocalDateTime.parse(date, formatter);
    }

    public static boolean isDateBetween(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
        if (date.isAfter(startDate) && date.isBefore(endDate)) {
            return true;
        }
        return false;
    }

}
