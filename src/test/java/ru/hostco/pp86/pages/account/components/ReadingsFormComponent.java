package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import ru.hostco.pp86.helpers.Dates;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReadingsFormComponent {

    public SelenideElement component = $("p-dialog .ui-dialog-draggable");
    public SelenideElement calendarLink = component.$(".ui-calendar");
    public CalendarWithTimeComponent calendar = new CalendarWithTimeComponent();

    public ReadingsFormComponent clickByCalendarLink() {
        calendarLink.click();
        return this;
    }

    public ReadingsFormComponent selectDate(Dates date) {
        calendar.selectMonthAndYear(date.getMonthNumber(), date.getYear());
        calendar.selectDay(date.getDayNumber());
        return this;
    }

    public ReadingsFormComponent setTime(int hours, int minutes) {
        calendar.setTime(hours, minutes);
        return this;
    }

    public ReadingsFormComponent setTemperature(double temperature) {
        $$("p-dialog .ui-g").findBy(text("Температура")).parent().sibling(1).setValue(String.valueOf(temperature));
        return this;
    }
}
