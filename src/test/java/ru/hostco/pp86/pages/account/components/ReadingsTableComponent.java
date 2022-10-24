package ru.hostco.pp86.pages.account.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ReadingsTableComponent {

    public SelenideElement component = $(".account-content .table");
    public SelenideElement firstReading = $(".account-content .table .row:not(.th)");
}
