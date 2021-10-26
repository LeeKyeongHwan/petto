package com.example.petto.repository;

import com.example.petto.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    //IllegalArgumentException: Not a managed type: class java.lang.Long 에러 -> 원인: 제네릭을 Long, Facility 썼음

    Facility findByFacilityNo(Long facilityNo);

    Optional<Facility> findByFacilityName(String facilityName);
}
