package com.example.petto.service.report;

import com.example.petto.entity.Report;

import java.util.List;

public interface ReportService {

    public void register(Report report);

    public List<Report> getReportList();

    public Report read(Integer reportNo);

    public void makeReportExpired(Integer reportNo);

    public void deleteReport(Integer reportNo);

    public void reportModify(Report report);
}
