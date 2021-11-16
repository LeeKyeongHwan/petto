package com.example.petto.controller;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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
            String path = "./image/animalPics/" + id + "-" + dateId;
            File folder = new File(path);
            folder.mkdir();

            for(int i=0; i<animalPicsList.toArray().length; i++) {
                MultipartFile multipartFile = animalPicsList.get(i);

                FileOutputStream writer = new FileOutputStream("./image/animalPics/" + id + "-" + dateId + "/" + category + "-" + i + ".jpg");

                writer.write(multipartFile.getBytes());
                writer.close();

                isPicsSaved = true;
            }

        } catch (Exception e) {
            log.info("upload failed.");
        }

        return new ResponseEntity<Boolean>(isPicsSaved, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerReport(@Validated @RequestBody Report report) {
        log.info("registerReport(): " + report);

        reportService.register(report);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/reportList")
    public ResponseEntity<List<Report>> getReportList() {
        log.info("registerReport(): ");

        return new ResponseEntity<List<Report>>(reportService.getReportList(), HttpStatus.OK);
    }

    @GetMapping("/reportRead/{reportNo}")
    public ResponseEntity<Report> getreportRead(@PathVariable("reportNo") Integer reportNo) {
        log.info("getreportRead(): " + reportNo);

        return new ResponseEntity<Report>(reportService.read(reportNo), HttpStatus.OK);
    }

    @PutMapping("/makeReportExpired/{reportNo}")
    public ResponseEntity<Void> makeReportExpired(@PathVariable("reportNo") Integer reportNo) {
        log.info("makeReportExpired(): " + reportNo);

        reportService.makeReportExpired(reportNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/deleteReport")
    public ResponseEntity<Void> deleteReport(@RequestParam("reportNo") Integer reportNo,
                                             @RequestParam("id") String id,
                                             @RequestParam("imgUploadedTime") String imgUploadedTime) {

        log.info("deleteReport(): " + reportNo + ", " + id + ", " + imgUploadedTime);

        File path = new File("./image/animalPics");
        File fileList[] = path.listFiles();
        String fileName = "";
        File targetFile = null;
        boolean targetFileExists = false;

        String prevFolderName = id + "-" + imgUploadedTime;

        try {
            for (File file : fileList) {

                fileName = file.getName();

                if (fileName.matches(prevFolderName)) {
                    log.info("matches and delete!");
                    targetFileExists = true;
                    targetFile = file;
                    break;
                }
            }

            if (targetFileExists) {

                File[] targetPics = targetFile.listFiles();

                for (int i = 0; i < targetPics.length; i++) {
                    targetPics[i].delete();
                    log.info("pics deleted.");
                }
            }

            targetFile.delete();

        } catch (Exception e) {
            ;
        }

        reportService.deleteReport(reportNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/modifyPics")
    public ResponseEntity<Integer> sendAnimalPics(@RequestParam(value = "animalPics", required = false) List<MultipartFile> animalPicsList,
                                                  @RequestParam("category") String category,
                                                  @RequestParam("id") String id,
                                                  @RequestParam("dateId") String dateId,
                                                  @RequestParam("prevFolderName") String prevFolderName,
                                                  @RequestParam(value = "deletePicsArr", required = false) Integer[] deletePicsArr) {

        log.info("sendAnimalPics(): " + prevFolderName + ", " + deletePicsArr + ", " + category + ", " + id + ", " + dateId + ", " + animalPicsList);

        File path = new File("./image/animalPics");
        File fileList[] = path.listFiles();
        String fileName = "";

        File targetFile = null;
        boolean targetFileExists = false;

        String modifiedFileName = id + "-" + dateId;
        int numOfPics = 0; //반환할 총 사진 카운트 값

        for(File file : fileList) {

            fileName = file.getName();

            if(fileName.matches(prevFolderName)) {
                log.info("matches!");
                targetFile = file;
                targetFileExists = true;
                break;
            }
        }

        if(targetFileExists) {

            for (int i = targetFile.listFiles().length - 1; i >= 0; i--) {

                String tmpFileName = targetFile.listFiles()[i].getName();
                String picNum = String.valueOf(tmpFileName.substring(tmpFileName.length() - 5).charAt(0));

                for (int j = deletePicsArr.length - 1; j >= 0; j--) {

                    if(picNum.matches(Integer.toString(deletePicsArr[j]))) targetFile.listFiles()[i].delete();
                    //break;
                }
                //log.info("xx: " + targetFile.listFiles().length);
            }

            for (int i = 0; i < targetFile.listFiles().length; i++) {
                targetFile.listFiles()[i].renameTo(new File("./image/animalPics/" + prevFolderName + "/" + category + "-" + i + ".jpg"));
                numOfPics++;
            }

            if(targetFile.listFiles().length == 0 && animalPicsList == null) targetFile.delete();
            else targetFile.renameTo(new File("./image/animalPics/" + modifiedFileName));
        }

        //삭제 했으니까 남은 파일들 이름 바꿈 -> 폴더이름 바꿈 -> 새로 들어온 파일들 n값 뒤로 숫자 넣어주고 마무리 --> 전체 이미지 Cnt값 반환

        if(!targetFileExists && animalPicsList != null) {

            String newPath = "./image/animalPics/" + modifiedFileName;
            File folder = new File(newPath);
            folder.mkdir();
        }
        
        if(animalPicsList != null) {

            log.info("new Pics Input Start!");

            try {
                for (int i=0; i < animalPicsList.toArray().length; i++) {
                    MultipartFile multipartFile = animalPicsList.get(i);

                    FileOutputStream writer = new FileOutputStream("./image/animalPics/" + modifiedFileName + "/" + category + "-" + numOfPics + ".jpg");

                    writer.write(multipartFile.getBytes());
                    writer.close();

                    numOfPics++;
                }

            } catch (Exception e) {
                log.info("pic modify failed!");
            }
        }
        return new ResponseEntity<Integer>(numOfPics, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> reportModify(@Validated @RequestBody Report report) {
        log.info("reportModify(): " + report);

        reportService.reportModify(report);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
