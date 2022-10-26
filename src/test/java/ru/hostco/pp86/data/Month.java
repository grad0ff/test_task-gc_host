package ru.hostco.pp86.data;

public enum Month {

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

    Month(int maxDays, String russianName) {
        this.maxDays = maxDays;
        this.russianName = russianName;
    }

    public static Month valueByNumber(int number) {
        for (Month month : Month.values()) {
            if (month.numberOf() == number) return month;
        }
        throw new NullPointerException();
    }

    public static Month valueByRussian(String russianName) {
        for (Month month : Month.values()) {
            if (month.russianName.equals(russianName)) return month;
        }
        throw new NullPointerException();
    }

    public int maxDays() {
        return maxDays;
    }

    public int maxDays(int year) {
        if (this == FEBRUARY && (year % 100 == 0 || year % 4 == 0)) {
            maxDays++;
        }
        return maxDays;
    }

    public String russianName() {
        return russianName;
    }

    public int numberOf() {
        return this.ordinal() + 1;
    }
}
