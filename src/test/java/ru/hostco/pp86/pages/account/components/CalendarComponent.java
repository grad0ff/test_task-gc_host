package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.hostco.pp86.data.Date;
import ru.hostco.pp86.helpers.Scrolls;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class CalendarComponent {

    public SelenideElement component = $(".ui-datepicker-calendar-container");
    public SelenideElement monthLabel = $(".ui-datepicker-month");
    public SelenideElement yearLabel = $(".ui-datepicker-year");

    @Step("Select date: {date}")
    public CalendarComponent selectDate(Date date) {
        selectMonthAndYear(date.getMonthNumber(), date.getYear());
        selectDay(date.getDayNumber());
        return this;
    }

    @Step("Select month and year: {month}\\.{year}")
    public CalendarComponent selectMonthAndYear(int month, int year) {
        int scrollCount = Scrolls.getDateScrollCount(month, year, monthLabel.text(), parseInt(yearLabel.text()));
        if (scrollCount < 0) fewScrollToPrevDay(Math.abs(scrollCount));
        else fewScrollToNextDay(scrollCount);
        return this;
    }

    @Step("Scrolling up to month and year")
    public void fewScrollToNextDay(int scrollCount) {
        for (int i = 0; i < scrollCount; i++) {
            scrollToNextDay();
        }
    }

    @Step("Scrolling down to month and year")
    public void fewScrollToPrevDay(int scrollCount) {
        for (int i = 0; i < scrollCount; i++) {
            scrollToPrevDay();
        }
    }

    @Step("Select day: {day}")
    public void selectDay(int day) {
        $$(byAttribute("draggable", "false")).findBy(text(valueOf(day))).click();
    }

    private void scrollToPrevDay() {
        $(".ui-datepicker-prev").click();
    }

    private void scrollToNextDay() {
        $(".ui-datepicker-next").click();
    }
}
