package com.example.petto.service;
import com.example.petto.entity.Member;

import java.util.List;

public interface AdminService {

    public List<Member> memberList( ) throws Exception;

    public Member read(Long memberNo) throws Exception;

    public Member edit(Long memberNo, Member member) throws Exception;

    public void remove(Long memberNo) throws Exception;

}