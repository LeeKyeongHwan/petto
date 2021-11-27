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

    //Optional<Animals> findByNoticeNo(String noticeNo); 엔티티의 변수명에 언더바를 넣으면 findBy로 찾을 수 없다.

    @Query("select an from Animals an where an.notice_no = :noticeNo")
    Optional<Animals> findByNoticeNo(String noticeNo);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Animals an set an.numberOfLiked = an.numberOfLiked + 1 where an.notice_no = :noticeNo")
    void addNumberOfLiked(String noticeNo);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Animals an set an.numberOfLiked = an.numberOfLiked - 1 where an.notice_no = :noticeNo")
    void subNumberOfLiked(String noticeNo);

    @Query("select an from Animals an where an.state = :state")
    List<Animals> findByState(String state);

    @Query(value = "SELECT A.* FROM animals A inner join liked_animal B on A.notice_no = B.notice_no WHERE member_no = :memberNo", nativeQuery = true)
    List<Animals> myLikedAnimals(long memberNo);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Animals an set an.sharedCnt = an.sharedCnt + 1 where an.notice_no = :noticeNo")
    void plusSharedCnt(String noticeNo);
}
