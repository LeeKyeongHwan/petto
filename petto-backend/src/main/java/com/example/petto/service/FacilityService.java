package com.example.petto.service;

import com.example.petto.entity.Facility;

import java.util.List;

public interface FacilityService {

    public List<Facility> getFacilityList();

    public Facility getFacilityInfo(Integer facilityNo);

    public List<String> getFacilityNoAndAddr(String carenm);
