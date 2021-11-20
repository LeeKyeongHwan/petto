package com.example.petto.controller;


import com.example.petto.entity.QnA;
import com.example.petto.service.QnAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/petto/qna")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnAController {

    @Autowired
    private QnAService service;

    @ResponseBody
    @GetMapping("/lists")
    public List<QnA> getLists () throws Exception{
        log.info("getLists(): ");

        return service.list();
    }

    @ResponseBody
    @PostMapping("/register")
    public long postRegister (@RequestBody QnA qna) throws Exception{
        log.info("postRegister(): " + qna);

        service.register(qna);

        return qna.getQnaNo();
    }

    @ResponseBody
    @GetMapping("/{qnaNo}")
    public QnA getRead(@PathVariable long qnaNo) throws Exception{
        log.info("getRead(): " + qnaNo);

        return service.read(qnaNo);
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> postModify(@Validated @RequestBody QnA qna) throws Exception{
        log.info("postModify(): " + qna);

        service.modify(qna);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{qnaNo}")
    public void deleteBoardNo(@PathVariable long qnaNo) throws Exception{
        log.info("deleteBoardNo(): " + qnaNo);

        service.delete(qnaNo);
    }

    @GetMapping("/myQnaList/{id}")
    public ResponseEntity<List<QnA>> getMyBoardLists(@PathVariable("id") String id) throws Exception {
        log.info("id == > "+ id);
        log.info("getMyBoardLists(): " + service.myQnaList(id));
        return new ResponseEntity<>(service.myQnaList(id), HttpStatus.OK);
    }

}
