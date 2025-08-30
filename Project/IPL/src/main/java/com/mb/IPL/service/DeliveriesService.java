package com.mb.IPL.service;

import com.mb.IPL.dto.DeliveriesDto;
import com.mb.IPL.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeliveriesService {

    @Autowired
    private DeliveriesDto deliveriesDto;

    public ResponseEntity<ResponseStructure<Map<String, String>>> getSummary(int id) {
        ResponseStructure<Map<String, String>> str = new ResponseStructure<>();
        Map<String, String> result = deliveriesDto.getSummary(id);
        str.setData(result);
        str.setMessage("get data from match id");
        str.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
