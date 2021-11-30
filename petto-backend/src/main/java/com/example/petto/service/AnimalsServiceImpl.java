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
        return animalsRepository.findAll();
    }

    @Override
    public List<Animals> listByLocation(String cityName) {

        String[] northKyungki = { "남양주", "일산", "가평", "파주", "양주" };
        String[] southKyungki = { "부천", "하남", "광명", "양평", "광주", "여주", "용인", "성남", "수원", "오산", "화성", "안산", "평택" };

        List<Animals> kyungKiAnimals = animalsRepository.findByCityName("경기");

        if(cityName.equals("경기북부")) {
            List<Animals> northKyngkiAnimals = new ArrayList<Animals>();
            
            for(Animals ani : kyungKiAnimals) {
                
                for(int i=0; i<northKyungki.length; i++) {
                    if(ani.getCareaddr().contains(northKyungki[i])) {
                        northKyngkiAnimals.add(ani);
                    }
                }
            }
            return northKyngkiAnimals;
            
        } else if(cityName.equals("경기남부")) {
            List<Animals> southKyngkiAnimals = new ArrayList<Animals>();

            for(Animals ani : kyungKiAnimals) {

                for(int i=0; i<southKyungki.length; i++) {
                    if(ani.getCareaddr().contains(southKyungki[i])) {
                        southKyngkiAnimals.add(ani);
                    }
                }
            }
            return southKyngkiAnimals;
        }
        else return animalsRepository.findByCityName(cityName);
    }

    @Override
    public Animals getAnimalsInfo(String id) {

        if(id.length() > 10) {
            log.info("id.length() > 10 - " + id);
            return animalsRepository.findByNoticeNo(id).get();
        }
        return animalsRepository.findById(new Long(id)).get();
    }

    @Override
    public List<Animals> oldList() throws Exception {
        String state = "보호중";
        List<Animals> lists = animalsRepository.findByState(state);
        Collections.reverse(lists);
        List<Animals> olderList = new ArrayList<>();

        for(int i=0; i< 5; i++){
            olderList.add(lists.get(i));
        }
        return olderList;
    }

    @Override
    public List<Animals> myLikedAnimals(long memberNo) {
        return animalsRepository.myLikedAnimals(memberNo);
    }

    @Override
    public Long getNumOfAnimals() {
        return animalsRepository.count();
    }

    @Override
    public List<Animals> filterAnimals(String[] selectedPlace, String[] selectedKinds) {

        List<Animals> tmpList = animalsRepository.findAll();

        if(selectedPlace[0].matches("none") && selectedKinds[0].matches("none")) return tmpList;

        if(selectedPlace[0].matches("none")) return filter(tmpList, selectedKinds); //null로 하면 안됨
        else if(selectedKinds[0].matches("none")) return filter(tmpList, selectedPlace);

        return filter(filter(tmpList, selectedKinds), selectedPlace);
    }

    private List<Animals> filter(List<Animals> animalsList, String[] keywordList) {

        List<Animals> list = new ArrayList<Animals>();
        boolean isPlaceKeyword = true;

        for(String keyword : keywordList) {
            if(keyword.contains("개") || keyword.contains("고양이") || keyword.contains("기타")) isPlaceKeyword = false;
        }

        if(isPlaceKeyword) {
            for (int i = 0; i < animalsList.size(); i++) {

                for (int j = 0; j < keywordList.length; j++) {

                    if(animalsList.get(i).getCareaddr().contains(keywordList[j])) list.add(animalsList.get(i));
                }
            }
        } else {
            for (int i = 0; i < animalsList.size(); i++) {

                for (int j = 0; j < keywordList.length; j++) {

                    if(animalsList.get(i).getKind().contains(keywordList[j])) list.add(animalsList.get(i));
                }
            }
        }
        return list;
    }

    public void plusSharedCnt(String notice_no) {

        animalsRepository.plusSharedCnt(notice_no);
    }
}