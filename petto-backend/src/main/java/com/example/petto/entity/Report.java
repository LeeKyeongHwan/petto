package com.example.petto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="petto_report")
@Transactional
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_no")
    private Long reportNo;

    @Column(length = 10, nullable = false)
    private String category;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(length = 40, nullable = false)
    private String whenHappened;

    @Column(length = 40, nullable = false)
    private String whereHappened;

    @Column(length = 40, nullable = true)
    private String keepingPlace;

    @Column(length = 20, nullable = false)
    private String breed;

    @Column(length = 40, nullable = false)
    private String feature;

    @Column(length = 300, nullable = true)
    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean expired;

    @Column(length = 30, nullable = true)
    private String imgUploadedTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date updDate;
}
