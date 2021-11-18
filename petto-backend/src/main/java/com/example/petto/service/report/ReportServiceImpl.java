package com.example.petto.service.report;

import com.example.petto.entity.Report;
import com.example.petto.entity.ReportRelated.Reply;
import com.example.petto.repository.report.ReplyRepository;
import com.example.petto.repository.report.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReplyRepository replyRepository;

    public void register(Report report) {
        reportRepository.save(report);
    }
  
    public List<Report> getReportList() {
        return reportRepository.findAll();
    }

    public Report read(Integer reportNo) {
        try {
            Report report = reportRepository.findByReportNo(new Long(reportNo)).get();

            report.setReplies(null);

            return report;
        } catch (Exception e) {
            return null;
        }
    }

    public void makeReportExpired(Integer reportNo) {
        reportRepository.expireReport(new Long(reportNo));
    }

    public void deleteReport(Integer reportNo) {

        try {
            replyRepository.deleteByReportNo(new Long(reportNo));
        } catch (Exception e) {
            log.info("Report delete failed.");
        } finally {
            reportRepository.deleteByReportNo(new Long(reportNo));
        }
    }

    public void reportModify(Report report) {

        List<Reply> replyList = replyRepository.findByReportNo(new Long(report.getReportNo()));

        for(int i=0; i<replyList.size(); i++) report.addReplies(replyList.get(i));

        replyRepository.deleteByReportNo(report.getReportNo());

        reportRepository.save(report);
    }

    @Override
    public List<Report> myBoardList(String id) throws Exception {
        return reportRepository.myBoardList(id);
    }

    @Override
    public void saveReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public List<Reply> getReplyList(Integer reportNo) {
        return replyRepository.findByReportNo(new Long(reportNo));
    }

    @Override
    public void deleteReply(Integer replyNo) {
        replyRepository.deleteByReplyNo(new Long(replyNo));
    }

    @Override
    public void modifyReply(Reply reply) {
        replyRepository.save(reply);
    }
}
