package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.hostco.pp86.helpers.Months;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public SelenideElement calendar = $(".ui-datepicker-calendar-container");
    public SelenideElement monthLabel = $(".ui-datepicker-month");
    public SelenideElement yearLabel = $(".ui-datepicker-year");
//    public SelenideElement monthAndYearLabel = $(".ui-state-active");

    public CalendarComponent selectMonthAndYear(int month, int year) {
        String monthName = monthLabel.text();
        int currentMonthOrder = Months.valueOfByRussian(monthName).monthNumber();
        int currentYear = Integer.parseInt(yearLabel.text());
        int deltaOfYear = year - currentYear;
        int deltaOfMonth = month - currentMonthOrder;
        int scrollCount = deltaOfYear * 12 + deltaOfMonth;
        if (scrollCount < 0) fewScrollToPrev(Math.abs(scrollCount));
        else fewScrollToNext(Math.abs(scrollCount));

        return this;
    }

    public CalendarComponent fewScrollToNext(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-datepicker-next").click();
        }

        return this;
    }

    public CalendarComponent singleScrollToNext() {
        $(".ui-datepicker-next").click();

        return this;
    }

    public CalendarComponent fewScrollToPrev(int count) {
        for (int i = 0; i < count; i++) {
            $(".ui-datepicker-prev").click();
        }

        return this;
    }

    public CalendarComponent singleScrollToPrev() {
        $(".ui-datepicker-prev").click();

        return this;
    }

    public CalendarComponent selectDay(int day) {
        $$(byAttribute("draggable", "false")).findBy(text(String.valueOf(day))).click();
        return this;
    }

    public CalendarComponent selectYear(int year) {
        $("[draggable='false'] ").$(By.linkText(String.valueOf(year))).click();

        return this;
    }

}
