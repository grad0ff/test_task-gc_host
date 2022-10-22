package ru.hostco.pp86.helpers;

import java.util.Objects;

public class Dates {

    private int dayNumber;
    private int monthNumber;
    private int year;

    public Dates(int dayNumber, int monthNumber, int year) {
        this.dayNumber = dayNumber;
        this.monthNumber = monthNumber;

        this.year = year;
    }

    public Dates(String fullDateWithDot) {
        String[] date = fullDateWithDot.split("\\.");
        int dayNumber = Integer.parseInt(date[0]);
        int monthNumber = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        new Dates(dayNumber, monthNumber, year);
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }

    public String asStringFull() {
        return dayNumber + "." + monthNumber + "." + monthNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dates dates = (Dates) o;
        return dayNumber == dates.dayNumber && monthNumber == dates.monthNumber && year == dates.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayNumber, monthNumber, year);
    }

}
