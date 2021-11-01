package com.example.petto.repository;

import com.example.petto.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Facility findByFacilityNo(Long facilityNo);

    List<Facility> findByFacilityName(String facilityName);
