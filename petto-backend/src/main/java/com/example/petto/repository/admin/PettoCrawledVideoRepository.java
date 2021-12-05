package com.example.petto.repository.admin;

import com.example.petto.entity.PettoCrawledVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PettoCrawledVideoRepository extends JpaRepository<PettoCrawledVideo, Long> {

    @Transactional
    @Modifying
    @Query("update PettoCrawledVideo pc set pc.allowedOrNot = false where pc.videoNo = :videoNo")
    void blockVid(Long videoNo);

    @Transactional
    @Modifying
    @Query("update PettoCrawledVideo pc set pc.allowedOrNot = true where pc.videoNo = :videoNo")
    void allowVid(Long videoNo);

    @Query("select pc from PettoCrawledVideo pc where pc.allowedOrNot = true")
    List<PettoCrawledVideo> getAllowedVides();
}
