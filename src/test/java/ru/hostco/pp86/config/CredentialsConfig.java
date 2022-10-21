package ru.hostco.pp86.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();
}
