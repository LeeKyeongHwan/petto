package com.example.petto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString
public class MemberAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_Auth")
    private  Long rowNo;

    @Column(name = "member_no")
    private Long memberNo;

    @Column(length = 50 ,nullable = true)
    private String auth;

    public MemberAuth (Long memberNo , String auth) {

        this.memberNo = memberNo;
        this.auth = auth;
    }
}