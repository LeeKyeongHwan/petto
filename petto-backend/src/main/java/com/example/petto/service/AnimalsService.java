package com.example.petto.service;

import com.example.petto.entity.Animals;

import java.util.List;

public interface AnimalsService {

    public List<Animals> list() throws Exception;

    public Animals getAnimalsInfo(String id);

    public List<Animals> oldList() throws Exception;
}
