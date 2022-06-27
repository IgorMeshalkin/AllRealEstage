package com.igormeshalkin.util;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {
    public static DateTimeFormatter dateAndTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
    }

    public static DateTimeFormatter onlyDateFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }
}
