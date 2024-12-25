package config;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigReader {

    private static final IAppConfig AppConfig;

    static {
        String environment = System.getProperty("env");
        AppConfig = ConfigFactory.create(IAppConfig.class, System.getProperties());
        System.setProperty("env", environment);
    }

    public ConfigReader() {}

    public static String getEnvironment(){
        return AppConfig.env();
    }

    public static String getBrowser(){
        return AppConfig.browser();
    }

    public static String getBaseUrl(){
        String baseUrl = AppConfig.baseURL();
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Base URL is not defined in properties file.");
        }
        return baseUrl;
    }

    public static boolean isHeadless(){
        return AppConfig.headless();
    }

    public static int getMiniWaitTimeOut(){
        return AppConfig.miniWait();
    }

    public static int getMediumWaitTimeOut(){
        return AppConfig.mediumWait();
    }

    public static int getLongWaitTimeOut(){
        return AppConfig.longWait();
    }

    public static boolean isBrowserstackEnabled(){
        return AppConfig.isBrowserstackEnabled();
    }

    public static String getBrowserstackUsername(){
        return AppConfig.browserstackUsername();
    }

    public static String getBrowserstackAccessKey(){
        return AppConfig.browserstackAccessKey();
    }

    public static String getBrowserVersion(){
        return AppConfig.browserVersion();
    }

    public static String getBrowserstackOSPlatform(){
        return AppConfig.osPlatform();
    }

    public static String getBrowserstackOSVersion(){
        return AppConfig.osVersion();
    }

    public static String getUsername(){
        return AppConfig.username();
    }

    public static String getPassword(){
        return AppConfig.password();
    }
}
