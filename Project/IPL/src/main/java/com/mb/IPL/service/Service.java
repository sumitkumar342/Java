package com.mb.IPL.service;

import com.mb.IPL.dto.IplDto;
import com.mb.IPL.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private IplDto iplDto;

    public ResponseEntity<ResponseStructure<List<Map<String,Object>>>> homePage() {
        ResponseStructure<List<Map<String, Object>>> str = new ResponseStructure();
        List<Map<String, Object>> result = iplDto.home();
        str.setStatusCode(HttpStatus.FOUND.value());
        str.setData(result);
        str.setMessage("Founded the data");
        return new ResponseEntity<>(str, HttpStatus.OK);
    }


    public ResponseEntity<ResponseStructure<Map<String, Object>>> getMatchById(int id) {
        ResponseStructure<Map<String, Object>> str = new ResponseStructure<>();
        Map<String, Object> result = iplDto.getMatchById(id);
        str.setMessage("Match founded by id");
        str.setData(result);
        str.setStatusCode(HttpStatus.OK.value());
        return  new ResponseEntity<>(str, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Map<String, String>>>> getPlaced(String place) {
        ResponseStructure<List<Map<String, String>>> str = new ResponseStructure<>();
        List<Map<String, String>> result = iplDto.getPlaced(place);
        str.setData(result);
        str.setMessage("Data available location wise");
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Map<Integer, Integer>>> getMatchYearWise() {
        ResponseStructure<Map<Integer, Integer>> str = new ResponseStructure<>();
        Map<Integer, Integer> result = iplDto.getMatchYearWise();
        str.setStatusCode(HttpStatus.OK.value());
        str.setData(result);
        str.setMessage("Data founded year wise");
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Map<String, Map<Integer, Integer>>>> getMatchWonTeamWise() {
        ResponseStructure<Map<String, Map<Integer, Integer>>> str = new ResponseStructure<>();
        Map<String, Map<Integer, Integer>> result = iplDto.getMatchWonTeamWise();
        str.setMessage("Team wise list founded");
        str.setData(result);
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
