package ru.hostco.pp86.helpers.utils;

import ru.hostco.pp86.data.Month;
import ru.hostco.pp86.helpers.datetime.Time;

public class Scrolls {

    /*
     * Returns integer array of scroll counts for hour and minute in new time
     */
    public static int[] getTimeScrollCount(Time newTime, int actualHours, int actualMinutes) {
        int hourScrollCount = timeScrolls(newTime.getHours(), actualHours, 24);
        int minutesScrollCount = timeScrolls(newTime.getMinutes(), actualMinutes, 60);
        return new int[]{hourScrollCount, minutesScrollCount};
    }

    public static int timeScrolls(int newTimeElement, int actualTimeElement, int divider) {
        int delta = newTimeElement - actualTimeElement;
        int scrollCount = (Math.abs(delta)) % divider;
        if (delta < 0) return scrollCount * (-1);
        return scrollCount;
    }

    public static int getDateScrollCount(int newMonth, int newYear, String actualMonth, int actualYear) {
        int actualMonthNumber = Month.valueByRussian(actualMonth).numberOf();
        int deltaOfYear = newYear - actualYear;
        int deltaOfMonth = newMonth - actualMonthNumber;
        return deltaOfYear * 12 + deltaOfMonth;
    }
}
