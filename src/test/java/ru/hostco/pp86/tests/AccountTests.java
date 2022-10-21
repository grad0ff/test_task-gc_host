package ru.hostco.pp86.tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;

public class AccountTests {

    @BeforeMethod
    void beforeEach() {
        Selenide.clearBrowserLocalStorage();
    }


}
