package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;
import com.example.petto.entity.MemberRelated.LikedAnimal;
import com.example.petto.entity.MemberRelated.UpdateAlarm;
import com.example.petto.repository.AnimalsRepository;
import com.example.petto.repository.LikedAnimalRepository;
import com.example.petto.repository.MemberRepository;
import com.example.petto.repository.memberRelated.UpdateAlarmRepository;
import com.example.petto.utility_python.PythonRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LikedAnimalRepository likedAnimalRepository;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    BCryptPasswordEncoder decoder;

    @Autowired
    UpdateAlarmRepository updateAlarmRepository;

    @Override
    public boolean idDupliChk(String id) {

        if (memberRepository.findById(id).isEmpty()) return true;

        return false;
    }

    @Override
    public boolean nicknameDupliChk(String nickname) {

        if (memberRepository.findByNickname(nickname).isEmpty()) return true;

        return false;
    }

    @Override
    public void signup(MemberRequest memberRequest) {

        String id = memberRequest.getId();
        String password = passwordEncoder.encode(memberRequest.getPassword());
        String email = memberRequest.getEmail();
        String phoneNumber = memberRequest.getPhoneNumber();
        String name = memberRequest.getName();
        String birthday = memberRequest.getBirthday();
        String petsRaised = memberRequest.getPetsRaised();
        String nickname = memberRequest.getNickname();
        String auth = memberRequest.getAuth();

        Member member = new Member(id, password, email, phoneNumber, name, birthday, petsRaised, nickname, auth);

        memberRepository.save(member);
    }

    @Override
    public boolean checkValidEmail(String email, String birthday) {

        Optional<Member> member = memberRepository.findByEmailAndBirthday(email, birthday); //NonUniqueResultException 이메일 체크 메소드도 만들어야 하나 고민중..

        if (!member.isEmpty()) {
            String id = member.get().getId();

            String res = new PythonRequest().findIdAndPwByEmail(email, id);
            log.info(res);

            return true;
        }
        return false;
    }

    @Override
    public String checkValidEmailForPw(String email, String id) {

        Optional<Member> member = memberRepository.findByEmailAndId(email, id);

        if (!member.isEmpty()) {

            String confidentialCode = makeConfidentialCode().toString();

            String res = new PythonRequest().findIdAndPwByEmail(email, confidentialCode);
            log.info(res);

            return confidentialCode;
        }
        return "unvalid";
    }

    private StringBuffer makeConfidentialCode() {

        Random rnd = new Random();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < 6; i++) {

            if (rnd.nextBoolean()) {
                buf.append((char) ((int) (rnd.nextInt(26)) + 97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }
        return buf;
    }

    @Override
    public void changePassword(MemberRequest memberRequest) {

        String id = memberRequest.getId();
        String password = passwordEncoder.encode(memberRequest.getPassword());

        memberRepository.changePassword(id, password);
    }

    @Override
    //@Transactional
    public boolean login(MemberRequest memberRequest) {
        Optional<Member> maybeMember = memberRepository.findById(memberRequest.getId());

        log.info("member: " + maybeMember);

        if (maybeMember.isEmpty()) {
            log.info("login(): 그런 사람 없다.");
            return false;
        }

        Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(memberRequest.getPassword(), loginMember.getPassword())) {
            log.info("login(): 비밀번호 잘못 입력하였습니다.");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkIdValidation(String id) {
        Optional<Member> maybeMember = memberRepository.findById(id);

        if (maybeMember == null) {
            log.info("login(): 회원가입부터 하세요");
            return false;
        }

        return true;

    }

    @Override
    public Member getUserInfo(Integer userNo) {

        Member member = memberRepository.findByMemberNo(new Long(userNo)).get();

        member.removeLikedList(member.getLikedAnimals());
        member.removeAlarmList(member.getUpdateAlarmList());

        return member;
    }

    @Override
    public void modifyUserInfo(Member member) {

        String id = member.getId();
        String email = member.getEmail();
        String phoneNumber = member.getPhoneNumber();
        String name = member.getName();
        String birthday = member.getBirthday();
        String nickname = member.getNickname();

        memberRepository.modifyUserInfo(id, email, phoneNumber, name, birthday, nickname, new Long(member.getMemberNo()));
    }

    @Override
    public void addLikedAnimal(LikedAnimal likedAnimal) {
        animalsRepository.addNumberOfLiked(likedAnimal.getNoticeNo());
        likedAnimalRepository.save(likedAnimal);
    }

    @Override
    public List<LikedAnimal> getlikedAnimalList(Integer memberNo) {

        return likedAnimalRepository.findByMemberNo(new Long(memberNo));
    }

    @Override
    public void deleteLikedAnimal(LikedAnimal likedAnimal) {
        animalsRepository.subNumberOfLiked(likedAnimal.getNoticeNo());
        likedAnimalRepository.delete(likedAnimal.getNoticeNo(), likedAnimal.getMemberNo());
    }

    @Override
    @Transactional
    public void removeUser(Long memberNo) throws Exception {
        Optional<Member> maybeMember = memberRepository.findByMemberNo(memberNo);

        updateAlarmRepository.deleteById(maybeMember.get().getId());
        memberRepository.deleteById(memberNo);

        updateAlarmRepository.deleteById(maybeMember.get().getId());
        memberRepository.deleteById(memberNo);
    }

    @Override
    public List<LikedAnimal> deleteContainingMemberNo(Long memberNo) throws Exception {
        List<LikedAnimal> lists = likedAnimalRepository.findByMemberNo(memberNo);

        for (LikedAnimal list : lists) {
            likedAnimalRepository.deleteById(list.getLikedAnimalNo());
        }
        return null;
    }

    @Override
    public boolean passwordChk(MemberRequest memberRequest) {

        Optional<Member> maybeMember = memberRepository.findById(memberRequest.getId());

        if (maybeMember.isEmpty()) {
            return false;
        }

        Member User = maybeMember.get();

        if (!passwordEncoder.matches(memberRequest.getPassword(), User.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public void updateAlarm(UpdateAlarm updateAlarm) {
        updateAlarmRepository.save(updateAlarm);
    }

    @Override
    public void deleteAlarm(Long alarmNo) {
        updateAlarmRepository.deleteByAlarmNo(alarmNo);
    }

    @Override
    public List<UpdateAlarm> getUpdateAlarmList(String id) {

        Optional<Member> member = memberRepository.findById(id);

        if(member.isPresent()) {
            if(member.get().getAuth().matches("관리자")) return updateAlarmRepository.findById("관리자");
            else ;
        }
        return updateAlarmRepository.findById(id);
    }

    @Override
    public void deleteAllAlarms(String id) {
        updateAlarmRepository.deleteById(id);
    }
}


