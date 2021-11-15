package com.example.petto.service;

import com.example.petto.entity.VolBoardRelated.Comment;
import com.example.petto.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> list(Long volunteerNo) throws Exception {
        List<Comment> pageComment = commentRepository.findByVolunteerNo(volunteerNo);
        return pageComment;
    }


    @Override
    public void register(Comment comment) throws Exception {
        commentRepository.save(comment);
    }

    @Override
    public void modify(Long commentNo, Comment comment) throws Exception {
        Optional<Comment> editComment = commentRepository.findById(commentNo);
        Comment edit = editComment.get();
        edit.setComment(comment.getComment());

        commentRepository.save(edit);
    }



    @Override
    public void remove(Long commentNo) throws Exception {
        commentRepository.deleteById(commentNo);
    }

}