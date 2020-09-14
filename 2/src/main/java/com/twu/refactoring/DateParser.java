package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year = getTime(0, 4, "Year string is less than 4 characters", "Year is not an integer", 2000, 2012, "Year cannot be less than 2000 or more than 2012");

        month = getTime(5, 7, "Month string is less than 2 characters", "Month is not an integer", 1, 12, "Month cannot be less than 1 or more than 12");

        date = getTime(8, 10, "Date string is less than 2 characters", "Date is not an integer", 1, 31, "Date cannot be less than 1 or more than 31");

        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            hour = getTime(11, 13, "Hour string is less than 2 characters", "Hour is not an integer", 0, 23, "Hour cannot be less than 0 or more than 23");

            minute = getTime(14, 16, "Minute string is less than 2 characters", "Minute is not an integer", 0, 59, "Minute cannot be less than 0 or more than 59");

        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getTime(int i, int i2, String s, String s2, int i3, int i4, String s3) {
        int year = new Parameter(i, i2, s, s2, i3, i4, s3).invoke();
        return year;
    }

    protected class Parameter {
        private int i;
        private int i2;
        private String s;
        private String s2;
        private int i3;
        private int i4;
        private String s3;

        public Parameter(int i, int i2, String s, String s2, int i3, int i4, String s3) {
            this.i = i;
            this.i2 = i2;
            this.s = s;
            this.s2 = s2;
            this.i3 = i3;
            this.i4 = i4;
            this.s3 = s3;
        }

        public int invoke() {
            int year;
            try {
                String yearString = dateAndTimeString.substring(i, i2);
                year = Integer.parseInt(yearString);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(s2);
            }
            if (year < i3 || year > i4)
                throw new IllegalArgumentException(s3);
            return year;
        }
    }
}
