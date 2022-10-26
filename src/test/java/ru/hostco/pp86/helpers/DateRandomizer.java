package ru.hostco.pp86.helpers;

import ru.hostco.pp86.data.Month;

import java.time.LocalDate;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DateRandomizer {

    private static final int ACTUAL_YEAR = LocalDate.now().getYear();
    private static final int MONTH_VALUE = LocalDate.now().getMonthValue();
    private static final int DAY_OF_MONTH = LocalDate.now().getDayOfMonth();

    /**
     * Returns pseudo-random year from specified interval
     */
    public static int randomYear(int start, int end) {
        assertThat(end).isPositive();
        assertThat(start)
                .isPositive()
                .isLessThanOrEqualTo(end);
        int range = end - start;
        int random = new Random().nextInt(range + 1);
        return start + random;
    }

    /**
     * Returns pseudo-random month
     */
    public static Month randomMonth() {
        return randomMonth(1, 12);
    }

    /**
     * Returns pseudo-random month from specified interval
     */
    public static Month randomMonth(int start, int end) {
        assertThat(end).isPositive();
        assertThat(start)
                .isPositive()
                .isLessThanOrEqualTo(end);
        int range = end - start;
        int random = new Random().nextInt(range);
        int aSwitch = start + random;
        switch (aSwitch) {
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
     * Returns pseudo-random day for actual month of actual year
     */
    public static int randomDay() {
        return new Random().nextInt(31) + 1;
    }

    /**
     * Returns pseudo-random date from specified year of past
     */
    public static Date randomDateOfPast(int fromYear) {
        assertThat(fromYear)
                .isPositive()
                .isLessThanOrEqualTo(LocalDate.now().getYear());
        int year = randomYear(fromYear, ACTUAL_YEAR);
        Month month = randomMonth(1, MONTH_VALUE);
        assertThat(month).isNotNull();
        return createDate(year, month);
    }

    /**
     * Returns pseudo-random date from specified year of future
     */
    public static Date randomDateOfFuture(int toYear) {
        assertThat(toYear)
                .isPositive()
                .isGreaterThanOrEqualTo(LocalDate.now().getYear());
        int year = randomYear(ACTUAL_YEAR, toYear);
        Month month = randomMonth(MONTH_VALUE, 12);
        assertThat(month).isNotNull();
        return createDate(year, month);

    }

    /*
     * Returns new Date instance from specified year and month
     */
    private static Date createDate(int year, Month month) {
        int monthNumber = month.numberOf();
        int maxDays = month.maxDays(year);
        int dayNumber = new Random().nextInt(maxDays) + 1;
        return new Date(dayNumber, monthNumber, year);
    }
}
