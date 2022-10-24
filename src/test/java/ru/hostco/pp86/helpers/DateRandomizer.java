package ru.hostco.pp86.helpers;

import java.time.LocalDate;
import java.util.Random;

public class DateRandomizer {

    private static final int actualYear = LocalDate.now().getYear();
    private static final int actualMonth = LocalDate.now().getMonthValue();
    private static final int actualDay = LocalDate.now().getDayOfMonth();

    /**
     * Returns pseudo-random year in the specified interval
     */
    public static int randomYear(int first, int last) {
        int range = last - first;
        int random = new Random().nextInt(range + 1);
        return first + random;
    }

    /**
     * Returns pseudo-random month of year
     */
    public static Months randomMonth() {
        return randomMonth(1, 12);
    }

    /**
     * Returns pseudo-random month from specified interval
     */
    public static Months randomMonth(int first, int last) {
        int range = last - first;
        int random = new Random().nextInt(range + 1);
        switch (first + random) {
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
     * Returns pseudo-random date for specific year and month
     */
    public static Dates randomDateOfPast(int fromYear) {
        int year = randomYear(fromYear, actualYear);
        Months month = randomMonth(1, actualMonth);
        return createDate(year, month);
    }

    /**
     * Returns pseudo-random date for specific year and month
     */
    public static Dates randomDateOfFuture(int toYear) {
        int year = randomYear(actualYear, toYear);
        Months month = randomMonth(actualMonth, 12);
        return createDate(year, month);

    }

    private static Dates createDate(int year, Months month) {
        int monthNumber = month.numberOf();
        int maxDays = month.getMaxDays(year, month);
        int dayNumber = new Random().nextInt(maxDays) + 1;
        return new Dates(dayNumber + "." + monthNumber + "." + year);
    }

}

