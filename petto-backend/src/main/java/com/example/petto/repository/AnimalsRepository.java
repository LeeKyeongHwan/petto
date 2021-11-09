package com.example.petto.repository;

import com.example.petto.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {

    Optional<Animals> findById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Animals an set an.numberOfLiked = an.numberOfLiked + 1 where an.notice_no = :noticeNo")
    void addNumberOfLiked(String noticeNo);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Animals an set an.numberOfLiked = an.numberOfLiked - 1 where an.notice_no = :noticeNo")
    void subNumberOfLiked(String noticeNo);
}