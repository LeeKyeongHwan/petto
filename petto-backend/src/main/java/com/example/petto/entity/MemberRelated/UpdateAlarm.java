package com.example.petto.entity.MemberRelated;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="update_alarm")
public class UpdateAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_no")
    private Long alarmNo;

    @JoinColumn(name = "id", referencedColumnName = "id") //insertable = false, updatable = false 뺌
    private String id; //글 게시자

    @Column(length = 50, nullable = false)
    private String commentator; //댓글 작성자

    @Column(length = 100, nullable = false)
    private String title; //댓글쓴 글 제목

    @Column(length = 20, nullable = false)
    private String typeOfPost; //댓글쓴 글 종류 (목보실, 다른 종류 게시판 글)

    @Column(nullable = false)
    private Long postNo; //댓글쓴 글 번호

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date regDate;
}
