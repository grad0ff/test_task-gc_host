package ru.hostco.pp86.pages.account.tabs;

import ru.hostco.pp86.pages.Openable;
import ru.hostco.pp86.pages.account.tabs.subtabs.HealthSubTab;

public class OtherTab implements Openable {

    public static String URL = "/card";
    public HealthSubTab healthIndicatorsSubTab = new HealthSubTab();

    public String getUrl() {
        return URL;
    }

}
