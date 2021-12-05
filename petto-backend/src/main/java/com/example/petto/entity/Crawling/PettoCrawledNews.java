package com.example.petto.entity.Crawling;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@Entity
@Table(name="petto_crawled_news")
@Getter
@Transactional
public class PettoCrawledNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_no")
    private Long newsNo;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String url;
}
