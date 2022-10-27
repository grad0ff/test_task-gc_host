package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.hostco.pp86.data.Indicator;
import ru.hostco.pp86.helpers.datetime.Date;
import ru.hostco.pp86.helpers.datetime.Time;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class IndicatorsAddingFormComponent {

    public SelenideElement component = $("p-dialog .ui-dialog-draggable");
    public SelenideElement calendarLink = $("p-dialog .ui-dialog-draggable .ui-calendar");
    public CalendarWithTimeComponent calendar = new CalendarWithTimeComponent();

    @Step("Click by calendar link")
    public IndicatorsAddingFormComponent clickByCalendarLink() {
        calendarLink.click();
        return this;
    }

    @Step("Select date of recording")
    public IndicatorsAddingFormComponent selectDate(Date date) {
        calendar.selectDate(date);
        return this;
    }

    @Step("Select time of recording")
    public IndicatorsAddingFormComponent selectTime(Time time) {
        calendar.selectTime(time);
        return this;
    }

    @Step("Set temperature od body")
    public IndicatorsAddingFormComponent setTemperature(double temperature) {
        $$("p-dialog .ui-g").findBy(text(Indicator.TEMPERATURE.text())).$("input").setValue(String.valueOf(temperature));
        return this;
    }

    @Step("Set body weight")
    public IndicatorsAddingFormComponent setWeight(double weight) {
        $$("p-dialog .ui-g").findBy(text(Indicator.WEIGHT.text())).$("input").setValue(String.valueOf(weight));
        return this;
    }

    @Step("Set blood pressure")
    public IndicatorsAddingFormComponent setPressure(int maxPressure, int minPressure) {
        $$("p-dialog .ui-g").findBy(text(Indicator.PRESSURE.text())).$("input")
                .setValue(String.valueOf(maxPressure)).sibling(0)
                .setValue(String.valueOf(minPressure));
        return this;
    }

    @Step("Set sugar level")
    public IndicatorsAddingFormComponent setSugarLevel(double sugarLevel) {
        $$("p-dialog .ui-g").findBy(text(Indicator.SUGAR_LEVEL.text())).$("input").setValue(String.valueOf(sugarLevel));
        return this;
    }

    @Step("Set pulse level")
    public IndicatorsAddingFormComponent setPulse(int pulse) {
        $$("p-dialog .ui-g").findBy(text(Indicator.PULSE.text())).$("input").setValue(String.valueOf(pulse));
        return this;
    }

    @Step("Set mood description")
    public IndicatorsAddingFormComponent setMood(String mood) {
        $$("p-dialog .ui-g").findBy(text(Indicator.MOOD.text())).$("textarea").setValue(mood);
        return this;
    }

    @Step("Set alcohol level in blood")
    public IndicatorsAddingFormComponent setAlcohol(double alcohol) {
        $$("p-dialog .ui-g").findBy(text(Indicator.ALCOHOL.text())).$("input").setValue(String.valueOf(alcohol));
        return this;
    }

    @Step("Set ambivalence percent")
    public IndicatorsAddingFormComponent setAmbivalence(int ambivalence) {
        $$("p-dialog .ui-g").findBy(text(Indicator.AMBIVALENCE.text())).$("input").setValue(String.valueOf(ambivalence));
        return this;
    }

    @Step("Set health status description")
    public IndicatorsAddingFormComponent setHealthStatus(String healthStatus) {
        $$("p-dialog .ui-g").findBy(text(Indicator.HEALTH_STATUS.text())).$("textarea").setValue(healthStatus);
        return this;
    }

    @Step("Set skin condition description")
    public IndicatorsAddingFormComponent setSkinCondition(String skinCondition) {
        $$("p-dialog .ui-g").findBy(text(Indicator.SKIN_CONDITION.text())).$("textarea").setValue(skinCondition);
        return this;
    }

    @Step("Click by submit")
    public void clickBySubmit() {
        $("[type='submit']").click();
    }
}


