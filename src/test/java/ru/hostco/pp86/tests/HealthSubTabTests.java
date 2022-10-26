package ru.hostco.pp86.tests;

import com.codeborne.selenide.Condition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.commons.math3.util.Precision;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.hostco.pp86.data.Indicator;
import ru.hostco.pp86.helpers.Date;
import ru.hostco.pp86.helpers.DateRandomizer;
import ru.hostco.pp86.helpers.Time;
import ru.hostco.pp86.helpers.TimeRandomizer;
import ru.hostco.pp86.models.EntryPojoModel;
import ru.hostco.pp86.pages.account.components.IndicatorFormComponent;
import ru.hostco.pp86.pages.account.components.IndicatorsTableComponent;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.hostco.pp86.helpers.Indicators.randomValueFor;

@Test(groups = {"OTHER_HEALTH"}, suiteName = "Health sub tab tests")
public class HealthSubTabTests extends TestBase {

    HealthSubTab subTab = new HealthSubTab();
    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();


    @Description("Checks that record adding is possible")
    @Story("User add new record into indicators table")
    @Test(groups = {"UI", "AUTHORIZED"})
    void addTemperatureIndicatorTest() {

        IndicatorFormComponent form = subTab.indicatorForm;
        IndicatorsTableComponent indicatorsTable = new IndicatorsTableComponent();
        Date date = Date.today();
        Time time = TimeRandomizer.randomTime(0, 2);
        double temperature = Precision.round(36.3 + new Random().nextDouble(), 1);

        step("Open health sub tab in browser", () -> open(subTab.getUrl()));
        step("Click by indicator adding button", () -> subTab.clickByAddIndicatorButton());
        step("Select actual time and date in the inner calendar", () -> {
            form.clickByCalendarLink().calendar.component.shouldBe(visible);
            form.selectTime(time);
            form.selectDate(date);
        });
        step("Set temperature", () -> {
            sleep(500);
            form.setTemperature(temperature);
        });
        step("click by submit", form::clickBySubmit);
        step("Check that recordTable contains data", () -> {
            indicatorsTable.firstRecord.shouldHave(
                    text(date + " " + time),
                    text(Indicator.TEMPERATURE.text()),
                    text(String.valueOf(temperature))
            );
        });
    }

    @Description("Checks that date filtering by first day of interval is possible")
    @Story("User filters indicators table by first date of interval")
    @Test(groups = {"UI", "AUTHORIZED"})
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

    @Description("Checks that date filtering by last day of interval is possible")
    @Story("User filters indicators table by last date of interval")
    @Test(groups = {"UI", "AUTHORIZED"})
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

    @Description("Checks that date filtering by interval is possible")
    @Story("User filters indicators table by first day and last day of interval")
    @Test(groups = {"UI", "AUTHORIZED"})
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

    @Ignore
    @Description("Checks that reset date filters is possible")
    @Story("User resets date filtering to default values")
    @Test(groups = "AUTHORIZED")
    void resetDatesFilterTest() {
    }

    @Test(groups = {"UI", "API", "AUTHORIZED"})
    void addRandomReadingTest() {
        Indicator indicator = Indicator.random();
        IndicatorsTableComponent indicatorsTable = new IndicatorsTableComponent();
        LocalDateTime date = LocalDateTime.now();
        String dateAsString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String indicatorValue = randomValueFor(indicator);
        AtomicReference<EntryPojoModel> atomicReference = new AtomicReference<>();
        String path = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";

        atomicReference.set(new EntryPojoModel()
                .id(null)
                .createDate(dateAsString)
                .value(indicatorValue)
                .indicator(new EntryPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit())));

        step("Add record into indicator table with API", () -> {
            given()
                    .body(gson.toJson(List.of(atomicReference.get())))
                    .when()
                    .post(path)
                    .then()
                    .statusCode(200);
        });
        step("Open health sub tab in browser", () -> open(subTab.getUrl()));
        step("Check that indicators table contains data", () -> {
            indicatorsTable.firstRecord.shouldHave(
                    text(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))),
                    text(indicator.text()),
                    text(indicatorValue)
            );
        });
    }

    @Test(groups = {"UI", "API", "AUTHORIZED"}, dependsOnMethods = "dataAdding")
    void navigatePanelIsVisibleTest() {
        step("Open health sub tab", () -> open(subTab.getUrl()));
        step("Check that footer of indicators table is visible", () -> {
            subTab.indicatorsTableFooter.shouldBe(Condition.visible).shouldHave();
        });
    }

    @DataProvider(name = "addReadingTestDataProvider")
    public static Iterator<Object[]> getIndicators() {
        ArrayList<Object[]> objects = new ArrayList<>();
        Indicator[] indicators = Indicator.values();
        for (Indicator indicator : indicators) {
            objects.add(new Object[]{indicator.id(), indicator.text(), indicator.unit(), randomValueFor(indicator)});
        }
        return objects.iterator();
    }

    /*
     * Imitate data adding into DB
     */
    @Test(dataProvider = "addReadingTestDataProvider")
    void dataAdding(Integer id, String text, String unit, String value) {
        EntryPojoModel readingPojo = new EntryPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(value)
                .indicator(new EntryPojoModel.Indicator()
                        .id(id)
                        .name(text)
                        .unit(unit));
        String path = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        given()
                .cookie(authConfig.authCookieName(), authConfig.authCookieValue())
                .contentType(ContentType.JSON)
                .body(gson.toJson(List.of(readingPojo)))
                .log().all()
                .when()
                .post(path)
                .then()
                .statusCode(200);
    }

    @Ignore("Response as JSON is not exist")
    @Test(groups = {"API"})
    void updateReadingTest() {
        EntryPojoModel readingPojo = prepareDB();
        String path = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";

        step("Add record into indicator table with API", () -> {
            given()
                    .cookie(authConfig.authCookieName(), authConfig.authCookieValue())
                    .contentType(ContentType.JSON)
                    .body(gson.toJson(List.of(readingPojo)))
                    .when()
                    .post(path)
                    .then()
                    .statusCode(200);
        });
    }

    /*
     * Imitate data adding into DB
     */
    private EntryPojoModel prepareDB() {
        Indicator indicator = Indicator.random();
        EntryPojoModel entryPojo = new EntryPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(randomValueFor(indicator))
                .indicator(new EntryPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit()));
        return entryPojo;
    }
}
