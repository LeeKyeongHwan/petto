package com.example.petto.entity;

import com.example.petto.entity.MemberRelated.LikedAnimal;
import com.example.petto.entity.ReportRelated.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@DynamicUpdate
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

    @Column(columnDefinition = "integer default 0")
    private Integer viewCnt;

    @Column(columnDefinition = "boolean default false")
    private boolean expired;

    @Column(length = 30, nullable = true)
    private String imgUploadedTime;

    @Column(length = 10, nullable = true)
    private String imgUploadedCnt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date updDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true) //, orphanRemoval = true 이거때면 report_no가 null이됨
    @JoinColumn(name = "report_no") //,updatable = false, insertable = false
    private List<Reply> replies = new ArrayList<Reply>();

    public void addReplies(Reply reply) {
        replies.add(reply);
    }
}
