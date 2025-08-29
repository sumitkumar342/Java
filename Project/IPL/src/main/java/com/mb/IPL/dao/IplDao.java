package com.mb.IPL.dao;

import com.mb.IPL.configuration.Configartion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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
            boolean fileExists = file.exists();
            if(fileExists){
                List<String> existingLine = new ArrayList<>();
                existingLine = Files.readAllLines(file.toPath());
                return existingLine;
            }
            logger.info("File not found {}", filePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
