package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.hostco.pp86.helpers.Scrolls;
import ru.hostco.pp86.helpers.Time;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;

public class CalendarWithTimeComponent extends CalendarComponent {

    public SelenideElement hoursLabel = $("p-dialog .ui-hour-picker");
    public SelenideElement minutesLabel = $("p-dialog .ui-minute-picker");

    @Step("Select time")
    public CalendarComponent selectTime(Time time) {
        int[] scrolls = Scrolls.getTimeScrollCount(time, parseInt(hoursLabel.text()),
                parseInt(minutesLabel.text()));
        setHours(scrolls[0]);
        setMinutes(scrolls[1]);
        return this;
    }

    @Step("Select hours with scrolling up")
    public void fewScrollToNextHour(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToNextHour();
        }
    }


    @Step("Select hours with scrolling down")
    public void fewScrollToPrevHour(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToPrevHour();
        }
    }


    @Step("Select minutes with scrolling up")
    public void scrollToNextMinute(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToNextMinute();
        }
    }

    @Step("Select minutes with scrolling down")
    public void fewScrollToPrevMinute(int scrollsCount) {
        for (int i = 0; i < scrollsCount; i++) {
            scrollToPrevMinute();
        }
    }

    private CalendarWithTimeComponent setHours(int scrollsCount) {
        if (scrollsCount < 0) fewScrollToPrevHour(Math.abs(scrollsCount));
        else fewScrollToNextHour(scrollsCount);
        return this;
    }

    private CalendarWithTimeComponent setMinutes(int scrollsCount) {
        if (scrollsCount < 0) fewScrollToPrevMinute(Math.abs(scrollsCount));
        else scrollToNextMinute(scrollsCount);
        return this;
    }

    private void scrollToNextHour() {
        $(".ui-hour-picker .pi-chevron-up").click();
    }

    private void scrollToPrevHour() {
        $(".ui-hour-picker .pi-chevron-down").click();
    }

    private void scrollToNextMinute() {
        $(".ui-minute-picker .pi-chevron-up").click();
    }

    private void scrollToPrevMinute() {
        $(".ui-minute-picker .pi-chevron-down").click();
    }

}
