package ru.hostco.pp86.helpers;

import ru.hostco.pp86.data.Months;

public class Scrolls {

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
        int actualMonthNumber = Months.valueByRussian(actualMonth).numberOf();
        int deltaOfYear = newYear - actualYear;
        int deltaOfMonth = newMonth - actualMonthNumber;
        return deltaOfYear * 12 + deltaOfMonth;
    }
}
