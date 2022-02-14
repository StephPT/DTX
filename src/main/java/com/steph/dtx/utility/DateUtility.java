package com.steph.dtx.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public class DateUtility {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convertToDate(LocalDateTime date) {
        return java.sql.Date.valueOf(date.toLocalDate());
    }

    public static Date convertToDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getFirstDayOfMonth() {
        return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), 1);
    }

    public static LocalDate getLastDayOfMonth() {
        return LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
    }
}
