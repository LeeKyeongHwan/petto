package com.example.petto.controller;

import com.example.petto.entity.VolBoardRelated.Comment;
import com.example.petto.service.CommentService;
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
@RequestMapping("/petto/comments")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/lists/{volunteerNo}")
    public ResponseEntity<List<Comment>> commentList(@PathVariable("volunteerNo") Long volunteerNo) throws Exception{
        log.info("commentList: " + commentService.list(volunteerNo));
        return new ResponseEntity<>(commentService.list(volunteerNo), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Comment> register(@Validated @RequestBody Comment comment) throws Exception {
        log.info("post register request from vue");
        commentService.register(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/edit/{commentNo}")
    public ResponseEntity<Comment> modify(@PathVariable("commentNo") Long commentNo,
                                          @Validated @RequestBody Comment comment) throws Exception {

        commentService.modify(commentNo,comment);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @DeleteMapping("/{commentNo}")
    public ResponseEntity<Void> remove(@PathVariable("commentNo") Long commentNo) throws Exception {
        commentService.remove(commentNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}