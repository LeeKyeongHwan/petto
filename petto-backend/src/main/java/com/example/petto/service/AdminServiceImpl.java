package com.example.petto.service;


import com.example.petto.entity.Member;
import com.example.petto.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberRepository memberRepository;

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

            return member;
        } else {
            throw new NullPointerException();
        }

    }

    @Override
    public Member edit(Long memberNo,Member member) throws Exception {
        Optional<Member> adminEdit = memberRepository.findById(memberNo);

        adminEdit.ifPresent( changeInfo ->{
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


}
