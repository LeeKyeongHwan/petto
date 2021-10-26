package com.example.petto.repository;


import com.example.petto.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {

    Animals findByid(Long id);

}