package com.example.petto.service;


import com.example.petto.controller.request.SaveUserRequest;

public interface SessionService {
    void save(SaveUserRequest request);
    void logout();
    // Object get(SessionKey key);
}
