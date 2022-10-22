package ru.hostco.pp86.helpers;

public enum Months {

    JANUARY(31, "Январь"),
    FEBRUARY(28, "Февраль"),
    MARCH(31, "Март"),
    APRIL(30, "Апрель"),
    MAY(31, "Май"),
    JUNE(30, "Июнь"),
    JULY(31, "Июль"),
    AUGUST(31, "Август"),
    SEPTEMBER(30, "Сентябрь"),
    OCTOBER(31, "Октябрь"),
    NOVEMBER(30, "Ноябрь"),
    DECEMBER(31, "Декабрь");

    private int maxDays;
    private final String russianName;

    Months(int maxDays, String russianName) {
        this.maxDays = maxDays;
        this.russianName = russianName;
    }

    public static Months valueOfByRussian(String russianName) {
        for (Months month : Months.values()) {
            if (month.russianName.equals(russianName)) return month;
        }
        throw new NullPointerException();
    }

    public static Months getNumber(String russianName) {
        for (Months month : Months.values()) {
            if (month.russianName.equals(russianName)) return month;
        }
        throw new NullPointerException();
    }

    public int getMaxDays() {
        return maxDays;
    }

    public int getMaxDays(int year, Months month) {
        if ((year % 100 == 0 || year % 4 == 0) && month == Months.FEBRUARY) {
            maxDays++;
        }
        return maxDays;
    }

    public String getRussianName() {
        return russianName;
    }

    public int numberOf() {
        return this.ordinal() + 1;
    }
}
