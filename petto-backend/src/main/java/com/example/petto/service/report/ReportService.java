package com.example.petto.service.report;

import com.example.petto.entity.Report;

import java.util.List;

public interface ReportService {

    public void register(Report report);

    public List<Report> myBoardList(String id) throws Exception;

}
