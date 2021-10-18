package com.example.petto.controller.request;

public enum SessionKey {
    SESSION_USER(com.example.petto.controller.request.SessionUser.class);

    private Class<?> clazz;

    public Class<?> getClazz() {
        return clazz;
    }

    SessionKey(Class clazz) {
        this.clazz = clazz;
    }
}
