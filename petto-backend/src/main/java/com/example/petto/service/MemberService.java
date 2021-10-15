package com.example.petto.service;

        import com.example.petto.controller.request.MemberRequest;

public interface MemberService {


    public boolean login(MemberRequest memberRequest) throws Exception;
    public boolean idDupliChk(String id);
    public boolean checkIdValidation(String id) throws Exception;
    public void signup(MemberRequest memberRequest);

    public boolean nicknameDupliChk(String nickname);
}
