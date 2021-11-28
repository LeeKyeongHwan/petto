package com.example.petto.service.report;

import com.example.petto.entity.Report;
import com.example.petto.entity.ReportRelated.Reply;

import java.util.List;

public interface ReportService {

    public void register(Report report);

    public List<Report> getReportList();

    public Report read(Integer reportNo);

    public void makeReportExpired(Integer reportNo);

    public void deleteReport(Integer reportNo);

    public void reportModify(Report report);

    public List<Report> myBoardList(String id) throws Exception;

    public void saveReply(Reply reply);

    public List<Reply> getReplyList(Integer reportNo);

    public void deleteReply(Integer replyNo);

    public void modifyReply(Reply reply);

    public void plusViewCnt(Integer reportNo);
}
