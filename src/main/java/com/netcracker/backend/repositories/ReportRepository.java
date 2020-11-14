package com.netcracker.backend.repositories;

import com.netcracker.backend.model.Report;
import com.netcracker.backend.model.ReportStatusEnum;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {
    Long countByStatus(ReportStatusEnum status);
}
