package com.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateDifference {
	public long calculateDaysDifference(LocalDate date1, LocalDate date2) {
        return Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }
}

