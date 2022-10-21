package ru.hostco.pp86.pages.account;

import ru.hostco.pp86.pages.Openable;
import ru.hostco.pp86.pages.account.tabs.OtherTab;

public class Account implements Openable {

    public static String URL = "/account";
    public OtherTab otherTab = new OtherTab();

    public String getUrl() {
        return URL;
    }
}
