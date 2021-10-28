package com.example.petto.service;

import com.example.petto.entity.Animals;
import com.example.petto.repository.AnimalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AnimalsServiceImpl implements AnimalsService {

    @Autowired
    private AnimalsRepository animalsRepository;

    @Override
    public List<Animals> list() throws Exception {
        return animalsRepository.findAll();}

    @Override
    public Animals getAnimalsInfo(Integer id) {
        return animalsRepository.findById(new Long(id)).get();
    }
}