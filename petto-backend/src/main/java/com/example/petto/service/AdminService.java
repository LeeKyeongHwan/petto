package com.example.petto.service;
import com.example.petto.entity.Member;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminService {

    public List<Member> memberList( ) throws Exception;

    @Transactional
    public Member read(Long memberNo) throws Exception;

    public Member edit(Long memberNo, Member member) throws Exception;

    public void remove(Long memberNo) throws Exception;

}