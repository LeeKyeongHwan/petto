package com.example.petto.controller;

import com.example.petto.entity.Animals;
import com.example.petto.service.AnimalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/petto/animals")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class AnimalsController {

    @Autowired
    private AnimalsService animalsService;

    @GetMapping("/lists")
    public ResponseEntity<List<Animals>> getLists () throws Exception {
        log.info("getLists(): " + animalsService.list());
        return new ResponseEntity<>(animalsService.list(), HttpStatus.OK);
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
        log.info("memberNo == > "+ memberNo);
        log.info("getMyLikedAnimals(): " + animalsService.myLikedAnimals(memberNo));
        return new ResponseEntity<>(animalsService.myLikedAnimals(memberNo), HttpStatus.OK);
    }
}
