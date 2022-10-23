package ru.hostco.pp86.helpers;

import static java.lang.Integer.parseInt;

public class Scrolls {

    public static int[] getTimeScrolls(String newTimeAsString, int actualHours, int actualMinutes) {
        String[] splitTime = newTimeAsString.split(":");
        int hourScrollCount = timeScrolls(parseInt(splitTime[0]), actualHours, 24);
        int minutesScrollCount = timeScrolls(parseInt(splitTime[1]), actualMinutes, 60);
        return new int[]{hourScrollCount, minutesScrollCount};
    }

    public static int timeScrolls(int newTimeEntity, int actualTimeEntity, int divider) {
        int delta = newTimeEntity - actualTimeEntity;
        int scrollCount = (Math.abs(delta)) % divider;
        if (delta < 0) return ~scrollCount + 1;
        return scrollCount;
    }

    public static int getDateScroll(int newMonth, int newYear, String actualMonth, int actualYear) {
        int actualMonthNumber = Months.valueOfByRussian(actualMonth).numberOf();
        int deltaOfYear = newYear - actualYear;
        int deltaOfMonth = newMonth - actualMonthNumber;
        return deltaOfYear * 12 + deltaOfMonth;
    }
}
