package com.example.petto.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseSession {
    String id;
    String hashcode;

    public ResponseSession(String id, String hashcode) {
        this.id = id;
        this.hashcode = hashcode;
    }
}

