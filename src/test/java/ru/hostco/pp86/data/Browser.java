package ru.hostco.pp86.data;

public enum Browser {

    CHROME("100"),
    FIREFOX("98"),
    OPERA("84");

    private final String version;

    Browser(String version) {
        this.version = version;
    }

    public String version() {
        return version;
    }
}
