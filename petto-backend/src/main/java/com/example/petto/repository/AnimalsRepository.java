package com.example.petto.repository;


import com.example.petto.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {

}