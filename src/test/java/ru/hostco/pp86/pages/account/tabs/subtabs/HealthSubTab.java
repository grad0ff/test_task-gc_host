package ru.hostco.pp86.pages.account.tabs.subtabs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.pages.Openable;
import ru.hostco.pp86.pages.account.Account;
import ru.hostco.pp86.pages.account.components.CalendarComponent;
import ru.hostco.pp86.pages.account.tabs.OtherTab;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HealthSubTab implements Openable {

    public static String URL = Account.URL + OtherTab.URL + "/health";
    public SelenideElement subTub = $(By.linkText("Показатели здоровья"));
    public SelenideElement beginningDateField = $$(".ui-calendar").first().lastChild();
    public SelenideElement endDateField = $$(".ui-calendar").last().lastChild();
    public CalendarComponent calendar = new CalendarComponent();

    @Override
    public String getUrl() {
        return URL;
    }

    public HealthSubTab clickByBeginningDateField() {
        beginningDateField.click();
        return this;
    }

    public void selectDate(Dates date) {
        calendar.selectMonthAndYear(date.getMonthNumber(), date.getYear());
        calendar.selectDay(date.getDayNumber());
    }

    // todo проверить метод
    public void setBeginningDate(String date) {
        beginningDateField.setValue(date);
    }

    // todo проверить метод
    public void setEndDate(String date) {
        beginningDateField.setValue(date);
    }
}
