package com.mb.IPL.dto;

import com.mb.IPL.dao.IplDao;
import com.mb.IPL.exception.IdNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DeliveriesDto {
    private static final Logger logger = LoggerFactory.getLogger(DeliveriesDto.class);

    @Autowired
    private IplDao iplDao;

    public Map<String, String> getSummary(int id) {
        List<String> deliveriesData = iplDao.getDeliveriesData();
        Map<String, String> summary = new HashMap<>();
        boolean getMatch = false;
        int totalRuns = 0;
        int totalWickets = 0;
        for (String str : deliveriesData) {
            String[] deliveries = str.split(",");
            try {
                int matchId = Integer.parseInt(deliveries[0]);
                if (matchId == id) {
                    getMatch = true;
                    int runs = Integer.parseInt(deliveries[17].trim());
                    totalRuns += runs;
                    String wicket = deliveries.length > 18 ? deliveries[18].trim() : "";
                    if (!wicket.isEmpty()) {
                        totalWickets++;
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        if(!getMatch){
            throw new IdNotFoundException("Match id: " + id);
        }
        summary.put("Match ID", String.valueOf(id));
        summary.put("Total Runs", String.valueOf(totalRuns));
        summary.put("Total Wickets", String.valueOf(totalWickets));

        return summary;
    }


}
