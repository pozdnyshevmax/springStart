package com.example.demo.entyty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private  String login;

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    private  String pass;
}
