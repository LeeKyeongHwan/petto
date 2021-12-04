package com.example.petto.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@Entity
@Table(name="petto_crawled_videos")
@Getter
@Transactional
public class PettoCrawledVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_no")
    private Long videoNo;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String url;

    @Column(length = 400, nullable = true)
    private String imgSrc;

    @Column(columnDefinition = "boolean default true")
    private Boolean allowedOrNot;
}
