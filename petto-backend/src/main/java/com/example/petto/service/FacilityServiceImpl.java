package com.example.petto.service;

import com.example.petto.entity.Facility;
import com.example.petto.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<Optional<Facility>> tmpFacility = facilityRepository.findByFacilityName(carenm);

        if(!tmpFacility.isEmpty()) {

            Facility facility = tmpFacility.get(0).get();

            String facilityNo = String.valueOf(facility.getFacilityNo());
            String facilityAddr = facility.getFacilityAddr();
            String facilityName = facility.getFacilityName();

            List<String> list = new ArrayList<>();

            list.add(facilityNo);
            list.add(facilityAddr);
            list.add((facilityName));

            return list;

        } else {

            return null;
        }
    }
}
