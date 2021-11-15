package com.example.petto.repository;

import com.example.petto.entity.VolBoardRelated.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.volunteerNo = :volunteerNo")
    List<Comment> findByVolunteerNo(Long volunteerNo);
}
