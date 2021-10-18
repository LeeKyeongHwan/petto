package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;

public interface MemberService {

    public boolean idDupliChk(String id);

    public void signup(MemberRequest memberRequest);

    public boolean nicknameDupliChk(String nickname);

    public boolean checkValidEmail(String email, String birthday);

    public String checkValidEmailForPw(String email, String id);

    public void changePassword(MemberRequest memberRequest);

    public Member getUserInfo(Integer userNo);

    public void modifyUserInfo(Member member);
}
