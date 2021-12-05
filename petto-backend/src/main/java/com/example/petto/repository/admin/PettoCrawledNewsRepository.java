package com.example.petto.repository.admin;

import com.example.petto.entity.Crawling.PettoCrawledNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PettoCrawledNewsRepository extends JpaRepository<PettoCrawledNews, Long> {
}
