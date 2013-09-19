package com.aout.kata;

import java.util.Date;

public class TimeProvider {
    private static Date date;

    public static void ForceTime(Date date) {

        TimeProvider.date = date;
    }

    public static void Reset() {
        TimeProvider.date = null;
    }

    public static Date getCurrentTime() {
        if (TimeProvider.date!= null ) {
            return TimeProvider.date;
        }
        return new Date();
    }
}
