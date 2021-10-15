package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;

public interface MemberService {

    public boolean idDupliChk(String id);

    public void signup(MemberRequest memberRequest);
}
