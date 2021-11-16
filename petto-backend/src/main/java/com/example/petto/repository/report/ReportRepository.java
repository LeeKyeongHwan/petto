package com.example.petto.repository.report;

import com.example.petto.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByReportNo(Long ReportNo);

    @Transactional
    @Modifying
    @Query("update Report re set re.expired = true where re.reportNo = :reportNo")
    void expireReport(Long reportNo);

    @Transactional
    @Modifying
    void deleteByReportNo(Long reportNo);

    @Query(value = "SELECT A.* FROM petto_report A inner join petto_member B on A.writer = B.id WHERE writer = :id", nativeQuery = true)
    List<Report> myBoardList(String id);
}
