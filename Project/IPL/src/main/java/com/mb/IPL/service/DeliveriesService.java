package com.mb.IPL.service;

import com.mb.IPL.dto.DeliveriesDto;
import com.mb.IPL.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class DeliveriesService {

    @Autowired
    private DeliveriesDto deliveriesDto;

    public ResponseEntity<ResponseStructure<Map<String, Object>>> getSummary(int id) {
        ResponseStructure<Map<String, Object>> str = new ResponseStructure<>();
        Map<String, Object> result = deliveriesDto.getSummary(id);
        str.setData(result);
        str.setMessage("get data from match id");
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Map<String, Object>>> getPlayer(String name) {
        ResponseStructure<Map<String, Object>> str = new ResponseStructure<>();
        Map<String, Object> result = deliveriesDto.getPlayer(name);
        str.setMessage("Data founded by name");
        str.setData(result);
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);

    }

    public ResponseEntity<ResponseStructure<Map<String, Integer>>> getExtraRun(int year) {
        ResponseStructure<Map<String, Integer>> str = new ResponseStructure<>();
        Map<String, Integer> result = deliveriesDto.getExtraRun(year);
        str.setStatusCode(HttpStatus.OK.value());
        str.setData(result);
        str.setMessage("Data founded");
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> getBestEconomicalBowler(int year) {
        ResponseStructure<String> str = new ResponseStructure<>();
        String result = deliveriesDto.getBestEconomicalBowler(year);
        str.setMessage("Data founded");
        str.setData(result);
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
