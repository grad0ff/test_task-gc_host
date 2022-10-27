package ru.hostco.pp86.helpers.datetime;

import ru.hostco.pp86.data.Month;

import java.time.LocalDate;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DateRandomizer {

    private static final int ACTUAL_YEAR = LocalDate.now().getYear();
    private static final int ACTUAL_MONTH = LocalDate.now().getMonthValue();
    private static final int ACTUAL_DAY = LocalDate.now().getDayOfMonth();

    /**
     * Returns pseudo-random year between 100 years ago and 100 years ahead
     */
    public static int randomYear() {

        return randomYear(ACTUAL_YEAR - 100, ACTUAL_YEAR + 100);
    }

    /**
     * Returns pseudo-random year between 100 years and actual year
     */
    public static int randomYearFromPast(int fromYear) {

        return randomYear(fromYear, ACTUAL_YEAR);
    }

    /**
     * Returns pseudo-random year between actual year and 100 years ahead
     */
    public static int randomYearFromFuture(int toYear) {

        return randomYear(ACTUAL_YEAR, toYear);
    }

    /**
     * Returns pseudo-random year from specified years
     */
    public static int randomYear(int start, int end) {
        assertThat(end)
                .as("Given year is negative").isPositive();
        assertThat(start)
                .as("Given year is negative").isPositive()
                .as("Start year is less then end year").isLessThanOrEqualTo(end);
        int range = end - start;
        int random = new Random().nextInt(range + 1);

        return start + random;
    }

    /**
     * Returns pseudo-random month of year
     */
    public static Month randomMonth() {

        return randomMonth(1, 12);
    }

    /**
     * Returns pseudo-random month between first month and actual month
     */
    public static Month randomMonthFromPast() {

        return randomMonth(1, ACTUAL_MONTH);
    }

    /**
     * Returns pseudo-random month between actual month and last month
     */
    public static Month randomMonthFromFuture() {

        return randomMonth(ACTUAL_MONTH, 12);
    }

    /**
     * Returns pseudo-random month from specified interval
     */
    public static Month randomMonth(int start, int end) {
        assertThat(end)
                .as("Given month is negative").isPositive();
        assertThat(start)
                .as("Given month is negative").isPositive()
                .as("Start month is less then end year").isLessThanOrEqualTo(end);
        int range = end - start;
        int random = new Random().nextInt(range);
        int condition = start + random;
        switch (condition) {
            case 0:
                return Month.JANUARY;
            case 1:
                return Month.FEBRUARY;
            case 2:
                return Month.MARCH;
            case 3:
                return Month.APRIL;
            case 4:
                return Month.MAY;
            case 5:
                return Month.JUNE;
            case 6:
                return Month.JULY;
            case 7:
                return Month.AUGUST;
            case 8:
                return Month.SEPTEMBER;
            case 9:
                return Month.OCTOBER;
            case 10:
                return Month.NOVEMBER;
            case 11:
                return Month.DECEMBER;
        }

        return null;
    }

    /**
     * Returns pseudo-random day for actual month
     */
    public static int randomDay() {

        return randomDay(Month.valueByNumber(ACTUAL_MONTH), ACTUAL_YEAR);
    }

    /**
     * Returns pseudo-random day for specified month and year
     */
    public static int randomDay(Month month, int year) {
        assertThat(year)
                .as("Given month is negative").isPositive();
        int range = month.maxDays(year);

        return new Random().nextInt(range) + 1;
    }

    /**
     * Returns pseudo-random day between first day and actual day (inclusive) of actual month
     */
    public static int randomDayFromPast() {

        return new Random().nextInt(ACTUAL_DAY) + 1;
    }

    /**
     * Returns pseudo-random day between actual day (exclusive) and last day of actual month
     */
    public static int randomDayFromFuture() {
        Month month = Month.valueByNumber(ACTUAL_MONTH);
        int range = month.maxDays(ACTUAL_YEAR) - ACTUAL_DAY;
        int random = new Random().nextInt(range) + 1;

        return ACTUAL_DAY + random;
    }

    /**
     * Returns pseudo-random date from pseudo-random year between 100 years ago and 100 years ahead
     */
    public static Date randomDate() {
        int year = randomYear();
        Month month = randomMonth();
        int monthNumber = month.numberOf();
        int day = randomDay(month, year);

        return new Date(day, monthNumber, year);
    }

    /**
     * Returns pseudo-random date from specified years
     */
    public static Date randomDate(int start, int end) {
        int year = randomYear(start, end);
        Month month = randomMonth();
        int monthNumber = month.numberOf();
        int day = randomDay(month, year);

        return new Date(day, monthNumber, year);
    }

    /**
     * Returns pseudo-random day from past
     */
    public static Date randomDateOfPast() {

        return randomDateOfPast(ACTUAL_YEAR - 100);
    }

    /**
     * Returns pseudo-random date between given year and actual year
     */
    public static Date randomDateOfPast(int fromYear) {
        assertThat(fromYear)
                .as("Given year is negative")
                .isPositive()
                .as("Given year is greater then actual year")
                .isLessThanOrEqualTo(ACTUAL_YEAR);
        int year = randomYearFromPast(fromYear);
        Month month = randomMonthFromPast();
        int monthNumber = month.numberOf();
        int day = randomDay(month, year);

        return new Date(day, monthNumber, year);
    }

    /**
     * Returns pseudo-random date from pseudo-random year between actual year and 100 years ahead
     */
    public static Date randomDateOfFuture() {

        return randomDateOfFuture(ACTUAL_YEAR + 100);
    }

    /**
     * Returns pseudo-random date from specified year of ahead
     */
    public static Date randomDateOfFuture(int toYear) {
        assertThat(toYear)
                .as("Given year is negative")
                .isPositive()
                .as("Given year is less then actual year")
                .isGreaterThanOrEqualTo(ACTUAL_YEAR);
        int year = randomYearFromFuture(toYear);
        Month month = randomMonthFromFuture();
        int monthNumber = month.numberOf();
        int day = randomDay(month, year);

        return new Date(day, monthNumber, year);
    }
}
