package com.example.petto.service;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;
import com.example.petto.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean idDupliChk(String id) {

        List<Member> memberList = memberRepository.findAll();

        for(int i=0; i<memberList.size(); i++) {
            if(memberList.get(i).getId().equals(id)) {
                log.info("ddd");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean nicknameDupliChk(String nickname) {

        List<Member> memberList = memberRepository.findAll();

        for(int i=0; i<memberList.size(); i++) {
            if(memberList.get(i).getNickname().equals(nickname)) {
                return false;
            }
        }
        return true;
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

        Member member = new Member(id, password, email, phoneNumber, name, birthday, petsRaised, nickname);

        memberRepository.save(member);
    }



    @Override
    public boolean checkValidEmail(String email) {

        Optional<Member> member = memberRepository.findByEmail(email); //NonUniqueResultException 이메일 체크 메소드도 만들어야 하나 고민중..

        if(!member.isEmpty()) {
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

        if(!member.isEmpty()) {

            String confidentialCode = makeConfidentialCode().toString();

            String res = new PythonRequest().findIdAndPwByEmail(email, confidentialCode);
            log.info(res);

            return confidentialCode;
        }
        return "unvalid";
    }

    private StringBuffer makeConfidentialCode() {

        Random rnd =new Random();

        StringBuffer buf =new StringBuffer();

        for(int i=0;i<6;i++){

            if(rnd.nextBoolean()){
                buf.append((char)((int)(rnd.nextInt(26))+97));
            }else{
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
    public boolean login(MemberRequest memberRequest) throws Exception {
        Optional<Member> maybeMember = memberRepository.findById(memberRequest.getId());

        if (maybeMember == null)
        {
            log.info("login(): 그런 사람 없다.");
            return false;
        }

        /* Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(memberRequest.getPassword(), loginMember.getPassword()))
        {
            log.info("login(): 비밀번호 잘못 입력하였습니다.");
            return false;
        }
        */
        return true;
    }

    @Override
    public boolean checkIdValidation(String id) throws Exception {
        Optional<Member> maybeMember = memberRepository.findById(id);

        if (maybeMember == null)
        {
            log.info("login(): 회원가입부터 하세요");
            return false;
        }

        return true;

    }

}
