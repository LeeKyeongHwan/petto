package com.example.petto.repository.reportRelated;

import com.example.petto.entity.ReportRelated.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Transactional
    void deleteByReportNo(Long reportNo);

    List<Reply> findByReportNo(Long reportNo);

    @Modifying
    @Transactional
    void deleteByReplyNo(Long replyNo);
}
