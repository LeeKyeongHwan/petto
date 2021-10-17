package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;

public interface MemberService {

<<<<<<< HEAD

    public boolean login(MemberRequest memberRequest) throws Exception;

    public boolean idDupliChk(String id);

    public boolean checkIdValidation(String id) throws Exception;

=======
    public boolean idDupliChk(String id);

>>>>>>> f9ccf9b1e400e744ce2c3d77034cd40f0fecfb17
    public void signup(MemberRequest memberRequest);

    public boolean nicknameDupliChk(String nickname);
}
