package ru.hostco.pp86.tests.ui.account;

import org.testng.annotations.Test;
import ru.hostco.pp86.helpers.DateGradomizer;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.UiTestBase;

import java.util.Random;

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
    void readingsRecordingTest() {
        Dates randomDate = DateGradomizer.randomDate(1, -1);
        int randomHour = new Random().nextInt(25);
        int randomMinute = new Random().nextInt(61);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> subTab.clickByAddReadingButton());
        subTab.readingsForm.component.shouldBe(visible);
        subTab.readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible).shouldBe(interactable);
        subTab.readingsForm.selectDate(randomDate);
        sleep(500);
        subTab.readingsForm.clickByCalendarLink().calendar.component.shouldBe(visible).shouldBe(interactable);
        subTab.readingsForm.calendar.setTime(13, 9);
        subTab.readingsForm.setTemperature(36.6);
        sleep(3000);

    }

    @Test(groups = "authorized")
    void setBeginningDateTest() {
        Dates randomDate = DateGradomizer.randomDate(1, -1);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Dates currentBeginningDate = new Dates(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.asStringFull()).isEqualTo(randomDate.asStringFull());
        });
    }

    @Test(groups = "authorized")
    void setEndDateTest() {
        HealthSubTab subTab = new HealthSubTab();
        Dates randomDate = DateGradomizer.randomDate(-1, 1);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of end in calendar", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomDate);
        });
        step("Check that date set correctly", () -> {
            sleep(1000);
            Dates currentEndDate = new Dates(subTab.endDateField.getValue());
            assertThat(currentEndDate.asStringFull()).isEqualTo(randomDate.asStringFull());
        });
    }

    @Test(groups = "authorized")
    void setDatesFilterTest() {
        HealthSubTab subTab = new HealthSubTab();
        Dates randomBeginningDate = DateGradomizer.randomDate(1, -1);
        Dates randomEndDate = DateGradomizer.randomDate(-1, 1);

        step("Open health sub tab in account page in browser", () -> open(subTab.getUrl()));
        step("Select random date of beginning in calendar", () -> {
            subTab.beginningDateField.shouldBe(visible);
            subTab.clickByBeginningDateField().calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomBeginningDate);
        });
        step("Select random date of end in calendar", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndDateField()
                    .calendar.component.shouldBe(visible).shouldBe(interactable);
            subTab.selectDate(randomEndDate);
        });
        step("Check that dates set correctly", () -> {
            sleep(1000);
            Dates currentBeginningDate = new Dates(subTab.beginningDateField.getValue());
            assertThat(currentBeginningDate.asStringFull()).as("Beginning Date Assertion FAILED!").isEqualTo(randomBeginningDate.asStringFull());
            Dates currentEndDate = new Dates(subTab.endDateField.getValue());
            assertThat(currentEndDate.asStringFull()).as("End Date Assertion FAILED!").isEqualTo(randomEndDate.asStringFull());
        });
    }

    //todo
    @Test(groups = "authorized")
    void resetDatesFilterTest() {
    }
}
