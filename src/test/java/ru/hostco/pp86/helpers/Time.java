package ru.hostco.pp86.helpers;

import java.time.LocalTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Time {

    private static final String SEP = ":";
    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String shortTime) {
        int[] parsed = parseInts(shortTime);
        int hour = parsed[0];
        int minute = parsed[1];
        assertThat(isCorrect(hour, minute)).isTrue();
        this.hour = hour;
        this.minute = minute;
    }

    public static Time now() {
        LocalTime date = LocalTime.now();
        return new Time(date.getHour(), date.getMinute());
    }

    public int getHours() {
        return hour;
    }

    public int getMinutes() {
        return minute;
    }


    @Override
    public String toString() {
        String hour = intAsString(this.hour);
        String minute = intAsString(this.minute);
        return hour + SEP + minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time dates = (Time) o;
        return hour == dates.hour && minute == dates.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    private static String intAsString(int item) {
        String asString = String.valueOf(item);
        if (0 < item && item < 10) {
            asString = "0" + item;
        }
        return asString;
    }

    /*
     * Returns integers array for time in string format
     */
    private int[] parseInts(String timeAsString) {
        String[] splitTime = timeAsString.split(SEP);
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        return new int[]{hour, minute};
    }

    /*
     * Returns true if integers in time are correct
     */
    private static boolean isCorrect(int hour, int minute) {
        assertThat(hour).isBetween(0, 23);
        assertThat(minute).isBetween(0, 59);
        return true;
    }
}
