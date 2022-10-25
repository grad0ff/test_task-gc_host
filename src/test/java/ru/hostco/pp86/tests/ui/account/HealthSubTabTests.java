package ru.hostco.pp86.tests.ui.account;

import org.apache.commons.math3.util.Precision;
import org.testng.annotations.Test;
import ru.hostco.pp86.data.Date;
import ru.hostco.pp86.data.HealthIndicators;
import ru.hostco.pp86.data.Time;
import ru.hostco.pp86.helpers.DateRandomizer;
import ru.hostco.pp86.helpers.TimeRandomizer;
import ru.hostco.pp86.pages.account.components.ReadingsFormComponent;
import ru.hostco.pp86.pages.account.components.ReadingsTableComponent;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.TestBase;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {"ui", "authorized"}, testName = "Health sub tab tests")
public class HealthSubTabTests extends TestBase {

    HealthSubTab subTab = new HealthSubTab();

    @Test(groups = "authorized")
    void addTemperatureReadingTest() {
        ReadingsFormComponent readingsForm = subTab.readingsForm;
        ReadingsTableComponent readingsTable = new ReadingsTableComponent();
        Date date = Date.today();
        Time time = TimeRandomizer.randomTime(0, 2);
        double temperature = Precision.round(36.3 + new Random().nextDouble(), 1);

        step("Open health sub tab in browser", () -> open(subTab.getUrl()));
        step("Click by indicator adding button", () -> subTab.clickByAddReadingButton());
        step("Select actual time and date in the inner calendar", () -> {
            readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible);
            readingsForm.selectTime(time);
            readingsForm.selectDate(date);
        });
        step("Set temperature", () -> {
            sleep(500);
            readingsForm.setTemperature(temperature);
        });
        step("click by submit", readingsForm::clickBySubmit);
        step("Check that readingTable contains data", () -> {
            readingsTable.firstReading.shouldHave(
                    text(date + " " + time),
                    text(HealthIndicators.TEMPERATURE.text()),
                    text(String.valueOf(temperature))
            );
        });
//        delete record with API todo
    }

    @Test(groups = "authorized")
    void setBeginningDateTest() {
        Date randomDate = DateRandomizer.randomDateOfPast(2022);

        step("Open health sub tab", () -> open(subTab.getUrl()));
        step("Select random date of beginning", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Date currentBeginningDate = new Date(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.toString())
                    .as("Beginning date assertion FAILED")
                    .isEqualTo(randomDate.toString());
        });
    }

    @Test(groups = "authorized")
    void setEndingDateTest() {
        HealthSubTab subTab = new HealthSubTab();
        Date randomDate = DateRandomizer.randomDateOfFuture(2023);

        step("Open health sub tab", () -> open(subTab.getUrl()));
        step("Select random date of end", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndingDateField().calendar.component.shouldBe(visible);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Date currentEndDate = new Date(subTab.endDateField.getValue());
            assertThat(currentEndDate.toString())
                    .as("Ending date assertion FAILED")
                    .isEqualTo(randomDate.toString());
        });
    }

    @Test(groups = "authorized")
    void setDatesFilterTest() {
        HealthSubTab subTab = new HealthSubTab();
        Date randomBeginningDate = DateRandomizer.randomDateOfPast(2022);
        Date randomEndingDate = DateRandomizer.randomDateOfFuture(2023);

        step("Open health sub tab", () -> open(subTab.getUrl()));
        step("Select random date of beginning", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible);
            subTab.selectDate(randomBeginningDate);
        });
        step("Select random date of end", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndingDateField().calendar.component.shouldBe(visible);
            subTab.selectDate(randomEndingDate);
        });
        step("Check that dates set correctly", () -> {
            sleep(1000);
            Date currentBeginningDate = new Date(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.toString())
                    .as("Beginning date assertion FAILED")
                    .isEqualTo(randomBeginningDate.toString());
            Date currentEndDate = new Date(subTab.endDateField.getValue());
            assertThat(currentEndDate.toString())
                    .as("Ending date assertion FAILED")
                    .isEqualTo(randomEndingDate.toString());
        });
    }

    //todo
    @Test(groups = "authorized")
    void resetDatesFilterTest() {
    }
}
