package com.example.petto.repository.report;

import com.example.petto.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT A.* FROM petto_report A inner join petto_member B on A.writer = B.id WHERE writer = :id", nativeQuery = true)
    List<Report> myBoardList(String id);

}
