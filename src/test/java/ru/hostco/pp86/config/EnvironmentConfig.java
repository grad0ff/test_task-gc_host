package ru.hostco.pp86.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/environment.properties")
public interface EnvironmentConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();
}
