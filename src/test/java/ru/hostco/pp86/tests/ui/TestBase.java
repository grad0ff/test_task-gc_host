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
import ru.hostco.pp86.config.EnvironmentConfig;
import ru.hostco.pp86.data.Browsers;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.selenide.LogType.BROWSER;
import static ru.hostco.pp86.helpers.Cookies.createUiCookies;
import static ru.hostco.pp86.helpers.Cookies.setUiCookies;

@Test(groups = {"ui"})
public class TestBase {

    protected CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
    protected EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

    @BeforeClass
    public void beforeAll() {
        configureDriver(Browsers.FIREFOX);
    }

    private void configureDriver(Browsers browser) {
        String browserName = browser.name().toLowerCase();
        String browserVersion = browser.version().toLowerCase();
        Configuration.browser = browserName;
        Configuration.browserCapabilities = getCapabilities(browserName, browserVersion);
        Configuration.baseUrl = environmentConfig.getBaseUrl();
        Configuration.remote = environmentConfig.getRemoteUrl();
    }

    @BeforeGroups(groups = "ui")
    void prepareUiTest() {
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
                .enableLogs(BROWSER, Level.WARNING));
        open("");
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
    }

    @BeforeGroups(groups = {"authorized"})
    void authorize() {
        List<Cookie> cookies = createUiCookies(Map.of("connect.sid", credentialsConfig.getAuthCookie()));
        setUiCookies(cookies);
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
