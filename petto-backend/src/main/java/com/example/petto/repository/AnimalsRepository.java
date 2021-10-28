package com.example.petto.repository;


import com.example.petto.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {

    Optional<Animals> findById(Long id);
}