package ru.hostco.pp86.helpers;

import java.time.LocalDate;
import java.util.Random;

public class DateGradomizer {

    /**
     * Returns pseudo-random year in the specified interval
     */
    public static int getRandomYear(int offsetBefore, int offsetAfter) {
        int currentYear = LocalDate.now().getYear();
        int random = new Random().nextInt(offsetBefore + offsetAfter + 1);
        return currentYear - offsetBefore + random;
    }

    /**
     * Returns pseudo-random year in the default interval
     */
    public static int getRandomYear() {
        return getRandomYear(100, 100);
    }

    /**
     * Returns pseudo-random month
     */
    public static Months getRandomMonth() {
        int monthNumber = new Random().nextInt(12) + 1;
        switch (monthNumber) {
            case 1:
                return Months.JANUARY;
            case 2:
                return Months.FEBRUARY;
            case 3:
                return Months.MARCH;
            case 4:
                return Months.APRIL;
            case 5:
                return Months.MAY;
            case 6:
                return Months.JUNE;
            case 7:
                return Months.JULY;
            case 8:
                return Months.AUGUST;
            case 9:
                return Months.SEPTEMBER;
            case 10:
                return Months.OCTOBER;
            case 11:
                return Months.NOVEMBER;
            case 12:
                return Months.DECEMBER;
        }
        return null;
    }

    /**
     * Returns pseudo-random day for default month
     */
    public static int getRandomDayNumber() {
        return new Random().nextInt(31) + 1;
    }

    /**
     * Returns pseudo-random date as map for specific year and month
     */
    public static Dates randomDate(int yearOfStart, int yearOfEnd) {
        int year = getRandomYear(yearOfStart, yearOfEnd);
        Months month = getRandomMonth();
        int monthNumber = month.numberOf();
        int maxDays = month.getMaxDays(year, month);
        int dayNumber = new Random().nextInt(maxDays) + 1;
        return new Dates(dayNumber, monthNumber, year);
    }

    /**
     * Returns pseudo-random date as map for default year and month
     */
    public static Dates randomDate() {
        return randomDate(100, 100);
    }
}

