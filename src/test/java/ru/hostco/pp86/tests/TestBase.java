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
import org.testng.annotations.BeforeTest;
import ru.hostco.pp86.config.CredentialsConfig;
import ru.hostco.pp86.config.EnvironmentConfig;
import ru.hostco.pp86.data.Browser;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.selenide.LogType.BROWSER;
import static ru.hostco.pp86.helpers.utils.Cookies.createUiCookies;
import static ru.hostco.pp86.helpers.utils.Cookies.setUiCookies;

public class TestBase {

    protected CredentialsConfig authConfig = ConfigFactory.create(CredentialsConfig.class);
    protected EnvironmentConfig envConfig = ConfigFactory.create(EnvironmentConfig.class);
    private Logger logger = Logger.getLogger(TestBase.class.getName());


    @BeforeTest(groups = {"UI"})
    protected void beforeTests() {
        prepareTestEnvironment();
        prepareUiTests();
    }

    @BeforeTest(groups = {"UI"})
    protected void setBrowserCookies() {
        logger.info("Setting Browser Cookies");
        List<Cookie> cookies = createUiCookies(Map.of(authConfig.authCookieName(), authConfig.authCookieValue()));
        setUiCookies(cookies);
    }

    @BeforeTest(groups = {"API_UI"})
    protected void configureApiTests() {
        logger.info("Configuring Api Tests");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .addCookie(authConfig.authCookieName(), authConfig.authCookieValue())
                .build();
    }

    private void prepareTestEnvironment() {
        logger.info("Preparing Test Environment");
        Configuration.baseUrl = envConfig.getBaseUrl();
        Browser browser = Browser.CHROME;
        String browserName = browser.toString();
        String browserVersion = browser.version();
        if (System.getProperty("remote", "local").equals("remote")) {
            configureRemoteDriver(browserName, browserVersion);
        } else {
            Configuration.browser = browserName;
            Configuration.browserVersion = browserVersion;
        }
    }

    private void prepareUiTests() {
        logger.info("Preparing Selenide");
        open("");
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
                .enableLogs(BROWSER, Level.WARNING));
    }

    private void configureRemoteDriver(String browserName, String browserVersion) {
        Configuration.remote = envConfig.getRemoteUrl();
        Configuration.browserCapabilities = getRemoteCapabilities(browserName, browserVersion);
    }

    private MutableCapabilities getRemoteCapabilities(String browserName, String browserVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        return capabilities;
    }
}
