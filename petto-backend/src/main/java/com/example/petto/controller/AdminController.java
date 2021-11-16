package com.example.petto.controller;

import com.example.petto.entity.Member;
import com.example.petto.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/lists")
    public ResponseEntity<List<Member>> getLists () throws Exception {
        log.info("getLists(): " + adminService.list());

        return new ResponseEntity<>(adminService.list(), HttpStatus.OK);
    }

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
}
