package ru.hostco.pp86.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import ru.hostco.pp86.config.CredentialsConfig;
import ru.hostco.pp86.config.EnvironmentConfig;
import ru.hostco.pp86.data.Browser;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.selenide.LogType.BROWSER;
import static ru.hostco.pp86.helpers.Cookies.createUiCookies;
import static ru.hostco.pp86.helpers.Cookies.setUiCookies;

public class TestBase {

    protected CredentialsConfig authConfig = ConfigFactory.create(CredentialsConfig.class);
    protected EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);

    @BeforeClass(groups = "UI")
    protected void beforeUiTests() {
        configureRemoteDriver(Browser.CHROME);
    }

    @BeforeGroups(groups = {"UI"})
    protected void prepareUiTest() {
        open("");
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
                .enableLogs(BROWSER, Level.WARNING));
    }

    @BeforeGroups(groups = {"AUTHORIZED"})
    protected void authorize() {
        List<Cookie> cookies = createUiCookies(Map.of(authConfig.authCookieName(), authConfig.authCookieValue()));
        setUiCookies(cookies);
    }


    @BeforeGroups(groups = {"API"})
    protected void configureApiTests() {
        System.out.println("configureApiTests");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .addCookie(authConfig.authCookieName(), authConfig.authCookieValue())
                .build();
    }

    private void configureRemoteDriver(Browser browser) {
        String browserName = browser.name().toLowerCase();
        String browserVersion = browser.version().toLowerCase();
        Configuration.browser = browserName;
        Configuration.baseUrl = envConfig.getBaseUrl();
        if (System.getProperty("remote", "local").equals("remote")) {
            Configuration.browserCapabilities = getCapabilities(browserName, browserVersion);
            Configuration.remote = envConfig.getRemoteUrl();
        }
    }

    private MutableCapabilities getCapabilities(String browserName, String browserVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("selenoid:options", Map.of(
//                "enableVNC", true,
//                "enableVideo", true
        ));
        return capabilities;
    }
}
