package com.example.petto.repository;

import com.example.petto.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    //IllegalArgumentException: Not a managed type: class java.lang.Long 에러 -> 원인: 제네릭을 Long, Facility 썼음
}
