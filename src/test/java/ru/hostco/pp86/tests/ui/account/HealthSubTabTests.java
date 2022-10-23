package ru.hostco.pp86.tests.ui.account;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import ru.hostco.pp86.helpers.DateGradomizer;
import ru.hostco.pp86.helpers.Dates;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;
import ru.hostco.pp86.tests.ui.UiTestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = {"ui"})
public class HealthSubTabTests extends UiTestBase {

    @Test(groups = "authorized")
    void setStartOfIntervalTest() {
        HealthSubTab tab = new HealthSubTab();
        Dates randomDate = DateGradomizer.randomDate(1, 1);
        open(tab.getUrl());
        tab.clickByBeginningDateField().selectDate(randomDate);
        sleep(500);
        Dates currentBeginningDate = new Dates(tab.beginningDateField.getValue());
        assertThat(currentBeginningDate.asStringFull()).isEqualTo(randomDate.asStringFull());
    }
}
