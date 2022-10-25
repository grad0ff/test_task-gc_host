package ru.hostco.pp86.data;

public enum Browsers {

    CHROME("100"),
    FIREFOX("98"),
    OPERA("84");

    private final String version;

    Browsers(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }
}
