package ru.hostco.pp86.pages.account.tabs.subtabs;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.hostco.pp86.data.Date;
import ru.hostco.pp86.pages.Openable;
import ru.hostco.pp86.pages.account.Account;
import ru.hostco.pp86.pages.account.components.CalendarComponent;
import ru.hostco.pp86.pages.account.components.ReadingsFormComponent;
import ru.hostco.pp86.pages.account.tabs.OtherTab;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HealthSubTab implements Openable {

    public static String URL = Account.URL + OtherTab.URL + "/health";
    public SelenideElement beginningDateField = $$(".ui-calendar").first().lastChild();
    public SelenideElement endDateField = $$(".ui-calendar").last().lastChild();
    public SelenideElement addReadingButton = $(".control-health-block .create-block");
    public CalendarComponent calendar = new CalendarComponent();
    public ReadingsFormComponent readingsForm = new ReadingsFormComponent();

    @Override
    public String getUrl() {
        return URL;
    }

    @Step("Click by add reading button")
    public HealthSubTab clickByAddReadingButton() {
        addReadingButton.click();
        return this;
    }

    @Step("Click by beginning date field")
    public HealthSubTab clickByBeginningDateField() {
        beginningDateField.click();
        return this;
    }

    @Step("Click by ending date field")
    public HealthSubTab clickByEndingDateField() {
        endDateField.click();
        return this;
    }

    @Step("Select date in calendar")
    public void selectDate(Date date) {
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
