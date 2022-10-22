package ru.hostco.pp86.tests.ui;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.hostco.pp86.config.CredentialsConfig;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static ru.hostco.pp86.helpers.Cookies.createUiCookies;
import static ru.hostco.pp86.helpers.Cookies.setUiCookies;

@Test(groups = {"ui"})
public class UiTestBase {

    protected CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeClass
    public void beforeAll() {
        Configuration.baseUrl = "https://pp86.hostco.ru";
    }

    @BeforeGroups(groups = {"authorized"})
    void authorize() {
        List<Cookie> cookies = createUiCookies(Map.of("connect.sid", config.getAuthCookie()));
        open("");
        setUiCookies(cookies);

    }
//
//    @AfterMethod(groups = {"ui"})
//    void clearBrowserLocalStorage() {
//        System.out.println("clearBrowserLocalStorage");
//        Selenide.clearBrowserLocalStorage();
//    }
//
//    @AfterSuite(groups = {"ui"})
//    void clearBrowserCookies() {
//        System.out.println("clearBrowserCookies");
//        Selenide.clearBrowserCookies();
//    }
//
//    @AfterSuite
//    void afterSuite() {
//        Selenide.sleep(500);
//    }
}
