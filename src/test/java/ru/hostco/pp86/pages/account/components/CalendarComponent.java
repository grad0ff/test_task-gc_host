package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
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

    public CalendarComponent selectMonthAndYear(int month, int year) {
        int scrollCount = Scrolls.getDateScroll(month, year, monthLabel.text(), parseInt(yearLabel.text()));
        if (scrollCount < 0) fewScrollToPrevDay(Math.abs(scrollCount));
        else fewScrollToNextDay(scrollCount);
        return this;
    }

    public void selectDay(int day) {
        $$(byAttribute("draggable", "false")).findBy(text(valueOf(day))).click();
    }

    public void fewScrollToNextDay(int scrollCount) {
        for (int i = 0; i < scrollCount; i++) {
            scrollToNextDay();
        }
    }

    public void scrollToNextDay() {
        $(".ui-datepicker-next").click();
    }

    public void fewScrollToPrevDay(int scrollCount) {
        for (int i = 0; i < scrollCount; i++) {
            scrollToPrevDay();
        }
    }

    public void scrollToPrevDay() {
        $(".ui-datepicker-prev").click();
    }
}
