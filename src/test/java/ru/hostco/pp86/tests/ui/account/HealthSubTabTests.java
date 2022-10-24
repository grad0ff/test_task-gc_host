package ru.hostco.pp86.tests.ui.account;

import org.testng.annotations.Test;
import ru.hostco.pp86.helpers.DateRandomizer;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.pages.account.components.ReadingsFormComponent;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.UiTestBase;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {"ui", "authorized"}, testName = "Health sub tab tests")
public class HealthSubTabTests extends UiTestBase {

    HealthSubTab subTab = new HealthSubTab();

    @Test(groups = "authorized")
    void writeTemperatureIndicatorTest() {
        ReadingsFormComponent readingsForm = subTab.readingsForm;
        Dates randomDate = DateRandomizer.randomDateOfPast(2022);
        String time = "13:03";
        double temperature = 36.6;

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> subTab.clickByAddReadingButton());
        readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible).shouldBe(interactable);
        sleep(500);
        readingsForm.selectTime(time);
        readingsForm.selectDate(randomDate).setTemperature(temperature);
        sleep(3000);
    }

    @Test(groups = "authorized")
    void setBeginningDateTest() {
        Dates randomDate = DateRandomizer.randomDateOfPast(2022);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Dates currentBeginningDate = new Dates(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.getFullDate()).isEqualTo(randomDate.getFullDate());
        });
    }

    @Test(groups = "authorized")
    void setEndDateTest() {
        HealthSubTab subTab = new HealthSubTab();
        Dates randomDate = DateRandomizer.randomDateOfFuture(2023);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of end in calendar", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Dates currentEndDate = new Dates(subTab.endDateField.getValue());
            assertThat(currentEndDate.getFullDate()).isEqualTo(randomDate.getFullDate());
        });
    }

    @Test(groups = "authorized")
    void setDatesFilterTest() {
        HealthSubTab subTab = new HealthSubTab();
        Dates randomBeginningDate = DateRandomizer.randomDateOfPast(2022);
        Dates randomEndDate = DateRandomizer.randomDateOfFuture(2023);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomBeginningDate);
        });
        step("Select random date of end in calendar", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomEndDate);
        });
        step("Check that dates set correctly", () -> {
            sleep(1000);
            Dates currentBeginningDate = new Dates(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.getFullDate()).as("Beginning Date Assertion FAILED!").isEqualTo(randomBeginningDate.getFullDate());
            Dates currentEndDate = new Dates(subTab.endDateField.getValue());
            assertThat(currentEndDate.getFullDate()).as("End Date Assertion FAILED!").isEqualTo(randomEndDate.getFullDate());
        });
    }

    //todo
    @Test(groups = "authorized")
    void resetDatesFilterTest() {
    }
}
