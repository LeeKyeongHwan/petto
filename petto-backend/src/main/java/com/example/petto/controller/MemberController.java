package com.example.petto.controller;

import com.example.petto.controller.request.MemberRequest;
import com.example.petto.entity.Member;
import com.example.petto.entity.MemberRelated.LikedAnimal;
import com.example.petto.entity.MemberRelated.UpdateAlarm;
import com.example.petto.repository.MemberRepository;
import com.example.petto.repository.memberRelated.UpdateAlarmRepository;
import com.example.petto.service.MemberService;
import com.example.petto.session.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/petto/member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    private UserInfo info;

    private HttpSession session;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UpdateAlarmRepository updateAlarmRepository;

    @PostMapping("/idDupliChk/{id}")
    public ResponseEntity<Boolean> idDupliChk(@PathVariable("id") String id) {
        log.info("idDupliChk(): " + id);

        boolean NoDupliId = memberService.idDupliChk(id);

        return new ResponseEntity<Boolean>(NoDupliId, HttpStatus.OK);
    }

    @PostMapping("/nicknameDupliChk/{nickname}")
    public ResponseEntity<Boolean> nicknameDupliChk(@PathVariable("nickname") String nickname) {
        log.info("nicknameDupliChk(): " + nickname);

        boolean NoDupliNickname = memberService.nicknameDupliChk(nickname);

        return new ResponseEntity<Boolean>(NoDupliNickname, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Validated @RequestBody MemberRequest memberRequest) {
        log.info("signup(): " + memberRequest);

        memberService.signup(memberRequest);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/checkValidEmail")
    //@ResponseBody
    public ResponseEntity<String> checkValidEmail(@RequestParam("email") String email, @RequestParam("id") String id,
                                                  @RequestParam("birthday") String birthday) {

        if (!id.equals("")) {
            log.info("checkValidEmail(): " + email + ", " + id);

            String confidentialCode = memberService.checkValidEmailForPw(email, id);

            return new ResponseEntity<String>(confidentialCode, HttpStatus.OK);

        } else {

            log.info("checkValidEmail(): " + email, ", " + birthday);

            boolean isEmailExists = memberService.checkValidEmail(email, birthday);

            String EmailExists = "NotEmailExists";
            if (isEmailExists) EmailExists = "EmailExists";

            return new ResponseEntity<String>(EmailExists, HttpStatus.OK);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@Validated @RequestBody MemberRequest memberRequest) {
        log.info("changePassword(): " + memberRequest);

        memberService.changePassword(memberRequest);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/modifyUserInfo")
    public ResponseEntity<Void> modifyUserInfo(@Validated @RequestBody Member member) {
        log.info("modifyUserInfo(): " + member);

        memberService.modifyUserInfo(member);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<UserInfo> login(@RequestBody MemberRequest memberRequest, HttpServletRequest request) throws Exception {

        log.info("login() - id: " + memberRequest.getId() + ", password: " + memberRequest.getPassword());

        Boolean isSuccess = memberService.login(memberRequest);

        if (isSuccess) {
            log.info("Login Success");

            info = new UserInfo();
            String id = memberRequest.getId();
            info.setId(id);

            Member tmpMember = memberRepository.findById(memberRequest.getId()).get();

            Long memberNo = tmpMember.getMemberNo();
            info.setMemberNo(memberNo);

            String nickName = tmpMember.getNickname();
            info.setNickname(nickName);

            String auth = tmpMember.getAuth();
            info.setAuth(auth);

            log.info("Session Info: " + info);

            session = request.getSession();
            session.setAttribute("member", info);

            List<UpdateAlarm> updateAlarmList = updateAlarmRepository.findById(id);
            info.setUpdateAlarmList(updateAlarmList);

        } else {
            log.info("Login Failure");
            info = null;
        }

        return new ResponseEntity<UserInfo>(info, HttpStatus.OK);
    }

    @PostMapping("/needSession")
    public ResponseEntity<Boolean> postNeedSession(HttpServletRequest request) throws Exception {

        //UserInfo info = (UserInfo) session.getAttribute("member");
        Object obj = session.getAttribute("member");

        Boolean isLogin = false;

        if (obj != null) {
            log.info("Session Info: " + info);

            isLogin = memberService.checkIdValidation(info.getId());

            return new ResponseEntity<Boolean>(isLogin, HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(isLogin, HttpStatus.OK);
    }


    @PostMapping("/removeSession")
    public ResponseEntity<Boolean> removeSession(HttpServletRequest request) throws Exception {
        Boolean mustFalse = false;

        session.invalidate();

        return new ResponseEntity<Boolean>(mustFalse, HttpStatus.OK);
    }

    @PostMapping("/addLikedAnimal")
    public ResponseEntity<Void> addLikedAnimal(@Validated @RequestBody LikedAnimal likedAnimal) {
        log.info("addLikedAnimal(): " + likedAnimal);

        memberService.addLikedAnimal(likedAnimal);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/likedAnimalList/{memberNo}")
    public ResponseEntity<List<LikedAnimal>> getlikedAnimalList(@PathVariable("memberNo") Integer memberNo) {
        log.info("getlikedAnimalList(): " + memberNo);

        List<LikedAnimal> likedAnimalList = memberService.getlikedAnimalList(memberNo);

        return new ResponseEntity<List<LikedAnimal>>(likedAnimalList, HttpStatus.OK);
    }

    @PutMapping("/deleteLikedAnimal")
    public ResponseEntity<Void> deleteLikedAnimal(@Validated @RequestBody LikedAnimal likedAnimal) {
        log.info("deleteLikedAnimal(): " + likedAnimal);

        memberService.deleteLikedAnimal(likedAnimal);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/{memberNo}")
    public ResponseEntity<Void> removeUser(@PathVariable("memberNo") Long memberNo) throws Exception {
        log.info("memberNo == " + memberNo);
        memberService.deleteContainingMemberNo(memberNo);
        memberService.removeUser(memberNo);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/update_alarm")
    public ResponseEntity<Void> updateAlarm(@Validated @RequestBody UpdateAlarm updateAlarm) {
        log.info("updateAlarm: " + updateAlarm);

        memberService.updateAlarm(updateAlarm);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete_alarm/{alarmNo}")
    public ResponseEntity<Void> deleteAlarm(@PathVariable("alarmNo") Long alarmNo) {
        log.info("deleteAlarm: " + alarmNo);

        memberService.deleteAlarms(alarmNo);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //관리자?
//    @GetMapping("/memberlists")
//    public ResponseEntity lists() throws Exception {
//        log.info("Member Lists");


    @PostMapping("/passwordChk")
    public ResponseEntity<Boolean> passwordChk(@Validated @RequestBody MemberRequest memberRequest) throws Exception {
        log.info("passwordChk(): " + memberRequest);

      boolean passwordChk = memberService.passwordChk(memberRequest);

       log.info("passwordChk(): " + passwordChk);

       return new ResponseEntity<Boolean>(passwordChk,HttpStatus.OK);
    }
}




