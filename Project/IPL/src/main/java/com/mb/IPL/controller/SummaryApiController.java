package com.mb.IPL.controller;

import com.mb.IPL.entity.ResponseStructure;
import com.mb.IPL.service.DeliveriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/besteconomical/{year}")
    public ResponseEntity<ResponseStructure<List<String>>> getBestEconomicalBowler(@PathVariable("year") int year){
        return deliveriesService.getBestEconomicalBowler(year);
    }
//    @GetMapping("/getplayermatch")
//    public ResponseEntity<ResponseStructure<List<Object>>> getDetails(@RequestParam String pname,
//                                                                 @RequestParam String place){
//        return deliveriesService.getDetails(pname, place);
//    }
    @GetMapping("/heighestrunchess")
    public ResponseEntity<ResponseStructure<String>> heighestRunChess(){
        return deliveriesService.heighestrunchess();
    }

}
