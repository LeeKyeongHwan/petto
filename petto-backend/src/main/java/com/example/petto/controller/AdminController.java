package com.example.petto.controller;

import com.example.petto.entity.Member;
import com.example.petto.entity.QnA;
import com.example.petto.service.AdminService;
import com.example.petto.service.QnAService;
import com.example.petto.session.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petto/admin")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private QnAService qnAService;

    @Transactional
    @GetMapping("/lists")
    public ResponseEntity<List<Member>> getLists () throws Exception {
        log.info("getLists(): " + adminService.memberList());

        return new ResponseEntity<>(adminService.memberList(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{memberNo}")
    public ResponseEntity<Member> read(@PathVariable("memberNo") Long memberNo) throws Exception {
        Member member = adminService.read(memberNo);

        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PutMapping("/{memberNo}")
    public ResponseEntity<Member> edit(@PathVariable("memberNo") Long memberNo,
                                       @Validated @RequestBody Member member ) throws Exception {
        adminService.edit(memberNo,member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{memberNo}")
    public ResponseEntity<Void> remove(@PathVariable("memberNo") Long memberNo) throws Exception {
        adminService.remove(memberNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/qna/lists")
    public ResponseEntity<List<QnA>> getQnALists () throws Exception {
        log.info("getQnALists(): " + qnAService.qnaList());

        return new ResponseEntity<>(qnAService.qnaList(), HttpStatus.OK);
    }

    @GetMapping("/qna/{qnaNo}")
    public ResponseEntity<QnA> QnARead(@PathVariable("qnaNo") Long qnaNo) throws Exception {
        QnA qnA = qnAService.qnaRead(qnaNo);

        return new ResponseEntity<QnA>(qnA, HttpStatus.OK);
    }

    @PutMapping("/qna/{qnaNo}")
    public ResponseEntity<QnA> answers(@PathVariable("qnaNo") Long qnaNo,
                                       @Validated @RequestBody QnA qnA ) throws Exception {
        qnAService.qnaAnswers(qnaNo,qnA);
        return new ResponseEntity<>(qnA, HttpStatus.OK);
    }
}