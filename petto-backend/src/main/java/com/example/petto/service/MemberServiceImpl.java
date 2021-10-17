package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;
import com.example.petto.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean idDupliChk(String id) {

        List<Member> memberList = memberRepository.findAll();

        for(int i=0; i<memberList.size(); i++) {
            if(memberList.get(i).getId().equals(id)) {
                log.info("ddd");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean nicknameDupliChk(String nickname) {

        List<Member> memberList = memberRepository.findAll();

        for(int i=0; i<memberList.size(); i++) {
            if(memberList.get(i).getNickname().equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void signup(MemberRequest memberRequest) {

        String id = memberRequest.getId();
        String password = passwordEncoder.encode(memberRequest.getPassword());
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
