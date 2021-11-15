package com.example.petto.repository;

import com.example.petto.entity.MemberRelated.LikedAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikedAnimalRepository extends JpaRepository<LikedAnimal, Long> {

    List<LikedAnimal> findByMemberNo(Long memberNo);

    @Transactional
    @Modifying
    @Query("delete LikedAnimal la where la.noticeNo = :noticeNo and la.memberNo = :memberNo")
    void delete(String noticeNo, Long memberNo);
}
