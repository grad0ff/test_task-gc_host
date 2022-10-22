package ru.hostco.pp86.pages.account.tabs.subtabs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.hostco.pp86.pages.Openable;
import ru.hostco.pp86.pages.account.Account;
import ru.hostco.pp86.pages.account.components.CalendarComponent;
import ru.hostco.pp86.pages.account.tabs.OtherTab;

import static com.codeborne.selenide.Selenide.$;

public class HealthSubTab implements Openable {

    public static String URL = Account.URL + OtherTab.URL + "/health";
    public SelenideElement subTub = $(By.linkText("Показатели здоровья"));
    public SelenideElement BeginningDateField = $(".create-filter-block .range-date-filter");
    public CalendarComponent calendar = new CalendarComponent();

    @Override
    public String getUrl() {
        return URL;
    }

    public HealthSubTab clickByBeginningDateField() {
        BeginningDateField.click();
        return this;
    }

    public void selectDate(int day, int month, int year) {
        calendar.selectMonthAndYear(month, year);
        calendar.selectDay(day);
    }

    // todo проверить метод
    public void setBeginningDate(String date) {
        BeginningDateField.setValue(date);
    }

    // todo проверить метод
    public void setEndDate(String date) {
        BeginningDateField.setValue(date);
    }
}
