package com.example.petto.controller;

import com.example.petto.entity.Facility;
import com.example.petto.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petto/facility")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class FacilityController {

    @Autowired
    FacilityService facilityService;

    @GetMapping("/getFacilityList")
    public ResponseEntity<List<Facility>> getFacilityList() {
        log.info("getFacilityList(): ");

        List<Facility> facilityList = facilityService.getFacilityList();

        return new ResponseEntity<List<Facility>>(facilityList, HttpStatus.OK);
    }

    @GetMapping("/getFacilityInfo/{facilityNo}")
    public ResponseEntity<Facility> getFacilityInfo(@PathVariable("facilityNo") Integer facilityNo) {
        log.info("getFacilityInfo(): " + facilityNo);

        return new ResponseEntity<Facility>(facilityService.getFacilityInfo(facilityNo), HttpStatus.OK);
    }

    @GetMapping("/getFacilityNoAndAddr/{carenm}")
    public ResponseEntity<List<String>> getFacilityNoAndAddr(@PathVariable("carenm") String carenm) {
        log.info("getFacilityNoAndAddr(): " + carenm);

        List<String> facilityNoAndAddr = facilityService.getFacilityNoAndAddr(carenm);

        return new ResponseEntity<List<String>>(facilityNoAndAddr, HttpStatus.OK);
    }
}