package com.example.petto.utility_python;

import com.example.petto.controller.request.MemberRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PythonRequest {

    public String findIdAndPwByEmail(String email, String idOrCode) {

        RestTemplate restTemplate = new RestTemplate();

        if(idOrCode.length() < 7) { //idOrCode의 길이가 7보다 작으면 비밀번호를 찾는것이고 7이상이면 아이디를 찾는 흐름이라 보시면 됩니다.
            //비번 찾기
            Info info = new Info(email, null, idOrCode);
            log.info("info: " + info.getEmail() + ", " + info.getCode());

            String result = restTemplate.postForObject("http://localhost:5000/findIdAndPwByEmail", info, String.class);

            return result;

        } else {
            //id 찾기
            Info info = new Info(email, idOrCode, null);
            log.info("info: " + info.getEmail() + ", " + info.getId());

            String result = restTemplate.postForObject("http://localhost:5000/findIdAndPwByEmail", info, String.class);

            return result;
        }
    }
}

class Info {
    private String email;
    private String id;
    private String code;

    public Info(String email, String id, String code) {
        this.email = email;
        this.id = id;
        this.code = code;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }
}
