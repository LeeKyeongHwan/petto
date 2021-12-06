package com.example.petto.controller;

import com.example.petto.entity.Animals;
import com.example.petto.service.AnimalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/petto/animals")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class AnimalsController {

    @Autowired
    private AnimalsService animalsService;

    @GetMapping("/lists")
    public ResponseEntity<List<Animals>> getLists() throws Exception {
        log.info("getLists(): ");
        return new ResponseEntity<List<Animals>>(animalsService.list(), HttpStatus.OK);
    }

    @GetMapping("/lists/{cityName}")
    public ResponseEntity<List<Animals>> getListsByLocation(@PathVariable("cityName") String cityName) {
        log.info("getListsByLocation(): " + cityName);
        return new ResponseEntity<List<Animals>>(animalsService.listByLocation(cityName), HttpStatus.OK);
    }

    @GetMapping("/getAnimalsInfo/{id}")
    public ResponseEntity<Animals> getAnimalsInfo(@PathVariable("id") String id) {
        log.info("getAnimalsInfo(): " + id);

        return new ResponseEntity<Animals>(animalsService.getAnimalsInfo(id), HttpStatus.OK);
    }

    @GetMapping("/orderLists")
    public ResponseEntity<List<Animals>> getOlderLists() throws Exception {
        log.info("getLists(): " + animalsService.oldList());
        return new ResponseEntity<>(animalsService.oldList(), HttpStatus.OK);
    }

    @GetMapping("/myLikedAnimals/{memberNo}")
    public ResponseEntity<List<Animals>> getMyLikedAnimals(@PathVariable("memberNo") Long memberNo) throws Exception {
        log.info("memberNo  "+ memberNo);
        log.info("getMyLikedAnimals(): " + animalsService.myLikedAnimals(memberNo));
        return new ResponseEntity<>(animalsService.myLikedAnimals(memberNo), HttpStatus.OK);
    }

    @GetMapping("/numOfAnimals")
    public ResponseEntity<Long> getNumOfAnimals() {
        log.info("getNumOfAnimals: ");

        Long numOfAnimals = animalsService.getNumOfAnimals();

        return new ResponseEntity<Long>(numOfAnimals, HttpStatus.OK);
    }

    @PostMapping("/filterList")
    public ResponseEntity<List<Animals>> getFilterList(@RequestParam("selectedPlace") String[] selectedPlace,
                                                       @RequestParam("selectedKinds") String[] selectedKinds) {

        log.info("getFilterList: " + selectedPlace + ", " + selectedKinds);

        List<Animals> filteredAnimals = animalsService.filterAnimals(selectedPlace, selectedKinds);

        return new ResponseEntity<List<Animals>>(filteredAnimals, HttpStatus.OK);
    }

    @PostMapping("/plusSharedCnt/{notice_no}")
    public ResponseEntity<Void> plusSharedCnt(@PathVariable("notice_no") String notice_no) {
        log.info("plusSharedCnt(): " + notice_no);

        animalsService.plusSharedCnt(notice_no);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
