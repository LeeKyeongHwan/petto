package com.example.petto.service.report;

import com.example.petto.entity.Report;
import com.example.petto.repository.report.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    public void register(Report report) {
        reportRepository.save(report);
    }
}
