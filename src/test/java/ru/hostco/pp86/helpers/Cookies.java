package ru.hostco.pp86.helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cookies {

    /*
     * returns cookies list for usage with browser
     */
    public static List<Cookie> createUiCookies(Map<String, String> cookiesMap) {
        List<Cookie> cookies = new ArrayList<>();
        cookiesMap.forEach((k, v) -> cookies.add(new Cookie(k, v)));
        return cookies;
    }

    /*
     * puts all cookies in list into browser cookie storage
     */
    public static void setUiCookies(List<Cookie> cookies) {
        cookies.forEach(WebDriverRunner.getWebDriver().manage()::addCookie);
    }
}
