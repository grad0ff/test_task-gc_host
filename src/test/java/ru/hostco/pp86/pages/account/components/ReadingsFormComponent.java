package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.helpers.HealthIndicators;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReadingsFormComponent {

    public SelenideElement component = $("p-dialog .ui-dialog-draggable");
    public SelenideElement calendarLink = $("p-dialog .ui-dialog-draggable .ui-calendar");
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

    public ReadingsFormComponent selectTime(String newTimeAsString) {
        calendar.setTime(newTimeAsString);
        return this;
    }

    public ReadingsFormComponent setTemperature(double temperature) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.TEMPERATURE.text())).$("input").setValue(String.valueOf(temperature));
        return this;
    }

    public ReadingsFormComponent setWeight(double weight) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.WEIGHT.text())).$("input").setValue(String.valueOf(weight));
        return this;
    }

    public ReadingsFormComponent setPressure(int maxPressure, int minPressure) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.PRESSURE.text())).$("input")
                .setValue(String.valueOf(maxPressure)).sibling(0)
                .setValue(String.valueOf(minPressure));
        return this;
    }

    public ReadingsFormComponent setSugarLevel(double sugarLevel) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.SUGAR_LEVEL.text())).$("input").setValue(String.valueOf(sugarLevel));
        return this;
    }

    public ReadingsFormComponent setPulse(int pulse) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.PULSE.text())).$("input").setValue(String.valueOf(pulse));
        return this;
    }

    public ReadingsFormComponent setMood(String mood) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.MOOD.text())).$("textarea").setValue(mood);
        return this;
    }

    public ReadingsFormComponent setAlcohol(double alcohol) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.ALCOHOL.text())).$("input").setValue(String.valueOf(alcohol));
        return this;
    }

    public ReadingsFormComponent setAmbivalence(int ambivalence) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.AMBIVALENCE.text())).$("input").setValue(String.valueOf(ambivalence));
        return this;
    }


    public ReadingsFormComponent setHealthStatus(String healthStatus) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.HEALTH_STATUS.text())).$("textarea").setValue(healthStatus);
        return this;
    }

    public ReadingsFormComponent setSkinCondition(String skinCondition) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.SKIN_CONDITION.text())).$("textarea").setValue(skinCondition);
        return this;
    }

    public void clickBySubmit() {
        $("[type='submit']").click();
    }
}


