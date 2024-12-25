package config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:${user.dir}/src/main/resources/env/application-${env}.properties"})
public interface IAppConfig extends Config {

    @Key("env")
    String env();

    @Key("browser")
    String browser();

    @Key("baseurl")
    String baseURL();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("miniWait")
    @DefaultValue("10")
    int miniWait();

    @Key("mediumWait")
    @DefaultValue("30")
    int mediumWait();

    @Key("longWait")
    @DefaultValue("60")
    int longWait();

    @Key("isBrowserstackEnabled")
    boolean isBrowserstackEnabled();

    @Key("browserstackUsername")
    String browserstackUsername();

    @Key("browserstackAccessKey")
    String browserstackAccessKey();

    @Key("browserVersion")
    String browserVersion();

    @Key("osPlatform")
    String osPlatform();

    @Key("osVersion")
    String osVersion();

    @Key("username")
    String username();

    @Key("password")
    String password();
}
