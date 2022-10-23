package ru.hostco.pp86.helpers;

import java.util.Objects;

public class Dates {

    private final int dayNumber;
    private final int monthNumber;
    private final int year;

    public Dates(String fullDate) {
        String[] date = fullDate.split("\\.");
        dayNumber = Integer.parseInt(date[0]);
        monthNumber = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
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

    public String getFullDate() {
        return dayNumber + "." + monthNumber + "." + year;
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
