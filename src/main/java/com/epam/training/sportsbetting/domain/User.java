package com.epam.training.sportsbetting.domain;

public class User {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
