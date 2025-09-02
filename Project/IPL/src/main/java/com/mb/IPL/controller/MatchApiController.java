package com.mb.IPL.controller;

import com.mb.IPL.entity.ResponseStructure;
import com.mb.IPL.service.Service;
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
import java.util.Set;

@RestController
@RequestMapping("/ipl")
public class MatchApiController {
    private static final Logger logger = LoggerFactory.getLogger(MatchApiController.class);

    @Autowired
    private Service service;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Map<String, Object>>>> home(){
        return service.homePage();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getMatchById(
            @PathVariable("id") int id) {
        return service.getMatchById(id);
    }

    @GetMapping("/place/{place}")
    public ResponseEntity<ResponseStructure<List<Map<String, String>>>> getPlaced(@PathVariable("place") String place){
        return service.getPlaced(place);
    }

    @GetMapping("/teams")
    public ResponseEntity<ResponseStructure<Map<Integer, Set<String>>>> getTeam(){
        return service.getTeam();
    }

    @GetMapping("/totalmatch")
    public ResponseEntity<ResponseStructure<Map<Integer, Integer>>> getMatchYearWise(){
        return service.getMatchYearWise();
    }

    @GetMapping("/matchwonteamwise")
    public ResponseEntity<ResponseStructure<Map<String, Map<Integer, Integer>>>> getMatchWonByTeam(){
        return service.getMatchWonTeamWise();
    }

}