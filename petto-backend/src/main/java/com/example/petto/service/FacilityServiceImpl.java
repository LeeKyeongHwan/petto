package com.example.petto.service;

import com.example.petto.entity.Facility;
import com.example.petto.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    @Override
    public List<String> getFacilityNoAndAddr(String carenm) {

        Facility facility = facilityRepository.findByFacilityName(carenm).get(0);
        //log.info("facility: " + facility);

        String facilityNo = String.valueOf(facility.getFacilityNo());
        String facilityAddr = facility.getFacilityAddr();
        String facilityName = facility.getFacilityName();

        List<String> list = new ArrayList<>();

        list.add(facilityNo);
        list.add(facilityAddr);
        list.add((facilityName));

        return list;
    }
}
