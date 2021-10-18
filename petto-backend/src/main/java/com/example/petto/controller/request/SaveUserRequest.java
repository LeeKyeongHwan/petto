package com.example.petto.controller.request;

import lombok.Getter;

@Getter
public class SaveUserRequest {

    private String id;

    public SaveUserRequest(String id) {
        this.id = id;
    }

    public SessionUser toSessionUser() {
        return new SessionUser(id);
    }
}