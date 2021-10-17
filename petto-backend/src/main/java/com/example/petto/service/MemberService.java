package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;

public interface MemberService {

    public boolean idDupliChk(String id);

    public void signup(MemberRequest memberRequest);

    public boolean nicknameDupliChk(String nickname);

    public boolean checkValidEmail(String email);

    public String checkValidEmailForPw(String email, String id);

    public void changePassword(MemberRequest memberRequest);
}
