package com.mb.IPL.dao;

import com.mb.IPL.configuration.Configartion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@Component
public class IplDao {
    private static final Logger logger = LoggerFactory.getLogger(IplDao.class);
    @Autowired
    private Configartion configartion;

    public List<String> matchesData() {
        String filePath = configartion.getMatches();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return Files.readAllLines(file.toPath());
            } else {
                logger.info("Match file not found {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error reading matches file: {}", filePath, e);
        }
        return Collections.emptyList();
    }

    public List<String> DeliveriesData() {
        String filePath = configartion.getDeliveries();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return Files.readAllLines(file.toPath());
            } else {
                logger.info("Deliveries file not found {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error reading deliveries file: {}", filePath, e);
        }
        return Collections.emptyList();
    }

}
