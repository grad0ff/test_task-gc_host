package ru.hostco.pp86.helpers;

public enum Months {

    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    private final String russianName;

    Months(String russianName) {
        this.russianName = russianName;
    }

    public static Months valueOfByRussian(String russianName) {
        for (Months month : Months.values()) {
            if (month.russianName.equals(russianName)) return month;
        }
        throw new NullPointerException();
    }

    public int monthNumber() {
        return this.ordinal() + 1;
    }
}
