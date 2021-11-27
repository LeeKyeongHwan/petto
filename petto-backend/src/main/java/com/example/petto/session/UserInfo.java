package com.example.petto.session;

import com.example.petto.entity.MemberRelated.UpdateAlarm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Data
@NoArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long memberNo;
    private String id;
    private String nickname;
    private String auth;
}