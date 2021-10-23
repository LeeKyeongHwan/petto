package com.example.petto.service;

import com.example.petto.entity.Facility;
import com.example.petto.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    FacilityRepository facilityRepository;

    @Override
    public List<Facility> getFacilityList() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility getFacilityInfo(Integer facilityNo) {
        return facilityRepository.findByFacilityNo(new Long(facilityNo));
    }
}
