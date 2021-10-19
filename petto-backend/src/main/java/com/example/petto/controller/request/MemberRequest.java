package com.example.petto.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRequest {
    private String id;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String birthday;
    private String petsRaised;
    private String nickname;
}
