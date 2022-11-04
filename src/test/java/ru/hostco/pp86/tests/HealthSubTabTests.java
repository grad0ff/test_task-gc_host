package ru.hostco.pp86.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.hostco.pp86.data.Indicator;
import ru.hostco.pp86.helpers.datetime.Date;
import ru.hostco.pp86.helpers.datetime.DateRandomizer;
import ru.hostco.pp86.helpers.datetime.Time;
import ru.hostco.pp86.models.EntriesPojoModel;
import ru.hostco.pp86.pages.account.components.EntriesTableComponent;
import ru.hostco.pp86.pages.account.components.IndicatorsAddingFormComponent;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.hostco.pp86.helpers.utils.Indicators.randomValueFor;

@Test(suiteName = "Health sub tab tests")
public class HealthSubTabTests extends TestBase {

    // TODO: 28.10.2022 Добавить аттачи

    HealthSubTab healthSubTab = new HealthSubTab();
    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    Faker faker = new Faker();

    @Description("Checks that record adding is possible")
    @Story("User add new record into indicators table")
    @Test(groups = {"UI"})
    void addTemperatureIndicatorTest() {
        IndicatorsAddingFormComponent form = healthSubTab.indicatorForm;
        EntriesTableComponent indicatorsTable = new EntriesTableComponent();
        Date date = Date.today();
        Time time = Time.now();
        double temperature = faker.number().randomDouble(1, 36, 40);

        step("Open health sub tab in browser", () -> open(healthSubTab.getUrl()));
        step("Click by indicator adding button", () -> healthSubTab.clickByAddIndicatorButton());
        step("Select actual time and date in the inner calendar", () -> {
            form.component.shouldBe(visible);
            form.clickByCalendarLink().calendar.component.shouldBe(visible);
            form.selectTime(time);
            form.selectDate(date);
        });
        step("Set temperature", () -> {
            sleep(500);
            form.setTemperature(temperature);
        });
        step("Click by submit", form::clickBySubmit);
        step("Check that entries table contains data", () -> {
            indicatorsTable.firstRecord.scrollIntoView(false).shouldHave(
                    text(date + " " + time),
                    text(Indicator.TEMPERATURE.text()),
                    text(String.valueOf(temperature))
            );
        });

        cleanDb();
    }

    @Description("Checks that date filtering by first day of interval is possible")
    @Story("User filters entries table by first date of interval")
    @Test(groups = {"UI"})
    void setBeginningDateTest() {
        Date randomDate = DateRandomizer.randomDateOfPast(2022);

        step("Open health sub tab", () -> open(healthSubTab.getUrl()));
        step("Select random date of beginning", () -> {
            healthSubTab.beginningDateField.shouldBe(visible);
            healthSubTab.clickByBeginningDateField().calendar.component.shouldBe(visible);
            healthSubTab.selectDate(randomDate);
        });
        step("Check that date sets correctly", () -> {
            sleep(1000);
            String beginningDate = healthSubTab.beginningDateField.getValue();
            assertThat(beginningDate)
                    .as("Beginning date assertion FAILED")
                    .isEqualTo(randomDate.toString());
        });
    }

    @Description("Checks that date filtering by last day of interval is possible")
    @Story("User filters entries table by last date of interval")
    @Test(groups = {"UI"})
    void setEndingDateTest() {
        HealthSubTab subTab = new HealthSubTab();
        Date randomDate = DateRandomizer.randomDateOfFuture(2023);

        step("Open health sub tab", () -> open(subTab.getUrl()));
        step("Select random date of end", () -> {
            subTab.endDateField.shouldBe(visible);
            subTab.clickByEndingDateField().calendar.component.shouldBe(visible);
            subTab.selectDate(randomDate);
        });
        step("Check that date sets correctly", () -> {
            sleep(1000);
            String givenDate = subTab.endDateField.getValue();
            assertThat(givenDate)
                    .as("Ending date assertion FAILED")
                    .isEqualTo(randomDate.toString());
        });
    }

