package ru.hostco.pp86.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.hostco.pp86.config.CredentialsConfig;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.selenide.LogType.BROWSER;
import static ru.hostco.pp86.helpers.Cookies.createUiCookies;
import static ru.hostco.pp86.helpers.Cookies.setUiCookies;

@Test(groups = {"ui"})
public class UiTestBase {

    protected CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeClass
    public void beforeAll() {
        Configuration.baseUrl = "https://pp86.hostco.ru";
//        Configuration.remote = "http://user1:1234@selenoid.autotests.cloud:4444/wd/hub"; // todo
//        Configuration.browserCapabilities = getCapabilities();
    }

    private MutableCapabilities getCapabilities() { // todo
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        return capabilities;
    }

    @BeforeGroups(groups = "ui")
    void prepareUiTest() {
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide()
                .screenshots(true)
                .includeSelenideSteps(true)
                .enableLogs(BROWSER, Level.ALL));
        open("");
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
    }

    @BeforeGroups(groups = {"authorized"})
    void authorize() {
        List<Cookie> cookies = createUiCookies(Map.of("connect.sid", config.getAuthCookie()));
        setUiCookies(cookies);
    }
}
