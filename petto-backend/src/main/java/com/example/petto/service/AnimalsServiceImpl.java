package com.example.petto.service;

import com.example.petto.entity.Animals;
import com.example.petto.repository.AnimalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<Animals> oldList() throws Exception {
        String state = "보호중";
        List<Animals> lists = animalsRepository.findByState(state);
        Collections.reverse(lists);
        List<Animals> olderList = new ArrayList<>();

        for(int i=0; i< 12; i++){
            olderList.add(lists.get(i));
        }
        return olderList;
    }
}