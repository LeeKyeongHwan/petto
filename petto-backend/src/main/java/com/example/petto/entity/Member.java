package com.example.petto.entity;

import com.example.petto.entity.MemberRelated.LikedAnimal;
import com.example.petto.entity.MemberRelated.UpdateAlarm;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="petto_member")
@Transactional
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String birthday;

    @Column(length = 50, nullable = false)
    private String petsRaised;

    @Column(length = 30, nullable = false)
    private String nickname;

    @Column(length = 20, nullable = false)
    private String auth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Date regDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date updDate;

    public Member(String id, String password, String email, String phoneNumber, String name, String birthday,
                  String petsRaised, String nickname, String auth) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.birthday = birthday;
        this.petsRaised = petsRaised;
        this.nickname = nickname;
        this.auth = auth;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "member_no")
    private List<LikedAnimal> likedAnimals = new ArrayList<LikedAnimal>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<UpdateAlarm> updateAlarmList = new ArrayList<UpdateAlarm>();
}
