package com.mb.IPL.controller;

import com.mb.IPL.entity.ResponseStructure;
import com.mb.IPL.service.DeliveriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/match-summary")
public class SummaryApiController {
    private static final Logger logger = LoggerFactory.getLogger(SummaryApiController.class);

    @Autowired
    private DeliveriesService deliveriesService;

    @GetMapping("teams/{id}")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getSummary(@PathVariable("id") int id){
        logger.info("input match id: {}", id);
        return deliveriesService.getSummary(id);
    }

    @GetMapping("/player/{name}")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getPlayer(@PathVariable("name") String name){
        return deliveriesService.getPlayer(name);
    }

    @GetMapping("/extra-run/{year}")
    public ResponseEntity<ResponseStructure<Map<String, Integer>>> getExtrarun(@PathVariable("year") int year){
        return deliveriesService.getExtraRun(year);
    }

}
