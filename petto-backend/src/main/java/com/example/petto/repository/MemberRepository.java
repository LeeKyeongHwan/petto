package com.example.petto.repository;

import com.example.petto.entity.Member;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(String id);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmailAndBirthday(String email, String birthday);

    Optional<Member> findByEmailAndId(String email, String id);

    @Transactional
    @Modifying
    @Query("update Member mem set mem.password = :password where mem.id = :id")
    void changePassword(String id, String password);

    Optional<Member> findByMemberNo(Long userNo);

    @Transactional
    @Modifying
    @Query("update Member mem set mem.id = :id, mem.email = :email, mem.phoneNumber = :phoneNumber, mem.name = :name, " +
            "mem.birthday = :birthday, mem.nickname = :nickname where mem.memberNo = :memberNo")
    void modifyUserInfo(String id, String email, String phoneNumber, String name, String birthday, String nickname, Long memberNo);
}
