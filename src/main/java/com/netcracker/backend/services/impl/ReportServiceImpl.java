package com.netcracker.backend.services.impl;

import com.netcracker.backend.model.Post;
import com.netcracker.backend.model.Report;
import com.netcracker.backend.model.ReportStatusEnum;
import com.netcracker.backend.repositories.PostRepository;
import com.netcracker.backend.repositories.ReportRepository;
import com.netcracker.backend.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final PostRepository postRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, PostRepository postRepository) {
        this.reportRepository = reportRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void sendReport(Long ID, String reason) {
        Post post = postRepository.findByID(ID);
        if (post != null) {
            Report report = new Report();
                    report.setPost(post);
                    report.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
                    report.setReason(reason);
                    report.setStatus(ReportStatusEnum.UNCHECKED);
            reportRepository.save(report);
        }
    }

    @Override
    public List<Report> getAllReports(Integer page, Integer count) {
        return reportRepository.findAll(PageRequest.of(page, count, Sort.by(Sort.Direction.DESC, "date")))
                .getContent();
    }

    @Override
    public Long getCountAllReports() {
        return reportRepository.count();
    }

    @Override
    public Long getCountUnreadReports() {
        return  reportRepository.countByStatus(ReportStatusEnum.UNCHECKED);
    }

    @Override
    public void markAsChecked(Long ID) {
        Optional<Report> reports = reportRepository.findById(ID);
        if (reports.isPresent()) {
            Report report = reports.get();
            report.setStatus(ReportStatusEnum.CHECKED);
            reportRepository.save(report);
        }
    }

    @Override
    public void markAsUnchecked(Long ID) {
        Optional<Report> reports = reportRepository.findById(ID);
        if (reports.isPresent()) {
            Report report = reports.get();
            report.setStatus(ReportStatusEnum.UNCHECKED);
            reportRepository.save(report);
        }
    }
}
