package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.hostco.pp86.data.Date;
import ru.hostco.pp86.data.HealthIndicators;
import ru.hostco.pp86.data.Time;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReadingsFormComponent {

    public SelenideElement component = $("p-dialog .ui-dialog-draggable");
    public SelenideElement calendarLink = $("p-dialog .ui-dialog-draggable .ui-calendar");
    public CalendarWithTimeComponent calendar = new CalendarWithTimeComponent();

    @Step("Click by calendar link")
    public ReadingsFormComponent clickByCalendarLink() {
        calendarLink.click();
        return this;
    }

    @Step("Select date of recording")
    public ReadingsFormComponent selectDate(Date date) {
        calendar.selectDate(date);
        return this;
    }

    @Step("Select time of recording")
    public ReadingsFormComponent selectTime(Time time) {
        calendar.selectTime(time);
        return this;
    }

    @Step("Set temperature od body")
    public ReadingsFormComponent setTemperature(double temperature) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.TEMPERATURE.text())).$("input").setValue(String.valueOf(temperature));
        return this;
    }

    @Step("Set body weight")
    public ReadingsFormComponent setWeight(double weight) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.WEIGHT.text())).$("input").setValue(String.valueOf(weight));
        return this;
    }

    @Step("Set blood pressure")
    public ReadingsFormComponent setPressure(int maxPressure, int minPressure) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.PRESSURE.text())).$("input")
                .setValue(String.valueOf(maxPressure)).sibling(0)
                .setValue(String.valueOf(minPressure));
        return this;
    }

    @Step("Set sugar level")
    public ReadingsFormComponent setSugarLevel(double sugarLevel) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.SUGAR_LEVEL.text())).$("input").setValue(String.valueOf(sugarLevel));
        return this;
    }

    @Step("Set pulse level")
    public ReadingsFormComponent setPulse(int pulse) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.PULSE.text())).$("input").setValue(String.valueOf(pulse));
        return this;
    }

    @Step("Set mood description")
    public ReadingsFormComponent setMood(String mood) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.MOOD.text())).$("textarea").setValue(mood);
        return this;
    }

    @Step("Set alcohol level in blood")
    public ReadingsFormComponent setAlcohol(double alcohol) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.ALCOHOL.text())).$("input").setValue(String.valueOf(alcohol));
        return this;
    }

    @Step("Set ambivalence percent")
    public ReadingsFormComponent setAmbivalence(int ambivalence) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.AMBIVALENCE.text())).$("input").setValue(String.valueOf(ambivalence));
        return this;
    }

    @Step("Set health status description")
    public ReadingsFormComponent setHealthStatus(String healthStatus) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.HEALTH_STATUS.text())).$("textarea").setValue(healthStatus);
        return this;
    }

    @Step("Set skin condition description")
    public ReadingsFormComponent setSkinCondition(String skinCondition) {
        $$("p-dialog .ui-g").findBy(text(HealthIndicators.SKIN_CONDITION.text())).$("textarea").setValue(skinCondition);
        return this;
    }

    @Step("Click by submit")
    public void clickBySubmit() {
        $("[type='submit']").click();
    }
}


