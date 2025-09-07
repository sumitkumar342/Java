package com.mb.IPL.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationStandAlone {

    private String matches;
    private String deliveries;

    public ConfigurationStandAlone() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        Config configartion = new Config();
        try {
                InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");

            if (input == null) {
                throw new RuntimeException("config.properties not found in resources folder");
            }

            properties.load(input);
            configartion.setMatches(properties.getProperty("app.config.matches"));
            configartion.setDeliveries(properties.getProperty("app.config.deliveries"));
//            this.matches = properties.getProperty("app.config.matches");
//            this.deliveries = properties.getProperty("app.config.deliveries");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public String getMatches() {
        return matches;
    }

    public String getDeliveries() {
        return deliveries;
    }
}
