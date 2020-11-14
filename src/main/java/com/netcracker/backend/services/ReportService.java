package com.netcracker.backend.services;

import com.netcracker.backend.model.Report;

import java.util.List;

public interface ReportService {
    void sendReport(Long ID, String reason);

    List<Report> getAllReports(Integer page, Integer count);

    Long getCountAllReports();

    void markAsChecked(Long ID);

    void markAsUnchecked(Long ID);

    Long getCountUnreadReports();
}
