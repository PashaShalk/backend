package com.netcracker.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "mo_reports", schema = "momentum")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private ReportStatusEnum status;

    private Timestamp date;
    private String reason;
}
