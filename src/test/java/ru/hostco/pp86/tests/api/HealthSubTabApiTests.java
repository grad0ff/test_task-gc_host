package ru.hostco.pp86.tests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.hostco.pp86.data.Indicators;
import ru.hostco.pp86.models.ReadingPojoModel;
import ru.hostco.pp86.tests.ui.TestBase;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static ru.hostco.pp86.helpers.Indicators.randomValue;

public class HealthSubTabApiTests extends TestBase {

    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    @DataProvider(name = "addReadingTestDp")
    public static Object[][] getIndicators() {
        Indicators[][] objects = new Indicators[][]{
                Arrays.stream((Indicators.values())).map()

        return Arrays.stream(Indicators.values()).map((i) -> new Object[]{i.id(), i.text()}).toArray();
    }

    @Test(groups = {"API"}, dataProvider = "addReadingTestDp")
    void test(Integer id, String text, String unit) {
        System.out.println(text);
    }

    @Test(groups = {"API"}, dataProvider = "addReadingTestDp")
    void addReadingTest(Integer id, String text, String unit) {
        ReadingPojoModel readingPojo = new ReadingPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
//                .value(randomValue(indicator))
                .indicator(new ReadingPojoModel.Indicator()
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
                .statusCode(200)

//                .body("id" , Matchers.notNullValue());
                .body("id", Matchers.nullValue());
    }

    @Test(groups = {"API"})
    void updateReadingTest() {
        Indicators indicator = Indicators.random();
        ReadingPojoModel readingPojo = new ReadingPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(randomValue(indicator))
                .indicator(new ReadingPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit()));
        String path = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";

        given()
                .cookie(authConfig.authCookieName(), authConfig.authCookieValue())
                .contentType(ContentType.JSON)
                .body(gson.toJson(List.of(readingPojo)))
                .when()
                .post(path)
                .then()
                .statusCode(200);
    }

    private void prepareDb() {
        Indicators indicator = Indicators.random();
        ReadingPojoModel readingPojo = new ReadingPojoModel()
                .id(null)
                .createDate(LocalDateTime.now().format(ISO_LOCAL_DATE_TIME))
                .value(randomValue(indicator))
                .indicator(new ReadingPojoModel.Indicator()
                        .id(indicator.id())
                        .name(indicator.text())
                        .unit(indicator.unit()));
        String path = envConfig.getBaseUrl() + "/api/pp/rest/health/saveAll";

        given()
                .cookie(authConfig.authCookieName(), authConfig.authCookieValue())
                .contentType(ContentType.JSON)
                .body(gson.toJson(List.of(readingPojo)))
                .when()
                .post(path)
                .then()
                .statusCode(200);
    }
}

