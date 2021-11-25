package com.example.petto.controller;

import com.example.petto.entity.VolunteerBoard;
import com.example.petto.service.VolunteerBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequestMapping("petto/voluntaryBoard")
@Controller
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")

public class VolunteerBoardController {
    @Autowired
    private VolunteerBoardService volunteerBoardService;

    @PostMapping("/file")
    @ResponseBody
    public ResponseEntity<VolunteerBoard> register(@RequestParam("volTitle") String volTitle, @RequestParam("closingDate") String closingDate,
                                                   @RequestParam("fileList") MultipartFile fileList,
                                                   @RequestParam("contentFileList") MultipartFile contentFileList ) throws Exception{

        log.info("requestUploadFile(): Processing");
        volunteerBoardService.write(volTitle, closingDate, fileList, contentFileList);
        return new ResponseEntity<VolunteerBoard>(HttpStatus.OK);

    }

    @GetMapping("/lists")
    public ResponseEntity<List<VolunteerBoard>> getLists () throws Exception {
        log.info("getLists(): " + volunteerBoardService.list());
        return new ResponseEntity<>(volunteerBoardService.list(), HttpStatus.OK);
    }

    @GetMapping("/{volunteerNo}")
    public ResponseEntity<VolunteerBoard> read(@PathVariable("volunteerNo") Long volunteerNo) throws Exception {
        VolunteerBoard volunteerBoard = volunteerBoardService.read(volunteerNo);
        return new ResponseEntity<VolunteerBoard>(volunteerBoard, HttpStatus.OK);
    }


    @PutMapping("/{volunteerNo}")
    public ResponseEntity<VolunteerBoard> modify(@PathVariable("volunteerNo") Long volunteerNo, VolunteerBoard volunteerBoard,
                                                 @RequestParam(value = "volTitle", required = false) String volTitle,
                                                 @RequestParam(value = "closingDate",required = false) String closingDate,
                                                 @RequestParam(value = "fileList",required = false) MultipartFile fileList,
                                                 @RequestParam(value = "contentFileList",required = false) MultipartFile contentFileList ) throws Exception {

        volunteerBoardService.modify(volunteerNo, volTitle, closingDate, fileList, contentFileList, volunteerBoard);
        return new ResponseEntity<>(volunteerBoard,HttpStatus.OK);
    }

    @DeleteMapping("/{volunteerNo}")
    public ResponseEntity<Void> remove(@PathVariable("volunteerNo") Long volunteerNo) throws Exception {
        volunteerBoardService.remove(volunteerNo);
        volunteerBoardService.deleteContainingVolunteerNo(volunteerNo);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}