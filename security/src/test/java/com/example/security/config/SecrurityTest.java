package com.example.security.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecrurityTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    // 사용자 비밀번호
    public void testEncoder() {
        String password = "1111";
        // encode() : 원 비밀번호 암호화
        String encodePass = passwordEncoder.encode(password);
        System.out.println("raw password " + password + ", encode password " + encodePass);

        // matches() : 원 비밀번호와 암호화 된 비밀번호의 일치여부
        System.out.println(passwordEncoder.matches(password, encodePass));
        System.out.println(passwordEncoder.matches("2222", encodePass));
    }
}
