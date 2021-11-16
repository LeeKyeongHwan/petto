package com.example.petto.service.report;

import com.example.petto.entity.Report;
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

    public void register(Report report) {
        reportRepository.save(report);
    }
  
    public List<Report> getReportList() {
        return reportRepository.findAll();
    }

    public Report read(Integer reportNo) {
        try {
            return reportRepository.findByReportNo(new Long(reportNo)).get();
        } catch (Exception e) {
            return null;
        }
    }

    public void makeReportExpired(Integer reportNo) {
        reportRepository.expireReport(new Long(reportNo));
    }

    public void deleteReport(Integer reportNo) {
        reportRepository.deleteByReportNo(new Long(reportNo));
    }

    public void reportModify(Report report) {
        reportRepository.save(report);
    }

    @Override
    public List<Report> myBoardList(String id) throws Exception {
        return reportRepository.myBoardList(id);
    }
  
}
