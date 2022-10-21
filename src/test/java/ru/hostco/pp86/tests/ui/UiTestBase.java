package ru.hostco.pp86.tests.ui;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;

public class UiTestBase {

    @BeforeSuite
    public void beforeAll() {
        Configuration.baseUrl = "https://pp86.hostco.ru";
    }

    @BeforeGroups(groups = {"authorized"})
    void authorize() {
        System.out.println("authorize");

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
