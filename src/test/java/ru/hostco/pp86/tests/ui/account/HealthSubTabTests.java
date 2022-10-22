package ru.hostco.pp86.tests.ui.account;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.UiTestBase;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = {"ui"})
public class HealthSubTabTests extends UiTestBase {

    @Test(groups = "authorized")
    void setStartOfIntervalTest() {
        HealthSubTab tab = new HealthSubTab();
        open(tab.getUrl());
        tab.clickByBeginningDateField().selectDate(13, 3, 2022);
        Selenide.sleep(2000);
    }
}
