package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;
import com.example.petto.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    public boolean idDupliChk(String id) {

        List<Member> memberList = memberRepository.findAll();

        for(int i=0; i<memberList.size(); i++) {
            if(memberList.get(i).getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void signup(MemberRequest memberRequest) {

        String id = memberRequest.getId();
        String password = memberRequest.getPassword();
        String email = memberRequest.getEmail();
        String phoneNumber = memberRequest.getPhoneNumber();
        String name = memberRequest.getName();
        String birthday = memberRequest.getBirthday();
        String petsRaised = memberRequest.getPetsRaised();
        String nickname = memberRequest.getNickname();

        Member member = new Member(id, password, email, phoneNumber, name, birthday, petsRaised, nickname);

        memberRepository.save(member);
    }
}
