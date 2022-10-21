package ru.hostco.pp86.tests.ui.account;

import org.testng.annotations.Test;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.UiTestBase;

import static com.codeborne.selenide.Selenide.open;
import static ru.hostco.pp86.helpers.Tests.getAuthCookie;

public class HealthSubTabTests extends UiTestBase {

    @Test
    void setStartOfIntervalTest() {

        HealthSubTab tab = new HealthSubTab();
        getAuthCookie();
        open(tab.getUrl());
        tab.selectStartOfInterval(13, 3, 2022);
    }
}
