package com.myblog2.myblog2.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
    public static void main(String[] args) {
        PasswordEncoder encoder= new BCryptPasswordEncoder();
        System.out.println(encoder.encode("testing"));
    }
}
