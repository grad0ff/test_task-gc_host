package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import ru.hostco.pp86.helpers.Scrolls;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;

public class CalendarWithTimeComponent extends CalendarComponent {

    public SelenideElement hoursLabel = $("p-dialog .ui-hour-picker");
    public SelenideElement minutesLabel = $("p-dialog .ui-minute-picker");

    public CalendarComponent setTime(String newTimeAsString) {
        int[] scrollsCounts = Scrolls.getTimeScrolls(newTimeAsString, parseInt(hoursLabel.text()),
                parseInt(minutesLabel.text()));
        setHours(scrollsCounts[0]);
        setMinutes(scrollsCounts[1]);
        return this;
    }

    public CalendarWithTimeComponent setHours(int scrollsCount) {
        if (scrollsCount < 0) fewScrollToPrevHour(Math.abs(scrollsCount));
        else fewScrollToNextHour(scrollsCount);
        return this;
    }

    public CalendarWithTimeComponent setMinutes(int scrollsCount) {
        if (scrollsCount < 0) fewScrollToPrevMinute(Math.abs(scrollsCount));
        else scrollToNextMinute(scrollsCount);
        return this;
    }

    public void fewScrollToNextHour(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToNextHour();
        }
    }

    public void scrollToNextHour() {
        $(".ui-hour-picker .pi-chevron-up").click();
    }

    public void fewScrollToPrevHour(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToPrevHour();
        }
    }

    public void scrollToPrevHour() {
        $(".ui-hour-picker .pi-chevron-down").click();
    }

    public void scrollToNextMinute(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToNextMinute();
        }
    }

    public void scrollToNextMinute() {
        $(".ui-minute-picker .pi-chevron-up").click();
    }

    public void fewScrollToPrevMinute(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToPrevMinute();
        }
    }

    public void scrollToPrevMinute() {
        $(".ui-minute-picker .pi-chevron-down").click();
    }
}
