package com.example.petto.repository;

import com.example.petto.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Long>{

    @Query(value = "SELECT A.* FROM petto_qna A inner join petto_member B on A.writer = B.id WHERE writer = :id", nativeQuery = true)
    List<QnA> myQnaList(String id);

}