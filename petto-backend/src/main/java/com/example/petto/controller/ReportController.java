package com.example.petto.controller;

import com.example.petto.entity.Animals;
import com.example.petto.entity.Report;
import com.example.petto.service.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petto/report")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/sendAnimalPics")
    public ResponseEntity<Boolean> sendAnimalPics(@RequestParam("animalPics") List<MultipartFile> animalPicsList,
                                               @RequestParam("category") String category,
                                               @RequestParam("id") String id,
                                               @RequestParam("dateId") String dateId) {

        log.info("sendAnimalPics(): " + animalPicsList + ", " + category + ", " + id + ", " + dateId);

        boolean isPicsSaved = false;

        try {
            for(int i=0; i<animalPicsList.toArray().length; i++) {
                MultipartFile multipartFile = animalPicsList.get(i);

                FileOutputStream writer = new FileOutputStream("./image/animalPics/" + dateId + "-" + id + "-" + category + "-" + i + ".jpg");

                writer.write(multipartFile.getBytes());
                writer.close();

                isPicsSaved = true;
            }

        } catch (Exception e) {
            log.info("upload failed.");
        }

        return new ResponseEntity<Boolean>(isPicsSaved, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Void> registerReport(@Validated @RequestBody Report report) {
        log.info("registerReport(): " + report);

        reportService.register(report);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/myBoardLists/{id}")
    public ResponseEntity<List<Report>> getMyBoardLists(@PathVariable("id") String id) throws Exception {
        log.info("id == > "+ id);
        log.info("getMyBoardLists(): " + reportService.myBoardList(id));
        return new ResponseEntity<>(reportService.myBoardList(id), HttpStatus.OK);
    }

}