    @Description("Checks that date filtering by interval is possible")
    @Story("User filters entries table by first day and last day of interval")
    @Test(groups = {"UI"})
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
        step("Check that dates sets correctly", () -> {
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

    @Test(groups = {"API_UI"})
    void addRandomIndicatorTest() {
        Indicator indicator = Indicator.random();
        EntriesTableComponent entriesTable = new EntriesTableComponent();
        LocalDateTime date = LocalDateTime.now();
        String dateAsString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String indicatorValue = randomValueFor(indicator);
        AtomicReference<EntriesPojoModel> atomicReference = new AtomicReference<>();
        String reqPath = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        atomicReference.set(new EntriesPojoModel()
                .id(null)
                .createDate(dateAsString)
                .value(indicatorValue)
                .indicator(new EntriesPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit())));

        step("Add record into entries table with API", () -> {
            given()
                    .body(gson.toJson(List.of(atomicReference.get())))
                    .when()
                    .post(reqPath)
                    .then()
                    .statusCode(200);
        });
        step("Open health sub tab in browser", () -> open(healthSubTab.getUrl()));
        step("Check that entries table contains data", () -> {
            String value = indicatorValue;
            if (indicator.equals(Indicator.PRESSURE)) {
                value = getPressureValue(indicatorValue);
            }
            entriesTable.firstRecord.scrollIntoView(false).shouldHave(
                    text(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))),
                    text(indicator.text()),
                    text(value)
            );
        });
        cleanDb();
    }

    @Test(groups = {"API_UI"}, dependsOnMethods = "dataAdding")
    void navigatePanelIsVisibleTest() {
        step("Open health sub tab", () -> open(healthSubTab.getUrl()));
        step("Check that footer of entries table is visible", () -> {
            healthSubTab.indicatorsTableFooter.shouldBe(visible);
            cleanDb();
        });
    }

    @DataProvider(name = "addIndicatorsTestDataProvider")
    public static Iterator<Object[]> getIndicators() {
        ArrayList<Object[]> objects = new ArrayList<>();
        Indicator[] indicators = Indicator.values();
        for (Indicator indicator : indicators) {
            objects.add(new Object[]{indicator.id(), indicator.text(), indicator.unit(), randomValueFor(indicator)});
            objects.add(new Object[]{indicator.id(), indicator.text(), indicator.unit(), randomValueFor(indicator)});
        }
        return objects.iterator();
    }

    /*
     * Imitate data adding into DB, adds records for each indicator type
     */
    @Test(groups = {"API_UI"}, dataProvider = "addIndicatorsTestDataProvider")
    void dataAdding(Integer id, String text, String unit, String value) {
        String reqPath = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        EntriesPojoModel pojo = new EntriesPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(value)
                .indicator(new EntriesPojoModel.Indicator()
                        .id(id)
                        .name(text)
                        .unit(unit));
        given()
                .body(gson.toJson(List.of(pojo)))
                .when()
                .post(reqPath)
                .then()
                .statusCode(200);
    }

    @Ignore("Request's response as JSON format is not exist for data adding. " +
            "It's need for indicator identification in DB and able manipulate it")
    @Test(groups = {"API"})
    void updateRecordTest() {
        EntriesPojoModel pojo = prepareDB();
        String reqPath = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        // body of request
        cleanDb();
    }

    @Ignore("Request's response as JSON format is not exist for data adding. " +
            "It's need for indicator identification in DB and able manipulate it")
    @Test(groups = {"API"})
    void deleteRecordTest() {
        EntriesPojoModel pojo = prepareDB();
        String reqPath = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        // body of request
        cleanDb();
    }

    private String getPressureValue(String indicatorValue) {
        String value;
        String[] split = StringUtils.strip(indicatorValue, "[\"]").split("\", \"");
        value = String.format("%s/ %s", split[0], split[1]);
        return value;
    }

    /*
     * Imitate data adding into DB.
     */
    private EntriesPojoModel prepareDB() {
        String reqPath = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";
        Indicator indicator = Indicator.random();
        EntriesPojoModel pojo = new EntriesPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(randomValueFor(indicator))
                .indicator(new EntriesPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit()));
        given()
                .body(gson.toJson(List.of(pojo)))
                .when()
                .post(reqPath)
                .then()
                .statusCode(200);
        return pojo;
    }

    /* imitates DB cleaning */
    void cleanDb() {
        ElementsCollection indicatorsList = $$(".table .td");
        SelenideElement delButton = $$(".table .td .button").findBy(text("Удалить"));
        SelenideElement delSubmitButton = $(".ui-dialog-footer .button");
        SelenideElement noDataElement = $(".account-content .no-data");
        SelenideElement controlBlock = $(".control-health-block");

        open(healthSubTab.getUrl());
        while (true) {
            controlBlock.shouldBe(visible);
            if (noDataElement.is(visible)) break;
            if (indicatorsList.first().is(visible)) {
                indicatorsList.forEach((element) -> {
                    delButton.shouldBe(visible).click();
                    switchTo().activeElement();
                    delSubmitButton.shouldBe(visible).click();
                    sleep(1000);
                });
            }
            refresh();
        }
    }

    @Test(groups = "API")
    void getAuthCookies() {
//        RestAssuredConfig config1 = config().redirect(new RedirectConfig().followRedirects(false));

        Map<String, String> cookies0 = given()
//                .config(config1)
                .relaxedHTTPSValidation()
                .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .urlEncodingEnabled(true)
                .cookie("_ym_d", "1666958446")
                .queryParam("redirectURI","https://pp86.hostco.ru:/callback")
//                .log().all()
                .get("https://pp86.hostco.ru/api/pp/patient/auth/esia")
                .then()
                .log().status()
//                .log().cookies()
                .extract().cookies();

        Response response1 = given()
                .relaxedHTTPSValidation()
                .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .cookie("_ym_d", "1666958446")
                .cookie(cookies0.get("AUTH_SESSION_ID"))
                .cookie(cookies0.get("AUTH_SESSION_ID_LEGACY"))
                .log().all()
                .queryParam("service", "https://pp86.hostco.ru/api/pp/auth/verify?redirectURI=https%3A%2F%2Fcas-test.hostco.ru%2Frealms%2Fesia%2Fprotocol%2Fcas%2Flogin%3Fservice%3Dhttps%253A%252F%252Fpp86.hostco.ru%252Fapi%252Fpatient%252Fauth%252Fesia%252Fverify%253FredirectURI%253Dhttps%25253A%25252F%25252Fpp86.hostco.ru%25253A%25252Fcallback")
                .get("https://cas-test.hostco.ru/realms/esia/protocol/cas/login")
                .then()
                .log().all()
                .extract().response();
        System.out.println();

    }
}
