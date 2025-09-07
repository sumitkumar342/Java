package com.mb.IPL.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.config")
public class Configartion {
    private static final Logger logger = LoggerFactory.getLogger(Configartion.class);

    private String matches;
    private String deliveries;

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        logger.info("\u001B[31mMatch file link: {}\u001B[0m", matches);
        this.matches = matches;
    }

    public String getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(String deliveries) {
        logger.info("Deliveries file link: {}", deliveries);
        this.deliveries = deliveries;
    }
}
