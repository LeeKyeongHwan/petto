package com.example.petto.service;


import com.example.petto.entity.Member;
import com.example.petto.entity.PettoCrawledVideo;
import com.example.petto.repository.LikedAnimalRepository;
import com.example.petto.repository.MemberRepository;
import com.example.petto.repository.admin.PettoCrawledVideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PettoCrawledVideoRepository pettoCrawledVideoRepository;

    @Override
    public List<Member> memberList() throws Exception {
        return memberRepository.findAll();
    }

    @Override
    public Member read(Long memberNo) throws Exception {
        Optional<Member> memberDetail = memberRepository.findById(memberNo);

        if(memberDetail.isPresent()) {
            Member member = memberDetail.get();
            memberRepository.save(member);

            member.removeAlarmList(member.getUpdateAlarmList());
            member.removeLikedList(member.getLikedAnimals());

            return member;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Member edit(Long memberNo, Member member) throws Exception {
        Optional<Member> adminEdit = memberRepository.findById(memberNo);

        adminEdit.ifPresent( changeInfo -> {
            changeInfo.setAuth(member.getAuth());
            member.setMemberNo(memberNo);
            memberRepository.save(changeInfo);
        });
        return member;
    }

    @Override
    public void remove(Long memberNo) throws Exception {
        memberRepository.deleteById(memberNo);
    }

    @Override
    public List<PettoCrawledVideo> getVids() {
        return pettoCrawledVideoRepository.findAll();
    }

    @Override
    public void changeVidStat(Long videoNo) {
        Optional<PettoCrawledVideo> vid = pettoCrawledVideoRepository.findById(videoNo);

        if(vid.get().getAllowedOrNot()) pettoCrawledVideoRepository.blockVid(videoNo);
        else pettoCrawledVideoRepository.allowVid(videoNo);
    }

    @Override
    public List<PettoCrawledVideo> getAllowedVids() {
        List<PettoCrawledVideo> vids = pettoCrawledVideoRepository.getAllowedVides();
        log.info("xx: " + vids.size());
        if(vids.size() < 12) return vids;

        Random rand = new Random();
        final int VIDS_SIZE = vids.size();
        final int OUPUT_VIDS_SIZE = 12;

        int[] ranArr = new int[OUPUT_VIDS_SIZE];

        int tmpNum;
        boolean isDupli;
        for(int i=0; i<OUPUT_VIDS_SIZE; i++) {
            isDupli = false;
            tmpNum = rand.nextInt(VIDS_SIZE);

            for(int j=0; j<i; j++) {
                if(tmpNum == ranArr[j]) {
                    i -= 1;
                    isDupli = true;
                    break;
                }
            }
            if(isDupli) continue;
            ranArr[i] = tmpNum;
        }

        List<PettoCrawledVideo> allowedVidsList = new ArrayList<PettoCrawledVideo>();
        for(int i=0; i<ranArr.length; i++) allowedVidsList.add(vids.get(ranArr[i]));

        return allowedVidsList;
    }
}