package com.example.petto.service;

import com.example.petto.entity.VolBoardRelated.Comment;

import java.util.List;

public interface CommentService {

    public void register(Comment comment) throws Exception;

    public List<Comment> list(Long volunteerNo) throws Exception;

    public void modify(Long commentNo,Comment comment) throws Exception;

    public void remove(Long commentNo) throws Exception;


}