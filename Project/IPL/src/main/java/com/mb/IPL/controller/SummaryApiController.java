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

import java.util.Map;

@RestController
@RequestMapping("/match-summary")
public class SummaryApiController {
    private static final Logger logger = LoggerFactory.getLogger(SummaryApiController.class);

    @Autowired
    private DeliveriesService deliveriesService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Map<String, String>>>  getSummary(@PathVariable("id") int id){
        logger.info("input match id: {}", id);
        return deliveriesService.getSummary(id);
    }
}
