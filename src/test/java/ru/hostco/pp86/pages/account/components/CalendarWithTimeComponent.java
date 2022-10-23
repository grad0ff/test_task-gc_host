package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarWithTimeComponent extends CalendarComponent {

    public SelenideElement hoursLabel = $("p-dialog .ui-hour-picker");
    public SelenideElement minutesLabel = $("p-dialog .ui-minute-picker");

    public CalendarComponent setTime(int hours, int minutes) {
        setHours(hours);
        setMinutes(minutes);
        return this;
    }

    public CalendarWithTimeComponent setHours(int hours) {
        int currentHours = Integer.parseInt(hoursLabel.text());
        int delta = hours - currentHours;
        int scrollCount = (Math.abs(delta)) % 24;
        if (delta < 0) fewScrollToPrevHour((scrollCount));
        else fewScrollToNextHour(scrollCount);
        return this;
    }

    public CalendarWithTimeComponent setMinutes(int minutes) {
        int currentMinutes = Integer.parseInt(minutesLabel.text());
        int delta = minutes - currentMinutes;
        int scrollCount = (Math.abs(delta)) % 60;
        if (delta < 0) fewScrollToPrevMinute((scrollCount));
        else fewScrollToNextMinute(scrollCount);
        return this;
    }

    public CalendarWithTimeComponent fewScrollToNextHour(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-hour-picker .pi-chevron-up").click();
        }
        return this;
    }

    public CalendarWithTimeComponent fewScrollToPrevHour(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-hour-picker .pi-chevron-down").click();
        }
        return this;
    }

    public CalendarWithTimeComponent fewScrollToNextMinute(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-minute-picker .pi-chevron-up").click();
        }
        return this;
    }

    public CalendarWithTimeComponent fewScrollToPrevMinute(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-minute-picker .pi-chevron-down").click();
        }
        return this;
    }


}
