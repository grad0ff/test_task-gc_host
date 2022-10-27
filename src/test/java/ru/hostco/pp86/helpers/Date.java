package ru.hostco.pp86.helpers;

import ru.hostco.pp86.data.Month;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Date {

    private static final String SEP = ".";
    private final int dayNumber;
    private final int monthNumber;
    private final int year;

    public Date(int dayNumber, int monthNumber, int year) {
        assertThat(isCorrect(dayNumber, monthNumber, year)).isTrue();
        this.dayNumber = dayNumber;
        this.monthNumber = monthNumber;
        this.year = year;
    }

    public Date(String fullDate) {
        int[] parsed = parseInts(fullDate);
        int dayNumber = parsed[0];
        int monthNumber = parsed[1];
        int year = parsed[2];
        assertThat(isCorrect(parsed[0], parsed[1], parsed[2])).isTrue();
        this.dayNumber = dayNumber;
        this.monthNumber = monthNumber;
        this.year = year;
    }

    public static Date today() {
        LocalDate actualDate = LocalDate.now();
        int dayNumber = actualDate.getDayOfMonth();
        int monthNumber = actualDate.getMonthValue();
        int year = actualDate.getYear();
        return new Date(dayNumber, monthNumber, year);
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        String day = intAsString(dayNumber);
        String month = intAsString(monthNumber);
        return day + SEP + month + SEP + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return dayNumber == date.dayNumber && monthNumber == date.monthNumber && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayNumber, monthNumber, year);
    }

    /*
     * Returns integer as string.
     * If int < 10 adds 0 before returned string
     */
    private static String intAsString(int item) {
        String asString = String.valueOf(item);
        if (0 < item && item < 10) {
            asString = "0" + item;
        }
        return asString;
    }

    /*
     * Returns integers array for date in string format
     */
    private int[] parseInts(String dateAsString) {
        String[] splitDate = dateAsString.split("\\.");
        int dayNumber = Integer.parseInt(splitDate[0]);
        int monthNumber = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        return new int[]{dayNumber, monthNumber, year};
    }

    /*
     * Returns true if integers in date are correct
     */
    private static boolean isCorrect(int dayNumber, int monthNumber, int year) {
        assertThat(year).isPositive();
        assertThat(monthNumber).isBetween(1, 12);
        Month month = Month.valueByNumber(monthNumber);
        int maxDays = month.maxDays(year);
        assertThat(dayNumber).isBetween(1, maxDays);
        return true;
    }
}
