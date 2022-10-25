package ru.hostco.pp86.helpers;

import ru.hostco.pp86.data.Months;

import java.time.LocalDate;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DateRandomizer {

    private static final int ACTUAL_YEAR = LocalDate.now().getYear();
    private static final int MONTH_VALUE = LocalDate.now().getMonthValue();
    private static final int DAY_OF_MONTH = LocalDate.now().getDayOfMonth();

    /**
     * Returns pseudo-random year in specified interval
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
    public static Months randomMonth() {
        return randomMonth(1, 12);
    }

    /**
     * Returns pseudo-random month from specified interval
     */
    public static Months randomMonth(int start, int end) {
        assertThat(end).isPositive();
        assertThat(start)
                .isPositive()
                .isLessThanOrEqualTo(end);
        int range = end - start;
        int random = new Random().nextInt(range);
        int aSwitch = start + random;
        switch (aSwitch) {
            case 0:
                return Months.JANUARY;
            case 1:
                return Months.FEBRUARY;
            case 2:
                return Months.MARCH;
            case 3:
                return Months.APRIL;
            case 4:
                return Months.MAY;
            case 5:
                return Months.JUNE;
            case 6:
                return Months.JULY;
            case 7:
                return Months.AUGUST;
            case 8:
                return Months.SEPTEMBER;
            case 9:
                return Months.OCTOBER;
            case 10:
                return Months.NOVEMBER;
            case 11:
                return Months.DECEMBER;
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
     * Returns pseudo-random date for specific year of past
     */
    public static Date randomDateOfPast(int fromYear) {
        assertThat(fromYear)
                .isPositive()
                .isLessThanOrEqualTo(LocalDate.now().getYear());
        int year = randomYear(fromYear, ACTUAL_YEAR);
        Months month = randomMonth(1, MONTH_VALUE);
        assertThat(month).isNotNull();
        return createDate(year, month);
    }

    /**
     * Returns pseudo-random date for specific year of future
     */
    public static Date randomDateOfFuture(int toYear) {
        assertThat(toYear)
                .isPositive()
                .isGreaterThanOrEqualTo(LocalDate.now().getYear());
        int year = randomYear(ACTUAL_YEAR, toYear);
        Months month = randomMonth(MONTH_VALUE, 12);
        assertThat(month).isNotNull();
        return createDate(year, month);

    }

    private static Date createDate(int year, Months month) {
        int monthNumber = month.numberOf();
        int maxDays = month.maxDays(year);
        int dayNumber = new Random().nextInt(maxDays) + 1;
        return new Date(dayNumber, monthNumber, year);
    }
}
