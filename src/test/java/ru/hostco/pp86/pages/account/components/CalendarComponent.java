package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public SelenideElement calendar = $(".ui-datepicker-calendar-container");
    public SelenideElement monthAndYearLabel = $(".ui-datepicker-title");
//    public SelenideElement monthAndYearLabel = $(".ui-state-active");

    public CalendarComponent selectDay(int day) {
        $("[draggable=*]").$(By.linkText(String.valueOf(day))).click();
        return this;
    }


    public CalendarComponent selectMonthAndYear(int month, int year) {
        String[] splitDate = monthAndYearLabel.getText().split("");
        String currentMonth = splitDate[0];
        int currentYear = Integer.parseInt(splitDate[1]);
        int deltaYears = year - currentYear;
        while (currentYear != year) {

        }
        $("[draggable=*]").$(By.linkText(String.valueOf(month))).click();
        return this;
    }

    public CalendarComponent selectYear(int year) {
        $("[draggable=*]").$(By.linkText(String.valueOf(year))).click();
        return this;
    }

}
