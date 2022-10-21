package ru.hostco.pp86.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    void beforeAll() {
        Configuration.baseUrl = "https://pp86.hostco.ru/";
        Configuration.holdBrowserOpen = true;

    }
}
